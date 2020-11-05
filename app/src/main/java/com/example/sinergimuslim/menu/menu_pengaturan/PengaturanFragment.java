package com.example.sinergimuslim.menu.menu_pengaturan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sinergimuslim.Config;
import com.example.sinergimuslim.LoginActivity;
import com.example.sinergimuslim.R;
import com.example.sinergimuslim.TantanganAplikasiActivity;
import com.example.sinergimuslim.menu.menu_data_relasi.DataRelasiFragment;

public class PengaturanFragment extends Fragment {

    Button logout1, logout2;
    Config config;
    TextView namePengaturan, pekerjaanPengaturan, lokasiPengaturan, tv_tentangAplikasi;
    String nameUperLower;
    ImageView img_tentangAplikasi;
    public static PengaturanFragment newInstance(){
        Bundle args = new Bundle();
        PengaturanFragment fragment = new PengaturanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.pengaturan_fragment, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout1 = view.findViewById(R.id.logout_pengaturan1);
        logout2 = view.findViewById(R.id.logout_pengaturan2);
        img_tentangAplikasi = view.findViewById(R.id.img_tentangAplikasi);
        tv_tentangAplikasi = view.findViewById(R.id.tv_tentangAplikasi);

        namePengaturan = view.findViewById(R.id.tv_namePengaturan);
        pekerjaanPengaturan = view.findViewById(R.id.tv_pekerjaanPengaturan);
        lokasiPengaturan = view.findViewById(R.id.tv_lokasiPengaturan);

        config = new Config(getContext());
        String nama_anggota = config.getNamaAnggota();
        String pekerjaan = config.getPekerjaan();
        String lokasi = config.getLokasi();

        img_tentangAplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TantanganAplikasiActivity.class);
                startActivity(intent);
            }
        });

        tv_tentangAplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TantanganAplikasiActivity.class);
                startActivity(intent);
            }
        });
try {
    nameUperLower = nama_anggota.substring(0, 1).toUpperCase() + nama_anggota.substring(1).toLowerCase();
} catch (Exception e) {
    e.printStackTrace();
}


        namePengaturan.setText(nameUperLower);
        pekerjaanPengaturan.setText(pekerjaan);
        lokasiPengaturan.setText(" " + lokasi);

        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

