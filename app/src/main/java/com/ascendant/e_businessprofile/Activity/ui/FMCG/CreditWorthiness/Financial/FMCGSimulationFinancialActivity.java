package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.Financial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessFNBFinancialModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessNFNBFinancialModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGSimulationFinancialActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    Button CR,DTE,DTEBDTA,DSCR,KasBersih,SalesGrowth,SalesGrowthMargin,BebanUsaha,LabaSebelum,LabaTahunBerjalan,Ekuitas,Liabilitas,Asset,GrossProfitMargin;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgsimulation_financial);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
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


        CR = findViewById(R.id.btnCurrentRatio);
        DTE = findViewById(R.id.btnDebtToEquity);
        DTEBDTA = findViewById(R.id.btnDebtToEBTDA);
        DSCR = findViewById(R.id.btnDebtServiceCoverageRatio);
        KasBersih = findViewById(R.id.btnKasBersih);
        SalesGrowth = findViewById(R.id.btnSalesGrowth);
        SalesGrowthMargin = findViewById(R.id.btnSalesGrossMargin);
        BebanUsaha = findViewById(R.id.btnBebanUsaha);
        LabaSebelum = findViewById(R.id.btnLabaSebelumPajak);
        LabaTahunBerjalan = findViewById(R.id.btnLabaTahunBerjalan);
        GrossProfitMargin = findViewById(R.id.btnGrossProfitMargin);
        Ekuitas = findViewById(R.id.btnEkuitas);
        Liabilitas = findViewById(R.id.btnLiabilitas);
        Asset = findViewById(R.id.btnAset);
        Intent data = getIntent();
        final String KATEGORI = data.getStringExtra("KATEGORI");
        if (KATEGORI.equals("fnb")){
            pList.addAll(FMCGCreditWorthinessFNBFinancialModel.getListData());
        }else if (KATEGORI.equals("non fnb")){
            pList.addAll(FMCGCreditWorthinessNFNBFinancialModel.getListData());
        }else{
            pList.addAll(CreditWorthinessModel.getListData());
        }
        CR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","CURRENT RATIO");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        GrossProfitMargin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","GROSS PROFIT MARGIN");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        DTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","DEBT TO EQUITY");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        DTEBDTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","DEBT TO EBITDA");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        DSCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","DEBT SERVICE COVERAGE RATIO");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        KasBersih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","KAS BERSIH");
                goInput.putExtra("KATEGORI","DISTRIBUTOR");
                startActivity(goInput);
            }
        });
        SalesGrowth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","SALES GROWTH");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        SalesGrowthMargin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","SALES GROWTH MARGIN");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        BebanUsaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","BEBAN USAHA THP LABA BRUTO");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        LabaSebelum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","LABA SEBELUM PAJAK THP PENDAPATAN");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        LabaTahunBerjalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","LABA TAHUN BERJALAN THP PENDAPATAN");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        Ekuitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","EKUITAS");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        Liabilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","LIABILITAS");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        Asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGSimulationFinancialActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","ASET");
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
    }
}