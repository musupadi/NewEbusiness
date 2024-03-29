package com.ascendant.e_businessprofile.Activity.ui.Contractor.Outlook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Farming.Outlook.FarmingNewsletterActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterOutlook;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsletterContractorActivity extends AppCompatActivity {
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    DB_Helper dbHelper;
    String Token;

    LinearLayout Available,Navigator;
    RecyclerView rv2;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter_contractor);
        try {
            dbHelper = new DB_Helper(this);
            Cursor cursor = dbHelper.checkUser();
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    Token = cursor.getString(0);
                }
            }
            rv = findViewById(R.id.recycler);
            Logic();
            //Cut Here
            rv2 = findViewById(R.id.recyclerNav);
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
        }catch (Exception e){
            Toast.makeText(NewsletterContractorActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NewsletterContractorActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void Logic(){
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.OutlookContractor(Token,"newsletter");
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterOutlook(NewsletterContractorActivity.this,mItems);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(NewsletterContractorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(NewsletterContractorActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewsletterContractorActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(NewsletterContractorActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}