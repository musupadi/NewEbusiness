package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.NewsActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterHospitalAssociation extends RecyclerView.Adapter<AdapterHospitalAssociation.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    public AdapterHospitalAssociation(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_persi,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.id.setText(dm.getId_persi());
        holderData.nama.setText(dm.getNama_persi());
        holderData.alamat.setText(dm.getAlamat_persi());
        holderData.telepon.setText(dm.getTelpon_persi());
        holderData.email.setText(dm.getEmail_persi());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id,nama,alamat,telepon,email;
        HolderData(View v){
            super(v);
            id = v.findViewById(R.id.tvID);
            nama = v.findViewById(R.id.tvNamaAsosiasi);
            alamat = v.findViewById(R.id.tvAlamat);
            telepon = v.findViewById(R.id.tvTelpon);
            email = v.findViewById(R.id.tvEmail);
        }
    }
}
