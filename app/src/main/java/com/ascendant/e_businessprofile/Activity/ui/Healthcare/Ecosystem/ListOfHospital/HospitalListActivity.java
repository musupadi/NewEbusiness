package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterListHospital;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerKota;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerProvinsi;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.Ecosystem.ListOfHospitalModel;
import com.ascendant.e_businessprofile.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalListActivity extends AppCompatActivity implements OnMapReadyCallback {
    LinearLayout Available,Navigator,AvailableList,AvailableMap;
    Fragment fragment;
    Dialog dialog;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    RecyclerView rv;
    private ArrayList<DataModel> pList = new ArrayList<>();
    private RecyclerView.LayoutManager mManager;
    private RecyclerView.Adapter mAdapter;
    Spinner Provinsi,Kota,Kelas;
    TextView IdProvinsi,IdKota;
    EditText Cari;
    private java.util.List<DataModel> mItems = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;
    LinearLayout LList,LMap;
    TextView List,Map;
    String Choice = "List";
    Bundle bundle;
    String KELAS = "";
    RecyclerView recycler;
    TextView Hospital,Bed;

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Declaration();
        Available.setVisibility(View.VISIBLE);
        pList.addAll(ListOfHospitalModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);
        OnClick();
    }
    private String Kelass(){
        KELAS = Kelas.getSelectedItem().toString();
        if (Kelas.getSelectedItem().toString().equals("Semua Kelas")){
            KELAS = "";
        }
        return  KELAS;
    }
    private void Logic(){
        mManager = new LinearLayoutManager(HospitalListActivity.this, LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseObject> data =api.ListHospital(Token,
                IdProvinsi.getText().toString(),
                IdKota.getText().toString(),
                Kelass(),
                Cari.getText().toString());
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData().getDaftar_rs();
                        Bed.setText(response.body().getData().getTotal_bed());
                        Hospital.setText(response.body().getData().getTotal_rs());
                        mAdapter = new AdapterListHospital(HospitalListActivity.this,mItems);
                        recycler.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                        mMap.clear();

                        int w = 0;
                        while (w < mItems.size()) {
                            final DataModel dm = mItems.get(w);
                            if (dm.getKelas_rs().equals("A")){
                                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.kelasa);
                                Bitmap b=bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(dm.getLatitude()),Double.parseDouble(dm.getLongitude())))
                                        .anchor(0.5f,0.5f)
                                        .title(dm.getNama_rs())
                                        .snippet(dm.getKode_rs())
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                            }else if(dm.getKelas_rs().equals("B")){
                                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.kelasb);
                                Bitmap b=bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(dm.getLatitude()),Double.parseDouble(dm.getLongitude())))
                                        .anchor(0.5f,0.5f)
                                        .title(dm.getNama_rs())
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                            }else if(dm.getKelas_rs().equals("C")){
                                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.kelasc);
                                Bitmap b=bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(dm.getLatitude()),Double.parseDouble(dm.getLongitude())))
                                        .anchor(0.5f,0.5f)
                                        .title(dm.getNama_rs())
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                            }else if(dm.getKelas_rs().equals("D")){
                                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.kelasd);
                                Bitmap b=bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(dm.getLatitude()),Double.parseDouble(dm.getLongitude())))
                                        .anchor(0.5f,0.5f)
                                        .title(dm.getNama_rs())
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                            }else if(dm.getKelas_rs().equals("D PRATAMA")){
                                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.kelasd);
                                Bitmap b=bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(dm.getLatitude()),Double.parseDouble(dm.getLongitude())))
                                        .anchor(0.5f,0.5f)
                                        .title(dm.getNama_rs())
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                            }else if(dm.getKelas_rs().equals("BELUM DITETAPKAN")){
                                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.belumditetapkan);
                                Bitmap b=bitmapdraw.getBitmap();
                                Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(dm.getLatitude()),Double.parseDouble(dm.getLongitude())))
                                        .anchor(0.5f,0.5f)
                                        .title(dm.getNama_rs())
                                        .snippet(dm.getKelas_rs())
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                            }

                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    String locAddress = marker.getTitle();
