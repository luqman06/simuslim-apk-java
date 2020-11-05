package com.example.sinergimuslim.menu.menu_data_anggota;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sinergimuslim.R;
import com.example.sinergimuslim.adapter.GridViewAnggota;
import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.api.UntilsApi;
import com.example.sinergimuslim.pojo.data_anggota.DataAnggota;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAnggotaFragment extends Fragment  {

    GridView gridView;
    Context context;
    Integer row;
    private ImageView img_search;
    private EditText ed_serch;
    private SearchView searchView;
    RecyclerView recyclerView;
    List<DataAnggota> model;
    BaseApiService mApiService;
    Handler handler = new Handler();
    GridViewAnggota anggota;
    private List<DataAnggota> dataAnggotaList;
    GridViewAnggota adapter;
    private ComponentName mComponent;
    private GridViewAnggota recyclerViewAdapter;
    GridViewAnggota gridViewAnggota;
   private SwipeRefreshLayout swipeRefreshLayout;




    public static DataAnggotaFragment newInstance() {
        Bundle args = new Bundle();
        DataAnggotaFragment fragment = new DataAnggotaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.data_anggota_fragment, container, false);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                onGetData();
//            }
//        }, 100);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mApiService = UntilsApi.getApiService();
        swipeRefreshLayout = view.findViewById(R.id.dataAnggtaSwipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onGetData("");
                swipeRefreshLayout.setRefreshing(false);
//                swipeRefreshLayout.setEnabled(false);
            }
        });

        onGetData("");
       // SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_anggota);
        searchView = view.findViewById(R.id.search);
       // img_search = (ImageView) view.findViewById(R.id.img_serchAnggota);
       // ed_serch = (EditText) view.findViewById(R.id.ed_searchAnggota);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (gridViewAnggota != null) {
                    gridViewAnggota.getFilter().filter(newText);
                }
                return true;
            }
        });


        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;


        if (screenWidth <= 480) {
            row = 1;
            // new ConfigShared(context).setPadding("10");

        } else {
            row = 2;
            // new ConfigShared(context).setPadding("5");

        }



//        searchView.setQueryHint("Cari anggota...");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                GridViewAnggota anggta = new GridViewAnggota(model, getContext());
//                anggta.getFilter().filter(query);
//             //   onGetData(query);
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                GridViewAnggota anggta = new GridViewAnggota(model, getContext());
//                anggta.getFilter().filter(newText);
//            //    onGetData(newText);
//                return false;
//            }
//        });
//        img_search.setOnClimnbj kbnkllckListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String serch = ed_serch.getText().toString();
//         //       anggota.getFilter().filter(serch);
//            }
//        });

//        gridView = (GridView) view.findViewById(R.id.grid_view);
//        gridView.setAdapter(new GridAdapter(getContext()));

    }


    private void onGetData(String key) {

/////////////////////////////////////////////////////
        Call<List<DataAnggota>> call = mApiService.getDataAnggota();
        call.enqueue(new Callback<List<DataAnggota>>() {
            @Override
            public void onResponse(Call<List<DataAnggota>> call, Response<List<DataAnggota>> response) {
//                Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();

                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), row);
                recyclerView.setLayoutManager(layoutManager);

                gridViewAnggota = new GridViewAnggota(response.body(), context);
                recyclerView.setAdapter(gridViewAnggota);

                dataAnggotaList = response.body();
                Log.i(DataAnggotaFragment.class.getSimpleName(), response.body().toString());
                model = new ArrayList<>();
                model.add(new DataAnggota());
                System.out.println("get the position " + model.get(0));
              //  adapter = new GridViewAnggota(dataAnggotaList, DataAnggotaFragment.this, listener);
               // recyclerView.setAdapter(ad);
              //  adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DataAnggota>> call, Throwable t) {
                Toast.makeText(getContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
//////////////////////////////////////////////////
    }
}

