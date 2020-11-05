package com.example.sinergimuslim.pojo.validasi_tambah_anggota;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ValidasiTambahAnggota {

	@SerializedName("password")
	private List<String> password;

	@SerializedName("kota")
	private List<String> kota;

	@SerializedName("nama_anggota")
	private List<String> namaAnggota;

	@SerializedName("no_wa")
	private List<String> noWa;

	@SerializedName("username")
	private List<String> username;

	public void setPassword(List<String> password){
		this.password = password;
	}

	public List<String> getPassword(){
		return password;
	}

	public void setKota(List<String> kota){
		this.kota = kota;
	}

	public List<String> getKota(){
		return kota;
	}

	public void setNamaAnggota(List<String> namaAnggota){
		this.namaAnggota = namaAnggota;
	}

	public List<String> getNamaAnggota(){
		return namaAnggota;
	}

	public void setNoWa(List<String> noWa){
		this.noWa = noWa;
	}

	public List<String> getNoWa(){
		return noWa;
	}

	public void setUsername(List<String> username){
		this.username = username;
	}

	public List<String> getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"ValidasiTambahAnggota{" +
			"password = '" + password + '\'' + 
			",kota = '" + kota + '\'' + 
			",nama_anggota = '" + namaAnggota + '\'' + 
			",no_wa = '" + noWa + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}