//                                    getData(locAddress);
                                    return true;
                                }
                            });
                            //Toasty("Test"+dm.getUsername_umkm());
                            w++;
                        }
                    }else{
//                        Toast.makeText(HospitalListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("DONTOL : ",e.toString());
                    Toast.makeText(HospitalListActivity.this, "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(HospitalListActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Main(){
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        Cari = findViewById(R.id.etSearchHospital);
        Provinsi = findViewById(R.id.spinnerProvinsi);
        Kota = findViewById(R.id.spinnerKota);
        Kelas = findViewById(R.id.spinnerKelas);
        IdProvinsi = findViewById(R.id.tvIdProvinsi);
        IdKota = findViewById(R.id.tvIdKota);
        LList = findViewById(R.id.linearList);
        LMap = findViewById(R.id.linearMap);
        List = findViewById(R.id.tvList);
        Map = findViewById(R.id.tvMap);
        AvailableList = findViewById(R.id.linearAvailableList);
        AvailableMap = findViewById(R.id.linearAvailableListMaps);
        GetProvinsi();
        Provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_provinsi());
                    IdProvinsi.setText(String.valueOf(clickedItems));
                    GetKota();
                }catch (Exception e){
                    Log.d("DONTOL : ",e.toString());
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
                    int clickedItems = Integer.parseInt(clickedItem.getId_kab_kota());
                    IdKota.setText(String.valueOf(clickedItems));
                    LatLng latLng = new LatLng(Double.parseDouble(clickedItem.getLatitude()),Double.parseDouble(clickedItem.getLongitude()));

                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10F));
                    if (clickedItem.getId_provinsi().equals("6")){
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12F));
                    }
                    if (Choice.equals("List")){
                        AvailableList.setVisibility(View.VISIBLE);
                        AvailableMap.setVisibility(View.GONE);
                        Logic();
                    }else{
                        AvailableList.setVisibility(View.GONE);
                        AvailableMap.setVisibility(View.VISIBLE);
                        Logic();
                    }
                }catch (Exception e){
                    Log.d("DONTOL : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Kelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        LList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choice = "List";
                List.setTextColor(Color.WHITE);
                Map.setTextColor(Color.rgb(31,83,137));
                LList.setBackgroundColor(Color.rgb(31,83,137));
                LMap.setBackgroundColor(Color.WHITE);
                AvailableList.setVisibility(View.VISIBLE);
                AvailableMap.setVisibility(View.GONE);
            }
        });
        LMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choice = "Map";
                List.setTextColor(Color.rgb(31,83,137));
                Map.setTextColor(Color.WHITE);
                LList.setBackgroundColor(Color.WHITE);
                LMap.setBackgroundColor(Color.rgb(31,83,137));
                AvailableList.setVisibility(View.GONE);
                AvailableMap.setVisibility(View.VISIBLE);
            }
        });
    }
    private void GetKota(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Kota(Token,IdProvinsi.getText().toString());
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.body().getKode().equals(200)){
                    mItems=response.body().getData();
                    SpinnerKota adapter = new SpinnerKota(HospitalListActivity.this,mItems);
                    Kota.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(HospitalListActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetProvinsi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Provinsi(Token);
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.body().getKode().equals(200)){
                    mItems=response.body().getData();
                    SpinnerProvinsi adapter = new SpinnerProvinsi(HospitalListActivity.this,mItems);
                    Provinsi.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(HospitalListActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void OnClick() {
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
    }

    private void Declaration() {
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Hospital = findViewById(R.id.tvJumlahRS);
        Bed = findViewById(R.id.tvJumlahBed);
        recycler = findViewById(R.id.recycler);
        Main();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng latLng = new LatLng(-6.255460, 106.924714);
//        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.kelasa);
//        Bitmap b=bitmapdraw.getBitmap();
//        Bitmap smallMarker = Bitmap.createScaledBitmap(b,50,50,false);
//        googleMap.addMarker(new MarkerOptions()
//                .position(new LatLng(-6.2554605,106.924714))
//                .anchor(0.5f,0.5f)
//                .title("Test")
//                .snippet("Test")
//                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12F));
    }
}