package com.example.sinergimuslim;

import android.widget.Filter;
import android.widget.Filterable;

import com.example.sinergimuslim.adapter.GridViewAnggota;
import com.example.sinergimuslim.api.BaseApiService;
import com.example.sinergimuslim.pojo.data_anggota.DataAnggota;

import java.util.ArrayList;
import java.util.List;

public class NameAnggota extends Filter {

    ArrayList<DataAnggota> filterku;
    GridViewAnggota adapterGrid;

    public NameAnggota(ArrayList<DataAnggota> filterku, GridViewAnggota adapterGrid) {
        this.filterku = filterku;
        this.adapterGrid = adapterGrid;
    }

    public NameAnggota(GridViewAnggota adapterGrid, ArrayList<DataAnggota> filterku) {
        this.adapterGrid = adapterGrid;
        this.filterku = filterku;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results= new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toLowerCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<DataAnggota> filteredNama=new ArrayList<>();

            for (int i= 0;i<filterku.size();i++)
            {
                //CHECK
                if(filterku.get(i).getNamaAnggota().toLowerCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredNama.add(filterku.get(i));
                }
            }

            results.count=filteredNama.size();
            results.values=filteredNama;

        }else
        {
            results.count=filterku.size();
            results.values=filterku;
        }

        return results;
    }


    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
//        adapterGrid.dataAnggotas = (List<DataAnggota>) results.values;

        //REFRESH
        adapterGrid.notifyDataSetChanged();

    }
}
