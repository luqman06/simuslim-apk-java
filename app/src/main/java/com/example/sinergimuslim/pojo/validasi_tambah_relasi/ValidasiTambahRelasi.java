package com.example.sinergimuslim.pojo.validasi_tambah_relasi;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ValidasiTambahRelasi{

	@SerializedName("potensi")
	private List<String> potensi;

	@SerializedName("keterangan")
	private List<String> keterangan;

	@SerializedName("kota")
	private List<String> kota;

	@SerializedName("nama_lengkap")
	private List<String> namaLengkap;

	@SerializedName("no_wa")
	private List<String> noWa;

	@SerializedName("tantangan")
	private List<String> tantangan;

	@SerializedName("nama_bidang")
	private List<String> namaBidang;

	@SerializedName("nama_perusahaan")
	private List<String> namaPerusahaan;

	@SerializedName("id_anggota")
	private List<String> idAnggota;

	@SerializedName("email")
	private List<String> email;

	public void setPotensi(List<String> potensi){
		this.potensi = potensi;
	}

	public List<String> getPotensi(){
		return potensi;
	}

	public void setKeterangan(List<String> keterangan){
		this.keterangan = keterangan;
	}

	public List<String> getKeterangan(){
		return keterangan;
	}

	public void setKota(List<String> kota){
		this.kota = kota;
	}

	public List<String> getKota(){
		return kota;
	}

	public void setNamaLengkap(List<String> namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public List<String> getNamaLengkap(){
		return namaLengkap;
	}

	public void setNoWa(List<String> noWa){
		this.noWa = noWa;
	}

	public List<String> getNoWa(){
		return noWa;
	}

	public void setTantangan(List<String> tantangan){
		this.tantangan = tantangan;
	}

	public List<String> getTantangan(){
		return tantangan;
	}

	public void setNamaBidang(List<String> namaBidang){
		this.namaBidang = namaBidang;
	}

	public List<String> getNamaBidang(){
		return namaBidang;
	}

	public void setNamaPerusahaan(List<String> namaPerusahaan){
		this.namaPerusahaan = namaPerusahaan;
	}

	public List<String> getNamaPerusahaan(){
		return namaPerusahaan;
	}

	public void setIdAnggota(List<String> idAnggota){
		this.idAnggota = idAnggota;
	}

	public List<String> getIdAnggota(){
		return idAnggota;
	}

	public void setEmail(List<String> email){
		this.email = email;
	}

	public List<String> getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return
			"ValidasiTambahAnggota{" +
			"potensi = '" + potensi + '\'' +
			",keterangan = '" + keterangan + '\'' +
			",kota = '" + kota + '\'' +
			",nama_lengkap = '" + namaLengkap + '\'' +
			",no_wa = '" + noWa + '\'' +
			",tantangan = '" + tantangan + '\'' +
			",nama_bidang = '" + namaBidang + '\'' +
			",nama_perusahaan = '" + namaPerusahaan + '\'' +
			",id_anggota = '" + idAnggota + '\'' +
			",email = '" + email + '\'' +
			"}";
		}
}