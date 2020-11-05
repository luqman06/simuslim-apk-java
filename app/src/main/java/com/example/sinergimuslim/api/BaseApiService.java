package com.example.sinergimuslim.api;

import com.example.sinergimuslim.pojo.data_anggota.DataAnggota;

import com.example.sinergimuslim.pojo.data_relasi.PojoRelasi;
import com.example.sinergimuslim.pojo.data_relasi_activity.PojoRelasiActivity;
import com.example.sinergimuslim.pojo.login_si.LoginSi;
import com.example.sinergimuslim.pojo.validasi_tambah_anggota.ValidasiTambahAnggota;
import com.example.sinergimuslim.pojo.validasi_tambah_relasi.ValidasiTambahRelasi;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("login")
    public Call<LoginSi> loginRequest(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<ValidasiTambahAnggota> tambahAnggota(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama_anggota") String nama_anggota,
            @Field("no_wa") String no_wa,
            @Field("kota") String kota
    );

    @FormUrlEncoded
    @POST("relasi/create")
    Call<ValidasiTambahRelasi> tambahRelasi(
            @Field("id_anggota") Integer id_anggota,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("no_wa") Integer no_wa,
            @Field("email") String email,
            @Field("kota") String kota,
            @Field("potensi") String potensi,
            @Field("tantangan") String tantangan,
            @Field("keterangan") String keterangan,
            @Field("nama_bidang[]") String nama_bidang,
            @Field("nama_perusahaan[]") String nama_perusahaan

    );

    @GET("anggota")
    Call<List<DataAnggota>> getDataAnggota();

    @GET("relasi")
    Call<List<PojoRelasi>> getDataRelasiAdmin();

    @Headers("Content-Type: application/json")
    @GET("relasi/per-id-anggota/{id_anggota}")
    Call<List<PojoRelasi>> getDataRelasi(
            @Path("id_anggota") String id_anggota
    );

    @GET("relasi/{id_relasi}")
    Call<PojoRelasiActivity> getDataRelasiActivity(
            @Path("id_relasi") String id_relasi


    );

    @DELETE("relasi/destroy/{id_relasi}")
    Call<JsonObject> deleteRelasi(@Path("id_relasi") String idRelasi);

    }

