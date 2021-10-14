package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool.FinancialStatementAnalysis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.OldRetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FMCGResultFinancialStatementAnalysisActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    TextView TittleKategori,KebutuhanTotal,KebutuhanPlafond;
    TextView KategoriDOR,KategoriDOI,KategoriDOP;
    TextView NilaiDOR,NilaiDOI,NilaiDOP;
    TextView KeteranganDOR,KeteranganDOI,KeteranganDOP;
    TextView KebutuhanDOR,KebutuhanDOI,KebutuhanDOP;
    Ascendant method = new Ascendant();
    Button kembali;
    String batasAtasDOR,batasAtasDOI,batasAtasDOP;
    String batasBawahDOR,batasBawahDOI,batasBawahDOP;
    LinearLayout failed;
    ScrollView DATA;
    LottieAnimationView ConnectionFailed,Loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgresult_financial_statement_analysis);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        Intent data = getIntent();
        final String COGS = data.getStringExtra("COGS");
        final String PIUTANG = data.getStringExtra("PIUTANG");
        final String HUTANG = data.getStringExtra("HUTANG");
        final String PERSEDIAAN = data.getStringExtra("PERSEDIAAN");
        final String KATEGORI = data.getStringExtra("KATEGORI");
        if (KATEGORI.equals("FOOD")){
            pList.addAll(CreditWorthinessModel.getListData());
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

        TittleKategori=findViewById(R.id.tvHeader);
        KategoriDOR=findViewById(R.id.tvTittleKategoriDOR);
        KategoriDOI=findViewById(R.id.tvTittleKategoriDOI);
        KategoriDOP=findViewById(R.id.tvTittleKategoriDOP);
        NilaiDOR=findViewById(R.id.tvNilaiDOR);
        NilaiDOI=findViewById(R.id.tvNilaiDOI);
        NilaiDOP=findViewById(R.id.tvNilaiDOP);
        KeteranganDOR=findViewById(R.id.tvKeteranganDOR);
        KeteranganDOI=findViewById(R.id.tvKeteranganDOI);
        KeteranganDOP=findViewById(R.id.tvKeteranganDOP);
        KebutuhanDOR=findViewById(R.id.tvKebutuhanDOR);
        KebutuhanDOI=findViewById(R.id.tvKebutuhanDOI);
        KebutuhanDOP=findViewById(R.id.tvKebutuhanDOP);
        KebutuhanTotal=findViewById(R.id.tvKebutuhanTotal);
        KebutuhanPlafond=findViewById(R.id.tvKebutuhanPlafond);
        kembali=findViewById(R.id.btnKembali);
        ConnectionFailed=findViewById(R.id.ConnectionFailed);
        DATA=findViewById(R.id.linearData);
        failed=findViewById(R.id.linearFailed);
        Loading=findViewById(R.id.Loading);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DATA.setAlpha(0.1f);
        failed.setVisibility(View.GONE);
        ConnectionFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOGIC(KATEGORI,COGS,PIUTANG,PERSEDIAAN,HUTANG);
            }
        });
        LOGIC(KATEGORI,COGS,PIUTANG,PERSEDIAAN,HUTANG);

