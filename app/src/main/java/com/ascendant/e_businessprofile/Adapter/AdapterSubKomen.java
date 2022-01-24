package com.ascendant.e_businessprofile.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Forum.DetailForumActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterSubKomen extends RecyclerView.Adapter<AdapterSubKomen.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    String NamaUser;
    String ID,CATEGORY,JUDUL;
    DB_Helper dbHelper;
    String Token;
    String IDSUB;
    public AdapterSubKomen(Context ctx, List<DataModel> mList,String NamaUser,String ID,String CATEGORY,String JUIDUL){
        this.ctx = ctx;
        this.mList = mList;
        this.NamaUser = NamaUser;
        this.ID = ID;
        this.CATEGORY = CATEGORY;
        this.JUDUL = JUIDUL;
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
        DataModel dm = mList.get(posistion);
        final Ascendant ascendant = new Ascendant();
        holderData.Komen.setText(dm.getIsi_komen());
        holderData.Nama.setText(dm.getNama_user());
        holderData.Jam.setText(dm.getTgl_komen());
        IDSUB=dm.getId_post_komen_sub();
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        if (dm.getNama_user().equals(NamaUser)){
            holderData.Report.setVisibility(View.GONE);
            holderData.Delete.setVisibility(View.VISIBLE);
            holderData.Edit.setVisibility(View.VISIBLE);
        }else{
            holderData.Report.setVisibility(View.VISIBLE);
            holderData.Delete.setVisibility(View.GONE);
            holderData.Edit.setVisibility(View.GONE);
        }
        if (dm.getImg_komen().equals("")){
            holderData.Gambar.setVisibility(View.GONE);
        }else{
            Glide.with(ctx)
                    .load(ascendant.BASE_URL()+dm.getImg_komen())
                    .into(holderData.Gambar);
        }
        holderData.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                // Set a title for alert dialog
                builder.setTitle("Pemberitahuan");

                // Ask the final question
                builder.setMessage("Apakah Anda Yakin Ingin Menghapus Komen ? ");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        DeleteKomen(IDSUB);
                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });
        holderData.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, DetailForumActivity.class);
                goInput.putExtra("ID",ID);
                goInput.putExtra("CATEGORY",CATEGORY);
                goInput.putExtra("JUDUL",JUDUL);
                goInput.putExtra("REPLY_NAME",dm.getNama_user());
                goInput.putExtra("REPLY",dm.getId_post_komen_sub());
                goInput.putExtra("EDIT","YES");
                goInput.putExtra("IDS",dm.getId_post_komen_sub());
                goInput.putExtra("ISI_KOMEN",dm.getIsi_komen());
                goInput.putExtra("SUB_KOMEN","YES");
                ctx.startActivities(new Intent[]{goInput});
            }
        });
        holderData.cardKomen.setVisibility(View.GONE);
    }

    private void DeleteKomen(String IDSUB){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.DeletePosting(Token,IDSUB,"sub_komen");
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                Toast.makeText(ctx, "Komen berhasil Terhapus", Toast.LENGTH_SHORT).show();
                Intent goInput = new Intent(ctx, DetailForumActivity.class);
                goInput.putExtra("ID",ID);
                goInput.putExtra("CATEGORY",CATEGORY);
                goInput.putExtra("JUDUL",JUDUL);
                goInput.putExtra("REPLY_NAME","");
                goInput.putExtra("REPLY","");
                goInput.putExtra("EDIT","NO");
                goInput.putExtra("ISI_KOMEN","");
                ctx.startActivities(new Intent[]{goInput});
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {

            }
        });
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
        LinearLayout Delete,Report,Edit;
        HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Komen = v.findViewById(R.id.tvKomen);
            Jam = v.findViewById(R.id.tvJam);
            Reply = v.findViewById(R.id.ivReply);
            Gambar = v.findViewById(R.id.ivGambar);
            cardKomen = v.findViewById(R.id.cardKomen);
            Delete = v.findViewById(R.id.linearDelete);
            Report = v.findViewById(R.id.linearReport);
            Edit = v.findViewById(R.id.linearEdit);
        }
    }
}

