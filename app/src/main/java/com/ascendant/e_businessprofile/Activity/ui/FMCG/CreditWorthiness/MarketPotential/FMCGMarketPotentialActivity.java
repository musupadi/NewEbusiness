package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.MarketPotential;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.OldRetroServer;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerProvinsi;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessMarketPotentialModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FMCGMarketPotentialActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    Ascendant method = new Ascendant();
    Spinner Provinsi,Kategori;
    TextView idKota,potensialPasar,penduduk;
    TextView MarketSize,hasil,Median,NamaProvinsi,hasilPotensiPasar;
    private List<DataModel> mItems = new ArrayList<>();
    private SpinnerProvinsi aProvinsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgmarket_potential);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(FMCGCreditWorthinessMarketPotentialModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
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


        Provinsi = findViewById(R.id.spinnerProvinsi);
        idKota = findViewById(R.id.tvidKota);
        MarketSize = findViewById(R.id.tvMarketSize);
        penduduk=findViewById(R.id.tvPenduduk);
        hasil=findViewById(R.id.tvHasil);
        Kategori=findViewById(R.id.spinnerKategori);
        Median=findViewById(R.id.tvMedian);
        potensialPasar=findViewById(R.id.tvPotensialPasar);
        NamaProvinsi=findViewById(R.id.tvNamaProvinsi);
        hasilPotensiPasar=findViewById(R.id.tvHasilPotensialPasar);
        getProvinsi();
        aProvinsi = new SpinnerProvinsi(FMCGMarketPotentialActivity.this,mItems);
        Provinsi.setAdapter(aProvinsi);
        Provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                String clickedItems = clickedItem.getId_provinsi();
                idKota.setText(clickedItems);
                NamaProvinsi.setText(clickedItem.getNama_provinsi());
                Logic(clickedItems,Kategori.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logic(idKota.getText().toString(),Kategori.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getProvinsi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> getProvinsi = api.Provinsi(Token);
        getProvinsi.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                mItems=response.body().getData();
                SpinnerProvinsi adapter = new SpinnerProvinsi(FMCGMarketPotentialActivity.this,mItems);
                Provinsi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(FMCGMarketPotentialActivity.this,"Kelas Tidak Ditemukan",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(final String kabkota, final String tipe){
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> permintaan = api.PotensialMarket(method.AUTH(),"FABAJakartaIndonesia2019kunci",kabkota,method.KategoriFMCG(tipe));
        permintaan.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.isSuccessful()){
                        MarketSize.setText(response.body().getMarket_size());
                        Median.setText(response.body().getTotal());
                        String kat = "FMCG";
                        if (!Kategori.getSelectedItem().toString().equals("SEMUA")){
                            kat = Kategori.getSelectedItem().toString();
                        }
                        penduduk.setText(response.body().getPenduduk()+" Jiwa");
                        potensialPasar.setText("Potensi Pasar");
                        hasilPotensiPasar.setText(response.body().getNilai());
                        if (response.body().getNilai().equals("Tinggi")){
                            hasilPotensiPasar.setTextColor(Color.RED);
                        }else if(response.body().getNilai().equals("Sedang")){
                            hasilPotensiPasar.setTextColor(Color.BLUE);
                        }else{
                            hasilPotensiPasar.setTextColor(Color.GREEN);
                        }
                        hasil.setText("Potensi Pasar \nMean = Rata - rata pendapatan perkapita nasional\nMean ≤ 20% dari Mean = Rendah\n>20% sampai Mean = Sedang\n>Mean = Tinggi");
                    }
                }catch (Exception e){
                    Toast.makeText(FMCGMarketPotentialActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {

            }
        });
    }
}