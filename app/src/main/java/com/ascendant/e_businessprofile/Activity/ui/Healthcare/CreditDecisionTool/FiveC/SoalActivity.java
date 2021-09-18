package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FiveC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
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

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital.HospitalListActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterListHospital;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerKota;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerProvinsi;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.NumberTextWatcher;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.CreditDecisionToolModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC.CapacityData;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC.CapitalData;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC.CharacterData;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC.CollateralData;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveC.ConditionData;
import com.ascendant.e_businessprofile.Model.StaticModel.Quis;
import com.ascendant.e_businessprofile.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pListt = new ArrayList<>();


    Button ya,tidak,cancel,simulasi;
    TextView soal,Tittle;
    Dialog myDialog;

    String Token;
    Button BOR,AVLOSBedah,AVLOSNonBedah,BTO,TOI,NDR,GDR;
    Ascendant ascendant = new Ascendant();
    TextView tittle;
    EditText jan,feb,mar,apr,mei,jun,jul,ags,sep,okt,nov,des;
    EditText sJan,sFeb,sMar,sApr,sMei,sJun,sJul,sAgs,sSep,sOkt,sNov,sDes;
    Button hitung2;

    LineChart chart;
    TextView staticText,skor,link,jumlah;
    LinearLayout main,input,result;
    EditText JumlahTempatTidur,Penduduk;
    Spinner Provinsi,Kota;
    Button hitung,tutup;
    String TITTLE_PERHITUNGAN="";
    String JAN = "0",FEB = "0",MAR = "0",APR = "0",MEI = "0",JUN = "0",JUL = "0",AGS = "0",SEP = "0",OKT = "0",NOV = "0",DES = "0";

    private RecyclerView.Adapter mAdapter;
    private List<DataModel> mItems = new ArrayList<>();
    private List<DataModel> pList = new ArrayList<>();
    private RecyclerView.LayoutManager mManager;
    private SpinnerProvinsi aProvinsi;
    TextView idKota,idProvinsi;
    TextView Rasio,tempattidur;
    ImageView back,home;
    CardView cardHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pListt.addAll(CreditDecisionToolModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pListt);
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




        Intent data = getIntent();
        String No = data.getStringExtra("NO");
        final String CATEGORY = data.getStringExtra("CATEGORY");
        final String Score = data.getStringExtra("SCORE");
        final String SIMULASI = data.getStringExtra("SIMULASI");
        final String JAWABAN = data.getStringExtra("JAWABAN");
        final int no = Integer.parseInt(No);
        ya = findViewById(R.id.btnYa);
        tidak = findViewById(R.id.btnTidak);
        soal = findViewById(R.id.tvSoal);
        Tittle = findViewById(R.id.tvTittle);
        cancel = findViewById(R.id.btnCancel);
        simulasi= findViewById(R.id.btnSimulasi);
        if (CATEGORY.equals("CHARACTER")){
            pList.addAll(CharacterData.getListData());
        }else if(CATEGORY.equals("CAPACITY")){
            pList.addAll(CapacityData.getListData());
        }else if(CATEGORY.equals("CAPITAL")){
            pList.addAll(CapitalData.getListData());
        }else if(CATEGORY.equals("COLLATERAL")){
            pList.addAll(CollateralData.getListData());
        }else if(CATEGORY.equals("CONDITION")){
            pList.addAll(ConditionData.getListData());
        }
        if (SIMULASI.equals("NOPE")){
            simulasi.setVisibility(View.INVISIBLE);
        }else{
            simulasi.setVisibility(View.VISIBLE);
            if (SIMULASI.equals("Simulasi Perhitungan Tempat Tidur")){
                myDialog = new Dialog(this);
                myDialog.setContentView(R.layout.dialog_rasio_kebutuhan);
                jumlah = myDialog.findViewById(R.id.tvJumlah);
                JumlahTempatTidur = myDialog.findViewById(R.id.etInputJumlahTempatTidur);
                Penduduk = myDialog.findViewById(R.id.etInputPenduduk);
                Provinsi = myDialog.findViewById(R.id.spinnerProvinsi);
                Kota = myDialog.findViewById(R.id.spinnerKota);
                idKota = myDialog.findViewById(R.id.tvidKota);
                idProvinsi = myDialog.findViewById(R.id.tvIdProvinsi);
                Rasio = myDialog.findViewById(R.id.tvRasioKebutuhan);
                tempattidur = myDialog.findViewById(R.id.jumlahTempatTidurDibutuhkan);
                cardHasil = myDialog.findViewById(R.id.cardHasil);
                tutup = myDialog.findViewById(R.id.btnTutup);
            }else if(SIMULASI.equals("Simulasi Perhitungan Indikator Kinerja RS")){
                myDialog = new Dialog(this);
                myDialog.setContentView(R.layout.dialog_perhitungan_indikator_kinerja_rs);
                tutup = myDialog.findViewById(R.id.btnTutup);
                //MAIN
                main = myDialog.findViewById(R.id.linearMain);
                BOR = myDialog.findViewById(R.id.btnBor);
                AVLOSBedah = myDialog.findViewById(R.id.btnAvlosBedah);
                AVLOSNonBedah = myDialog.findViewById(R.id.btnAvlosNonBedah);
                BTO = myDialog.findViewById(R.id.btnBTO);
                TOI = myDialog.findViewById(R.id.btnTOI);
                NDR = myDialog.findViewById(R.id.btnNDR);
                GDR = myDialog.findViewById(R.id.btnGDR);

                //INPUTAN
                input = myDialog.findViewById(R.id.linearInputan);
                tittle = myDialog.findViewById(R.id.tvTittle);
                jan = myDialog.findViewById(R.id.etJan);
                feb = myDialog.findViewById(R.id.etFeb);
                mar = myDialog.findViewById(R.id.etMar);
                apr = myDialog.findViewById(R.id.etApr);
                mei = myDialog.findViewById(R.id.etMei);
                jun = myDialog.findViewById(R.id.etJun);
                jul = myDialog.findViewById(R.id.etJul);
                ags = myDialog.findViewById(R.id.etAgt);
                sep = myDialog.findViewById(R.id.etSep);
                okt = myDialog.findViewById(R.id.etOkt);
                nov = myDialog.findViewById(R.id.etNov);
                des = myDialog.findViewById(R.id.etDes);
                sJan = myDialog.findViewById(R.id.satuanJan);
                sFeb = myDialog.findViewById(R.id.satuanFeb);
                sMar = myDialog.findViewById(R.id.satuanMar);
                sApr = myDialog.findViewById(R.id.satuanApr);
                sMei = myDialog.findViewById(R.id.satuanMei);
                sJun = myDialog.findViewById(R.id.satuanJun);
                sJul = myDialog.findViewById(R.id.satuanJul);
                sAgs = myDialog.findViewById(R.id.satuanAgt);
                sSep = myDialog.findViewById(R.id.satuanSep);
                sOkt = myDialog.findViewById(R.id.satuanOkt);
                sNov = myDialog.findViewById(R.id.satuanNov);
                sDes = myDialog.findViewById(R.id.satuanDes);
                hitung = myDialog.findViewById(R.id.btnHitung);

                //GRAPHIC
                result = myDialog.findViewById(R.id.linearResult);
                chart = myDialog.findViewById(R.id.chart);
                staticText = myDialog.findViewById(R.id.tvStaticText);
                skor = myDialog.findViewById(R.id.tvSkor);
            }
        }

        simulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SIMULASI.equals("Simulasi Perhitungan Tempat Tidur")){
                    LogicRasioKebutuhan();
                }else if(SIMULASI.equals("Simulasi Perhitungan Indikator Kinerja RS")){
                    LogicSimulasiperhitunganRS();
                }
            }
        });
        soal.setText(pList.get(no).getSoal_quis());
        Tittle.setText("Pernyataan "+pList.get(no).getNo_quis()+"/"+pList.size());
        ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (JAWABAN.equals("YA")){
                    if (no >= pList.size()-1){
                        Intent goInput = new Intent(SoalActivity.this, FiveCActivity.class);
                        LOGIC(CATEGORY,Integer.parseInt(Score));
                        startActivities(new Intent[]{goInput});
                    }else{
                        Intent goInput = new Intent(SoalActivity.this, SoalActivity.class);
                        goInput.putExtra("NO",String.valueOf(no+1));
                        goInput.putExtra("SCORE",String.valueOf(Integer.parseInt(Score)+2));
                        goInput.putExtra("CATEGORY",CATEGORY);
                        goInput.putExtra("SIMULASI",pList.get(no+1).getSimulasi());
                        goInput.putExtra("JAWABAN",pList.get(no+1).getJawaban());
                        startActivity(goInput);
                    }
                }else{
                    if (no >= pList.size()-1){
                        Intent goInput = new Intent(SoalActivity.this, FiveCActivity.class);
                        LOGIC(CATEGORY,Integer.parseInt(Score));
                        startActivities(new Intent[]{goInput});
                    }else{
                        Intent goInput = new Intent(SoalActivity.this, SoalActivity.class);
                        goInput.putExtra("NO",String.valueOf(no+1));
                        goInput.putExtra("SCORE",String.valueOf(Integer.parseInt(Score)));
                        goInput.putExtra("CATEGORY",CATEGORY);
                        goInput.putExtra("SIMULASI",pList.get(no+1).getSimulasi());
                        goInput.putExtra("JAWABAN",pList.get(no+1).getJawaban());
                        startActivity(goInput);
                    }
                }

            }
        });
        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (JAWABAN.equals("TIDAK")){
                    if (no >= pList.size()-1){
                        Intent goInput = new Intent(SoalActivity.this, FiveCActivity.class);
                        LOGIC(CATEGORY,Integer.parseInt(Score));
                        startActivities(new Intent[]{goInput});
                    }else{
                        Intent goInput = new Intent(SoalActivity.this, SoalActivity.class);
                        goInput.putExtra("NO",String.valueOf(no+1));
                        goInput.putExtra("SCORE",String.valueOf(Integer.parseInt(Score)+2));
                        goInput.putExtra("CATEGORY",CATEGORY);
                        goInput.putExtra("SIMULASI",pList.get(no+1).getSimulasi());
                        goInput.putExtra("JAWABAN",pList.get(no+1).getJawaban());
                        startActivity(goInput);
                    }
                }else{
                    if (no >= pList.size()-1){
                        Intent goInput = new Intent(SoalActivity.this, FiveCActivity.class);
                        LOGIC(CATEGORY,Integer.parseInt(Score));
                        startActivities(new Intent[]{goInput});
                    }else{
                        Intent goInput = new Intent(SoalActivity.this, SoalActivity.class);
                        goInput.putExtra("NO",String.valueOf(no+1));
                        goInput.putExtra("SCORE",String.valueOf(Integer.parseInt(Score)));
                        goInput.putExtra("CATEGORY",CATEGORY);
                        goInput.putExtra("SIMULASI",pList.get(no+1).getSimulasi());
                        goInput.putExtra("JAWABAN",pList.get(no+1).getJawaban());
                        startActivity(goInput);
                    }
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoalActivity.this,FiveCActivity.class);
                startActivity(intent);
            }
        });
    }
    private void LOGIC(String category,int score){

        String totalscore = "0";
        if (category.equals("CHARACTER")){
            if (score >= 0 && score <=5){
                totalscore = "5";
            }else if(score >5 && score<=10){
                totalscore = "10";
            }else if (score >11 && score<=15){
                totalscore = "15";
            }else if (score >=16 && score<=20){
                totalscore = "20";
            }
        }else if(category.equals("CAPACITY")){
            if (score >= 0 && score <=10){
                totalscore = "10";
            }else if(score >=11 && score<=20){
                totalscore = "20";
            }else if (score >21 && score<=30){
                totalscore = "30";
            }else if (score >=31 && score<=40){
                totalscore = "40";
            }
        }else if(category.equals("CAPITAL")){
            if (score >= 0 && score <=7){
                totalscore = "5";
            }else if(score >=8 && score<=14){
                totalscore = "10";
            }else if (score >=15 && score<=20){
                totalscore = "15";
            }
        }else if(category.equals("COLLATERAL")){
            if (score >= 0 && score <=5){
                totalscore = "5";
            }else if(score >=6 && score<=10){
                totalscore = "10";
            }
        }else if(category.equals("CONDITION")){
            if (score >=0 && score<=6){
                totalscore = "5";
            }else if(score >=7 && score<=11){
                totalscore = "10";
            }else if (score >=12 && score<=16){
                totalscore = "15";
            }
        }
        DB_Helper dbHelper = new DB_Helper(SoalActivity.this);
        Quis quis = new Quis(category,totalscore);
        dbHelper.saveScore(quis);

        dbHelper = new DB_Helper(this);
        Cursor cursorss = dbHelper.checkUser();
        if (cursorss.getCount()>0){
            while (cursorss.moveToNext()){
                Token = cursorss.getString(0);
            }
        }
    }

    @Override
    public void onBackPressed() {

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

    private void Logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseObject> ListRumahSakit = api.ListHospital(Token,
                idProvinsi.getText().toString(),
                idKota.getText().toString(),
                "",
                "");
        ListRumahSakit.enqueue(new Callback<ResponseObject>() {
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
    private void getKota(String Provinsi){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> getProvinsi = api.Kota(Token,Provinsi);
        getProvinsi.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                mItems=response.body().getData();
                SpinnerKota adapter = new SpinnerKota(SoalActivity.this,mItems);
                Kota.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(SoalActivity.this,"Kelas Tidak Ditemukan",Toast.LENGTH_SHORT).show();
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
                SpinnerProvinsi adapter = new SpinnerProvinsi(SoalActivity.this,mItems);
                Provinsi.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(SoalActivity.this,"Kelas Tidak Ditemukan",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LogicRasioKebutuhan(){
        myDialog.show();
        Rasio.setVisibility(View.INVISIBLE);
        tempattidur.setVisibility(View.INVISIBLE);
        getProvinsi();
        cardHasil.setVisibility(View.GONE);
        aProvinsi = new SpinnerProvinsi(SoalActivity.this,mItems);
        Provinsi.setAdapter(aProvinsi);
        Provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                String clickedItems = clickedItem.getId_provinsi();
                getKota(clickedItems);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        aProvinsi = new SpinnerProvinsi(SoalActivity.this,mItems);
        Kota.setAdapter(aProvinsi);
        Kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                idKota.setText(clickedItem.getId_kab_kota());
                Penduduk.setText(clickedItem.getTotal_penduduk());
                JumlahTempatTidur.setText(clickedItem.getJumlah_bed_rs());
                Logic();
//
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Penduduk.addTextChangedListener(new NumberTextWatcher(Penduduk));
        JumlahTempatTidur.addTextChangedListener(new NumberTextWatcher(JumlahTempatTidur));
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.hide();
            }
        });
    }
    private void LogicSimulasiperhitunganRS(){
        myDialog.show();
        main.setVisibility(View.VISIBLE);
        input.setVisibility(View.GONE);
        result.setVisibility(View.GONE);
        BOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITTLE_PERHITUNGAN = "BOR";
                main.setVisibility(View.GONE);
                input.setVisibility(View.VISIBLE);
                SuperLogic(TITTLE_PERHITUNGAN);
            }
        });
        AVLOSBedah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITTLE_PERHITUNGAN = "AVLOS_BEDAH";
                main.setVisibility(View.GONE);
                input.setVisibility(View.VISIBLE);
                SuperLogic(TITTLE_PERHITUNGAN);
            }
        });
        AVLOSNonBedah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITTLE_PERHITUNGAN = "AVLOS_NON_BEDAH";
                main.setVisibility(View.GONE);
                input.setVisibility(View.VISIBLE);
                SuperLogic(TITTLE_PERHITUNGAN);
            }
        });
        BTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITTLE_PERHITUNGAN = "BTO";
                main.setVisibility(View.GONE);
                input.setVisibility(View.VISIBLE);
                SuperLogic(TITTLE_PERHITUNGAN);
            }
        });
        TOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITTLE_PERHITUNGAN = "TOI";
                main.setVisibility(View.GONE);
                input.setVisibility(View.VISIBLE);
                SuperLogic(TITTLE_PERHITUNGAN);
            }
        });
        NDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITTLE_PERHITUNGAN = "NDR";
                main.setVisibility(View.GONE);
                input.setVisibility(View.VISIBLE);
                SuperLogic(TITTLE_PERHITUNGAN);
            }
        });
        GDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TITTLE_PERHITUNGAN = "GDR";
                main.setVisibility(View.GONE);
                input.setVisibility(View.VISIBLE);
                SuperLogic(TITTLE_PERHITUNGAN);
            }
        });
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.hide();
            }
        });
    }

    private void SuperLogic(final String TITTLE_PERHITUNGAN){
        //LOGIC INPUT
        if (TITTLE_PERHITUNGAN.equals("BOR")){
            tittle.setText("Masukan Angka BOR Perbulan (%)");
            Persen();
        }else if(TITTLE_PERHITUNGAN.equals("AVLOS_BEDAH")){
            tittle.setText("Masukan Angka AvLOS perb ulan (hari)");
            Hari();
        }else if(TITTLE_PERHITUNGAN.equals("AVLOS_NON_BEDAH")){
            tittle.setText("Masukan Angka AvLOS perbulan (hari)");
            Hari();
        }else if(TITTLE_PERHITUNGAN.equals("BTO")){
            tittle.setText("Masukan Angka BTO Perbulan (Jumlah Pemakaian)");
            Kali();
        }else if(TITTLE_PERHITUNGAN.equals("TOI")){
            tittle.setText("Masukan ANgka TOI Perbulan (Hari)");
            Hari();
        }else if(TITTLE_PERHITUNGAN.equals("NDR")){
            tittle.setText("Masukan Angka NDR perbulan \n(Jiwa/1000 Pasien Keluar)");
            Jiwa();
        }else if(TITTLE_PERHITUNGAN.equals("GDR")){
            tittle.setText("Masukan Angka GDR perbulan \n(Jiwa/1000 Pasien Keluar)");
            Jiwa();
        }
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checker().equals("SUCCES")){
                    JAN = IFELSES(jan);
                    FEB = IFELSES(feb);
                    MAR = IFELSES(mar);
                    APR = IFELSES(apr);
                    MEI = IFELSES(mei);
                    JUN = IFELSES(jun);
                    JUL = IFELSES(jul);
                    AGS = IFELSES(ags);
                    SEP = IFELSES(sep);
                    OKT = IFELSES(okt);
                    NOV = IFELSES(nov);
                    DES = IFELSES(des);
                    input.setVisibility(View.GONE);
                    result.setVisibility(View.VISIBLE);
                    //RESULT
                    YAxis leftAxis = chart.getAxisLeft();
                    leftAxis.removeAllLimitLines();

                    leftAxis.setAxisMinimum(0f);
                    leftAxis.enableGridDashedLine(10f,10f,0);
                    leftAxis.setDrawLimitLinesBehindData(true);

                    chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                    chart.getAxisRight().setEnabled(false);
                    //DONE
                    chart.animateY(1000);
                    chart.setVisibleXRangeMaximum(20);
                    chart.moveViewToX(10);
                    IFELSES(JAN,FEB,MAR,APR,MEI,JUN,JUL,AGS,SEP,OKT,NOV,DES,TITTLE_PERHITUNGAN);
                    Hitung(JAN,FEB,MAR,APR,MEI,JUN,JUL,AGS,SEP,OKT,NOV,DES,TITTLE_PERHITUNGAN);
                    //DONE
                }else{
                    Toast.makeText(SoalActivity.this, checker(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //DONE
    }

    public class MyXAxisValueFormater extends ValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyXAxisValueFormater(String[] values) {
            this.mValues=values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }

    private void Hitung(String Jan,String Feb,String Mar,String Apr,String Mei,String Jun,String Jul,String Ags,String Sep,String Okt,String Nov,String Des,String datasets){
        int size = 0;
        if (!Jan.equals("0")){
            size = size+1;
        }
        if (!Feb.equals("0")){
            size = size+1;
        }
        if (!Mar.equals("0")){
            size = size+1;
        }
        if (!Apr.equals("0")){
            size = size+1;
        }
        if (!Mei.equals("0")){
            size = size+1;
        }
        if (!Jun.equals("0")){
            size = size+1;
        }
        if (!Jul.equals("0")){
            size = size+1;
        }
        if (!Ags.equals("0")){
            size = size+1;
        }
        if (!Sep.equals("0")){
            size = size+1;
        }
        if (!Okt.equals("0")){
            size = size+1;
        }
        if (!Nov.equals("0")){
            size = size+1;
        }
        if (!Des.equals("0")){
            size = size+1;
        }
        int TotalSkor = 0;
        int rata2 = 0;
        if(size > 1){
            TotalSkor = Integer.parseInt(Jan)
                    +Integer.parseInt(Feb)
                    +Integer.parseInt(Mar)
                    +Integer.parseInt(Apr)
                    +Integer.parseInt(Mei)
                    +Integer.parseInt(Jun)
                    +Integer.parseInt(Jul)
                    +Integer.parseInt(Ags)
                    +Integer.parseInt(Sep)
                    +Integer.parseInt(Okt)
                    +Integer.parseInt(Nov)
                    +Integer.parseInt(Des);
            rata2 = TotalSkor/size;
        }else{
            rata2 = Integer.parseInt(Jan)
                    +Integer.parseInt(Feb)
                    +Integer.parseInt(Mar)
                    +Integer.parseInt(Apr)
                    +Integer.parseInt(Mei)
                    +Integer.parseInt(Jun)
                    +Integer.parseInt(Jul)
                    +Integer.parseInt(Ags)
                    +Integer.parseInt(Sep)
                    +Integer.parseInt(Okt)
                    +Integer.parseInt(Nov)
                    +Integer.parseInt(Des);
        }

        if (datasets.equals("BOR")){
            skor.setText("Skor Nilai BOR : "+rata2+"%");
            staticText.setText("Standar Nilai Rujukan BOR yang baik :\nKemenkes : 60-85 %\n" +
                    "Grafik Barber Johnson : 75 - 85 %\n" +
                    "Expertise FABA : 60 - 80 %");
        }else if(datasets.equals("AVLOS_BEDAH")){
            skor.setText("Skor Nilai AvLOS_BEDAH : "+rata2+" Hari");
            staticText.setText("Standar Nilai Rujukan AvLOS-BEDAH yang baik :\nKemenkes : 6-9 Hari %\n" +
                    "Grafik Barber Johnson : 3 - 12 %\n" +
                    "Expertise FABA : 4 - 7 %");
        }else if(datasets.equals("AVLOS_NON_BEDAH")){
            skor.setText("Skor Nilai AvLOS_NON_BEDAH : "+rata2+" Hari");
            staticText.setText("Standar Nilai Rujukan AvLOS-NON-BEDAH yang baik :\nKemenkes : 6-9 Hari\n" +
                    "Grafik Barber Johnson : 3 - 12 Haro\n" +
                    "Expertise FABA : 3 - 15 %");
        }else if(datasets.equals("BTO")){
            skor.setText("Skor Nilai BTO : "+rata2+" Kali Per Tahun");
            staticText.setText("Standar Nilai Rujukan BTO yang baik :\nKemenkes : 30-50 Kali\n" +
                    "Grafik Barber Johnson : Lebih dari 30 Kali\n" +
                    "Expertise FABA : 40 - 50 Kali");
        }else if(datasets.equals("TOI")){
            skor.setText("Skor Nilai TOI : "+rata2+" Hari");
            staticText.setText("Standar Nilai Rujukan TOI yang baik :\nKemenkes : 1-3 Hari %\n" +
                    "Grafik Barber Johnson : 1 - 3 Hari\n" +
                    "Expertise FABA : 1 - 3 Hari");
        }else if(datasets.equals("NDR")){
            skor.setText("Skor Nilai NDR : "+rata2+" Jiwa/1000 Pasien Keluar");
            staticText.setText("Standar Nilai Rujukan NDR yang baik :\nKemenkes : < 25 Jiwa/1000 Pasien Keluar\n" +
                    "Grafik Barber Johnson : < 25 Jiwa/1000 Pasien Keluar\n" +
                    "Expertise FABA : < 25 Jiwa/1000 Pasien Keluar");
        }
        else if(datasets.equals("GDR")){
            skor.setText("Skor Nilai GDR : "+rata2+" Jiwa/1000 Pasien Keluar");
            staticText.setText("Standar Nilai Rujukan GDR yang baik :\n Kemenkes : < 45 Jiwa/1000 Pasien Keluar\n" +
                    "Grafik Barber Johnson : < 45 Jiwa/1000 Pasien Keluar\n" +
                    "Expertise FABA : < 45 Jiwa/1000 Pasien Keluar");
        }
    }

    private void IFELSES(String Jan,String Feb,String Mar,String Apr,String Mei,String Jun,String Jul,String Ags,String Sep,String Okt,String Nov,String Des,String datasets){
        ArrayList<Entry> yValue = new ArrayList<>();
        yValue.add(new Entry(0,Float.parseFloat(Jan+"f")));
        yValue.add(new Entry(1,Float.parseFloat(Feb+"f")));
        yValue.add(new Entry(2,Float.parseFloat(Mar+"f")));
        yValue.add(new Entry(3,Float.parseFloat(Apr+"f")));
        yValue.add(new Entry(4,Float.parseFloat(Mei+"f")));
        yValue.add(new Entry(5,Float.parseFloat(Jun+"f")));
        yValue.add(new Entry(6,Float.parseFloat(Jul+"f")));
        yValue.add(new Entry(7,Float.parseFloat(Ags+"f")));
        yValue.add(new Entry(8,Float.parseFloat(Sep+"f")));
        yValue.add(new Entry(9,Float.parseFloat(Okt+"f")));
        yValue.add(new Entry(10,Float.parseFloat(Nov+"f")));
        yValue.add(new Entry(11,Float.parseFloat(Des+"f")));

        LineDataSet set1 = new LineDataSet(yValue,datasets);
        set1.setColor(Color.rgb(37,197,223));
        set1.setLineWidth(5f);
        set1.setFillAlpha(110);
        set1.setValueTextSize(10);
        set1.setCircleRadius(5);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        chart.setData(data);

        String[] values = new String[]{"JAN","FEB","MAR","APR","MEI","JUN","JUL","AGS","SEP","OKT","NOV","DES"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormater(values));
        xAxis.setGranularity(1);


    }
    private String checker(){
        String message = "SUCCES";
        if (jan.getText().toString().isEmpty()){
            message = "Bulan Januari Kosong";
        }else if(feb.getText().toString().isEmpty()){
            message = "Bulan Februari Kosong";
        }else if(mar.getText().toString().isEmpty()){
            message = "Bulan Maret Kosong";
        }else if(apr.getText().toString().isEmpty()){
            message = "Bulan April Kosong";
        }else if(mei.getText().toString().isEmpty()){
            message = "Bulan Mei Kosong";
        }else if(jun.getText().toString().isEmpty()){
            message = "Bulan Juni Kosong";
        }else if(jul.getText().toString().isEmpty()){
            message = "Bulan Juli Kosong";
        }else if(ags.getText().toString().isEmpty()){
            message = "Bulan Agustus Kosong";
        }else if(sep.getText().toString().isEmpty()){
            message = "Bulan September Kosong";
        }else if(okt.getText().toString().isEmpty()){
            message = "Bulan Oktober Kosong";
        }else if(nov.getText().toString().isEmpty()){
            message = "Bulan November Kosong";
        }else if (des.getText().toString().isEmpty()){
            message = "Bulan Desember Kosong";
        }
        return message;
    }


    public void Hari(){
        sJan.setText("Hari");
        sFeb.setText("Hari");
        sMar.setText("Hari");
        sApr.setText("Hari");
        sMei.setText("Hari");
        sJun.setText("Hari");
        sJul.setText("Hari");
        sAgs.setText("Hari");
        sSep.setText("Hari");
        sOkt.setText("Hari");
        sNov.setText("Hari");
        sDes.setText("Hari");
    }

    public void Kali(){
        sJan.setText("Kali");
        sFeb.setText("Kali");
        sMar.setText("Kali");
        sApr.setText("Kali");
        sMei.setText("Kali");
        sJun.setText("Kali");
        sJul.setText("Kali");
        sAgs.setText("Kali");
        sSep.setText("Kali");
        sOkt.setText("Kali");
        sNov.setText("Kali");
        sDes.setText("Kali");
    }

    public void Persen(){
        sJan.setText("%");
        sFeb.setText("%");
        sMar.setText("%");
        sApr.setText("%");
        sMei.setText("%");
        sJun.setText("%");
        sJul.setText("%");
        sAgs.setText("%");
        sSep.setText("%");
        sOkt.setText("%");
        sNov.setText("%");
        sDes.setText("%");
    }

    public void Jiwa(){
        sJan.setVisibility(View.GONE);
        sFeb.setVisibility(View.GONE);
        sMar.setVisibility(View.GONE);
        sApr.setVisibility(View.GONE);
        sMei.setVisibility(View.GONE);
        sJun.setVisibility(View.GONE);
        sJul.setVisibility(View.GONE);
        sAgs.setVisibility(View.GONE);
        sSep.setVisibility(View.GONE);
        sOkt.setVisibility(View.GONE);
        sNov.setVisibility(View.GONE);
        sDes.setVisibility(View.GONE);
    }

    private void LOGIC(String perhitungan){
        JAN = IFELSES(jan);
        FEB = IFELSES(feb);
        MAR = IFELSES(mar);
        APR = IFELSES(apr);
        MEI = IFELSES(mei);
        JUN = IFELSES(jun);
        JUL = IFELSES(jul);
        AGS = IFELSES(ags);
        SEP = IFELSES(sep);
        OKT = IFELSES(okt);
        NOV = IFELSES(nov);
        DES = IFELSES(des);
        input.setVisibility(View.GONE);
        result.setVisibility(View.VISIBLE);
    }
    private String IFELSES(EditText data){
        String done="0";
        if (data.getText().toString().isEmpty()){
            done = data.getText().toString();
        }else{
            done = data.getText().toString();
        }
        return done;
    }
}