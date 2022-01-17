package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterGambarForum extends RecyclerView.Adapter<AdapterGambarForum.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    public AdapterGambarForum(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_gambar,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGambarForum.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        final Ascendant ascendant = new Ascendant();
        Glide.with(ctx)
                .load(ascendant.BASE_URL()+dm.getFile_img_post())
                .into(holderData.Gambar);
        holderData.Caption.setText(dm.getCaption());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Caption;
        ImageView Gambar;
        HolderData(View v){
            super(v);
            Caption = v.findViewById(R.id.tvCaption);
            Gambar = v.findViewById(R.id.ivGambar);
        }
    }
}

