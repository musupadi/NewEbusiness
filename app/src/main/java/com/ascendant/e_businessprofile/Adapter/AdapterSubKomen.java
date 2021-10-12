package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterSubKomen extends RecyclerView.Adapter<AdapterSubKomen.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    public AdapterSubKomen(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_sub_komen,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSubKomen.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        final Ascendant ascendant = new Ascendant();
        holderData.Komen.setText(dm.getIsi_komen());
        holderData.Nama.setText(dm.getNama_user());
        if (dm.getImg_komen().equals("")){
            holderData.Gambar.setVisibility(View.GONE);
        }else{
            Glide.with(ctx)
                    .load(ascendant.BASE_URL()+dm.getImg_komen())
                    .into(holderData.Gambar);
        }
        holderData.cardKomen.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama,Komen,Jam;
        ImageView Reply,Gambar;
        RecyclerView recyclerView;
        CardView cardKomen;
        HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Komen = v.findViewById(R.id.tvKomen);
            Jam = v.findViewById(R.id.tvJam);
            Reply = v.findViewById(R.id.ivReply);
            Gambar = v.findViewById(R.id.ivGambar);
            cardKomen = v.findViewById(R.id.cardKomen);
        }
    }
}

