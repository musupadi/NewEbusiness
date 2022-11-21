package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.PortraitWebViewEbookActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterEBook extends RecyclerView.Adapter<AdapterEBook.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Dialog myDialog;
    Button View,Download;
    int positions=0;
    DB_Helper dbHelper;
    String Token,Level;
    public AdapterEBook(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_ebook,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        myDialog = new Dialog(ctx);
        myDialog.setContentView(R.layout.dialog_view_download);
        holderData.Tanggal.setText(ascendant.MagicDateChange(dm.getTgl_upload_business_refrence()));
        holderData.Nama.setText(ascendant.SmallText(dm.getNama_business_refrence()));
        holderData.ID.setText(String.valueOf(posistion+1));

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
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        if (Level.equals("trial")){
                            Toast.makeText(ctx, "You Cannot Download File", Toast.LENGTH_SHORT).show();
                        }else{
                            ascendant.Download(ctx,"pdf",dm.getLink_file_business_refrence(),dm.getNama_business_refrence());
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
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ascendant.BASE_URL()+dm.getLink_file_business_refrence()));
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
                                    Toast.makeText(ctx, "Gagal", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }catch (Exception e){
                            Log.d("Zyaga Error : ",e.toString());
                            Toast.makeText(ctx, e.toString(), Toast.LENGTH_SHORT).show();
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
        LinearLayout card;
        TextView Tanggal,Nama,ID;

        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            Tanggal = v.findViewById(R.id.tvTanggal);
            Nama = v.findViewById(R.id.tvNama);
            ID = v.findViewById(R.id.tvId);
        }
    }
}
