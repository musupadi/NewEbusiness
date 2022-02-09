package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterBerita;
import com.ascendant.e_businessprofile.Adapter.AdapterFullBerita;
import com.ascendant.e_businessprofile.Adapter.AdapterOutlook;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBeritaActivity extends AppCompatActivity {
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    ImageView Left,Right;
    TextView Paging;
    DB_Helper dbHelper;
    String Token;

    LinearLayout Available,Navigator;
    RecyclerView rv2,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    String Kategori;
    Ascendant ascendant = new Ascendant();
    TextView Nama;
    String Names;
    String Page = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        rv = findViewById(R.id.recycler);
        Left = findViewById(R.id.ivLeft);
        Right = findViewById(R.id.ivRight);
        Paging = findViewById(R.id.tvPaging);

        Intent data = getIntent();
        Kategori = data.getStringExtra("KATEGORI");
        //Cut Here
        rv2 = findViewById(R.id.recyclerNav);
        Nama = findViewById(R.id.tvNama);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningOutlookModel.getListData());
        rv2.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv2.setAdapter(adapters);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        Available.setVisibility(View.GONE);
                        Navigator.setVisibility(View.VISIBLE);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        Available.setVisibility(View.VISIBLE);
                        Navigator.setVisibility(View.GONE);
                    }
                }catch (Exception e){

                }

            }
        });

        //Cut Here
        if (Kategori.equals("all")){
            Names = "All";
        }else if (Kategori.equals("HEALTHCARE")){
            Names = "Healthcare";
        }else if(Kategori.equals("FMCG")){
            Names = "FMCG";
        }else if(Kategori.equals("MINING")){
            Names = "Mining";
        }else if(Kategori.equals("CONTRACTOR")){
            Names = "Contractor";
        }else if(Kategori.equals("OIL AND GAS")){
            Names = "Oil & Gas";
        }else if(Kategori.equals("FARMING")){
            Names = "Farming";
        }
        Nama.setText("News "+Names+" Industry");
        Logic();

        Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page=String.valueOf(Integer.parseInt(Page)+1);
                Logic();
                Paging.setText(Page);
            }
        });
        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Page=String.valueOf(Integer.parseInt(Page)-1);
                Logic();
                Paging.setText(Page);
            }
        });
    }
    private void Logic(){
        mManager = new LinearLayoutManager(DetailBeritaActivity.this, LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.Berita(Token,Kategori,Page);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterFullBerita(DetailBeritaActivity.this,mItems);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        if (Integer.parseInt(Page)>Integer.parseInt(response.body().getMax_page())-1){
                            Right.setVisibility(View.INVISIBLE);
                        }else{
                            Right.setVisibility(View.VISIBLE);
                        }
                        if (Integer.parseInt(Page)==1){
                            Left.setVisibility(View.INVISIBLE);
                        }else{
                            Left.setVisibility(View.VISIBLE);
                        }
                    }else{
                        Toast.makeText(DetailBeritaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(DetailBeritaActivity.this, "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(DetailBeritaActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}