package com.example.sinergimuslim.pojo.data_relasi;

import com.google.gson.annotations.SerializedName;

public class PojoRelasi{

	@SerializedName("potensi")
	private String potensi;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("id_relasi")
	private String idRelasi;

	@SerializedName("kota")
	private String kota;

	@SerializedName("foto")
	private String foto;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("no_wa")
	private String noWa;

	@SerializedName("tantangan")
	private String tantangan;

	@SerializedName("nama_anggota")
	private String namaAnggota;

	@SerializedName("email")
	private String email;

	public void setPotensi(String potensi){
		this.potensi = potensi;
	}

	public String getPotensi(){
		return potensi;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setIdRelasi(String idRelasi){
		this.idRelasi = idRelasi;
	}

	public String getIdRelasi(){
		return idRelasi;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setNoWa(String noWa){
		this.noWa = noWa;
	}

	public String getNoWa(){
		return noWa;
	}

	public void setTantangan(String tantangan){
		this.tantangan = tantangan;
	}

	public String getTantangan(){
		return tantangan;
	}

	public void setNamaAnggota(String namaAnggota){
		this.namaAnggota = namaAnggota;
	}

	public String getNamaAnggota(){
		return namaAnggota;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"PojoRelasi{" + 
			"potensi = '" + potensi + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",id_relasi = '" + idRelasi + '\'' +
			",kota = '" + kota + '\'' + 
			",foto = '" + foto + '\'' +
			",nama_lengkap = '" + namaLengkap + '\'' + 
			",no_wa = '" + noWa + '\'' + 
			",tantangan = '" + tantangan + '\'' + 
			",nama_anggota = '" + namaAnggota + '\'' +
			",email = '" + email + '\'' + 
			"}";
		}
}