package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import com.ascendant.e_businessprofile.API.OldRetroServer;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterListHospital;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerKota;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerProvinsi;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
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
import java.util.List;

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
    TextView JumlahRumahSakit,JumlahBed,NamaRs,KelasRs,JumlahPekerjaRs,TotalBedRs;
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
    CardView card;
    private GoogleMap mMap;
    Dialog myDialog;
    Button detail;
    Ascendant method = new Ascendant();
    Button btnCari;
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
                            //Toasty("Test"+dm.getUsername_umkm());
                            w++;
                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
//                                    Toast.makeText(HospitalListActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                                    String locAddress = marker.getTitle();
                                    getData(locAddress);
                                    return true;
                                }
                            });
                        }
                    }else{
//                        Toast.makeText(HospitalListActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(HospitalListActivity.this, "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(HospitalListActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getData(String namaRS){
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_data_rs);
        NamaRs=myDialog.findViewById(R.id.tvNamaRS);
        KelasRs=myDialog.findViewById(R.id.tvKelasRS);
        JumlahPekerjaRs=myDialog.findViewById(R.id.tvPekerjaRS);
        TotalBedRs=myDialog.findViewById(R.id.tvTotalJumlahBedRS);
        detail=myDialog.findViewById(R.id.btnDetail);
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> getData = api.listHospital(method.AUTH(),"0","0",namaRS,"0","FABAJakartaIndonesia2019kunci");
        getData.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                java.util.List<DataModel> mItems = new ArrayList<>();
                if (response == null){
                    Toast.makeText(HospitalListActivity.this, "", Toast.LENGTH_SHORT).show();
                }else{
                    if (response.isSuccessful()) {
                        mItems = response.body().getList();
                        int w = 0;
                        final DataModel dm = mItems.get(w);

                        myDialog.show();
                        NamaRs.setText(dm.getNama_rs());
                        KelasRs.setText(dm.getKelas_rs());
                        JumlahPekerjaRs.setText(dm.getJumlah_tenaga_rs());
                        TotalBedRs.setText(dm.getJumlah_bed_rs());
                        detail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DetailRS(dm.getKode_rs());
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
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
                    Log.d("ZYARGA : ",e.toString());
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
                    Log.d("ZYARGA : ",e.toString());
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
                card.setVisibility(View.VISIBLE);
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
                card.setVisibility(View.GONE);
            }
        });
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic();
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
        card = findViewById(R.id.card);
        btnCari = findViewById(R.id.btnCari);
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

    private void DetailRS(String kode) {
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> DetailRS = api.detailHospital(method.AUTH(),kode,"FABAJakartaIndonesia2019kunci");
        DetailRS.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.isSuccessful()){
                    Intent goInput = new Intent(HospitalListActivity.this, DetailHospitalActivity.class);
                    goInput.putExtra("Kode",response.body().getKode_rs());
                    goInput.putExtra("Registrasi",response.body().getTgl_registrasi_rs());
                    goInput.putExtra("NamaRS",response.body().getNama_rs());
                    goInput.putExtra("Jenis",response.body().getJenis_rs());
                    goInput.putExtra("KlsRS",response.body().getKelas_rs());
                    goInput.putExtra("DirekturRS",response.body().getDirektur_rs());
                    goInput.putExtra("LatarBelakangPendidikan",response.body().getLatar_pendidikan_direktur_rs());
                    goInput.putExtra("Pemilik",response.body().getPemilik_rs());
                    goInput.putExtra("Alamat",response.body().getAlamat_rs());
                    goInput.putExtra("Kota",response.body().getNama_kab_kota());
                    goInput.putExtra("KodePos",response.body().getKode_pos_rs());
                    goInput.putExtra("Telepon",response.body().getTelepon_rs());
                    goInput.putExtra("Fax",response.body().getFax_rs());
                    goInput.putExtra("Email",response.body().getEmail_rs());
                    goInput.putExtra("TeleponHumas",response.body().getTelepon_humas_rs());
                    goInput.putExtra("Website",response.body().getWebsite_rs());
                    goInput.putExtra("LuasTanah",response.body().getLuas_tanah_rs());
                    goInput.putExtra("LuasBangunan",response.body().getLuas_bangunan_rs());
                    goInput.putExtra("NoSuratIzin",response.body().getNo_surat_ijin());
                    goInput.putExtra("TanggalSuratIzin",response.body().getTanggal_surat_ijin());
                    goInput.putExtra("SuratIzinDari",response.body().getSurat_ijin_dari());
                    goInput.putExtra("SifatSuratIjin",response.body().getSifat_surat_ijin());
                    goInput.putExtra("MasaBerlakuSuratIjin",response.body().getMasa_berlaku_ijin());
                    goInput.putExtra("StatusPenyelenggara",response.body().getStatus_penyelenggara());
                    goInput.putExtra("StatusAkreditasi",response.body().getStatus_akreditasi());
                    goInput.putExtra("TglAkreditasi",response.body().getTgl_akreditasi());
                    goInput.putExtra("BerlakuSampai",response.body().getBerlaku_sampai_dengan());
                    goInput.putExtra("VVIP",response.body().getVvip_bed());
                    goInput.putExtra("VIP",response.body().getVip_bed());
                    goInput.putExtra("Kelas1",response.body().getKelas_1_bed());
                    goInput.putExtra("Kelas2",response.body().getKelas_2_bed());
                    goInput.putExtra("Kelas3",response.body().getKelas_3_bed());
                    goInput.putExtra("ICU",response.body().getIcu_bed());
                    goInput.putExtra("PICU",response.body().getPicu_bed());
                    goInput.putExtra("NICU",response.body().getNicu_bed());
                    goInput.putExtra("TTBayiBaru",response.body().getTt_bayi_baru_lahir_bed());
                    goInput.putExtra("HCU",response.body().getHcu_bed());
                    goInput.putExtra("ICCU",response.body().getIccu_bed());
                    goInput.putExtra("IGD",response.body().getIgd_bed());
                    goInput.putExtra("TTRuangOperasi",response.body().getTt_di_ruang_operasi_bed());
                    goInput.putExtra("TTRuangIsolasi",response.body().getTt_di_ruang_operasi_bed());
                    goInput.putExtra("DrUmum",response.body().getDr_umum());
                    goInput.putExtra("DrOG",response.body().getDr_sp_og());
                    goInput.putExtra("DrPD",response.body().getDr_sp_pd());
                    goInput.putExtra("DrB",response.body().getDr_sp_b());
                    goInput.putExtra("DrRad",response.body().getDr_sp_rad());
                    goInput.putExtra("DrRM",response.body().getDr_sp_rm());
                    goInput.putExtra("DrAn",response.body().getDr_sp_an());
                    goInput.putExtra("DrJp",response.body().getDr_sp_jp());
                    goInput.putExtra("DrM",response.body().getDr_sp_m());
                    goInput.putExtra("DrTHT",response.body().getDr_sp_tht());
                    goInput.putExtra("DrPK",response.body().getDr_sp_pk());
                    goInput.putExtra("DrParu",response.body().getDr_sp_paru());
                    goInput.putExtra("DrBedahThoraks",response.body().getDr_sp_bedah_thoraks());
                    goInput.putExtra("DrBedahAnak",response.body().getDr_sp_bedah_anak());
                    goInput.putExtra("DrBedahOrhopedi",response.body().getDr_sp_bedah_orthopedi());
                    goInput.putExtra("DrA",response.body().getDr_sp_a());
                    goInput.putExtra("DrOkupasi",response.body().getDr_sp_okupasi());
                    goInput.putExtra("DrUrologi",response.body().getDr_sp_urologi());
                    goInput.putExtra("DrOrthopedi",response.body().getDr_sp_orthopedi());
                    goInput.putExtra("DrKulit",response.body().getDr_sp_kulit_dan_kelamin());
                    goInput.putExtra("DrForensik",response.body().getDr_sp_forensik());
                    goInput.putExtra("DrPsikiatri",response.body().getDr_sp_psikiatri());
                    goInput.putExtra("DrOfthalmologi",response.body().getDr_sp_ofthamologi());
                    goInput.putExtra("DrAnatomi",response.body().getDr_sp_patologi_anatomi());
                    goInput.putExtra("DrJiwa",response.body().getDr_sp_kes_kejiwaan());
                    goInput.putExtra("DrSaraf",response.body().getDr_sp_saraf());
                    goInput.putExtra("DrLainnya",response.body().getDr_sp_lainnya());
                    goInput.putExtra("DrBedahSaraf",response.body().getDr_sp_bedah_saraf());
                    goInput.putExtra("DrBedahPlastik",response.body().getDr_sp_bedah_plastik());
                    goInput.putExtra("DrSubSpesialis",response.body().getDr_sub_spesialis());
                    goInput.putExtra("DrGigi",response.body().getDr_gigi());
                    goInput.putExtra("DrBedahMulut",response.body().getDr_gigi_sp_bedah_mulut());
                    goInput.putExtra("DrKonservasi",response.body().getDr_gigi_sp_konservasi());
                    goInput.putExtra("DrPenyakitMulut",response.body().getDr_gigi_sp_penyakit_mulut());
                    goInput.putExtra("DrRadiologi",response.body().getDr_gigi_sp_radiologi());
                    goInput.putExtra("DrKarangGigi",response.body().getDr_gigi_sp_karang_gigi());
                    goInput.putExtra("DrGigiAnak",response.body().getDr_gigi_sp_anak());
                    goInput.putExtra("DrGigiTiruan",response.body().getDr_gigi_sp_gigi_tiruan());
                    goInput.putExtra("DrPeriodonsia",response.body().getDr_gigi_sp_periodonsia());
                    goInput.putExtra("DrGigiLainnya",response.body().getDr_gigi_sp_lainnya());
                    goInput.putExtra("BidanPendidik",response.body().getBidan_pendidik());
                    goInput.putExtra("BidanKlinik",response.body().getBidan_klinik());
                    goInput.putExtra("Apoteker",response.body().getApoteker());
                    goInput.putExtra("AnalisFarmasi",response.body().getAnalis_farmasi());
                    goInput.putExtra("Radiografer",response.body().getRadiografer());
                    goInput.putExtra("Radioterapis",response.body().getRadioterapis());
                    goInput.putExtra("Elektromedis",response.body().getElektromedis());
                    goInput.putExtra("TeknisiGigi",response.body().getTeknisi_gigi());
                    goInput.putExtra("AnalisKesehataan",response.body().getAnalis_kesehatan());
                    goInput.putExtra("Refraksionis",response.body().getRefaksionis());
                    goInput.putExtra("RekamMedik",response.body().getRekam_medik());
                    goInput.putExtra("Ortotik",response.body().getOrtotik());
                    goInput.putExtra("TransfusiDarah",response.body().getTeknisi_transfusi_darah());
                    goInput.putExtra("Kardiovaskular",response.body().getTeknisi_kardiovaskular());
                    goInput.putExtra("Epidemiologi",response.body().getEpidemiologi());
                    goInput.putExtra("PromosiKesehatan",response.body().getPromosi_kesehatan());
                    goInput.putExtra("Perilaku",response.body().getPerilaku());
                    goInput.putExtra("Kesja",response.body().getKesja());
                    goInput.putExtra("AdministrasiKesehatan",response.body().getAdministrasi_kesehatan());
                    goInput.putExtra("Biostatik",response.body().getBiostatistik());
                    goInput.putExtra("Reproduksi",response.body().getReproduksi());
                    goInput.putExtra("InformasiKesehatan",response.body().getInformasi_kesehatan());
                    goInput.putExtra("KesmasLainnya",response.body().getKesmas_lainnya());
                    //Tenaga Kesehatan Lainnya
                    goInput.putExtra("Sanitasi",response.body().getSanitasi());
                    goInput.putExtra("Entomologi",response.body().getEntomologi());
                    goInput.putExtra("Mikrobiologi",response.body().getMikrobiologi());
                    goInput.putExtra("KesehatanLingkungan",response.body().getKesehatan_lingkungan());
                    goInput.putExtra("TerapiWicara",response.body().getTerapi_wicara());
                    goInput.putExtra("Nutrisionis",response.body().getNutrisionis());
                    goInput.putExtra("Dietsien",response.body().getDietsien());
                    goInput.putExtra("Fisioterapi",response.body().getFisioterapi());
                    goInput.putExtra("TerapiOkupasi",response.body().getTerapi_okupasi());
                    goInput.putExtra("Akupunturis",response.body().getAkupunturis());
                    //Tenaga Non Kesehatan
                    goInput.putExtra("ProgramKesehatan",response.body().getProgram_kesehatan());
                    goInput.putExtra("Administrasikeuangan",response.body().getAdministrasi_keuangan());
                    goInput.putExtra("Humas",response.body().getHumas());
                    goInput.putExtra("Perencanaan",response.body().getPerencanaan());
                    goInput.putExtra("JaminanKesehatan",response.body().getJaminan_kesehatan());
                    goInput.putExtra("Dosen",response.body().getDosen());
                    goInput.putExtra("Psikologi",response.body().getPsikologi());
                    goInput.putExtra("Pelaporan",response.body().getPelaporan());
                    goInput.putExtra("InformasiTeknologi",response.body().getInformasi_teknologi());
                    goInput.putExtra("Hukum",response.body().getHukum());
                    goInput.putExtra("Pekarya",response.body().getPekarya());
                    goInput.putExtra("Perpustakaan",response.body().getPerpustakaan());
                    goInput.putExtra("Widyaswara",response.body().getWidyaiswara());
                    goInput.putExtra("TenagaNonKers",response.body().getTenaga_non_kes());
                    //Data Peralatan Di Rumah Sakit
                    goInput.putExtra("MejaOperasi",response.body().getMeja_operasi());
                    goInput.putExtra("MesinAnestesi",response.body().getMesin_anestesi());
                    goInput.putExtra("Ventilator",response.body().getVentilator());
                    goInput.putExtra("Inkubator",response.body().getInkubator());
                    goInput.putExtra("BlueLight",response.body().getBlue_light());
                    goInput.putExtra("U_S_G",response.body().getUsg());
                    goInput.putExtra("XRay",response.body().getX_ray());
                    goInput.putExtra("CTScan",response.body().getCt_scan());
                    goInput.putExtra("MRI",response.body().getMri());
                    goInput.putExtra("EEG",response.body().getEeg());
                    goInput.putExtra("EKG",response.body().getEkg());
                    goInput.putExtra("Defibrilator",response.body().getDefibrilator());
                    goInput.putExtra("Autoclav",response.body().getAutoclav());
                    goInput.putExtra("RawatJalan",response.body().getRawat_jalan());
                    goInput.putExtra("RawatInap",response.body().getRawat_inap());
                    goInput.putExtra("I_G_D",response.body().getI_g_dbank());
                    goInput.putExtra("BOR",response.body().getBor());
                    goInput.putExtra("ALOS",response.body().getAlos());
                    goInput.putExtra("TOI",response.body().getToi());
                    goInput.putExtra("NDR",response.body().getNdr());
                    goInput.putExtra("GDR",response.body().getGdr());
                    goInput.putExtra("LayananUnggulan",response.body().getLayanan_unggulan());
                    goInput.putExtra("SIMRS",response.body().getSimrs());
                    goInput.putExtra("Ambulan",response.body().getAmbulan());
                    goInput.putExtra("BankDarah",response.body().getBank_darah());
                    goInput.putExtra("TanggalUpdate",response.body().getTgl_update());
                    startActivities(new Intent[]{goInput});
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(HospitalListActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}