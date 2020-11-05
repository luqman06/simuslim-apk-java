package com.example.sinergimuslim.menu.menu_data_relasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sinergimuslim.Config;
import com.example.sinergimuslim.LoginActivity;
import com.example.sinergimuslim.R;
import com.example.sinergimuslim.adapter.ReclerViewAdapterGv;
import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.api.UntilsApi;

import com.example.sinergimuslim.pojo.data_relasi.PojoRelasi;
import com.example.sinergimuslim.pojo.data_relasi.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRelasiFragment extends Fragment {

    Integer row;
    Button logout;
    BaseApiService mApiService;
    RecyclerView recyclerView;
    Context context;
    Config config;
    String idangota;
   private SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    public static DataRelasiFragment newInstance() {
        Bundle args = new Bundle();
        DataRelasiFragment fragment = new DataRelasiFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.data_relasi_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mApiService = UntilsApi.getApiService();
        config = new Config(getActivity());
        idangota = config.getId();
        final String role = config.getRole();




        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;


        if (screenWidth <= 480) {
            row = 2;
            // new ConfigShared(context).setPadding("10");

        } else {
            row = 3;
            // new ConfigShared(context).setPadding("5");

        }
        recyclerView = (RecyclerView) view.findViewById(R.id.main_reclerview);

//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),row);
//        recyclerView.setLayoutManager(layoutManager);
//
//        ReclerViewAdapterGv reclerViewAdapterGv = new ReclearViewAdapterGv();
//        recyclerView.setAdapter(reclerViewAdapterGv);

        logout = view.findViewById(R.id.logout_relasi);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
//        Toast.makeText(getContext(), role , Toast.LENGTH_SHORT).show();
        if (role.equals("Anggota")) {
            onGetRelsi();
            swipeRefreshLayout = view.findViewById(R.id.dataRelasiSwipe);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onGetRelsi();
                    swipeRefreshLayout.setRefreshing(false);
//                swipeRefreshLayout.setEnabled(false);
                }
            });
        }
        if (role.equals("admin")){
            onGetRelasiAdmin();
            swipeRefreshLayout = view.findViewById(R.id.dataRelasiSwipe);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onGetRelasiAdmin();
                    swipeRefreshLayout.setRefreshing(false);
//                swipeRefreshLayout.setEnabled(false);
                }
            });
        }


    }

    private void onGetRelasiAdmin() {
        Call<List<PojoRelasi>> call = mApiService.getDataRelasiAdmin();
        call.enqueue(new Callback<List<PojoRelasi>>() {
            @Override
            public void onResponse(Call<List<PojoRelasi>> call, Response<List<PojoRelasi>> response) {
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), row);
                recyclerView.setLayoutManager(layoutManager);

                ReclerViewAdapterGv reclerViewAdapterGv = new ReclerViewAdapterGv(response.body(), getActivity().getApplicationContext());
                recyclerView.setAdapter(reclerViewAdapterGv);
            }
            @Override
            public void onFailure(Call<List<PojoRelasi>> call, Throwable t) {
                Toast.makeText(getContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onGetRelsi() {
        Call<List<PojoRelasi>> call
                = mApiService
                .getDataRelasi(idangota);
        final PojoRelasi finalPojoRelasi = new PojoRelasi();
        call.enqueue(new Callback<List<PojoRelasi>>() {
            @Override
            public void onResponse(Call<List<PojoRelasi>> call, Response<List<PojoRelasi>> response) {
                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());

                        Toast.makeText(context, "Get Nama : " + jsonObject.getString("nama_lengkap"), Toast.LENGTH_SHORT).show();
                    

                        finalPojoRelasi.setNamaLengkap(jsonObject.getString("nama_lengkap"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), row);
                recyclerView.setLayoutManager(layoutManager);

                ReclerViewAdapterGv reclerViewAdapterGv = new ReclerViewAdapterGv(response.body(), getActivity().getApplicationContext());
                recyclerView.setAdapter(reclerViewAdapterGv);
//                Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PojoRelasi>> call, Throwable t) {
                Toast.makeText(getContext(), "gagal" + t, Toast.LENGTH_SHORT).show();
            }

        });


    }
}
