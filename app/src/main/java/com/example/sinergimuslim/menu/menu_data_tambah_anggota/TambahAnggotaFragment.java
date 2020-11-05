package com.example.sinergimuslim.menu.menu_data_tambah_anggota;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sinergimuslim.R;
import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.api.UntilsApi;
import com.example.sinergimuslim.menu.menu_pengaturan.PengaturanFragment;
import com.example.sinergimuslim.pojo.validasi_tambah_anggota.ValidasiTambahAnggota;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TambahAnggotaFragment extends Fragment {

   private EditText et_namalengkapTamabahAnggota, et_nmrWhatsAppTambahAnggota, et_kotaTambahAnggota, et_usernameTambahAnggota, et_passwordTambahAnggota;
   private Button btnTamabahDataAnggota;
   private BaseApiService mApiService;

    public static TambahAnggotaFragment newInstance(){
        Bundle args = new Bundle();
        TambahAnggotaFragment fragment = new TambahAnggotaFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tambah_anggota_fragment, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_namalengkapTamabahAnggota = view.findViewById(R.id.et_namalengkapTamabahAnggota);
        et_nmrWhatsAppTambahAnggota = view.findViewById(R.id.et_nmrWhatsAppTambahAnggota);
        et_kotaTambahAnggota = view.findViewById(R.id.et_kotaTambahAnggota);
        et_usernameTambahAnggota = view.findViewById(R.id.et_usernameTambahAnggota);
        et_passwordTambahAnggota = view.findViewById(R.id.et_passwordTambahAnggota);
        btnTamabahDataAnggota = view.findViewById(R.id.btnTamabahDataAnggota);
        mApiService = UntilsApi.getApiService();
        initComponents();
    }

    private void initComponents() {
        btnTamabahDataAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (TextUtils.isEmpty(et_namalengkapTamabahAnggota.getText())) {
//                    et_namalengkapTamabahAnggota.setError("Harap di isi");
//                } else if (TextUtils.isEmpty(et_nmrWhatsAppTambahAnggota.getText())) {
//                    et_nmrWhatsAppTambahAnggota.setError("Harap di isi");
//                } else if (TextUtils.isEmpty(et_kotaTambahAnggota.getText())) {
//                    et_kotaTambahAnggota.setError("Harap di isi");
//                } else if (TextUtils.isEmpty(et_usernameTambahAnggota.getText())) {
//                    et_usernameTambahAnggota.setError("Harap di isi");
//                } else if (TextUtils.isEmpty(et_passwordTambahAnggota.getText())) {
//                    et_passwordTambahAnggota.setError("Harap di isi");
//                } else {
//                    requestRegister();
//                }
                requestRegister();
            }
        });
    }

    private void requestRegister() {
        mApiService.tambahAnggota(

                et_usernameTambahAnggota.getText().toString().trim(),
                et_passwordTambahAnggota.getText().toString().trim(),
                et_namalengkapTamabahAnggota.getText().toString().trim(),
                et_nmrWhatsAppTambahAnggota.getText().toString().trim(),
                et_kotaTambahAnggota.getText().toString().trim()
                ).enqueue(new Callback<ValidasiTambahAnggota>() {
            @Override
            public void onResponse(Call<ValidasiTambahAnggota> call, Response<ValidasiTambahAnggota> response) {

//                Toast.makeText(getContext(), "sukses : " + response.body().toString(), Toast.LENGTH_SHORT).show();
                String usernameAnggota = null;
                String passwordAnggota = null;
                String namaLengkapAnggota = null;
                String no_waAnggota = null;
                String kotaAnggota = null;

                try {
                    usernameAnggota = response.body().getUsername().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    passwordAnggota = response.body().getPassword().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    namaLengkapAnggota = response.body().getNamaAnggota().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    no_waAnggota = response.body().getNoWa().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    kotaAnggota = response.body().getKota().toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (usernameAnggota != null|| passwordAnggota !=null || namaLengkapAnggota !=null || no_waAnggota !=null || kotaAnggota !=null){
                    Toast.makeText(getActivity(), "Harap di isi semua data dengan benar", Toast.LENGTH_SHORT).show();
                    if (usernameAnggota !=null){
                        et_usernameTambahAnggota.setError("Harap di isi dengan Benar");
                    }

                    if (passwordAnggota !=null){
                        et_passwordTambahAnggota.setError("Harap di isi dengan Benar");
                    }

                    if (namaLengkapAnggota !=null){
                        et_namalengkapTamabahAnggota.setError("Harap di isi dengan Benar");
                    }

                    if (no_waAnggota != null){
                        et_nmrWhatsAppTambahAnggota.setError("Harap di isi dengan Benar");
                    }

                    if (kotaAnggota != null){
                        et_kotaTambahAnggota.setError("Harap di isi dengan Benar");
                    }
                }else {
                et_usernameTambahAnggota.setText("");
                et_passwordTambahAnggota.setText("");
                et_namalengkapTamabahAnggota.setText("");
                et_nmrWhatsAppTambahAnggota.setText("");
                et_kotaTambahAnggota.setText("");
                }
            }

            @Override
            public void onFailure(Call<ValidasiTambahAnggota> call, Throwable t) {
                Toast.makeText(getContext(), "Koneksi gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

