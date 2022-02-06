package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.Financial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.OldRetroServer;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.NumberTextWatcher;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus.ResponseKMK;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FMCGCreditWorthinessHitunganActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    EditText num1,num2,num3;
    TextInputLayout ti1,ti2,ti3;
    Button Hitung;
    TextView hasil,tittle,header,value,penjelasan,hasilTotal,range,batasBawah,batasAtas,nama;
    CardView cardHasil;
    double nomor1,nomor2,nomor3;
    ImageView back,home;
    ImageView rumus;
    LinearLayout hasils;
    DB_Helper dbHelper;
    String Token;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgcredit_worthiness_hitungan);



        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(CreditWorthinessModel.getListData());
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


        num1=findViewById(R.id.etNomor1);
        num2=findViewById(R.id.etNomor2);
        num3=findViewById(R.id.etNomor3);
        ti1=findViewById(R.id.tiNomor1);
        ti2=findViewById(R.id.tiNomor2);
        ti3=findViewById(R.id.tiNomor3);
        Hitung=findViewById(R.id.btnHitung);
        hasil=findViewById(R.id.tvHasil);
        cardHasil=findViewById(R.id.cardHasil);
        tittle=findViewById(R.id.tvTittle);
        rumus=findViewById(R.id.ivRumus);
        value=findViewById(R.id.tvValue);
        penjelasan=findViewById(R.id.tvPenjelasan);
        back=findViewById(R.id.ivBack);
        home=findViewById(R.id.ivHome);
        hasilTotal=findViewById(R.id.tvHasilTotal);
        range=findViewById(R.id.tvRange);
        batasBawah=findViewById(R.id.tvBatasBawah);
        batasAtas=findViewById(R.id.tvBatasAtas);
        hasils=findViewById(R.id.linearHasils);
        nama=findViewById(R.id.tvNama);
        num1.addTextChangedListener(new NumberTextWatcher(num1));
        num2.addTextChangedListener(new NumberTextWatcher(num2));
        num3.addTextChangedListener(new NumberTextWatcher(num3));
        hasils.setVisibility(View.GONE);
        Intent data = getIntent();
        String HITUNG = data.getStringExtra("HITUNG"); //NAMA
        String KATEGORI = data.getStringExtra("KATEGORI"); // fnb
        if (KATEGORI.equals("non fnb")){
            getRumus(HITUNG.toLowerCase(),"non food",KATEGORI);
        }else if(KATEGORI.equals("fnb")){
            getRumus(HITUNG.toLowerCase(),"food",KATEGORI);
        }else{
            getRumus(HITUNG.toLowerCase(),"tobacco",KATEGORI);
        }

    }
    private void getImage(String url){
        Glide.with(FMCGCreditWorthinessHitunganActivity.this)
                .load(url)
                .into(rumus);
    }
    private void getRumus(final String param,String kategori,final String KATEGORI){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseKMK> Rumus =api.KMKV2(method.AUTH(),
                Token,
                param,
                kategori);
        Rumus.enqueue(new Callback<ResponseKMK>() {
            @Override
            public void onResponse(Call<ResponseKMK> call, Response<ResponseKMK> response) {
                batasAtas.setText(String.valueOf(response.body().data.range_atas));
                batasBawah.setText(String.valueOf(response.body().data.range_bawah));


                getImage(response.body().data.data.getRumus_param());
                if (KATEGORI.equals("non fnb")){
                    nonfood(param.toUpperCase(),response.body().data.data.getPenjelasan_param(),response.body().data.data.getPenjelasan2_param());
                }else if(KATEGORI.equals("fnb")){
                    nonfood(param.toUpperCase(),response.body().data.data.getPenjelasan_param(),response.body().data.data.getPenjelasan2_param());
                }else{
                    nonfood(param.toUpperCase(),response.body().data.data.getPenjelasan_param(),response.body().data.data.getPenjelasan2_param());
                }
            }

            @Override
            public void onFailure(Call<ResponseKMK> call, Throwable t) {
                Log.d("Zyarga",t.toString());
//                Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String Checker2(){
        String checker = "OK";
        if (num1.getText().toString().isEmpty() || num2.getText().toString().isEmpty()){
            checker="Harap Isi Semua";
        }
        return checker;
    }
    private String Checker3(){
        String checker = "OK";
        if (num1.getText().toString().isEmpty() || num2.getText().toString().isEmpty() || num3.getText().toString().isEmpty()){
            checker="Harap Isi Semua";
        }
        return checker;
    }
    //NON FOOD
    private void nonfood(String hitung,String penjelasan1,String penjelasan2){
        if (hitung.equals("CURRENT RATIO")){
            num3.setVisibility(View.GONE);
            tittle.setText("Current Ratio");
            ti1.setHint("Aktiva Lancar");
            ti2.setHint("Hutang Lancar");
            nama.setText("Current Ratio");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Current Ratio Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(method.BatasanDouble(
                    Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("GROSS PROFIT MARGIN")){
            num3.setVisibility(View.GONE);
            tittle.setText("Gross Profit Margin");
            ti1.setHint("Laba Kotor (Gross Profit)");
            ti2.setHint("Pendapatan Penjualan");
            penjelasan.setText(penjelasan1);
            nama.setText("Gross Profit Margin");
            value.setText(penjelasan2);
            range.setText("Gross Profit Margin Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if (hitung.equals("DEBT TO EQUITY")){
            num3.setVisibility(View.GONE);
            tittle.setText("Debt To Equity");
            ti1.setHint("Total Hutang");
            ti2.setHint("Total Equitas");
            nama.setText("Debt To Equity");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Debt To Equity Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" X");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" X");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("DEBT TO EBITDA")){
            tittle.setText("Debt to EBITDA");
            num3.setVisibility(View.GONE);
            ti1.setHint("Total Hutang");
            ti2.setHint("EBITDA");
            nama.setText("Debt to EBITDA");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Debt to EBITDA Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" X");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" X");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("DEBT SERVICE COVERAGE RATIO")){
            tittle.setText("Debt Service Coverage Ratio");
            ti1.setHint("Laba Sebelum Pajak");
            ti2.setHint("Hutang Bank");
            ti3.setHint("Beban Bunga");
            nama.setText("Debt Service Coverage Ratio");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Debt Service Coverage Ratio Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" X");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker3().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" X");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker3(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("KAS BERSIH")){
            tittle.setText("Kas Bersih Dari Operasi");
            num3.setVisibility(View.GONE);
            ti1.setHint("Kas Bersih dari Operasi");
            ti2.setHint("Beban Pokok Penjualan");
            nama.setText("Kas Bersih Dari Operasi");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Kas Bersih Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("SALES GROWTH")){
            tittle.setText("Sales Growth");
            num3.setVisibility(View.GONE);
            ti1.setHint("Pendapatan");
            ti2.setHint("Pendapatan Tahun Sebelumnya");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            nama.setText("Sales Growth");
            range.setText("Kas Bersih Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("SALES GOWTH MARGIN")){
            tittle.setText("Sales Gross Margin");
            num3.setVisibility(View.GONE);
            ti1.setHint("Laba Bruto");
            ti2.setHint("Pendapatan");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            nama.setText("Sales Gross Margin");
            range.setText("Sales Gross Margin Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("BEBAN USAHA THP LABA BRUTO")){
            tittle.setText("Beban Usaha Terhadap Laba Bruto");
            num3.setVisibility(View.GONE);
            ti1.setHint("Beban Usaha");
            ti2.setHint("Laba Bruto");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            nama.setText("Beban Usaha Terhadap Laba Bruto");
            range.setText("Beban Usaha Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("LABA SEBELUM PAJAK THP PENDAPATAN")){
            tittle.setText("Laba Sebelum Pajak Terhadap Pendapatan");
            num3.setVisibility(View.GONE);
            ti1.setHint("Laba Sebelum Pajak");
            ti2.setHint("Pendapatan");
            nama.setText("Laba Sebelum Pajak Yerhadap Pendapatan");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Beban Usaha Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("LABA TAHUN BERJALAN THP PENDAPATAN")){
            tittle.setText("Laba Tahun Berjalan Terhadap Pendapatan");
            num3.setVisibility(View.GONE);
            ti1.setHint("Laba Tahun Berjalan");
            ti2.setHint("Pendapatan");
            nama.setText("Laba Tahun Berjalan Terhadap Pendapatan");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Laba Tahun Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" %");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" %");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("EKUITAS")){
            tittle.setText("Ekuitas");
            num3.setVisibility(View.GONE);
            ti1.setHint("Total Asset");
            ti2.setHint("Total (Hutang)");
            nama.setText("Ekuitas");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Ekuitas Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" Rp(Juta)");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)-MagicChange(num2);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" Rp(Juta)");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("LIABILITAS")){
            tittle.setText("Liabilitas");
            num3.setVisibility(View.GONE);
            ti1.setHint("Asset");
            ti2.setHint("Equitas");
            nama.setText("Liabilitas");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Liabilitas Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" Rp(Juta)");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)-MagicChange(num2);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" Rp(Juta)");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("ASET")) {
            tittle.setText("Asset");
            num3.setVisibility(View.GONE);
            ti1.setHint("Hutang");
            ti2.setHint("Modal");
            nama.setText("Asset");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Aset Dikatakan Baik apabila berada dalam Range " +
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString()))) +
                    " - " +String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" Rp(Juta)");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")) {
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1) + MagicChange(num2);
                        hasil.setText(": " + String.valueOf(method.BatasanDouble(total))+" Rp(Juta)");
                        if (total >= Double.parseDouble(batasBawah.getText().toString()) && total <= Double.parseDouble(batasAtas.getText().toString())) {
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        } else {
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    } else {
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("DOR")){
            tittle.setText("Days of Receivable (DOR)");
            num3.setVisibility(View.GONE);
            ti1.setHint("Piutang Dagang pada akhir periode (Rp)");
            ti2.setHint("Harga Pokok Penjualan Setahun (Rp)");
            nama.setText("DOR");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("DOR Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" Hari");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" Hari");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("DOI")){
            tittle.setText("Days of Inventory (DOI)");
            num3.setVisibility(View.GONE);
            ti1.setHint("Nilai Persediaan Barang di akhir periode (Rp)");
            ti2.setHint("Harga Pokok Penjualan Setahun (Rp)");
            penjelasan.setText(penjelasan1);
            nama.setText("DOI");
            value.setText(penjelasan2);
            range.setText("DOI Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" Hari");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" Hari");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("DOP")){
            tittle.setText("Days of Payable (DOP)");
            num3.setVisibility(View.GONE);
            ti1.setHint("Hutang Dagang");
            ti2.setHint("Beban Pokok Penjualan");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            nama.setText("DOP");
            range.setText("DOP Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" Hari");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker2().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/(MagicChange(num2)/365);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" Hari");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker2(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("ASSET TURNOVER")){
            tittle.setText("Asset Turnover");
            ti1.setHint("Pendapatan");
            ti2.setHint("Total Asset");
            nama.setText("Asset Turnover");
            ti3.setHint("Total Asset Tahun Sebelumnya");
            penjelasan.setText(penjelasan1);
            value.setText(penjelasan2);
            range.setText("Asset Turnover Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" X");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker3().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/((MagicChange(num2)+MagicChange(num3))/2.0);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" X");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker3(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(hitung.equals("INVENTORY TURNOVER")){
            tittle.setText("Inventory Turnover");
            ti1.setHint("Pendapatan");
            ti2.setHint("Persediaan Barang");
            ti3.setHint("Persediaan Barang Tahun Sebelumnya");
            penjelasan.setText(penjelasan1);
            nama.setText("Inventory Turnover");
            value.setText(penjelasan2);
            range.setText("Inventory Turnover Dikatakan Baik apabila berada dalam Range "+
                    String.valueOf(method.BatasanDouble(Double.parseDouble(batasBawah.getText().toString())))+
                    " - "+String.valueOf(
                    method.BatasanDouble(Double.parseDouble(batasAtas.getText().toString())))+" X");
            Hitung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Checker3().equals("OK")){
                        hasils.setVisibility(View.VISIBLE);
                        double total = MagicChange(num1)/((MagicChange(num2)+MagicChange(num3))/2.0);
                        hasil.setText(": "+String.valueOf(method.BatasanDouble(total))+" X");
                        if (total >=  Double.parseDouble(batasBawah.getText().toString()) &&total <= Double.parseDouble(batasAtas.getText().toString())){
                            hasilTotal.setText(": Bagus");
                            hasilTotal.setTextColor(Color.GREEN);
                        }else{
                            hasilTotal.setText(": Tidak Bagus");
                            hasilTotal.setTextColor(Color.RED);
                        }
                    }else{
                        Toast.makeText(FMCGCreditWorthinessHitunganActivity.this, Checker3(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private Double MagicChange(TextView tv){
        String Snum1 = tv.getText().toString().replace(".","");
        nomor1 = Double.parseDouble(Snum1.replace(",",""));
        return nomor1;
    }
}