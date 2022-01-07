package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalRequirementRatio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerKota;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerProvinsi;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.HospitalRequirementRatioModel;
import com.ascendant.e_businessprofile.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalRequirementRatioActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    EditText JumlahTempatTidur,Penduduk;
    Spinner Provinsi,Kota;
    Button hitung;
    private RecyclerView.Adapter mAdapter;
    private List<DataModel> mItems = new ArrayList<>();
    private List<DataModel> pListt = new ArrayList<>();
    private RecyclerView.LayoutManager mManager;
    TextView idKota,idProvinsi,jumlah;
    TextView Rasio,tempattidur;
    ImageView back,home;
    CardView cardHasil;
    Ascendant ascendant = new Ascendant();
    DB_Helper dbHelper;
    String Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_requirement_ratio);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);


        Available.setVisibility(View.VISIBLE);
        pList.addAll(HospitalRequirementRatioModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);

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

        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        JumlahTempatTidur = findViewById(R.id.etInputJumlahTempatTidur);
        Penduduk = findViewById(R.id.etInputPenduduk);
        Provinsi = findViewById(R.id.spinnerProvinsi);
        Kota = findViewById(R.id.spinnerKota);
        jumlah = findViewById(R.id.tvJumlah);
//        hitung = findViewById(R.id.btnHitung);
        idKota = findViewById(R.id.tvidKota);
        idProvinsi = findViewById(R.id.tvIdProvinsi);
        Rasio = findViewById(R.id.tvRasioKebutuhan);
        tempattidur = findViewById(R.id.jumlahTempatTidurDibutuhkan);
        back = findViewById(R.id.ivBack);
        home = findViewById(R.id.ivHome);
        cardHasil = findViewById(R.id.cardHasil);
        Rasio.setVisibility(View.INVISIBLE);
        tempattidur.setVisibility(View.INVISIBLE);
        GetProvinsi();
        Provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_provinsi());
                    idProvinsi.setText(String.valueOf(clickedItems));
                    GetKota();
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    idKota.setText(clickedItem.getId_kab_kota());
                    Penduduk.setText(clickedItem.getTotal_penduduk());
                    JumlahTempatTidur.setText(clickedItem.getJumlah_bed_rs());
                    Logic();
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void Logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseObject> data =api.ListHospital(Token,
                idProvinsi.getText().toString(),
                idKota.getText().toString(),
                "",
                "");
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.isSuccessful()){
                    JumlahTempatTidur.setText(response.body().getData().getTotal_bed());
                    Hitung();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
    private void Hitung(){
        cardHasil.setVisibility(View.VISIBLE);
        Rasio.setVisibility(View.VISIBLE);
        tempattidur.setVisibility(View.VISIBLE);
        String jumlahTempatTidur = JumlahTempatTidur.getText().toString().replace(".","");
        String jumlahPenduduk = Penduduk.getText().toString().replace(".","");

        double jPenduduk = Double.parseDouble(jumlahPenduduk.replace(",",""));
        double jTT = Double.parseDouble(jumlahTempatTidur.replace(",",""));
        double rasio = ((jTT / jPenduduk) * 1000);
        DecimalFormat df2 = new DecimalFormat("#.##");

        Rasio.setText(": "+String.valueOf(df2.format(rasio)));
        double hasil = jTT - (jPenduduk / 1000);
        if (hasil < 0.0 ){
            tempattidur.setTextColor(Color.RED);
        }else{
            tempattidur.setTextColor(Color.BLACK);
        }
        if (rasio < 1.0){
            jumlah.setText("Kekurangan");
            jumlah.setTextColor(Color.RED);
            tempattidur.setTextColor(Color.RED);
            tempattidur.setText(": "+hasil);
        }else{
            jumlah.setText("Kelebihan");
            jumlah.setTextColor(Color.GREEN);
            tempattidur.setTextColor(Color.GREEN);
            tempattidur.setText(": "+hasil);
        }

        tempattidur.setText(": "+String.valueOf(df2.format(hasil)));
    }
    private void GetProvinsi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Provinsi(Token);
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.body().getKode().equals(200)){
                    mItems=response.body().getData();
                    SpinnerProvinsi adapter = new SpinnerProvinsi(HospitalRequirementRatioActivity.this,mItems);
                    Provinsi.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(HospitalRequirementRatioActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetKota(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Kota(Token,idProvinsi.getText().toString());
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.body().getKode().equals(200)){
                    mItems=response.body().getData();
                    SpinnerKota adapter = new SpinnerKota(HospitalRequirementRatioActivity.this,mItems);
                    Kota.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(HospitalRequirementRatioActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}