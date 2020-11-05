package com.example.sinergimuslim.pojo.data_relasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class 	Post {

	@SerializedName("id_relasi")
	@Expose
	private Integer idRelasi;
	@SerializedName("nama_lengkap")
	@Expose
	private String namaLengkap;
	@SerializedName("no_wa")
	@Expose
	private String noWa;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("kota")
	@Expose
	private String kota;
	@SerializedName("potensi")
	@Expose
	private String potensi;
	@SerializedName("tantangan")
	@Expose
	private String tantangan;
	@SerializedName("keterangan")
	@Expose
	private String keterangan;
	@SerializedName("foto")
	@Expose
	private String foto;
	@SerializedName("nama_anggota")
	@Expose
	private String namaAnggota;

	public Post() {
	}

	public Integer getIdRelasi() {
		return idRelasi;
	}

	public void setIdRelasi(Integer idRelasi) {
		this.idRelasi = idRelasi;
	}

	public String getNamaLengkap() {
		return namaLengkap;
	}

	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}

	public String getNoWa() {
		return noWa;
	}

	public void setNoWa(String noWa) {
		this.noWa = noWa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getPotensi() {
		return potensi;
	}

	public void setPotensi(String potensi) {
		this.potensi = potensi;
	}

	public String getTantangan() {
		return tantangan;
	}

	public void setTantangan(String tantangan) {
		this.tantangan = tantangan;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNamaAnggota() {
		return namaAnggota;
	}

	public void setNamaAnggota(String namaAnggota) {
		this.namaAnggota = namaAnggota;
	}

}
