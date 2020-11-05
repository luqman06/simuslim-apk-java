package com.example.sinergimuslim;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "sharedPreference";


    //data save
    public static final String USERNAME = "username";
    public static final String ID = "id";
    public static final String PEKERJAAN = "pekerjaan";
    public static final String NO_WA = "no_wa";
    public static final String LOKASI = "lokasi";
    public static final String NAMA_ANGGOTA = "nama_anggota";
    public static final String ROLE = "role";


    public Config(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //role
    public void setRole(String role) {
        editor.putString(ROLE, role);
        editor.commit();
    }

    public String getRole() {
        return pref.getString(ROLE, "");

    }
    //username
    public void setUsername(String username) {
        editor.putString(USERNAME, username);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(USERNAME, "");

    }

    //id
    public void setId(String id) {
        editor.putString(ID, id);
        editor.commit();
    }

    public String getId() {
        return pref.getString(ID, "");

    }

    //pekerjaan
    public void setPekerjaan(String pekerjaan) {
        editor.putString(PEKERJAAN, pekerjaan);
        editor.commit();
    }

    public String getPekerjaan() {
        return pref.getString(PEKERJAAN, "");

    }


    //no_wa
    public void setNoWa(String no_wa) {
        editor.putString(NO_WA, no_wa);
        editor.commit();
    }

    public String getNoWa() {
        return pref.getString(NO_WA, "");

    }

    //lokasi
    public void setLokasi(String lokasi){
        editor.putString(LOKASI, lokasi);
        editor.commit();
    }

    public String getLokasi(){
        return pref.getString(LOKASI, "");

    }
//nama anggota
public void setNamaAnggota(String namaAnggota){
    editor.putString(NAMA_ANGGOTA, namaAnggota);
    editor.commit();
}

    public String getNamaAnggota(){
        return pref.getString(NAMA_ANGGOTA, "");

    }


    public static final String SHARED_PREF_USERNAME = "username";

    public static final String SHARED_PREF_PASSWORD = "password";


}
