package br.com.wslt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Historico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hisId;
	
	@ManyToOne
	@JoinColumn(name="usrId", nullable = false)
	private Usuario Usuario;
	
	@Column(length=50, nullable = false)
	private String hisMusica;
	
	@Column(length=50, nullable = false)
	private String hisArtista;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date hisData;
	
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	public Date getHisData() {
		return hisData;
	}
	public void setHisData(Date hisData) {
		this.hisData = hisData;
	}
	public int getHisId() {
		return hisId;
	}
	public void setHisId(int hisId) {
		this.hisId = hisId;
	}

	public String getHisMusica() {
		return hisMusica;
	}
	public void setHisMusica(String hisMusica) {
		this.hisMusica = hisMusica;
	}
	public String getHisArtista() {
		return hisArtista;
	}
	public void setHisArtista(String hisArtista) {
		this.hisArtista = hisArtista;
	}
}
