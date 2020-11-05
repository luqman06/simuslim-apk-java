package com.example.sinergimuslim.sharedprefence;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigShared {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "sharedPreference";

    //data save
    public static final String USERNAME = "username";
    public static final String PASS = "pass";
    public static final String NAMAANGGOTA = "nama_anggota";
    public static final String NOWA = "no_wa";
    public static final String KOTA = "kota";

    public static final String PADDING = "padding";

    public ConfigShared(Context _context){
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    //username
    public void setUsername(String username){
        editor.putString(USERNAME, username);
        editor.commit();
    }

    public String getUsername(){
        return pref.getString(USERNAME, "");

    }

    //pass

    public void setPass(String pass){
        editor.putString(PASS,pass);
        editor.commit();
    }
    public String getPass(){
        return pref.getString(PASS, " ");
    }

    //nama anggota

    public void setNamaanggota(String namaanggota){
        editor.putString(NAMAANGGOTA,namaanggota);
        editor.commit();
    }
    public String getNamaanggota(){
        return pref.getString(NAMAANGGOTA, " ");
    }

    //no wa

    public void setNowa(String nowa){
        editor.putString(NOWA,nowa);
        editor.commit();
    }
    public String getNowa(){
        return pref.getString(NOWA, " ");
    }

    //kota

    public void setKota(String kota){
        editor.putString(KOTA,kota);
        editor.commit();
    }
    public String getKota(){
        return pref.getString(KOTA, " ");
    }


    //kota

    public void setPadding(String padding){
        editor.putString(PADDING,padding);
        editor.commit();
    }
    public String getPadding(){
        return pref.getString(PADDING, "");
    }



}
