package criptoLogin.com.login.entity;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.Encoded;

@Entity
@Table
public class CryptoInfo {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String iv;
	
	
	private String chave;


	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}
	

	public String getKey() {
		return chave;
	}

	public void setKey(String chave) {
		this.chave = chave;
	}
	
	
}
