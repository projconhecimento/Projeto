package criptoLogin.com.login.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.sound.sampled.AudioFormat.Encoding;

import com.sun.jersey.core.util.Base64;

import criptoLogin.com.login.entity.CryptoInfo;
import criptoLogin.com.login.entity.Palavra;
import criptoLogin.com.login.entity.Sinonimo;
import criptoLogin.com.login.entity.Usuario;
import criptoLogin.com.login.persistence.PersistenceManager;

public class CryptoUtil {

	
	public static byte[] encrypt(String password)
	{
		try 
		{
			Cipher ci = getCryptoKey(true);
			byte[] encoded = ci.doFinal(password.getBytes());
			return encoded;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decrypt(byte[] password)
	{
		try 
		{
			Cipher ci = getCryptoKey(false);
			String passw =  new String(ci.doFinal(password));
			return passw;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void deCryptoAll()
	{
		List<Usuario> usuarios = PersistenceManager.getObjects(Usuario.class);
		for(Usuario usuario : usuarios)
		{
			usuario.setEmail(decrypt(usuario.getEmail().getBytes()));
			usuario.setLogin(decrypt(usuario.getLogin().getBytes()));
			usuario.setName(decrypt(usuario.getName().getBytes()));
			PersistenceManager.Persist(usuario);
		}
	}
	public static void cryptoAll()
	{
		List<Usuario> usuarios = PersistenceManager.getObjects(Usuario.class);
		for(Usuario usuario : usuarios)
		{
			usuario.setEmail(new String(encrypt(usuario.getEmail())));
			usuario.setLogin(new String(encrypt(usuario.getLogin())));
			usuario.setName(new String(encrypt(usuario.getName())));
			PersistenceManager.Persist(usuario);
		}
	}
	public static void generateKey() throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		if(PersistenceManager.getObjects(CryptoInfo.class).isEmpty())
		{
			CryptoInfo crypto = new CryptoInfo();
			
			SecureRandom random = new SecureRandom();
			byte[] iv = random.generateSeed(16);
			IvParameterSpec ivSpecParam = new IvParameterSpec(iv);
			
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecretKey sKey = kgen.generateKey();
			crypto.setIv(new String(iv));
			crypto.setKey(new String(sKey.getEncoded()));
			PersistenceManager.Persist(crypto);
		}
	}
	private static Cipher getCryptoKey(boolean encrypt) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException 
	{
		CryptoInfo crypto = (CryptoInfo) PersistenceManager.getObjects(CryptoInfo.class).get(0);
		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivSpecParam = new IvParameterSpec(crypto.getIv().getBytes());
		SecretKeySpec sKey = new SecretKeySpec(crypto.getKey().getBytes(), "AES");
		
		if(encrypt)
		{
			ci.init(Cipher.ENCRYPT_MODE, sKey, ivSpecParam);
		}
		else
		{
			ci.init(Cipher.DECRYPT_MODE, sKey, ivSpecParam);
		}
		return ci;
	}
	
	public static void carregaSinonimos()
	{
		if(PersistenceManager.getObjects(Sinonimo.class).isEmpty())
		{
			String[] palavras = {"Importante", "Dificuldade", "Grande", "Porém", "Apresentar", "Necessidade"};
			String[] sinonimos = {"Significativo", "Notável", "Apuro", "Complicação", "Amplo", "Vasto", "Mas", "Todavia", "Mostrar", "Exibir", "Carência", "Escassez"};
			int count = 0;
			for(int i =0; i < palavras.length; i++)
			{
				Sinonimo sinonimo = new Sinonimo();
				sinonimo.setPalavraChave(palavras[i]);
				List<Palavra> palavraSinonimo = new ArrayList<Palavra>();
				for(int m=0; m<2; m++)
				{
					Palavra palavra = new Palavra();
					palavra.setPalavra(sinonimos[count]);
					PersistenceManager.Persist(palavra);

					palavraSinonimo.add(palavra);
					count++;
				}
				sinonimo.getPalavras().addAll(palavraSinonimo);
				PersistenceManager.Persist(sinonimo);
			}
		}
	}
}
