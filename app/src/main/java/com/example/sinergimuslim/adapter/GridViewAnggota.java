package com.example.sinergimuslim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sinergimuslim.NameAnggota;
import com.example.sinergimuslim.R;
import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.api.UntilsApi;
import com.example.sinergimuslim.pojo.data_anggota.DataAnggota;

import java.util.ArrayList;
import java.util.List;

public class GridViewAnggota extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable  {

    public List<DataAnggota> dataAnggotas;
    public List<DataAnggota> angotaFilter;
    public Context mCtx;
    //BaseApiService mApiService;
    NameAnggota filter;

    public GridViewAnggota(List<DataAnggota> dataAnggotas, Context mCtx) {
        this.dataAnggotas = dataAnggotas;
        this.angotaFilter = dataAnggotas;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int width = parent.getResources().getDisplayMetrics().widthPixels / 2;
        int height = parent.getResources().getDisplayMetrics().heightPixels / 2;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anggota, parent, false);
        ;
        //   view.setLayoutParams(new RelativeLayout.LayoutParams(width,height));

        return new RowCell(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final DataAnggota dataAnggota = angotaFilter.get(position);
        ((RowCell) holder).nama.setText(dataAnggota.getNamaAnggota());
        ((RowCell) holder).tv_noWa.setText(dataAnggota.getNoWa());
        ((RowCell) holder).tv_daerahAnggota.setText(dataAnggota.getKota());


    }

    @Override
    public int getItemCount() {
        return angotaFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String text = constraint.toString();
                if (text.isEmpty()) {
                    angotaFilter = dataAnggotas;
                } else {
                    ArrayList<DataAnggota> filterable = new ArrayList<>();
                    for (DataAnggota model : dataAnggotas) {
                        System.out.println("check equal " + model.getNamaAnggota() + "   " + text);
                        if (model.getNamaAnggota().toLowerCase().contains(text)) {
                            filterable.add(model);
                        }
                    }
                    angotaFilter = filterable;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = angotaFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                angotaFilter = (ArrayList<DataAnggota>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    private static class RowCell extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nama, tv_noWa, tv_daerahAnggota;
        BaseApiService mApiService;
        private List<DataAnggota> dataAnggotas;
        private Context mCtx;


        public RowCell(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.im_anggota);
            nama = (TextView) itemView.findViewById(R.id.tv_namaAnggota);
            tv_noWa = (TextView) itemView.findViewById(R.id.tv_noWa);
            tv_daerahAnggota = (TextView) itemView.findViewById(R.id.tv_daerahAnggota);
            mApiService = UntilsApi.getApiService();

        }
    }
}


