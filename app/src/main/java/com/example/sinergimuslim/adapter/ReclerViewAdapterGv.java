package com.example.sinergimuslim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sinergimuslim.DataRelasiActivity;
import com.example.sinergimuslim.NameAnggota;
import com.example.sinergimuslim.R;
import com.example.sinergimuslim.pojo.data_relasi.PojoRelasi;
import com.example.sinergimuslim.pojo.data_relasi.Post;

import java.util.List;

public class ReclerViewAdapterGv extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

   public List<PojoRelasi> sRelasis;
    Context context;




    public ReclerViewAdapterGv(List<PojoRelasi> sRelasis, Context context) {
        this.sRelasis = sRelasis;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int width = parent.getResources().getDisplayMetrics().widthPixels / 2;
        int height = parent.getResources().getDisplayMetrics().heightPixels / 2;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        ;
        //   view.setLayoutParams(new RelativeLayout.LayoutParams(width,height));
        return new ReclerViewAdapterGv.RowCell(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final PojoRelasi pojoRelasi = sRelasis.get(position);
     

        ((ReclerViewAdapterGv.RowCell) holder).nama.setText(pojoRelasi.getNamaLengkap());
        ((RowCell) holder).tv_potensi.setText(pojoRelasi.getPotensi());
        ((RowCell) holder).relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ((RowCell) holder).ctxView, DataRelasiActivity.class);
                intent.putExtra("id",pojoRelasi.getIdRelasi());
                ((RowCell) holder).ctxView.startActivity(intent);

            }
        });



    }


    @Override
    public int getItemCount() {

        return sRelasis.size();
    }


    private static class RowCell extends RecyclerView.ViewHolder  {
        public ImageView imageView;
        public TextView nama, tv_potensi;
        public RelativeLayout relativeLayout;
        private Context ctxView;






        public RowCell(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.recyclerview_item_image);
            nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_potensi= (TextView) itemView.findViewById(R.id.tv_potensi);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.container);
            ctxView = itemView.getContext();
            final PojoRelasi data = new PojoRelasi();



//            relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(ctxView, DataRelasiActivity.class);
//                    intent.putExtra("id",data.getIdRelasi());
//                    ctxView.startActivity(intent);
//
//
//                }
//            });



        }


        }
    }