//        if (KATEGORI.equals("FOOD")){
//            TittleKategori.setText("F & B");
//            batasAtasDOR = "50";
//            batasAtasDOI = "65";
//            batasAtasDOP = "75";
//            batasBawahDOR = "45";
//            batasBawahDOI = "45";
//            batasBawahDOP = "45";
//            KategoriDOR.setText(batasBawahDOR+"-"+batasAtasDOR);
//            KategoriDOI.setText(batasBawahDOI+"-"+batasAtasDOI);
//            KategoriDOP.setText(batasBawahDOP+"-"+batasAtasDOP);
//            double dor = (Double.parseDouble(method.MagicChange(PIUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            double doi = (Double.parseDouble(method.MagicChange(PERSEDIAAN))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            double dop = (Double.parseDouble(method.MagicChange(HUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            NilaiDOR.setText(method.PembulatanHari(String.valueOf(dor))+" Hari");
//            NilaiDOI.setText(method.PembulatanHari(String.valueOf(doi))+" Hari");
//            NilaiDOP.setText(method.PembulatanHari(String.valueOf(dop))+" Hari");
//            KebutuhanDOR.setText(method.MagicRP(Double.parseDouble(String.valueOf(dor*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            KebutuhanDOI.setText(method.MagicRP(Double.parseDouble(String.valueOf(doi*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            KebutuhanDOP.setText(method.MagicRP(Double.parseDouble(String.valueOf(dop*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            double KDOR = Double.parseDouble(method.MagicChange(KebutuhanDOR.getText().toString()));
//            double KDOI = Double.parseDouble(method.MagicChange(KebutuhanDOI.getText().toString()));
//            double KDOP = Double.parseDouble(method.MagicChange(KebutuhanDOP.getText().toString()));
//            double total = KDOR+KDOI-KDOP;
//            KebutuhanTotal.setText(method.MagicRP(total));
//            KebutuhanPlafond.setText(method.MagicRP(Double.parseDouble(String.valueOf(Double.parseDouble(String.valueOf(method.MagicChange(KebutuhanTotal.getText().toString())))*0.70))));
//            if (dor>=45 && dor<=60){
//                KeteranganDOR.setText("Normal");
//            }else{
//                KeteranganDOR.setText("Tidak Normal");
//            }
//            if(doi>=45 && dor<=60){
//                KeteranganDOI.setText("Normal");
//            }else{
//                KeteranganDOI.setText("Tidak Normal");
//            }
//            if(dop>=45 && dop<=60){
//                KeteranganDOP.setText("Normal");
//            }else{
//                KeteranganDOP.setText("Tidak Norma");
//            }
//        }else if(KATEGORI.equals("NON FOOD ROKOK")){
//            TittleKategori.setText("TOBACCO");
//            batasAtasDOR = "60";
//            batasAtasDOI = "60";
//            batasAtasDOP = "65";
//            batasBawahDOR = "55";
//            batasBawahDOI = "55";
//            batasBawahDOP = "60";
//            KategoriDOR.setText(batasBawahDOR+"-"+batasAtasDOR);
//            KategoriDOI.setText(batasBawahDOI+"-"+batasAtasDOI);
//            KategoriDOP.setText(batasBawahDOP+"-"+batasAtasDOP);
//            double dor = (Double.parseDouble(method.MagicChange(PIUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            double doi = (Double.parseDouble(method.MagicChange(PERSEDIAAN))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            double dop = (Double.parseDouble(method.MagicChange(HUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            NilaiDOR.setText(method.PembulatanHari(String.valueOf(dor))+" Hari");
//            NilaiDOI.setText(method.PembulatanHari(String.valueOf(doi))+" Hari");
//            NilaiDOP.setText(method.PembulatanHari(String.valueOf(dop))+" Hari");
//            KebutuhanDOR.setText(method.MagicRP(Double.parseDouble(String.valueOf(dor*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            KebutuhanDOI.setText(method.MagicRP(Double.parseDouble(String.valueOf(doi*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            KebutuhanDOP.setText(method.MagicRP(Double.parseDouble(String.valueOf(dop*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            double KDOR = Double.parseDouble(method.MagicChange(KebutuhanDOR.getText().toString()));
//            double KDOI = Double.parseDouble(method.MagicChange(KebutuhanDOI.getText().toString()));
//            double KDOP = Double.parseDouble(method.MagicChange(KebutuhanDOP.getText().toString()));
//            double total = KDOR+KDOI+KDOP;
//            KebutuhanTotal.setText(method.MagicRP(total));
//            KebutuhanPlafond.setText(method.MagicRP(Double.parseDouble(String.valueOf(Double.parseDouble(String.valueOf(method.MagicChange(KebutuhanTotal.getText().toString())))*0.70))));
//            if (dor>=45 && dor<=60){
//                KeteranganDOR.setText("Normal");
//            }else{
//                KeteranganDOR.setText("Tidak Normal");
//            }
//            if(doi>=45 && dor<=60){
//                KeteranganDOI.setText("Normal");
//            }else{
//                KeteranganDOI.setText("Tidak Normal");
//            }
//            if(dop>=45 && dop<=60){
//                KeteranganDOP.setText("Normal");
//            }else{
//                KeteranganDOP.setText("Tidak Norma");
//            }
//        }else{
//            TittleKategori.setText("Non F & B");
//            batasAtasDOR = "70";
//            batasAtasDOI = "70";
//            batasAtasDOP = "95";
//            batasBawahDOR = "50";
//            batasBawahDOI = "50";
//            batasBawahDOP = "85";
//            KategoriDOR.setText(batasBawahDOR+"-"+batasAtasDOR);
//            KategoriDOI.setText(batasBawahDOI+"-"+batasAtasDOI);
//            KategoriDOP.setText(batasBawahDOP+"-"+batasAtasDOP);
//            double dor = (Double.parseDouble(method.MagicChange(PIUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            double doi = (Double.parseDouble(method.MagicChange(PERSEDIAAN))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            double dop = (Double.parseDouble(method.MagicChange(HUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));
//            NilaiDOR.setText(method.PembulatanHari(String.valueOf(dor))+" Hari");
//            NilaiDOI.setText(method.PembulatanHari(String.valueOf(doi))+" Hari");
//            NilaiDOP.setText(method.PembulatanHari(String.valueOf(dop))+" Hari");
//            KebutuhanDOR.setText(method.MagicRP(Double.parseDouble(String.valueOf(dor*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            KebutuhanDOI.setText(method.MagicRP(Double.parseDouble(String.valueOf(doi*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            KebutuhanDOP.setText(method.MagicRP(Double.parseDouble(String.valueOf(dop*(Double.parseDouble(method.MagicChange(COGS))/365)))));
//            double KDOR = Double.parseDouble(method.MagicChange(KebutuhanDOR.getText().toString()));
//            double KDOI = Double.parseDouble(method.MagicChange(KebutuhanDOI.getText().toString()));
//            double KDOP = Double.parseDouble(method.MagicChange(KebutuhanDOP.getText().toString()));
//            double total = KDOR+KDOI+KDOP;
//            KebutuhanTotal.setText(method.MagicRP(total));
//            KebutuhanPlafond.setText(method.MagicRP(Double.parseDouble(String.valueOf(Double.parseDouble(String.valueOf(method.MagicChange(KebutuhanTotal.getText().toString())))*0.70))));
//            if (dor>=45 && dor<=60){
//                KeteranganDOR.setText("Normal");
//            }else{
//                KeteranganDOR.setText("Tidak Normal");
//            }
//            if(doi>=45 && dor<=60){
//                KeteranganDOI.setText("Normal");
//            }else{
//                KeteranganDOI.setText("Tidak Normal");
//            }
//            if(dop>=45 && dop<=60){
//                KeteranganDOP.setText("Normal");
//            }else{
//                KeteranganDOP.setText("Tidak Norma");
//            }
//        }
    }
    private void LOGIC(String kategori,final String COGS,final String PIUTANG,final String PERSEDIAAN,final String HUTANG){
        DATA.setAlpha(0.1f);
        failed.setVisibility(View.GONE);
        Loading.setVisibility(View.VISIBLE);
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> Rumus;
        if (kategori.equals("FOOD")){
            TittleKategori.setText("F & B");
            Rumus=api.KMK2(method.AUTH(),"FABAJakartaIndonesia2019kunci","Ricardo","food");
        }else if (kategori.equals("NON FOOD ROKOK")){
            TittleKategori.setText("TOBACCO");
            Rumus=api.KMK2(method.AUTH(),"FABAJakartaIndonesia2019kunci","Millos","tobacco");
        }else{
            TittleKategori.setText("Non F & B");
            Rumus=api.KMK2(method.AUTH(),"FABAJakartaIndonesia2019kunci","DJ Soda","non food");
        }
        Rumus.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                DATA.setAlpha(1f);
                Loading.setVisibility(View.GONE);
                batasBawahDOR = response.body().getDor_bawah();
                batasAtasDOR = response.body().getDor_atas();
                batasBawahDOI = response.body().getDoi_bawah();
                batasAtasDOI = response.body().getDoi_atas();
                batasBawahDOP = response.body().getDop_bawah();
                batasAtasDOP = response.body().getDop_atas();
                KategoriDOR.setText(batasBawahDOR+"-"+batasAtasDOR);
                KategoriDOI.setText(batasBawahDOI+"-"+batasAtasDOI);
                KategoriDOP.setText(batasBawahDOP+"-"+batasAtasDOP);
                double dor = (Double.parseDouble(method.MagicChange(PIUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));
                double doi = (Double.parseDouble(method.MagicChange(PERSEDIAAN))*365.0)/Double.parseDouble(method.MagicChange(COGS));
                double dop = (Double.parseDouble(method.MagicChange(HUTANG))*365.0)/Double.parseDouble(method.MagicChange(COGS));

                NilaiDOR.setText(String.valueOf(method.PembulatanHari(String.valueOf(dor)))+" Hari");
                NilaiDOI.setText(String.valueOf(method.PembulatanHari(String.valueOf(doi)))+" Hari");
                NilaiDOP.setText(String.valueOf(method.PembulatanHari(String.valueOf(dop)))+" Hari");
                //DOR
                double hDOR;
                double hDOI;
                double hDOP;
                if(dor > Double.parseDouble(batasAtasDOR)){
                    hDOR=Double.parseDouble(batasAtasDOR);
                    KebutuhanDOR.setText(method.MagicRP(Double.parseDouble(String.valueOf(Double.parseDouble(batasAtasDOR)*(Double.parseDouble(method.MagicChange(COGS))/365)))));
                }else{
                    hDOR = dor;
                    KebutuhanDOR.setText(method.MagicRP(Double.parseDouble(String.valueOf(dor*(Double.parseDouble(method.MagicChange(COGS))/365)))));
                }
                //DOI
                if(doi > Double.parseDouble(batasAtasDOI)){
                    hDOI=Double.parseDouble(batasAtasDOI);
                    KebutuhanDOI.setText(method.MagicRP(Double.parseDouble(String.valueOf(Double.parseDouble(batasAtasDOI)*(Double.parseDouble(method.MagicChange(COGS))/365)))));
                }else{
                    hDOI= doi;
                    KebutuhanDOI.setText(method.MagicRP(Double.parseDouble(String.valueOf(doi*(Double.parseDouble(method.MagicChange(COGS))/365)))));
                }
                //DOP
                if (dop < Double.parseDouble(batasBawahDOP)){
                    hDOP = Double.parseDouble(batasBawahDOP);
                    KebutuhanDOP.setText(method.MagicRP(Double.parseDouble(String.valueOf(Double.parseDouble(batasBawahDOP)*(Double.parseDouble(method.MagicChange(COGS))/365)))));
                }else{
                    hDOP = dop;
                    KebutuhanDOP.setText(method.MagicRP(Double.parseDouble(String.valueOf(dop*(Double.parseDouble(method.MagicChange(COGS))/365)))));
                }
                double KDOR = Double.parseDouble(method.MagicChange(KebutuhanDOR.getText().toString()));
                double KDOI = Double.parseDouble(method.MagicChange(KebutuhanDOI.getText().toString()));
                double KDOP = Double.parseDouble(method.MagicChange(KebutuhanDOP.getText().toString()));
                //double total = KDOR+KDOI+KDOP;
                double total = Double.parseDouble(method.MagicChange(PIUTANG))+Double.parseDouble(method.MagicChange(PERSEDIAAN))-Double.parseDouble(method.MagicChange(HUTANG));
                KebutuhanTotal.setText(method.MagicRP(total));
                KebutuhanPlafond.setText(method.MagicRP
                        (Double.parseDouble(
                                String.valueOf(
                                        Double.parseDouble(
                                                String.valueOf(
                                                        method.MagicChange(
                                                                KebutuhanTotal.getText().toString()
                                                        )
                                                )
                                        )*0.70
                                )
                                )
                        )
                );
                if (hDOR>=Double.parseDouble(batasBawahDOR) && hDOR<=Double.parseDouble(batasAtasDOR)){
                    KeteranganDOR.setText("Normal");
                }else{
                    KeteranganDOR.setText("Tidak Normal");
                }
                if(hDOI>=Double.parseDouble(batasBawahDOI) && hDOI<=Double.parseDouble(batasAtasDOI)){
                    KeteranganDOI.setText("Normal");
                }else{
                    KeteranganDOI.setText("Tidak Normal");
                }
                if(hDOP>=Double.parseDouble(batasBawahDOP) && hDOP<=Double.parseDouble(batasAtasDOP)){
                    KeteranganDOP.setText("Normal");
                }else{
                    KeteranganDOP.setText("Tidak Norma");
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                failed.setVisibility(View.VISIBLE);
                DATA.setAlpha(0.1f);
            }
        });

    }
}
