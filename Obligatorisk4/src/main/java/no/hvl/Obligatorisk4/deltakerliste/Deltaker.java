package no.hvl.Obligatorisk4.deltakerliste;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig4jpa")
public class Deltaker {

	private String fornavn;
	private String etternavn;

	@Id
	private String mobil;
	private String kjonn;
	private String passord;
	private String salt;

	public Deltaker() {
	}

	public Deltaker(String fornavn, String etternavn, String mobil, String kjonn, String passord, String salt) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.mobil = mobil;
		this.kjonn = kjonn;
		this.passord = passord;
		this.salt = salt;

	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return fornavn + " | " + etternavn + " | " + mobil + " | " + kjonn;
	}
}
