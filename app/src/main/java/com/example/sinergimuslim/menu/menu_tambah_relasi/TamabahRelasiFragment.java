package com.example.sinergimuslim.menu.menu_tambah_relasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sinergimuslim.Config;
import com.example.sinergimuslim.R;
import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.api.UntilsApi;
import com.example.sinergimuslim.menu.menu_pengaturan.PengaturanFragment;
import com.example.sinergimuslim.pojo.validasi_tambah_relasi.ValidasiTambahRelasi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TamabahRelasiFragment extends Fragment {

    private EditText et_namalengkap, et_nomerWhatsapp, et_Email,
            tvbidang1, tvbidang2, tvbidang3, et_kota, et_namaperusahaan1,
            et_namaperusahaan2, et_namaperusahaan3, et_potensi, et_tantangan, et_keterangan;
     ImageView btnBidang1, btnBidang2, btnPerusahaan1, btnPerusahaan2;
    private LinearLayout line1, line2, line3, linep1, linep2, linep3;
    private Button btnTamabahData;
    TamabahRelasiFragment mContext;
    private BaseApiService mApiService;
    private ProgressDialog loadingBar;
    int no_wa;
    Config config;
    String emailPattern;
    String foundPattern;




    public static TamabahRelasiFragment newInstance() {
        Bundle args = new Bundle();
        TamabahRelasiFragment fragment = new TamabahRelasiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tamabah_relasi_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mContext = this;
        mApiService = UntilsApi.getApiService();
        line1 = view.findViewById(R.id.ll1);
        line2 = view.findViewById(R.id.ll2);
        line3 = view.findViewById(R.id.ll3);

        linep1 = view.findViewById(R.id.llp1);
        linep2 = view.findViewById(R.id.llp2);
        linep3 = view.findViewById(R.id.llp3);

        btnBidang1 = view.findViewById(R.id.addBidang1);
        btnBidang2 = view.findViewById(R.id.addBidang2);

        btnPerusahaan1 = view.findViewById(R.id.addPerusahaan1);
        btnPerusahaan2 = view.findViewById(R.id.addPerusahaan2);

        tvbidang1 = view.findViewById(R.id.et_bidang1);
        tvbidang2 = view.findViewById(R.id.et_bidang2);
        tvbidang3 = view.findViewById(R.id.et_bidang3);
        et_namalengkap = view.findViewById(R.id.et_namalengkap);
        et_nomerWhatsapp = view.findViewById(R.id.et_nomerWhatsapp);
        et_Email = view.findViewById(R.id.et_Email);
        et_kota = view.findViewById(R.id.et_kota);
        et_namaperusahaan1 = view.findViewById(R.id.et_perusahaan1);
        et_namaperusahaan2 = view.findViewById(R.id.et_perusahaan2);
        et_namaperusahaan3 = view.findViewById(R.id.et_perusahaan3);
        et_potensi = view.findViewById(R.id.et_potensi);
        et_tantangan = view.findViewById(R.id.et_tantangan);
        et_keterangan = view.findViewById(R.id.et_keterangan);

        btnTamabahData = view.findViewById(R.id.btnTamabahData);

        initComponents();


    }

    private void initComponents() {

        btnBidang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "bias", Toast.LENGTH_SHORT).show();
                if (!tvbidang1.getText().toString().equals("")) {
                    line2.setVisibility(View.VISIBLE);
                    btnBidang1.setVisibility(View.GONE);
                }
            }
        });

        btnBidang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvbidang2.getText().toString().equals("")) {
                    line3.setVisibility(View.VISIBLE);
                    btnBidang2.setVisibility(View.GONE);
                }

            }
        });

        btnPerusahaan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvbidang1.getText().toString().equals("")) {
                    linep2.setVisibility(View.VISIBLE);
                    btnPerusahaan1.setVisibility(View.GONE);
                }
            }
        });

        btnPerusahaan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvbidang2.getText().toString().equals("")) {
                    linep3.setVisibility(View.VISIBLE);
                    btnPerusahaan2.setVisibility(View.GONE);
                }

            }
        });


        btnTamabahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                config = new Config(getContext());
                Integer idangota = Integer.parseInt(config.getId());
//                Toast.makeText(getContext(), idangota + "", Toast.LENGTH_SHORT).show();
                String email = et_Email.getText().toString().trim();
                emailPattern = "[a-zA-Z._-]+@[a-z]+\\.+[a-z]+";
                foundPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                // loading = ProgressDialog.show(mContext, null, "Harap Tunggu", true,false);

//                if (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    Toast.makeText(getActivity(), "valid email address", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getContext(), "hus", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    Toast.makeText(getContext(), "na", Toast.LENGTH_SHORT).show();
//
//                }

