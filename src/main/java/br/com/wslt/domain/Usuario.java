package br.com.wslt.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int usrId;
	
	@Column(length=25, nullable = false)
	private String usrLastLg;
	
	@Column(length=15, nullable = false)
	private String usrLastSn;
	
	@Column(length=25, nullable = false)
	private String usrtwitLg;
	
	@Column(length=15, nullable = false)
	private String usrtwitSn;
	
	public int getUsrId() {
		return usrId;
	}
	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}
	public String getUsrLastLg() {
		return usrLastLg;
	}
	public void setUsrLastLg(String usrLastLg) {
		this.usrLastLg = usrLastLg;
	}
	public String getUsrLastSn() {
		return usrLastSn;
	}
	public void setUsrLastSn(String usrLastSn) {
		this.usrLastSn = usrLastSn;
	}
	public String getUsrtwitLg() {
		return usrtwitLg;
	}
	public void setUsrtwitLg(String usrtwitLg) {
		this.usrtwitLg = usrtwitLg;
	}
	public String getUsrtwitSn() {
		return usrtwitSn;
	}
	public void setUsrtwitSn(String usrtwitSn) {
		this.usrtwitSn = usrtwitSn;
	}
	
}
