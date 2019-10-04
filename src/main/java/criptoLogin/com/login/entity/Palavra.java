package criptoLogin.com.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Palavra {


	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String palavra;

	public String getPalavra()
	{
		return palavra;
	}

	public void setPalavra(String palavra)
	{
		this.palavra = palavra;
	}
	
	
}
