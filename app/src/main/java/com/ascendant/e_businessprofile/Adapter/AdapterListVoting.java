package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.NewsActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.VotingActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.EcosystemFragment;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListVoting extends RecyclerView.Adapter<AdapterListVoting.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Dialog dialog;
    PDFView Img;
    LinearLayout lVote,lClose;
    DB_Helper dbHelper;
    String Token,Level;
    RecyclerView rv;

    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    public AdapterListVoting(Context ctx, List<DataModel> mList,RecyclerView rv){
        this.ctx = ctx;
        this.mList = mList;
        this.rv = rv;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_voting,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.dialog_gambar);
        ascendant = new Ascendant();
        Img = dialog.findViewById(R.id.pdf);
        lVote = dialog.findViewById(R.id.linearVote);
        lClose = dialog.findViewById(R.id.linearTutup);
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
                Level = cursor.getString(1);
            }
        }
        Glide.with(ctx)
                .load(dm.getFile_preview())
                .into(holderData.Gambar);
        holderData.JumlahVote.setText(dm.getTotal_vote());
        holderData.Nama.setText(dm.getNama_design());
        if (dm.getPilihan()==1){
            holderData.Check.setVisibility(View.VISIBLE);
        }else{
            holderData.Check.setVisibility(View.GONE);
        }
        holderData.Preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                lClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.hide();
                    }
                });
                lVote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Level.equals("trial")){
                            Toast.makeText(ctx, "Trial Cannot Vote", Toast.LENGTH_SHORT).show();
                        }else{
                            Voting(dm.getId_design_vote());
                        }
                    }
                });
                new RetreivePDFStream().execute(ascendant.BASE_URL()+dm.getFile_preview_pdf());
            }
        });


        holderData.Vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Level.equals("trial")){
                    Toast.makeText(ctx, "Trial Cannot Vote", Toast.LENGTH_SHORT).show();
                }else{
                    Voting(dm.getId_design_vote());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        ImageView Gambar,Check;
        LinearLayout Vote,Preview;
        TextView JumlahVote,Nama;
        LinearLayout card;
        public HolderData(View v) {
            super(v);
            Gambar = v.findViewById(R.id.ivGambar);
            Vote = v.findViewById(R.id.linearVote);
            Preview = v.findViewById(R.id.linearPreview);
            JumlahVote = v.findViewById(R.id.tvTotalVote);
            Nama = v.findViewById(R.id.tvNama);
            card = v.findViewById(R.id.card);
            Check = v.findViewById(R.id.ivCheck);
        }
    }
    private void Logic(){
        mManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.DaftarDesign(Token);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterListVoting(ctx,mItems,rv);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(ctx, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ctx, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ctx, LoginActivity.class);
                    ctx.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Voting(String ids){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.VoteDesign(Token,ids);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    Toast.makeText(ctx, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Logic();
                    dialog.hide();
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {

            }
        });
    }
    class RetreivePDFStream extends AsyncTask<String,Void, InputStream> {
        InputStream inputStream;
        @Override
        protected InputStream doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            Img.fromStream(inputStream).load();
        }
    }
}
