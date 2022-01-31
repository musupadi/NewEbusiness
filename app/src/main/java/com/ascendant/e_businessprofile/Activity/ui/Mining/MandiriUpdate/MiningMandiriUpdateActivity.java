package com.ascendant.e_businessprofile.Activity.ui.Mining.MandiriUpdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.ModuleActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterMandiriUpdate;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiningMandiriUpdateActivity extends AppCompatActivity {
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    DB_Helper dbHelper;
    String Token;

    LinearLayout Available,Navigator;
    RecyclerView rv2,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    String Kategori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mining_mandiri_update);



        Uri datas = this.getIntent().getData();
        if (datas != null && datas.isHierarchical()) {
            String uri = this.getIntent().getDataString();
            Log.i("MyApp", "Deep link clicked " + uri);
            List<String> params = datas.getPathSegments();
            String IDS = params.get(0); //
            Kategori = params.get(1); //
            Intent goInput = new Intent(MiningMandiriUpdateActivity.this, DetailMandiriUpdate.class);
            goInput.putExtra("ID",IDS);
            startActivity(goInput);
        }else{
            Intent intent = getIntent();
            Kategori = intent.getExtras().getString("KATEGORI");
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
                    Backs();
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
        }
    }

    private void Logic(){
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.MandiriUpdate(Token,Kategori,"1");
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterMandiriUpdate(MiningMandiriUpdateActivity.this,mItems);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(MiningMandiriUpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(MiningMandiriUpdateActivity.this, "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(MiningMandiriUpdateActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Backs(){
        Intent intent = new Intent(MiningMandiriUpdateActivity.this, ModuleActivity.class);
        if (Kategori.equals("HEALTHCARE")){
            intent.putExtra("MODULE", "HEALTHCARE");
            startActivity(intent);
        }else if (Kategori.equals("FMCG")){
            intent.putExtra("MODULE", "FMCG");
            startActivity(intent);
        }else if (Kategori.equals("MINING")){
            intent.putExtra("MODULE", "Mining");
            startActivity(intent);
        }else if (Kategori.equals("CONTRACTOR")){
            intent.putExtra("MODULE", "Contractor");
            startActivity(intent);
        }else if (Kategori.equals("OIL AND GAS")){
            intent.putExtra("MODULE", "Oil & Gas");
            startActivity(intent);
        }else if (Kategori.equals("FARMING")){
            intent.putExtra("MODULE", "Farming");
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        Backs();
    }
}