package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Players.DetailFMCGPlayerActivity;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.DataPerusahaan;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterBusinessModel extends RecyclerView.Adapter<AdapterBusinessModel.HolderData> implements Filterable {
    private List<DataPerusahaan> mList;
    private List<DataPerusahaan> mListFull;
    private Context ctx;
    String User, Person, Id, Level;

    public AdapterBusinessModel(Context ctx, List<DataPerusahaan> mList) {
        this.ctx = ctx;
        this.mList = mList;
        mListFull = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_perusahaan, viewGroup, false);
//        HolderData holder = new HolderData(layout);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int posistion) {
        final DataPerusahaan dm = mList.get(posistion);
        holderData.nama.setText(dm.getNama_perusahaan());
        holderData.alamat.setText(dm.getAlamat_perusahaan());
        String distributor="FMCG";
        if (dm.getDistributor().equals("1")){
            distributor="Distributor";
        }
        holderData.distributor.setText(distributor);
        holderData.bidang.setText(dm.getBidang());
        if (dm.getTbk().equals("1")){
            holderData.list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, DetailFMCGPlayerActivity.class);
                    goInput.putExtra("ID",dm.getId_perusahaan());
                    ctx.startActivity(goInput);
                }
            });
        }else{
            holderData.list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ctx, "Perusahaan Non TBK", Toast.LENGTH_SHORT).show();
                }
            });
        }
        holderData.kota.setText(dm.getKab_kota());
        holderData.provinsi.setText(dm.getProvinsi());
        holderData.produk.setText(dm.getProduk());
        holderData.dm = dm;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView nama,alamat,distributor,bidang,produk,kota,provinsi;
        DataPerusahaan dm;
        LinearLayout list;

        HolderData(View v) {
            super(v);
            nama = v.findViewById(R.id.tvNama);
            list = v.findViewById(R.id.linearList);
            alamat = v.findViewById(R.id.tvAlamat);
            distributor = v.findViewById(R.id.tvDistributor);
            bidang = v.findViewById(R.id.tvBidang);
            produk = v.findViewById(R.id.tvProduk);
            kota = v.findViewById(R.id.tvKota);
            provinsi = v.findViewById(R.id.tvProvinsi);
        }
    }
    public void LoadMore(List<DataPerusahaan> data){
        for (DataPerusahaan dp : data){
            mList.add(dp);
        }
    }



    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataPerusahaan> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mListFull);
            } else {
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (DataPerusahaan dm : mListFull) {
                    if (dm.getNama_perusahaan().toLowerCase().contains(fillterPattern)) {
                        filteredList.add(dm);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
