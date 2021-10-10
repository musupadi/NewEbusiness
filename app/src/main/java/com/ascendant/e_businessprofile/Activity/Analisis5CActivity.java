package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.OldRetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Adapter5C;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.EbookModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Analisis5CActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pListt = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    private RecyclerView rvEbook;
    private ArrayList<EbookModel> pList = new ArrayList<>();
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    ProgressBar pd;
    Ascendant method = new Ascendant();
    TextView header;
    String kat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisis5_cactivity);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pListt.addAll(CreditWorthinessModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pListt);
        rv.setAdapter(adapters);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
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

        header = findViewById(R.id.tvHeader);
        rvEbook = findViewById(R.id.recycler);
        pd = findViewById(R.id.pdLoading);
        Intent data = getIntent();
        final String KATEGORI = data.getStringExtra("KATEGORI");
        final String PERNYATAAN = data.getStringExtra("PERNYATAAN");
        final String TIPE = data.getStringExtra("TIPE");
        if (KATEGORI.equals("hospital")){
            header.setText("Healthcare");
            pd.setVisibility(View.VISIBLE);
            ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
            Call<ResponseArrayObject> eBook = api.fivec(method.AUTH(),"FABAJakartaIndonesia2019kunci","",PERNYATAAN,KATEGORI);
            rvEbook.setLayoutManager(new LinearLayoutManager(Analisis5CActivity.this));
            eBook.enqueue(new Callback<ResponseArrayObject>() {
                @Override
                public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                    DB_Helper db_helper = new DB_Helper(Analisis5CActivity.this);
                    db_helper.resetFiveC("0",Analisis5CActivity.this);
                    mItems =response.body().getData();
                    Adapter5C adapter = new Adapter5C(Analisis5CActivity.this,mItems,mItems.size());
                    rvEbook.setItemViewCacheSize(100);
                    rvEbook.setDrawingCacheEnabled(true);
                    rvEbook.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                    rvEbook.setAdapter(adapter);
                    pd.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                    Toast.makeText(Analisis5CActivity.this, "Failed To Connect Server", Toast.LENGTH_SHORT).show();
                    pd.setVisibility(View.GONE);
                }
            });
        }else{
            if (KATEGORI.equals("food")){
                header.setText("Food & Beverage");
                kat = "food";
            }else if(KATEGORI.equals("non food")){
                header.setText("Non Food & Beverage");
                kat = "non food";
            }else{
                header.setText("Tobacco");
                kat = "tobacco";
            }
            pd.setVisibility(View.VISIBLE);
            ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
            Call<ResponseArrayObject> eBook = api.fivec(method.AUTH(),"FABAJakartaIndonesia2019kunci",kat,PERNYATAAN,TIPE);
            rvEbook.setLayoutManager(new LinearLayoutManager(Analisis5CActivity.this));
            eBook.enqueue(new Callback<ResponseArrayObject>() {
                @Override
                public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                    DB_Helper db_helper = new DB_Helper(Analisis5CActivity.this);
                    db_helper.resetFiveC("0",Analisis5CActivity.this);
                    mItems =response.body().getData();
                    Adapter5C adapter = new Adapter5C(Analisis5CActivity.this,mItems,mItems.size());
                    rvEbook.setItemViewCacheSize(100);
                    rvEbook.setDrawingCacheEnabled(true);
                    rvEbook.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                    rvEbook.setAdapter(adapter);
                    pd.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                    Toast.makeText(Analisis5CActivity.this, "Failed To Connect Server", Toast.LENGTH_SHORT).show();
                    pd.setVisibility(View.GONE);
                }
            });

        }
    }
}