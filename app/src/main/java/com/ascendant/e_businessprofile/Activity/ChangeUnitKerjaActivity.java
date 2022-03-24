package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerArea;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerGroup;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerRegion;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeUnitKerjaActivity extends AppCompatActivity {
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Dialog myDialog;
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    DB_Helper dbHelper;
    String Token;
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;


    Button PilihDivisi;
    //Spinner spDivisi,spWilayah;
    Spinner RegionKantor;
    LinearLayout Region,KantorPusat;
    TextView IdRegion,IdArea,IdDirektorat,IdGroup;
    //Region
    Spinner spRegion;

    Spinner DaftarArea;
    EditText Cabang;
    Spinner Direktorat,Group;
    EditText Departement,UnitTeam;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    EditText Nama,NIP,Telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_unit_kerja);
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningOutlookModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);

        Back.setOnClickListener(new android.view.View.OnClickListener() {
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

        DB_Helper dbHelper = new DB_Helper(ChangeUnitKerjaActivity.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        PilihDivisi = findViewById(R.id.btnPilih);
        RegionKantor = findViewById(R.id.spRegionOrKantor);
        Region = findViewById(R.id.linearRegion);
        KantorPusat = findViewById(R.id.linearKantorPusat);
        IdRegion = findViewById(R.id.tvIdRegion);
        IdArea = findViewById(R.id.tvIdArea);
        IdDirektorat = findViewById(R.id.tvIdDirektorat);
        IdGroup = findViewById(R.id.tvIdGroup);
        //Region
        spRegion = findViewById(R.id.spRegion);
        DaftarArea = findViewById(R.id.spArea);
        Cabang = findViewById(R.id.etCabang);
        //Kantor Pusat
        Direktorat = findViewById(R.id.spDirektorat);
        Group= findViewById(R.id.spGroup);
        Departement = findViewById(R.id.etDepartemen);
        UnitTeam = findViewById(R.id.etUnitTeam);

        RegionKantor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                if (RegionKantor.getSelectedItem().toString().equals("Region")){
                    Region.setVisibility(android.view.View.VISIBLE);
                    KantorPusat.setVisibility(android.view.View.GONE);
                }else if (RegionKantor.getSelectedItem().toString().equals("Kantor Pusat")){
                    Region.setVisibility(android.view.View.GONE);
                    KantorPusat.setVisibility(android.view.View.VISIBLE);
                }else{
                    Region.setVisibility(android.view.View.GONE);
                    KantorPusat.setVisibility(android.view.View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Kantor Pusat
        GetGroup();
        //Region
        GetRegion();
        spRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_region());
                    IdRegion.setText(String.valueOf(clickedItems));
                    GetArea(IdRegion.getText().toString());
                }catch (Exception e){
//                    Toast.makeText(getActivity(), "Failed ?", Toast.LENGTH_SHORT).show();
//                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_group_pusat());
                    IdGroup.setText(String.valueOf(clickedItems));
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        DaftarArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_wilayah_mandiri());
                    IdArea.setText(String.valueOf(clickedItems));
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        PilihDivisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateDivisi();
            }
        });

    }





    private void UpdateDivisi(){
        if (RegionKantor.getSelectedItem().toString().equals("Mohon Pilih Unit Kerja")){
            Toast.makeText(ChangeUnitKerjaActivity.this, "Mohon Pilih Unit Kerja", Toast.LENGTH_SHORT).show();
        }else{
            if (RegionKantor.getSelectedItem().toString().equals("Region")){
                if (Cabang.getText().toString().isEmpty()){
                    Toast.makeText(ChangeUnitKerjaActivity.this, "Mohon Isi Cabang", Toast.LENGTH_SHORT).show();
                }else{
                    ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                    final Call<ResponseObject> data =api.UpdateUserRegion(Token,"region",IdArea.getText().toString(),Cabang.getText().toString());
                    data.enqueue(new Callback<ResponseObject>() {
                        @Override
                        public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                            try {
                                Intent goInput = new Intent(ChangeUnitKerjaActivity.this, HomeActivity.class);
                                goInput.putExtra("FORUM","PROFIL");
                                startActivities(new Intent[]{goInput});
                            }catch (Exception e){
                                Toast.makeText(ChangeUnitKerjaActivity.this, "Terjadi Kesalahan : "+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseObject> call, Throwable t) {
                            Toast.makeText(ChangeUnitKerjaActivity.this,"Koneksi Gagal ",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else{
                if (Departement.getText().toString().isEmpty()){
                    Toast.makeText(this, "Harap isi Departemen", Toast.LENGTH_SHORT).show();
                }else{
                    ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                    final Call<ResponseObject> data =api.UpdateUserKantor(Token,"kantor_pusat",IdGroup.getText().toString(),Departement.getText().toString());
                    data.enqueue(new Callback<ResponseObject>() {
                        @Override
                        public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                            try {
                                Toast.makeText(ChangeUnitKerjaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ChangeUnitKerjaActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }catch (Exception e){
                                Toast.makeText(ChangeUnitKerjaActivity.this, "Terjadi Kesalahan : "+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseObject> call, Throwable t) {
                            Toast.makeText(ChangeUnitKerjaActivity.this,"Koneksi Gagal ",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
    }
    private void GetArea(String id){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Area(Token,id);
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        SpinnerArea adapter = new SpinnerArea(ChangeUnitKerjaActivity.this,mItems);
                        DaftarArea.setAdapter(adapter);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetRegion(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Region(Token);
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        SpinnerRegion adapter = new SpinnerRegion(ChangeUnitKerjaActivity.this,mItems);
                        spRegion.setAdapter(adapter);
                    }
                }catch (Exception e){
                    Log.d("Zyarga Errors : ",e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(ChangeUnitKerjaActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("Zyarga Errors : ",t.toString());
            }
        });
    }
    private void GetGroup(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Group(Token);
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        SpinnerGroup adapter = new SpinnerGroup(ChangeUnitKerjaActivity.this,mItems);
                        Group.setAdapter(adapter);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}