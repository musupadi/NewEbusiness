package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool.FinancialStatementAnalysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.NumberTextWatcher;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolFNBFinancialStatementAnalysisModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FMCGFinancialStatementAnalysisActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    EditText COGS,TotalPiutang,TotalHutang,TotalPersediaan;
    TextView KebutuhanModalKerja,Plafond;
    Button Hitung;
    ImageView back,home;
    TextView dor,doi,dop;
    CardView cardView;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    TextView NilaiDOR,KeteranganDOR,NilaiDOI,KeteranganDOI,NilaiDOP,KeteranganDOP,StandarDOR,StandarDOI,StandarDOP,header;
    TextView SoalA,HasilA,SoalB,HasilB,SoalC,HasilC;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgfinancial_statement_analysis);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        Intent data = getIntent();
        final String KATEGORI = data.getStringExtra("KATEGORI");
        if (KATEGORI.equals("FOOD")){
            pList.addAll(FMCGCreditDecisionToolFNBFinancialStatementAnalysisModel.getListData());
        }else if (KATEGORI.equals("NON FOOD ROKOK")){
            pList.addAll(CreditWorthinessModel.getListData());
        }else{
            pList.addAll(CreditWorthinessModel.getListData());
        }
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


        COGS=findViewById(R.id.etCOGS);
        TotalPiutang=findViewById(R.id.etTotalPiutang);
        TotalHutang=findViewById(R.id.etHutang);
        TotalPersediaan=findViewById(R.id.etPersediaan);
        Hitung=findViewById(R.id.btnHitung);
        doi=findViewById(R.id.tvDOI);
        dor=findViewById(R.id.tvDOR);
        dop=findViewById(R.id.tvDOP);
        NilaiDOR=findViewById(R.id.tvNilaiDOR);
        KeteranganDOR=findViewById(R.id.tvKeteranganDOR);
        NilaiDOI=findViewById(R.id.tvNilaiDOI);
        KeteranganDOI=findViewById(R.id.tvKeteranganDOI);
        NilaiDOP=findViewById(R.id.tvNilaiDOP);
        KeteranganDOP=findViewById(R.id.tvKeteranganDOP);
        StandarDOR=findViewById(R.id.tvStandarDOR);
        StandarDOI=findViewById(R.id.tvStandarDOI);
        StandarDOP=findViewById(R.id.tvStandarDOP);
        cardView=findViewById(R.id.cardHasil);
        header=findViewById(R.id.tvHeader);
        SoalA=findViewById(R.id.tvSoalA);
        HasilA=findViewById(R.id.tvHasilA);
        SoalB=findViewById(R.id.tvSoalB);
        HasilB=findViewById(R.id.tvHasilB);
        SoalC=findViewById(R.id.tvSoalC);
        HasilC=findViewById(R.id.tvHasilC);
        cardView.setVisibility(View.GONE);

