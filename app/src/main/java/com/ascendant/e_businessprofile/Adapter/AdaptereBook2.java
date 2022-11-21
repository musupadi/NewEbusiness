package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.PortraitWebViewEbookActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaptereBook2 extends RecyclerView.Adapter<AdaptereBook2.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    DB_Helper dbHelper;
    String Token,Level;
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
        DataModel dm = mList.get(posistion);
        final Ascendant method = new Ascendant();
        myDialog = new Dialog(ctx);
        myDialog.setContentView(R.layout.dialog_view_download);
        holderData.icon.setText(String.valueOf(posistion+1));
        holderData.judul.setText(dm.getNama_ebook());
        holderData.tanggal.setText(ascendant.MagicDateChange(dm.getTgl_upload_ebook()));
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
                Level = cursor.getString(1);
            }
        }
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);

                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        if (Level.equals("trial")){
                            Toast.makeText(ctx, "You Cannot Download File", Toast.LENGTH_SHORT).show();
                        }else{
                            method.Download(ctx,dm.getExt_file(),dm.getLink_file_ebook(),dm.getNama_ebook());
                        }
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        try {
                            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                            final Call<ResponseObject> data =api.OpenEbook(Token);
                            data.enqueue(new Callback<ResponseObject>() {
                                @Override
                                public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                                    if (Level.equals("trial")){
                                        Toast.makeText(ctx, "You Cannot Download File", Toast.LENGTH_SHORT).show();
                                    }else{
                                        if (dm.getLink_ebook().equals("") || dm.getLink_ebook().isEmpty()){
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getLink_file_ebook()));
                                            ctx.startActivity(browserIntent);
                                        }else{
                                            if (dm.getMode_ebook().equals("P")){
                                                Intent i = new Intent(ctx, PortraitWebViewEbookActivity.class);
                                                i.putExtra("LINK", dm.getLink_ebook());
                                                ctx.startActivity(i);
                                            }else{
                                                Intent i = new Intent(ctx, LandscapeWebViewEbookActivity.class);
                                                i.putExtra("LINK", dm.getLink_ebook());
                                                ctx.startActivity(i);
                                            }
                                        }
                                    }

                                }

                                @Override
                                public void onFailure(Call<ResponseObject> call, Throwable t) {
                                    Toast.makeText(ctx, "Terjadi Kesalahan Koneksi", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }catch (Exception e){
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getLink_file_ebook()));
                            ctx.startActivity(browserIntent);
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView judul,icon,tanggal;
        CardView card;
        HolderData(View v){
            super(v);
            judul = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.card);
            icon = v.findViewById(R.id.tvId);
            tanggal = v.findViewById(R.id.tvTanggal);
        }
    }
}

