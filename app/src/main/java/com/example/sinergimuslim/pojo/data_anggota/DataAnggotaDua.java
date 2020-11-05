package com.example.sinergimuslim.pojo.data_anggota;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataAnggotaDua {

	@Expose
	@SerializedName("kota")
	private String kota;


	@SerializedName("nama_anggota")
	private String namaAnggota;

	@SerializedName("no_wa")
	private String noWa;

	@SerializedName("id")
	private String id;

	@SerializedName("id_user")
	private String idUser;

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getNamaAnggota() {
		return namaAnggota;
	}

	public void setNamaAnggota(String namaAnggota) {
		this.namaAnggota = namaAnggota;
	}

	public String getNoWa() {
		return noWa;
	}

	public void setNoWa(String noWa) {
		this.noWa = noWa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
}