//                if (TextUtils.isEmpty(et_namalengkap.getText())) {
//                    et_namalengkap.setError("Harap di isi");
//                } else if (TextUtils.isEmpty(et_nomerWhatsapp.getText())) {
//                    et_nomerWhatsapp.setError("Harap di isi");
//                } else if (TextUtils.isEmpty(et_Email.getText())) {
//                    et_Email.setError("Harap di isi");
//                } else if (tvbidang1.getText().toString().matches(foundPattern)) {
//                    tvbidang1.setError("Harap di isi");
//                } else if (et_kota.getText().toString().matches(foundPattern)) {
//                    et_kota.setError("Harap di isi");
//                } else if (et_namaperusahaan1.getText().toString().matches(foundPattern)) {
//                    et_namaperusahaan1.setError("Harap di isi");
//                } else if (TextUtils.isEmpty(et_potensi.getText())) {
//                    et_potensi.setError("Harap di isi");
//                } else if (et_tantangan.getText().toString().matches(foundPattern)) {
//                    et_tantangan.setError("Harap di isi");
//                } else if (et_keterangan.getText().toString().matches(foundPattern)) {
//                    et_keterangan.setError("Harap di isi");
//                } else {
//
//
//                    requestRelasi();
//                }
//
//                if (TextUtils.isEmpty(et_nomerWhatsapp.getText())){
//                    et_nomerWhatsapp.setError("Harap di isi");
//                }else {
//                    requestRelasi();
//                }
                requestRelasi();



            }

        });
    }

    private void requestRelasi() {
        config = new Config(getContext());
        Integer idangota = Integer.parseInt(config.getId());
       try {
            no_wa = Integer.parseInt(et_nomerWhatsapp.getText().toString());
       } catch (NumberFormatException e) {
           e.printStackTrace();
       }

        mApiService.tambahRelasi(
                idangota,
                et_namalengkap.getText().toString().trim(),
                no_wa,
                et_Email.getText().toString().trim(),
                et_kota.getText().toString().trim(),
                et_potensi.getText().toString().trim(),
                et_tantangan.getText().toString().trim(),
                et_keterangan.getText().toString().trim(),
                tvbidang1.getText().toString().trim(),
                et_namaperusahaan1.getText().toString().trim()
        ).enqueue(new Callback<ValidasiTambahRelasi>() {
            @Override
            public void onResponse(Call<ValidasiTambahRelasi> call, Response<ValidasiTambahRelasi> response) {

                String potensi = null;
                String keterangan = null;
                String kota = null;
                String namaLengkap = null;
                String noWa = null;
                String tantangan = null;
                String namaBidang = null;
                String namaPerushaan = null;
                String email = null;

                try {
                    potensi = response.body().getPotensi().toString();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    keterangan = response.body().getKeterangan().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    kota = response.body().getKota().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    namaLengkap = response.body().getNamaLengkap().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    noWa = response.body().getNoWa().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    tantangan = response.body().getTantangan().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    namaBidang = response.body().getNamaBidang().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    namaBidang = response.body().getNamaBidang().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    email = response.body().getEmail().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    namaPerushaan = response.body().getNamaPerusahaan().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (keterangan !=null || kota !=null || namaLengkap !=null || noWa !=null || tantangan !=null || namaBidang !=null || namaPerushaan !=null || email !=null ){


                if (keterangan != null){
                    et_keterangan.setError("Harap di isi dengan benar");

                }
                if (kota != null){
                    et_kota.setError("Harap di isi dengan benar");

                }
                if (namaLengkap != null){
                    et_namalengkap.setError("Harap di isi dengan benar");

                }
                if (TextUtils.isEmpty(et_nomerWhatsapp.getText())){
                    et_nomerWhatsapp.setError("Harap di isi dengan benar");

                }
                if (tantangan !=null){
                    et_tantangan.setError("Harap di isi dengan benar");

                }
                if (TextUtils.isEmpty(tvbidang1.getText())){
                    tvbidang1.setError("Harap di isi dengan benar");
//                    Toast.makeText(getActivity(), "Harap Di Isi Bidang", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(et_namaperusahaan1.getText())){
                  et_namaperusahaan1.setError("Harap di isi");
//                    Toast.makeText(getActivity(), "Harap Di Isi Perusahaan", Toast.LENGTH_SHORT).show();
//                    et_namaperusahaan1.setHint("Harap di isi");


                }
                if (email !=null){
                    et_Email.setError("Harap di isi dengan benar");

                }
                if (potensi != null){
                    et_potensi.setError("Harap di isi dengan benar");
                }

                    Toast.makeText(getActivity(), "Harap Koreksi Lagi Data Yang di masukan", Toast.LENGTH_SHORT).show();
                }else {


                    et_keterangan.setText("");
                    et_kota.setText("");
                    et_nomerWhatsapp.setText("");
                    et_namalengkap.setText("");
                    et_tantangan.setText("");
                    tvbidang1.setText("");
                    tvbidang2.setText("");
                    line2.setVisibility(View.GONE);
                    tvbidang3.setText("");
                    line3.setVisibility(View.GONE);
                    et_namaperusahaan1.setText("");
                    et_namaperusahaan2.setText("");
                    linep2.setVisibility(View.GONE);
                    et_namaperusahaan3.setText("");
                    linep3.setVisibility(View.GONE);
                    et_potensi.setText("");
                    et_Email.setText("");
                    btnBidang1.setVisibility(View.VISIBLE);
                    btnBidang2.setVisibility(View.VISIBLE);
                    btnPerusahaan1.setVisibility(View.VISIBLE);
                    btnPerusahaan2.setVisibility(View.VISIBLE);
                }
//                Toast.makeText(getActivity(), "Suka", Toast.LENGTH_SHORT).show();





            }

            @Override
            public void onFailure(Call<ValidasiTambahRelasi> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();

            }
        });

    }

}