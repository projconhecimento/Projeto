package criptoLogin.com.login.entity;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class RankingSenhas {

	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private BigDecimal nota;
	
	private Long caracteres;
	
	private GregorianCalendar data;

	public BigDecimal getNota() {
		return nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

	public Long getCaracteres() {
		return caracteres;
	}

	public void setCaracteres(Long caracteres) {
		this.caracteres = caracteres;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	
}
