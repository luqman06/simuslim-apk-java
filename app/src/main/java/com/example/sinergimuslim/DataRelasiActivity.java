package com.example.sinergimuslim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.api.UntilsApi;
import com.example.sinergimuslim.pojo.data_relasi_activity.PojoRelasiActivity;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRelasiActivity extends AppCompatActivity {
    String idRelasi;
    BaseApiService mApiService;
    TextView namaDetailRelasi,waDetailRelasi,locasiDetailRelasi,emailDetailRelasi,
            bidang1DetailRelasi,bidang2DetailRelasi,bidang3DetailRelasi,perusahaan1DetailRelasi,
            perusahaan2DetailRelasi,perusahaan3DetailRelasi,potensiDetailRelasi, keteranganRelasi, tantanganRelasi;
    Button buttonBack,deleteRelasi;
    String bidang1, bidang2 = null, bidang3 = null, perusahaan1, perusahaan2 = null, perusahaan3 = null;
    String bidangCompelete, perusahaanCompelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_relasi);

        mApiService = UntilsApi.getApiService();

        Intent intent = getIntent();

        idRelasi = intent.getStringExtra("id");
//        Toast.makeText(this, idRelasi, Toast.LENGTH_SHORT).show();

        getData();

        namaDetailRelasi = findViewById(R.id.tv_nameDetailRelasi);
        waDetailRelasi = findViewById(R.id.no_waDeatailRelasi);
        locasiDetailRelasi = findViewById(R.id.tv_locasiDaetailRelasi);
        emailDetailRelasi = findViewById(R.id.tv_emailDetailRelasi);
        bidang1DetailRelasi = findViewById(R.id.tv_bidang1);
//        bidang2DetailRelasi = findViewById(R.id.tv_bidang2);
//        bidang3DetailRelasi = findViewById(R.id.tv_bidang3);
        perusahaan1DetailRelasi = findViewById(R.id.tv_perusahaan1);
//        perusahaan2DetailRelasi = findViewById(R.id.tv_perusahaan2);
//        perusahaan3DetailRelasi = findViewById(R.id.tv_perusahaan3);
        potensiDetailRelasi = findViewById(R.id.tv_potensiDetailRelasi);
        tantanganRelasi = findViewById(R.id.tv_tantanganRelasi);
        keteranganRelasi = findViewById(R.id.tv_keteranganRelasi);
        buttonBack = findViewById(R.id.buttonBack);
        deleteRelasi = findViewById(R.id.deleteRelasi);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataRelasiActivity.super.onBackPressed();
            }
        });

        deleteRelasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletRelasi();
            }
        });

    }

    private void deletRelasi() {

        Call<JsonObject> objectCall = mApiService.deleteRelasi(idRelasi);
        objectCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                onBackPressed();
                Toast.makeText(DataRelasiActivity.this, "Data Relasi Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(DataRelasiActivity.this, "Data Relasi Gagal Di Hapus", Toast.LENGTH_SHORT).show();
            }
        });
        };

    private void getData() {
        Call<PojoRelasiActivity> call = mApiService.getDataRelasiActivity(idRelasi);
        call.enqueue(new Callback<PojoRelasiActivity>() {
            @Override
            public void onResponse(Call<PojoRelasiActivity> call, Response<PojoRelasiActivity> response) {
               namaDetailRelasi.setText(response.body().getNamaLengkap());
               waDetailRelasi.setText(response.body().getNoWa());
               locasiDetailRelasi.setText(response.body().getKota());
               emailDetailRelasi.setText(response.body().getEmail());
               tantanganRelasi.setText(response.body().getTantangan());
               keteranganRelasi.setText(response.body().getKeterangan());



               try {
                   bidang1 = response.body().getNamaBidang().get(0).toString().trim();
                   bidang2 = response.body().getNamaBidang().get(1);
                   bidang3 = response.body().getNamaBidang().get(2);
               } catch (Exception e) {
                   e.printStackTrace();
               }
                try {
                    perusahaan1 = response.body().getNamaPerusahaan().get(0);
                    perusahaan2 = response.body().getNamaPerusahaan().get(1);
                    perusahaan3 = response.body().getNamaPerusahaan().get(2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                bidangCompelete = "Bidang " + bidang1;
               perusahaanCompelete = "Perusahaan " + perusahaan1;
               if (bidang2 != null){
                   bidangCompelete += "\n" + "Bidang " + bidang2;
               }
               if (bidang3 != null){
                   bidangCompelete += "\n" + "Bidang " + bidang3;
               }
               if (perusahaan2 != null){
                   perusahaanCompelete += "\n" + "Perusahaan " + perusahaan2;
               }
               if (perusahaan3 != null){
                   perusahaanCompelete += "\n" + "Perusahaan " + perusahaan3;
               }

               bidang1DetailRelasi.setText(bidangCompelete);
               perusahaan1DetailRelasi.setText(perusahaanCompelete);
              // bidang2DetailRelasi.setText(bidabg[1]);
               // bidang3DetailRelasi.setText(bidabg[2]);

//                List namaBidang = response.body().getNamaBidang();

//                for (int i=0; i<response.body().getNamaBidang().length; i++)
//                {
//                    type var = arr[i];
//                    statements using var;
//                }
               potensiDetailRelasi.setText(response.body().getPotensi());
            }

            @Override
            public void onFailure(Call<PojoRelasiActivity> call, Throwable t) {

            }
        });
    }


}
