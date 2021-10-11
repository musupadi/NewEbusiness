package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdaptereBook2 extends RecyclerView.Adapter<AdaptereBook2.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    public AdaptereBook2(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_book,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptereBook2.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        final Ascendant method = new Ascendant();
        holderData.icon.setText(String.valueOf(posistion+1));
        holderData.judul.setText(dm.getNama_ebook());
        holderData.judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getLink_file_ebook()));
                ctx.startActivity(browserIntent);
            }
        });
        holderData.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method.Download(ctx,dm.getExt_file(),dm.getLink_file_ebook(),dm.getNama_ebook());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView judul;
        ImageView download;
        TextView icon;
        HolderData(View v){
            super(v);
            judul = v.findViewById(R.id.tvJudul);
            download = v.findViewById(R.id.ivDownloadBook);
            icon = v.findViewById(R.id.ivIcon);
        }
    }
}

