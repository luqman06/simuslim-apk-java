package com.example.sinergimuslim.pojo.login_si;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("role")
	private String role;

	@SerializedName("kota")
	private String kota;

	@SerializedName("nama_anggota")
	private String namaAnggota;

	@SerializedName("no_wa")
	private String noWa;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id_anggota")
	private String idAnggota;

	@SerializedName("username")
	private String username;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setNamaAnggota(String namaAnggota){
		this.namaAnggota = namaAnggota;
	}

	public String getNamaAnggota(){
		return namaAnggota;
	}

	public void setNoWa(String noWa){
		this.noWa = noWa;
	}

	public String getNoWa(){
		return noWa;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setIdAnggota(String idAnggota){
		this.idAnggota = idAnggota;
	}

	public String getIdAnggota(){
		return idAnggota;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"role = '" + role + '\'' + 
			",kota = '" + kota + '\'' + 
			",nama_anggota = '" + namaAnggota + '\'' + 
			",no_wa = '" + noWa + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",id_anggota = '" + idAnggota + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}