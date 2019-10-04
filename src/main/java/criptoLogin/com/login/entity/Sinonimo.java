package criptoLogin.com.login.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Sinonimo {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String palavraChave;
	
	@OneToMany
	private Set<Palavra> palavras = new HashSet<Palavra>();

	public String getPalavraChave()
	{
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave)
	{
		this.palavraChave = palavraChave;
	}

	public Set<Palavra> getPalavras()
	{
		return palavras;
	}

	public void setPalavras(Set<Palavra> palavras)
	{
		this.palavras = palavras;
	}
	
	
}
