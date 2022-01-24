package com.ascendant.e_businessprofile.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Forum.DetailForumActivity;
import com.ascendant.e_businessprofile.Activity.ui.Forum.EditForumActivity;
import com.ascendant.e_businessprofile.Activity.ui.Forum.InputGambarActivity;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterGambarForum extends RecyclerView.Adapter<AdapterGambarForum.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    DB_Helper dbHelper;
    String Token;
    String ID_POST;
    String ID,CATEGORY,JUDUL;
    public AdapterGambarForum(Context ctx, List<DataModel> mList,String ID_POST,String ID,String CATEGORY,String JUDUL){
        this.ctx = ctx;
        this.mList = mList;
        this.ID_POST = ID_POST;
        this.ID = ID;
        this.CATEGORY = CATEGORY;
        this.JUDUL = JUDUL;
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
        final Ascendant ascendant = new Ascendant();
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkUser();
        holderData.PlusImage.setVisibility(View.GONE);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        if (posistion+1>mList.size()){
            holderData.PlusImage.setVisibility(View.VISIBLE);
            holderData.Gambar.setVisibility(View.GONE);
            holderData.Delete.setVisibility(View.GONE);
            holderData.PlusImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goInput = new Intent(ctx, InputGambarActivity.class);
                    goInput.putExtra("IDS",ID_POST);
                    goInput.putExtra("ID",ID);
                    goInput.putExtra("CATEGORY",CATEGORY);
                    goInput.putExtra("JUDUL",JUDUL);
                    goInput.putExtra("REPLY_NAME","");
                    goInput.putExtra("REPLY","");
                    ctx.startActivities(new Intent[]{goInput});
                }
            });
        }else{
            DataModel dm = mList.get(posistion);
            Glide.with(ctx)
                    .load(ascendant.BASE_URL()+dm.getFile_img_post())
                    .into(holderData.Gambar);
            holderData.Caption.setText(dm.getCaption());
            holderData.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setMessage("Hapus Gambar")
                            .setCancelable(false)
                            .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    try {
                                        HapusGambar(dm.getId_post_img());
                                    }catch (Exception e){
                                        Toast.makeText(ctx, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            //Set your icon here
                            .setTitle("Perhatian !!!")
                            .setIcon(R.drawable.splash_logo);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Caption,PlusImage;
        ImageView Gambar,Delete;
        HolderData(View v){
            super(v);
            Caption = v.findViewById(R.id.tvCaption);
            Gambar = v.findViewById(R.id.ivGambar);
            Delete = v.findViewById(R.id.ivDelete);
            PlusImage = v.findViewById(R.id.tvPlusImage);
        }
    }

    private void HapusGambar(String ID){
        final ProgressDialog pd = new ProgressDialog(ctx);
        pd.setMessage("Sedang Menghapus Gambar");
        pd.show();
        pd.setCancelable(false);

        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.DeleteGambarPost(Token,ID);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent intent = new Intent(ctx, HomeActivity.class);
                    ctx.startActivity(intent);
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(ctx, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