//        Locale localeID = new Locale("in", "ID");
//        formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        KebutuhanModalKerja=findViewById(R.id.tvKebutuhanModalKerja);
        COGS.addTextChangedListener(new NumberTextWatcher(COGS));
        TotalPiutang.addTextChangedListener(new NumberTextWatcher(TotalPiutang));
        TotalHutang.addTextChangedListener(new NumberTextWatcher(TotalHutang));
        TotalPersediaan.addTextChangedListener(new NumberTextWatcher(TotalPersediaan));

        if (KATEGORI.equals("FOOD")){
            header.setText("FMCG / Credit Decision Tool / Food & Beverage / Working Capital Credit");
        }else if (KATEGORI.equals("NON FOOD ROKOK")){
            header.setText("FMCG / Credit Decision Tool / Tobacco / Working Capital Credit");
        }else{
            header.setText("FMCG / Credit Decision Tool / Non Food & Beverage / Working Capital Credit");
        }
        Hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Checker().equals("OK")){
                    Intent goInput = new Intent(FMCGFinancialStatementAnalysisActivity.this, FMCGResultFinancialStatementAnalysisActivity.class);
                    goInput.putExtra("KATEGORI",KATEGORI);
                    goInput.putExtra("COGS",COGS.getText().toString());
                    goInput.putExtra("PIUTANG",TotalPiutang.getText().toString());
                    goInput.putExtra("HUTANG",TotalHutang.getText().toString());
                    goInput.putExtra("PERSEDIAAN",TotalPersediaan.getText().toString());
                    startActivity(goInput);
                }else{
                    Toast.makeText(FMCGFinancialStatementAnalysisActivity.this, Checker(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private String Checker(){
        String checker = " OK";
        if(COGS.getText().toString().isEmpty()){
            checker = "Silahkan Masukan COGS Per Tahun";
        }else if(TotalPiutang.getText().toString().isEmpty()){
            checker = "Silahkan Masukan Total Piutang";
        }else if(TotalHutang.getText().toString().isEmpty()){
            checker = "Silahkan Masukan Total Hutang";
        }else if(TotalPersediaan.getText().toString().isEmpty()){
            checker = "Silahkan Masukan Total Persediaan";
        }else{
            checker = "OK";
        }
        return checker;
    }
    private String AscnetChanger(String Ascnet){
        String Ascnet1 = Ascnet.replace(".","");
        return Ascnet1.replace(",","");
    }

    private void AscnetStage1(String kategori){
        cardView.setVisibility(View.VISIBLE);
        double cogs = Double.parseDouble(AscnetChanger(COGS.getText().toString()))/365;
        double TPiutang = Double.parseDouble(AscnetChanger(TotalPiutang.getText().toString()));
        double THutang = Double.parseDouble(AscnetChanger(TotalHutang.getText().toString()));
        double Persediaan = Double.parseDouble(AscnetChanger(TotalPersediaan.getText().toString()));
        AscnetStage2(TPiutang,Persediaan,THutang,cogs,kategori);
    }
    private void AscnetStage2(double piutang,double persediaan,double hutang,double cogs,String kategori){
        double DOR = piutang*365/(cogs);
        double DOI = persediaan*365/(cogs);
        double DOP = hutang*365/(cogs);
        BigDecimal bd1 = new BigDecimal(DOR).setScale(0, RoundingMode.HALF_UP);
        BigDecimal bd2 = new BigDecimal(DOI).setScale(0, RoundingMode.HALF_UP);
        BigDecimal bd3 = new BigDecimal(DOP).setScale(0, RoundingMode.HALF_UP);
        dor.setText(": "+String.valueOf(bd1)+" Hari");
        doi.setText(": "+String.valueOf(bd2)+" Hari");
        dop.setText(": "+String.valueOf(bd3)+" Hari");
        AscnetStage3(piutang,persediaan,hutang,DOR,DOI,DOP,kategori);
    }
    private void IFELSESKeterangan(TextView tv,String nilai){
        if (nilai.equals("NORMAL")){
            tv.setText("Normal");
            tv.setTextColor(Color.GREEN);
        }else{
            tv.setText("Tidak Normal");
            tv.setTextColor(Color.RED);
        }
    }
    private void AscnetStage3(double piutang,double persediaan,double hutang,double DOR,double DOI,double DOP,String kategori){
        if (kategori.equals("PRINCIPAL")){
            StandarDOR.setText("50-75");
            StandarDOI.setText("50-75");
            StandarDOP.setText("45-60");
            double Ascnet1=0.0;
            double Ascnet2=0.0;
            double Ascnet3=0.0;
            if (DOR < 50.0){
                Ascnet1=50.0;
                IFELSESKeterangan(KeteranganDOR,"TIDAK NORMAL");
            }else if(DOR > 75.0){
                Ascnet1=75.0;
                IFELSESKeterangan(KeteranganDOR,"TIDAK NORMAL");
            }else{
                Ascnet1=DOR;
                IFELSESKeterangan(KeteranganDOR,"NORMAL");
            }
            if (DOI < 50.0){
                Ascnet2=50.0;
                IFELSESKeterangan(KeteranganDOI,"TIDAK NORMAL");
            }else if(DOI > 75.0){
                Ascnet2=75.0;
                IFELSESKeterangan(KeteranganDOI,"TIDAK NORMAL");
            }else{
                Ascnet2=DOI;
                IFELSESKeterangan(KeteranganDOI,"NORMAL");
            }
            if (DOP < 45.0){
                Ascnet3=45.0;
                IFELSESKeterangan(KeteranganDOP,"TIDAK NORMAL");
            }else if(DOP > 60){
                Ascnet3=60.0;
                IFELSESKeterangan(KeteranganDOP,"TIDAK NORMAL");
            }else{
                Ascnet3=DOP;
                IFELSESKeterangan(KeteranganDOP,"NORMAL");
            }
            NilaiDOR.setText(String.valueOf(method.BatasanDouble2(Ascnet1)));
            NilaiDOI.setText(String.valueOf(method.BatasanDouble2(Ascnet2)));
            NilaiDOP.setText(String.valueOf(method.BatasanDouble2(Ascnet3)));
            SoalA.setText(String.valueOf(method.BatasanDouble2(Ascnet1))+" X "+String.valueOf(method.MagicRP(piutang)));
            SoalB.setText(String.valueOf(method.BatasanDouble2(Ascnet2))+" X "+String.valueOf(method.MagicRP(persediaan)));
            SoalC.setText(String.valueOf(method.BatasanDouble2(Ascnet3))+" X "+String.valueOf(method.MagicRP(hutang)));
            HasilA.setText(": "+String.valueOf(method.MagicRP(Ascnet1*piutang)));
            HasilB.setText(": "+String.valueOf(method.MagicRP(Ascnet2*persediaan)));
            HasilC.setText(": "+String.valueOf(method.MagicRP(Ascnet3*hutang)));
            AscnetStage4(Ascnet1*piutang,Ascnet2*persediaan,Ascnet3*hutang);
        }else if(kategori.equals("DISTRIBUTOR")){
            StandarDOR.setText("45-60");
            StandarDOI.setText("45-60");
            StandarDOP.setText("45-65");
            double Ascnet1=0.0;
            double Ascnet2=0.0;
            double Ascnet3=0.0;
            if (DOR < 45.0){
                Ascnet1=45.0;
                IFELSESKeterangan(KeteranganDOR,"TIDAK NORMAL");
            }else if(DOR > 60.0){
                Ascnet1=60.0;
                IFELSESKeterangan(KeteranganDOR,"TIDAK NORMAL");
            }else{
                Ascnet1=DOR;
                IFELSESKeterangan(KeteranganDOR,"NORMAL");
            }
            if (DOI < 45.0){
                Ascnet2=45.0;
                IFELSESKeterangan(KeteranganDOI,"TIDAK NORMAL");
            }else if(DOI > 60.0){
                Ascnet2=60.0;
                IFELSESKeterangan(KeteranganDOI,"TIDAK NORMAL");
            }else{
                Ascnet2=DOI;
                IFELSESKeterangan(KeteranganDOI,"NORMAL");
            }
            if (DOP < 45.0){
                Ascnet3=45.0;
                IFELSESKeterangan(KeteranganDOP,"TIDAK NORMAL");
            }else if(DOP > 65){
                Ascnet3=65.0;
                IFELSESKeterangan(KeteranganDOP,"TIDAK NORMAL");
            }else{
                Ascnet3=DOP;
                IFELSESKeterangan(KeteranganDOP,"NORMAL");
            }
            NilaiDOR.setText(String.valueOf(method.BatasanDouble2(Ascnet1)));
            NilaiDOI.setText(String.valueOf(method.BatasanDouble2(Ascnet2)));
            NilaiDOP.setText(String.valueOf(method.BatasanDouble2(Ascnet3)));
            SoalA.setText(String.valueOf(method.BatasanDouble2(Ascnet1))+" X "+String.valueOf(method.MagicRP(piutang)));
            SoalB.setText(String.valueOf(method.BatasanDouble2(Ascnet2))+" X "+String.valueOf(method.MagicRP(persediaan)));
            SoalC.setText(String.valueOf(method.BatasanDouble2(Ascnet3))+" X "+String.valueOf(method.MagicRP(hutang)));
            HasilA.setText(": "+String.valueOf(method.MagicRP(Ascnet1*piutang)));
            HasilB.setText(": "+String.valueOf(method.MagicRP(Ascnet2*persediaan)));
            HasilC.setText(": "+String.valueOf(method.MagicRP(Ascnet3*hutang)));
            AscnetStage4(Ascnet1*piutang,Ascnet2*persediaan,Ascnet3*hutang);
        }

    }
    private void AscnetStage4(double a,double b,double c){
        double TotalAscnet = a+b-c;
        KebutuhanModalKerja.setText(": "+method.MagicRP(TotalAscnet));
    }
}