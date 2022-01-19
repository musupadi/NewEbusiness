package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterPerusahaanFarming extends RecyclerView.Adapter<AdapterPerusahaanFarming.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    public AdapterPerusahaanFarming(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_perusahaan_farmers,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.Nama.setText(dm.getNama_perusahaan());
        holderData.Bidang.setText(dm.getBidang());
        holderData.Kategori.setText(dm.getKategori_perusahaan());
        holderData.Alamat.setText(ascendant.Changer(dm.getAlamat()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout card;
        TextView Nama,Bidang,Kategori,Alamat;
        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            Nama = v.findViewById(R.id.tvNama);
            Bidang = v.findViewById(R.id.tvBidang);
            Alamat = v.findViewById(R.id.tvAlamat);
            Kategori = v.findViewById(R.id.tvKategori);
        }
    }
}
