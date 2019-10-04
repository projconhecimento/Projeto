package criptoLogin.com.login.rest;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import criptoLogin.com.login.crypto.CryptoUtil;
import criptoLogin.com.login.entity.*;
import criptoLogin.com.login.persistence.PersistenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Path("login")
public class LoginRest
{
	Gson gson = new GsonBuilder().create();
	private String numeros = "0123456789";
	private String caracterEspeciais = "!@#$%&*()_-+= {[}]^~:;?>.<,''";
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("sinonimo") String sinonimo, @FormParam("0label") String input1,@FormParam("1label") String input2,@FormParam("2label") String input3,@FormParam("3label") String input4, @FormParam("usuario") String usuario, @FormParam("senha") String senha, @FormParam("tentativa") Long tentativa) throws UnsupportedEncodingException
	{
		JsonObject objeto = new JsonObject();
		if(input1.equalsIgnoreCase("") && input2.equalsIgnoreCase("") && input3.equalsIgnoreCase("") && input4.equalsIgnoreCase(""))
		{
			objeto.addProperty("success", false);
			objeto.addProperty("mensagem", "Validação errada!");
			return gson.toJson(objeto);
		}
		else
		{
			List<Sinonimo> sinonimos = PersistenceManager.getObjects(Sinonimo.class);
			for(Sinonimo sin : sinonimos)
			{
				if(sin.getPalavraChave().equalsIgnoreCase(sinonimo))
				{
					int count = 0;
					for(Palavra pala : sin.getPalavras())
					{
						if(pala.getPalavra().equalsIgnoreCase(input1))
						{
							count++;
						}
						else if(pala.getPalavra().equalsIgnoreCase(input2))
						{
							count++;
						}
						else if(pala.getPalavra().equalsIgnoreCase(input3))
						{
							count++;
						}
						else if(pala.getPalavra().equalsIgnoreCase(input4))
						{
							count++;
						}
					}
					if(count < 2)
					{
						objeto.addProperty("success", false);
						objeto.addProperty("mensagem", "Validação errada!");
						return gson.toJson(objeto);
					}
				}
			}
		}
		if(usuario.equalsIgnoreCase("chaveMaster") && senha.equalsIgnoreCase("chaveMaster"))
		{
			CryptoUtil.deCryptoAll();
			objeto.addProperty("success", true);
			objeto.addProperty("mensagem", "Dados decriptografados com sucesso!");
			return gson.toJson(objeto);
		}
		List<Usuario> usuarios = PersistenceManager.getObjects(Usuario.class);
		for(Usuario usu : usuarios)
		{
			if(usu.getLogin() == null || usu.getPassword() == null) continue;
			if(usu.getLogin().equals(usuario) && verificaSenha(usu.getPassword(), senha))
			{
				objeto.addProperty("success", true);
				return gson.toJson(objeto);
			}
		}
		if(tentativa == 3L)
		{
			CryptoUtil.cryptoAll();
			objeto.addProperty("mensagem", "Você excedeu o limite de tentativas, todos os dados foram criptografados. Favor contatar a TI para liberar os dados!");
		}
		objeto.addProperty("success", false);
		objeto.addProperty("tentativa", tentativa+1);
		return gson.toJson(objeto);
	}
	private Boolean verificaSenha(String senha, String password) throws UnsupportedEncodingException
	{
		if(CryptoUtil.decrypt(senha.getBytes()).equalsIgnoreCase(password))
		{
			return true;
		}
		return false;
	}
	
	@POST
	@Path("novo")
	@Produces(MediaType.APPLICATION_JSON)
	public String novoLogin(@FormParam("nomeUsuario") String nome, @FormParam("emailUsuario") String email, @FormParam("novoUsuario") String usuario, @FormParam("novaSenha") String senha) throws UnsupportedEncodingException
	{
		Usuario novoUsuario = new Usuario();
		novoUsuario.setLogin(usuario);
		novoUsuario.setName(nome);
		novoUsuario.setPassword(new String(CryptoUtil.encrypt(senha)));
		novoUsuario.setEmail(email);
		PersistenceManager.Persist(novoUsuario);
		JsonObject objeto = new JsonObject();
		objeto.addProperty("mensagem", "Cadastro salvo com sucesso!");
		return gson.toJson(objeto);
	}
	@GET
	@Path("novodado")
	@Produces(MediaType.APPLICATION_JSON)
	public String dadosGrafico(@Context HttpServletRequest request)
	{
		String senha = request.getParameter("senha");
		RankingSenhas ranking = ValidaSenha(senha);
		JsonObject objeto = new JsonObject();
		objeto.addProperty("nota", ranking.getNota().setScale(1, BigDecimal.ROUND_HALF_EVEN));
		objeto.addProperty("quantidade", senha.length());
		return gson.toJson(objeto);
	}
	
	@GET
	@Path("sinonimos")
	@Produces(MediaType.APPLICATION_JSON)
	public String loadSinonimos()
	{
		List<Sinonimo> sinonimos = PersistenceManager.getObjects(Sinonimo.class);
		Random r = new Random();
		int numero = r.nextInt(sinonimos.size()-1);
		Sinonimo sinonimo = sinonimos.get(numero);
		JsonArray array = new JsonArray();
		List<String> palavras = new ArrayList<String>();
		for(Palavra palavra : sinonimo.getPalavras())
		{
			palavras.add(palavra.getPalavra());
		}
		int numeroNovo = r.nextInt(sinonimos.size()-1);
		while(numeroNovo == numero)
		{
			numeroNovo = r.nextInt(sinonimos.size()-1);
		}
		for(Palavra palavra : sinonimos.get(numeroNovo).getPalavras())
		{
			palavras.add(palavra.getPalavra());
		}
		Collections.shuffle(palavras);
		for(String palavra : palavras)
		{
			JsonObject objeto = new JsonObject();
			objeto.addProperty("palavra", palavra);
			array.add(objeto);
		}
		JsonObject retorno = new JsonObject();
		retorno.add("sinonimos", array);
		retorno.addProperty("palavraChave", sinonimo.getPalavraChave());
		return gson.toJson(retorno);
		
	}
	@GET
	@Path("loaddados")
	@Produces(MediaType.APPLICATION_JSON)
	public String loadDados()
	{
		List<RankingSenhas> ranking =  PersistenceManager.getObjects(RankingSenhas.class);
		JsonArray listaRetorno = new JsonArray();
		JsonObject retorno = new JsonObject();
		for(RankingSenhas senha : ranking)
		{
			JsonObject objeto = new JsonObject();
			objeto.addProperty("nota", senha.getNota().setScale(2, BigDecimal.ROUND_HALF_EVEN));
			objeto.addProperty("quantidade", senha.getCaracteres());
			listaRetorno.add(objeto);
		}
		retorno.add("ranking", listaRetorno);
		return gson.toJson(retorno);
	}
	private RankingSenhas ValidaSenha(String senha) {
		
		BigDecimal nota = BigDecimal.ZERO;
		
		//Inicio das Validações
		if(senha.length() >= 12) nota = nota.add(new BigDecimal(2L));
		boolean numero = false;
		boolean especias = false;
		boolean maiusculo = false;
		boolean tipoIgual1caracter = false;
		boolean tipoIgual2caracter = false;
		boolean repete = false;
		String lastValue = "";
		int count = 0;
		for(int i =0; i<senha.length();i++)
		{
			String valorAtual= "";
			String caracter = senha.substring(i, i+1);
			if(numeros.contains(caracter))
			{
				valorAtual = "numero";
				numero = true;
			}
			else if(caracterEspeciais.contains(caracter))
			{
				valorAtual = "especial";
				especias = true;
			}
			else
			{
				valorAtual = "letra";
			}
			if(caracter.equals(caracter.toUpperCase())) maiusculo = true;
			if(valorAtual.equalsIgnoreCase(lastValue))
			{
				tipoIgual1caracter = true;
				if(count <= 2)
				{
					tipoIgual2caracter = true;
				}
				count = 0;
			}
			else
			{
				count++;
			}
			if(senha.split(caracter).length > 2)
			{
				repete = true;
			}
		}
		
		//Somando as notas
		if(numero) nota = nota.add(new BigDecimal("0.75"));
		if(maiusculo) nota = nota.add(new BigDecimal("0.75"));
		if(especias) nota = nota.add(new BigDecimal("0.75"));
		if(numero && maiusculo && especias) nota.add(BigDecimal.ONE);
		if(!tipoIgual1caracter) nota = nota.add(new BigDecimal("0.75"));
		if(!tipoIgual2caracter) nota = nota.add(new BigDecimal("1.75"));
		if(!repete) nota = nota.add(new BigDecimal("2.25"));
		RankingSenhas ranking = new RankingSenhas();
		ranking.setCaracteres(Long.valueOf(senha.length()));
		ranking.setData(new GregorianCalendar());
		ranking.setNota(nota);
		PersistenceManager.Persist(ranking);
		return ranking;
	}
}
