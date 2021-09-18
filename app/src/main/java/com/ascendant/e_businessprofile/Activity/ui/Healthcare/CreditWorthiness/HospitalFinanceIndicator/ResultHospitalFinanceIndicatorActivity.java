package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalFinanceIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.HospitalFinanceIndicatorModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class ResultHospitalFinanceIndicatorActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();


    CardView card1,card2,card3;
    TextView tvBobotROI1,tvSkorROI1,tvBobotROI2,tvSkorROI2,tvBobotROI3,tvSkorROI3;
    TextView tvBobotCashRatio1,tvSkorCashRatio1,tvBobotCashRatio2,tvSkorCashRatio2,tvBobotCashRatio3,tvSkorCashRatio3;
    TextView tvBobotCurrentRatio1,tvSkorCurrentRatio1,tvBobotCurrentRatio2,tvSkorCurrentRatio2,tvBobotCurrentRatio3,tvSkorCurrentRatio3;
    TextView tvBobotCP1,tvSkorCP1,tvBobotCP2,tvSkorCP2,tvBobotCP3,tvSkorCP3;
    TextView tvBobotPP1,tvSkorPP1,tvBobotPP2,tvSkorPP2,tvBobotPP3,tvSkorPP3;
    TextView tvBobotPerputaranAset1,tvSkorPerputaranAset1,tvBobotPerputaranAset2,tvSkorPerputaranAset2,tvBobotPerputaranAset3,tvSkorPerputaranAset3;
    TextView tvBobotRasioAktiva1,tvSkorRasioAktiva1,tvBobotRasioAktiva2,tvSkorRasioAktiva2,tvBobotRasioAktiva3,tvSkorRasioAktiva3;
    TextView tvTotal1,tvTotal2,tvTotal3,tvKeterangan1,tvKeterangan2,tvKeterangan3;
    String BobotROI,SkorROI,BobotCashRatio,SkorCashRatio,BobotCurentRatio,
            SkorCurrentRatio,BobotCP,SkorCP,BobotPP,SkorPP,BobotPerputaranAset,SkorPerputaranAset,
            BobotRasioAktiva,SkorRasioAktiva,Total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_hospital_finance_indicator);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);


        Available.setVisibility(View.VISIBLE);
        pList.addAll(HospitalFinanceIndicatorModel.getListData());
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


        card1 = findViewById(R.id.cardTahunPertama);
        card2 = findViewById(R.id.cardTahunKedua);
        card3 = findViewById(R.id.cardTahunKetiga);
        tvBobotROI1 = findViewById(R.id.tvBobotROI1);
        tvBobotROI2 = findViewById(R.id.tvBobotROI2);
        tvBobotROI3 = findViewById(R.id.tvBobotROI3);
        tvSkorROI1 = findViewById(R.id.tvSkorROI1);
        tvSkorROI2 = findViewById(R.id.tvSkorROI2);
        tvSkorROI3 = findViewById(R.id.tvSkorROI3);
        tvBobotCashRatio1 = findViewById(R.id.tvBobotCashRatio1);
        tvBobotCashRatio2 = findViewById(R.id.tvBobotCashRatio2);
        tvSkorCashRatio2 = findViewById(R.id.tvSkorCashRatio2);
        tvSkorCashRatio3 = findViewById(R.id.tvSkorCashRatio3);
        tvBobotCashRatio3 = findViewById(R.id.tvBobotCashRatio3);
        tvSkorCashRatio1 = findViewById(R.id.tvSkorCashRatio1);
        tvBobotCurrentRatio1 = findViewById(R.id.tvBobotCurrentRatio1);
        tvBobotCurrentRatio2 = findViewById(R.id.tvBobotCurrentRatio2);
        tvBobotCurrentRatio3 = findViewById(R.id.tvBobotCurrentRatio3);
        tvSkorCurrentRatio1 = findViewById(R.id.tvSkorCurrentRatio1);
        tvSkorCurrentRatio2 = findViewById(R.id.tvSkorCurrentRatio2);
        tvSkorCurrentRatio3 = findViewById(R.id.tvSkorCurrentRatio3);
        tvBobotCP1 = findViewById(R.id.tvBobotCollectivePeriod1);
        tvBobotCP2 = findViewById(R.id.tvBobotCollectivePeriod2);
        tvBobotCP3 = findViewById(R.id.tvBobotCollectivePeriod3);
        tvSkorCP1 = findViewById(R.id.tvSkorCollectivePeriod1);
        tvSkorCP2 = findViewById(R.id.tvSkorCollectivePeriod2);
        tvSkorCP3 = findViewById(R.id.tvSkorCollectivePeriod3);
        tvBobotPP1 = findViewById(R.id.tvBobotPerputaranPersediaan1);
        tvBobotPP2 = findViewById(R.id.tvBobotPerputaranPersediaan2);
        tvBobotPP3 = findViewById(R.id.tvBobotPerputaranPersediaan3);
        tvSkorPP1 = findViewById(R.id.tvSkorPerputaranPersediaan1);
        tvSkorPP2 = findViewById(R.id.tvSkorPerputaranPersediaan2);
        tvSkorPP3 = findViewById(R.id.tvSkorPerputaranPersediaan3);
        tvBobotPerputaranAset1 = findViewById(R.id.tvBobotPerputaranAset1);
        tvBobotPerputaranAset2 = findViewById(R.id.tvBobotPerputaranAset2);
        tvBobotPerputaranAset3 = findViewById(R.id.tvBobotPerputaranAset3);
        tvSkorPerputaranAset1 = findViewById(R.id.tvSkorPerputaranAset1);
        tvSkorPerputaranAset2 = findViewById(R.id.tvSkorPerputaranAset2);
        tvSkorPerputaranAset3 = findViewById(R.id.tvSkorPerputaranAset3);
        tvBobotRasioAktiva1 = findViewById(R.id.tvBobotRasioAktiva1);
        tvBobotRasioAktiva2 = findViewById(R.id.tvBobotRasioAktiva2);
        tvBobotRasioAktiva3 = findViewById(R.id.tvBobotRasioAktiva3);
        tvSkorRasioAktiva1 = findViewById(R.id.tvSkorRasioAktiva1);
        tvSkorRasioAktiva2 = findViewById(R.id.tvSkorRasioAktiva2);
        tvSkorRasioAktiva3 = findViewById(R.id.tvSkorRasioAktiva3);
        tvTotal1 = findViewById(R.id.tvTotal1);
        tvTotal2 = findViewById(R.id.tvTotal2);
        tvTotal3 = findViewById(R.id.tvTotal3);
        tvKeterangan1 = findViewById(R.id.tvNilai1);
        tvKeterangan2 = findViewById(R.id.tvNilai2);
        tvKeterangan3 = findViewById(R.id.tvNilai3);

        final DB_Helper dbHelper = new DB_Helper(ResultHospitalFinanceIndicatorActivity.this);
        Cursor cursor = dbHelper.checkAnalis("1");
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                SkorROI = cursor.getString(1);
                SkorCashRatio = cursor.getString(2);
                SkorCurrentRatio = cursor.getString(3);
                SkorCP = cursor.getString(4);
                SkorPP = cursor.getString(5);
                SkorPerputaranAset = cursor.getString(6);
                SkorRasioAktiva = cursor.getString(7);
            }
            card1.setVisibility(View.VISIBLE);
            //ROI
            if (Integer.parseInt(SkorROI) > 18){
                BobotROI = "15.0";
            }else if(Integer.parseInt(SkorROI) > 15 && Integer.parseInt(SkorROI) <=18){
                BobotROI = "13.5";
            }else if(Integer.parseInt(SkorROI) > 13 && Integer.parseInt(SkorROI) <=15){
                BobotROI = "12.0";
            }else if(Integer.parseInt(SkorROI) > 12 && Integer.parseInt(SkorROI) <= 13){
                BobotROI = "10.5";
            }else if(Integer.parseInt(SkorROI) > 10.5 && Integer.parseInt(SkorROI) <= 12){
                BobotROI = "9.0";
            }else if(Integer.parseInt(SkorROI) > 9 && Integer.parseInt(SkorROI) <= 10.5){
                BobotROI = "7.5";
            }else if(Integer.parseInt(SkorROI) > 7 && Integer.parseInt(SkorROI) <= 9){
                BobotROI = "6.0";
            }else if(Integer.parseInt(SkorROI) > 5 && Integer.parseInt(SkorROI) <= 7){
                BobotROI = "5.0";
            }else if(Integer.parseInt(SkorROI) > 3 && Integer.parseInt(SkorROI) <= 5){
                BobotROI = "4.0";
            }else if(Integer.parseInt(SkorROI) > 1 && Integer.parseInt(SkorROI) <= 3){
                BobotROI = "3.0";
            }else if(Integer.parseInt(SkorROI) > 0 && Integer.parseInt(SkorROI) <= 1){
                BobotROI = "2.0";
            }else if(Integer.parseInt(SkorROI) <= 0){
                BobotROI = "1.0";
            }
            //CashRatio
            if (Integer.parseInt(SkorCashRatio) > 35){
                BobotCashRatio = "15.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 25 && Integer.parseInt(SkorCashRatio) <= 35){
                BobotCashRatio = "12.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 15 && Integer.parseInt(SkorCashRatio) < 25){
                BobotCashRatio = "9.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 10 && Integer.parseInt(SkorCashRatio) < 15){
                BobotCashRatio = "6.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 5 && Integer.parseInt(SkorCashRatio) < 10){
                BobotCashRatio = "3.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 0 && Integer.parseInt(SkorCashRatio) < 5){
                BobotCashRatio = "0";
            }
            //CurrentRatio
            if (Integer.parseInt(SkorCurrentRatio) >= 125){
                BobotCurentRatio = "15.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 110 && Integer.parseInt(SkorCurrentRatio) <125){
                BobotCurentRatio = "12.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 100 && Integer.parseInt(SkorCurrentRatio) <110){
                BobotCurentRatio = "9.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 95 && Integer.parseInt(SkorCurrentRatio) <100){
                BobotCurentRatio = "6.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 90 && Integer.parseInt(SkorCurrentRatio) <95){
                BobotCurentRatio = "3.0";
            }else if(Integer.parseInt(SkorCurrentRatio) < 90){
                BobotCurentRatio = "0";
            }
            //CollectionPeriod
            if(Integer.parseInt(SkorCP) <= 60){
                BobotCP = "15.0";
            }else if(Integer.parseInt(SkorCP) > 60 && (Integer.parseInt(SkorCP) <= 90)){
                BobotCP = "13.5";
            }else if(Integer.parseInt(SkorCP) > 90 && (Integer.parseInt(SkorCP) <= 120)){
                BobotCP = "12.0";
            }else if(Integer.parseInt(SkorCP) > 120 && (Integer.parseInt(SkorCP) <= 150)){
                BobotCP = "10.5";
            }else if(Integer.parseInt(SkorCP) > 150 && (Integer.parseInt(SkorCP) <= 180)){
                BobotCP = "9.0";
            }else if(Integer.parseInt(SkorCP) > 180 && (Integer.parseInt(SkorCP) <= 210)){
                BobotCP = "7.2";
            }else if(Integer.parseInt(SkorCP) > 210 && (Integer.parseInt(SkorCP) <= 240)){
                BobotCP = "5.4";
            }else if(Integer.parseInt(SkorCP) > 240 && (Integer.parseInt(SkorCP) <= 270)){
                BobotCP = "3.6";
            }else if(Integer.parseInt(SkorCP) > 270 && (Integer.parseInt(SkorCP) <= 300)){
                BobotCP = "1.6";
            }else if(Integer.parseInt(SkorCP) > 300){
                BobotCP = "0";
            }
            //Perputaran Persediaan
            if(Integer.parseInt(SkorPP) <= 60){
                BobotPP = "10.0";
            }else if(Integer.parseInt(SkorPP) > 60 && (Integer.parseInt(SkorPP) <= 90)){
                BobotPP = "9.0";
            }else if(Integer.parseInt(SkorPP) > 90 && (Integer.parseInt(SkorPP) <= 120)){
                BobotPP = "8.0";
            }else if(Integer.parseInt(SkorPP) > 120 && (Integer.parseInt(SkorPP) <= 150)){
                BobotPP = "7.0";
            }else if(Integer.parseInt(SkorPP) > 150 && (Integer.parseInt(SkorPP) <= 180)){
                BobotPP = "6.0";
            }else if(Integer.parseInt(SkorPP) > 180 && (Integer.parseInt(SkorPP) <= 210)){
                BobotPP = "4.6";
            }else if(Integer.parseInt(SkorPP) > 210 && (Integer.parseInt(SkorPP) <= 240)){
                BobotPP = "3.6";
            }else if(Integer.parseInt(SkorPP) > 240 && (Integer.parseInt(SkorPP) <= 270)){
                BobotPP = "2.4";
            }else if(Integer.parseInt(SkorPP) > 270 && (Integer.parseInt(SkorPP) <= 300)){
                BobotPP = "1.2";
            }else if(Integer.parseInt(SkorPP) > 300){
                BobotPP = "0";
            }
            //TATO
            if (Integer.parseInt(SkorPerputaranAset) > 120){
                BobotPerputaranAset = "10.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 105 && Integer.parseInt(SkorPerputaranAset) <= 120){
                BobotPerputaranAset = "9.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 90 && Integer.parseInt(SkorPerputaranAset) <= 105){
                BobotPerputaranAset = "8.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 75 && Integer.parseInt(SkorPerputaranAset) <= 90){
                BobotPerputaranAset = "7.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 60 && Integer.parseInt(SkorPerputaranAset) <= 75){
                BobotPerputaranAset = "6.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 40 && Integer.parseInt(SkorPerputaranAset) <= 60){
                BobotPerputaranAset = "5.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 20 && Integer.parseInt(SkorPerputaranAset) <= 40){
                BobotPerputaranAset = "4.0";
            }else if(Integer.parseInt(SkorPerputaranAset) <= 20){
                BobotPerputaranAset = "3.0";
            }
            //Aktiva Bersih
            if (Integer.parseInt(SkorRasioAktiva) >= 0 && Integer.parseInt(SkorRasioAktiva) < 10){
                BobotRasioAktiva = "0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 10 && Integer.parseInt(SkorRasioAktiva) < 20){
                BobotRasioAktiva = "8.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 20 && Integer.parseInt(SkorRasioAktiva) < 30){
                BobotRasioAktiva = "12.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 30 && Integer.parseInt(SkorRasioAktiva) < 40){
                BobotRasioAktiva = "14.5";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 40 && Integer.parseInt(SkorRasioAktiva) < 50){
                BobotRasioAktiva = "20.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 50 && Integer.parseInt(SkorRasioAktiva) < 60){
                BobotRasioAktiva = "18.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 60 && Integer.parseInt(SkorRasioAktiva) < 70){
                BobotRasioAktiva = "17.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 70 && Integer.parseInt(SkorRasioAktiva) < 80){
                BobotRasioAktiva = "16.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 80 && Integer.parseInt(SkorRasioAktiva) < 90){
                BobotRasioAktiva = "15.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 90 && Integer.parseInt(SkorRasioAktiva) < 100){
                BobotRasioAktiva = "14.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 100 && Integer.parseInt(SkorRasioAktiva) < 110){
                BobotRasioAktiva = "13.0";
            }
            tvBobotROI1.setText(BobotROI);
            tvSkorROI1.setText(SkorROI);
            tvBobotCashRatio1.setText(BobotCashRatio);
            tvSkorCashRatio1.setText(SkorCashRatio);
            tvBobotCurrentRatio1.setText(BobotCurentRatio);
            tvSkorCurrentRatio1.setText(SkorCurrentRatio);
            tvBobotCP1.setText(BobotCP);
            tvSkorCP1.setText(SkorCP);
            tvBobotPP1.setText(BobotPP);
            tvSkorPP1.setText(SkorPP);
            tvBobotPerputaranAset1.setText(BobotPerputaranAset);
            tvSkorPerputaranAset1.setText(SkorPerputaranAset);
            String tot = String.valueOf(Double.parseDouble(tvBobotROI1.getText().toString())+
                    Double.parseDouble(tvBobotCashRatio1.getText().toString())+
                    Double.parseDouble(tvBobotCurrentRatio1.getText().toString())+
                    Double.parseDouble(tvBobotCP1.getText().toString())+
                    Double.parseDouble(tvBobotPP1.getText().toString())+
                    Double.parseDouble(tvBobotPerputaranAset1.getText().toString())+
                    Double.parseDouble(tvBobotRasioAktiva1.getText().toString()));
            tvTotal1.setText(tot);

            tvKeterangan1.setText("Rumah Sakit Memiliki keuangan "+keterangan(tot)+" dengan Nilai "+ifelses(tot)+" Skor "+tot);
        }else{
            card1.setVisibility(View.GONE);
        }

        cursor = dbHelper.checkAnalis("2");
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                SkorROI = cursor.getString(1);
                SkorCashRatio = cursor.getString(2);
                SkorCurrentRatio = cursor.getString(3);
                SkorCP = cursor.getString(4);
                SkorPP = cursor.getString(5);
                SkorPerputaranAset = cursor.getString(6);
                SkorRasioAktiva = cursor.getString(7);
            }
            card2.setVisibility(View.VISIBLE);
            //ROI
            if (Integer.parseInt(SkorROI) > 18){
                BobotROI = "15.0";
            }else if(Integer.parseInt(SkorROI) > 15 && Integer.parseInt(SkorROI) <=18){
                BobotROI = "13.5";
            }else if(Integer.parseInt(SkorROI) > 13 && Integer.parseInt(SkorROI) <=15){
                BobotROI = "12.0";
            }else if(Integer.parseInt(SkorROI) > 12 && Integer.parseInt(SkorROI) <= 13){
                BobotROI = "10.5";
            }else if(Integer.parseInt(SkorROI) > 10.5 && Integer.parseInt(SkorROI) <= 12){
                BobotROI = "9.0";
            }else if(Integer.parseInt(SkorROI) > 9 && Integer.parseInt(SkorROI) <= 10.5){
                BobotROI = "7.5";
            }else if(Integer.parseInt(SkorROI) > 7 && Integer.parseInt(SkorROI) <= 9){
                BobotROI = "6.0";
            }else if(Integer.parseInt(SkorROI) > 5 && Integer.parseInt(SkorROI) <= 7){
                BobotROI = "5.0";
            }else if(Integer.parseInt(SkorROI) > 3 && Integer.parseInt(SkorROI) <= 5){
                BobotROI = "4.0";
            }else if(Integer.parseInt(SkorROI) > 1 && Integer.parseInt(SkorROI) <= 3){
                BobotROI = "3.0";
            }else if(Integer.parseInt(SkorROI) > 0 && Integer.parseInt(SkorROI) <= 1){
                BobotROI = "2.0";
            }else if(Integer.parseInt(SkorROI) <= 0){
                BobotROI = "1.0";
            }
            //CashRatio
            if (Integer.parseInt(SkorCashRatio) > 35){
                BobotCashRatio = "15.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 25 && Integer.parseInt(SkorCashRatio) <= 35){
                BobotCashRatio = "12.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 15 && Integer.parseInt(SkorCashRatio) < 25){
                BobotCashRatio = "9.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 10 && Integer.parseInt(SkorCashRatio) < 15){
                BobotCashRatio = "6.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 5 && Integer.parseInt(SkorCashRatio) < 10){
                BobotCashRatio = "3.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 0 && Integer.parseInt(SkorCashRatio) < 5){
                BobotCashRatio = "0";
            }
            //CurrentRatio
            if (Integer.parseInt(SkorCurrentRatio) >= 125){
                BobotCurentRatio = "15.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 110 && Integer.parseInt(SkorCurrentRatio) <125){
                BobotCurentRatio = "12.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 100 && Integer.parseInt(SkorCurrentRatio) <110){
                BobotCurentRatio = "9.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 95 && Integer.parseInt(SkorCurrentRatio) <100){
                BobotCurentRatio = "6.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 90 && Integer.parseInt(SkorCurrentRatio) <95){
                BobotCurentRatio = "3.0";
            }else if(Integer.parseInt(SkorCurrentRatio) < 90){
                BobotCurentRatio = "0";
            }
            //CollectionPeriod
            if(Integer.parseInt(SkorCP) <= 60){
                BobotCP = "15.0";
            }else if(Integer.parseInt(SkorCP) > 60 && (Integer.parseInt(SkorCP) <= 90)){
                BobotCP = "13.5";
            }else if(Integer.parseInt(SkorCP) > 90 && (Integer.parseInt(SkorCP) <= 120)){
                BobotCP = "12.0";
            }else if(Integer.parseInt(SkorCP) > 120 && (Integer.parseInt(SkorCP) <= 150)){
                BobotCP = "10.5";
            }else if(Integer.parseInt(SkorCP) > 150 && (Integer.parseInt(SkorCP) <= 180)){
                BobotCP = "9.0";
            }else if(Integer.parseInt(SkorCP) > 180 && (Integer.parseInt(SkorCP) <= 210)){
                BobotCP = "7.2";
            }else if(Integer.parseInt(SkorCP) > 210 && (Integer.parseInt(SkorCP) <= 240)){
                BobotCP = "5.4";
            }else if(Integer.parseInt(SkorCP) > 240 && (Integer.parseInt(SkorCP) <= 270)){
                BobotCP = "3.6";
            }else if(Integer.parseInt(SkorCP) > 270 && (Integer.parseInt(SkorCP) <= 300)){
                BobotCP = "1.6";
            }else if(Integer.parseInt(SkorCP) > 300){
                BobotCP = "0";
            }
            //Perputaran Persediaan
            if(Integer.parseInt(SkorPP) <= 60){
                BobotPP = "10.0";
            }else if(Integer.parseInt(SkorPP) > 60 && (Integer.parseInt(SkorPP) <= 90)){
                BobotPP = "9.0";
            }else if(Integer.parseInt(SkorPP) > 90 && (Integer.parseInt(SkorPP) <= 120)){
                BobotPP = "8.0";
            }else if(Integer.parseInt(SkorPP) > 120 && (Integer.parseInt(SkorPP) <= 150)){
                BobotPP = "7.0";
            }else if(Integer.parseInt(SkorPP) > 150 && (Integer.parseInt(SkorPP) <= 180)){
                BobotPP = "6.0";
            }else if(Integer.parseInt(SkorPP) > 180 && (Integer.parseInt(SkorPP) <= 210)){
                BobotPP = "4.6";
            }else if(Integer.parseInt(SkorPP) > 210 && (Integer.parseInt(SkorPP) <= 240)){
                BobotPP = "3.6";
            }else if(Integer.parseInt(SkorPP) > 240 && (Integer.parseInt(SkorPP) <= 270)){
                BobotPP = "2.4";
            }else if(Integer.parseInt(SkorPP) > 270 && (Integer.parseInt(SkorPP) <= 300)){
                BobotPP = "1.2";
            }else if(Integer.parseInt(SkorPP) > 300){
                BobotPP = "0";
            }
            //TATO
            if (Integer.parseInt(SkorPerputaranAset) > 120){
                BobotPerputaranAset = "10.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 105 && Integer.parseInt(SkorPerputaranAset) <= 120){
                BobotPerputaranAset = "9.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 90 && Integer.parseInt(SkorPerputaranAset) <= 105){
                BobotPerputaranAset = "8.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 75 && Integer.parseInt(SkorPerputaranAset) <= 90){
                BobotPerputaranAset = "7.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 60 && Integer.parseInt(SkorPerputaranAset) <= 75){
                BobotPerputaranAset = "6.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 40 && Integer.parseInt(SkorPerputaranAset) <= 60){
                BobotPerputaranAset = "5.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 20 && Integer.parseInt(SkorPerputaranAset) <= 40){
                BobotPerputaranAset = "4.0";
            }else if(Integer.parseInt(SkorPerputaranAset) <= 20){
                BobotPerputaranAset = "3.0";
            }
            //Aktiva Bersih
            if (Integer.parseInt(SkorRasioAktiva) >= 0 && Integer.parseInt(SkorRasioAktiva) < 10){
                BobotRasioAktiva = "0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 10 && Integer.parseInt(SkorRasioAktiva) < 20){
                BobotRasioAktiva = "8.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 20 && Integer.parseInt(SkorRasioAktiva) < 30){
                BobotRasioAktiva = "12.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 30 && Integer.parseInt(SkorRasioAktiva) < 40){
                BobotRasioAktiva = "14.5";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 40 && Integer.parseInt(SkorRasioAktiva) < 50){
                BobotRasioAktiva = "20.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 50 && Integer.parseInt(SkorRasioAktiva) < 60){
                BobotRasioAktiva = "18.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 60 && Integer.parseInt(SkorRasioAktiva) < 70){
                BobotRasioAktiva = "17.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 70 && Integer.parseInt(SkorRasioAktiva) < 80){
                BobotRasioAktiva = "16.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 80 && Integer.parseInt(SkorRasioAktiva) < 90){
                BobotRasioAktiva = "15.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 90 && Integer.parseInt(SkorRasioAktiva) < 100){
                BobotRasioAktiva = "14.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 100 && Integer.parseInt(SkorRasioAktiva) < 110){
                BobotRasioAktiva = "13.0";
            }
            tvBobotROI2.setText(BobotROI);
            tvSkorROI2.setText(SkorROI);
            tvBobotCashRatio2.setText(BobotCashRatio);
            tvSkorCashRatio2.setText(SkorCashRatio);
            tvBobotCurrentRatio2.setText(BobotCurentRatio);
            tvSkorCurrentRatio2.setText(SkorCurrentRatio);
            tvBobotCP2.setText(BobotCP);
            tvSkorCP2.setText(SkorCP);
            tvBobotPP2.setText(BobotPP);
            tvSkorPP2.setText(SkorPP);
            tvBobotPerputaranAset2.setText(BobotPerputaranAset);
            tvSkorPerputaranAset2.setText(SkorPerputaranAset);
            String tot = String.valueOf(Double.parseDouble(tvBobotROI2.getText().toString())+
                    Double.parseDouble(tvBobotCashRatio2.getText().toString())+
                    Double.parseDouble(tvBobotCurrentRatio2.getText().toString())+
                    Double.parseDouble(tvBobotCP2.getText().toString())+
                    Double.parseDouble(tvBobotPP2.getText().toString())+
                    Double.parseDouble(tvBobotPerputaranAset2.getText().toString())+
                    Double.parseDouble(tvBobotRasioAktiva2.getText().toString()));
            tvTotal2.setText(tot);

            tvKeterangan2.setText("Rumah Sakit Memiliki keuangan "+keterangan(tot)+" dengan Nilai "+ifelses(tot)+" Skor "+tot);
        }else{
            card2.setVisibility(View.GONE);
        }

        cursor = dbHelper.checkAnalis("3");
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                SkorROI = cursor.getString(1);
                SkorCashRatio = cursor.getString(2);
                SkorCurrentRatio = cursor.getString(3);
                SkorCP = cursor.getString(4);
                SkorPP = cursor.getString(5);
                SkorPerputaranAset = cursor.getString(6);
                SkorRasioAktiva = cursor.getString(7);
            }
            card3.setVisibility(View.VISIBLE);
            //ROI
            if (Integer.parseInt(SkorROI) > 18){
                BobotROI = "15.0";
            }else if(Integer.parseInt(SkorROI) > 15 && Integer.parseInt(SkorROI) <=18){
                BobotROI = "13.5";
            }else if(Integer.parseInt(SkorROI) > 13 && Integer.parseInt(SkorROI) <=15){
                BobotROI = "12.0";
            }else if(Integer.parseInt(SkorROI) > 12 && Integer.parseInt(SkorROI) <= 13){
                BobotROI = "10.5";
            }else if(Integer.parseInt(SkorROI) > 10.5 && Integer.parseInt(SkorROI) <= 12){
                BobotROI = "9.0";
            }else if(Integer.parseInt(SkorROI) > 9 && Integer.parseInt(SkorROI) <= 10.5){
                BobotROI = "7.5";
            }else if(Integer.parseInt(SkorROI) > 7 && Integer.parseInt(SkorROI) <= 9){
                BobotROI = "6.0";
            }else if(Integer.parseInt(SkorROI) > 5 && Integer.parseInt(SkorROI) <= 7){
                BobotROI = "5.0";
            }else if(Integer.parseInt(SkorROI) > 3 && Integer.parseInt(SkorROI) <= 5){
                BobotROI = "4.0";
            }else if(Integer.parseInt(SkorROI) > 1 && Integer.parseInt(SkorROI) <= 3){
                BobotROI = "3.0";
            }else if(Integer.parseInt(SkorROI) > 0 && Integer.parseInt(SkorROI) <= 1){
                BobotROI = "2.0";
            }else if(Integer.parseInt(SkorROI) <= 0){
                BobotROI = "1.0";
            }
            //CashRatio
            if (Integer.parseInt(SkorCashRatio) > 35){
                BobotCashRatio = "15.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 25 && Integer.parseInt(SkorCashRatio) <= 35){
                BobotCashRatio = "12.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 15 && Integer.parseInt(SkorCashRatio) < 25){
                BobotCashRatio = "9.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 10 && Integer.parseInt(SkorCashRatio) < 15){
                BobotCashRatio = "6.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 5 && Integer.parseInt(SkorCashRatio) < 10){
                BobotCashRatio = "3.0";
            }else if (Integer.parseInt(SkorCashRatio) >= 0 && Integer.parseInt(SkorCashRatio) < 5){
                BobotCashRatio = "0";
            }
            //CurrentRatio
            if (Integer.parseInt(SkorCurrentRatio) >= 125){
                BobotCurentRatio = "15.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 110 && Integer.parseInt(SkorCurrentRatio) <125){
                BobotCurentRatio = "12.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 100 && Integer.parseInt(SkorCurrentRatio) <110){
                BobotCurentRatio = "9.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 95 && Integer.parseInt(SkorCurrentRatio) <100){
                BobotCurentRatio = "6.0";
            }else if(Integer.parseInt(SkorCurrentRatio) >= 90 && Integer.parseInt(SkorCurrentRatio) <95){
                BobotCurentRatio = "3.0";
            }else if(Integer.parseInt(SkorCurrentRatio) < 90){
                BobotCurentRatio = "0";
            }
            //CollectionPeriod
            if(Integer.parseInt(SkorCP) <= 60){
                BobotCP = "15.0";
            }else if(Integer.parseInt(SkorCP) > 60 && (Integer.parseInt(SkorCP) <= 90)){
                BobotCP = "13.5";
            }else if(Integer.parseInt(SkorCP) > 90 && (Integer.parseInt(SkorCP) <= 120)){
                BobotCP = "12.0";
            }else if(Integer.parseInt(SkorCP) > 120 && (Integer.parseInt(SkorCP) <= 150)){
                BobotCP = "10.5";
            }else if(Integer.parseInt(SkorCP) > 150 && (Integer.parseInt(SkorCP) <= 180)){
                BobotCP = "9.0";
            }else if(Integer.parseInt(SkorCP) > 180 && (Integer.parseInt(SkorCP) <= 210)){
                BobotCP = "7.2";
            }else if(Integer.parseInt(SkorCP) > 210 && (Integer.parseInt(SkorCP) <= 240)){
                BobotCP = "5.4";
            }else if(Integer.parseInt(SkorCP) > 240 && (Integer.parseInt(SkorCP) <= 270)){
                BobotCP = "3.6";
            }else if(Integer.parseInt(SkorCP) > 270 && (Integer.parseInt(SkorCP) <= 300)){
                BobotCP = "1.6";
            }else if(Integer.parseInt(SkorCP) > 300){
                BobotCP = "0";
            }
            //Perputaran Persediaan
            if(Integer.parseInt(SkorPP) <= 60){
                BobotPP = "10.0";
            }else if(Integer.parseInt(SkorPP) > 60 && (Integer.parseInt(SkorPP) <= 90)){
                BobotPP = "9.0";
            }else if(Integer.parseInt(SkorPP) > 90 && (Integer.parseInt(SkorPP) <= 120)){
                BobotPP = "8.0";
            }else if(Integer.parseInt(SkorPP) > 120 && (Integer.parseInt(SkorPP) <= 150)){
                BobotPP = "7.0";
            }else if(Integer.parseInt(SkorPP) > 150 && (Integer.parseInt(SkorPP) <= 180)){
                BobotPP = "6.0";
            }else if(Integer.parseInt(SkorPP) > 180 && (Integer.parseInt(SkorPP) <= 210)){
                BobotPP = "4.6";
            }else if(Integer.parseInt(SkorPP) > 210 && (Integer.parseInt(SkorPP) <= 240)){
                BobotPP = "3.6";
            }else if(Integer.parseInt(SkorPP) > 240 && (Integer.parseInt(SkorPP) <= 270)){
                BobotPP = "2.4";
            }else if(Integer.parseInt(SkorPP) > 270 && (Integer.parseInt(SkorPP) <= 300)){
                BobotPP = "1.2";
            }else if(Integer.parseInt(SkorPP) > 300){
                BobotPP = "0";
            }
            //TATO
            if (Integer.parseInt(SkorPerputaranAset) > 120){
                BobotPerputaranAset = "10.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 105 && Integer.parseInt(SkorPerputaranAset) <= 120){
                BobotPerputaranAset = "9.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 90 && Integer.parseInt(SkorPerputaranAset) <= 105){
                BobotPerputaranAset = "8.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 75 && Integer.parseInt(SkorPerputaranAset) <= 90){
                BobotPerputaranAset = "7.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 60 && Integer.parseInt(SkorPerputaranAset) <= 75){
                BobotPerputaranAset = "6.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 40 && Integer.parseInt(SkorPerputaranAset) <= 60){
                BobotPerputaranAset = "5.0";
            }else if(Integer.parseInt(SkorPerputaranAset) > 20 && Integer.parseInt(SkorPerputaranAset) <= 40){
                BobotPerputaranAset = "4.0";
            }else if(Integer.parseInt(SkorPerputaranAset) <= 20){
                BobotPerputaranAset = "3.0";
            }
            //Aktiva Bersih
            if (Integer.parseInt(SkorRasioAktiva) >= 0 && Integer.parseInt(SkorRasioAktiva) < 10){
                BobotRasioAktiva = "0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 10 && Integer.parseInt(SkorRasioAktiva) < 20){
                BobotRasioAktiva = "8.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 20 && Integer.parseInt(SkorRasioAktiva) < 30){
                BobotRasioAktiva = "12.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 30 && Integer.parseInt(SkorRasioAktiva) < 40){
                BobotRasioAktiva = "14.5";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 40 && Integer.parseInt(SkorRasioAktiva) < 50){
                BobotRasioAktiva = "20.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 50 && Integer.parseInt(SkorRasioAktiva) < 60){
                BobotRasioAktiva = "18.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 60 && Integer.parseInt(SkorRasioAktiva) < 70){
                BobotRasioAktiva = "17.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 70 && Integer.parseInt(SkorRasioAktiva) < 80){
                BobotRasioAktiva = "16.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 80 && Integer.parseInt(SkorRasioAktiva) < 90){
                BobotRasioAktiva = "15.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 90 && Integer.parseInt(SkorRasioAktiva) < 100){
                BobotRasioAktiva = "14.0";
            }else if(Integer.parseInt(SkorRasioAktiva) >= 100 && Integer.parseInt(SkorRasioAktiva) < 110){
                BobotRasioAktiva = "13.0";
            }
            tvBobotROI3.setText(BobotROI);
            tvSkorROI3.setText(SkorROI);
            tvBobotCashRatio3.setText(BobotCashRatio);
            tvSkorCashRatio3.setText(SkorCashRatio);
            tvBobotCurrentRatio3.setText(BobotCurentRatio);
            tvSkorCurrentRatio3.setText(SkorCurrentRatio);
            tvBobotCP3.setText(BobotCP);
            tvSkorCP3.setText(SkorCP);
            tvBobotPP3.setText(BobotPP);
            tvSkorPP3.setText(SkorPP);
            tvBobotPerputaranAset3.setText(BobotPerputaranAset);
            tvSkorPerputaranAset3.setText(SkorPerputaranAset);
            String tot = String.valueOf(Double.parseDouble(tvBobotROI3.getText().toString())+
                    Double.parseDouble(tvBobotCashRatio3.getText().toString())+
                    Double.parseDouble(tvBobotCurrentRatio3.getText().toString())+
                    Double.parseDouble(tvBobotCP3.getText().toString())+
                    Double.parseDouble(tvBobotPP3.getText().toString())+
                    Double.parseDouble(tvBobotPerputaranAset3.getText().toString())+
                    Double.parseDouble(tvBobotRasioAktiva3.getText().toString()));
            tvTotal3.setText(tot);

            tvKeterangan3.setText("Rumah Sakit Memiliki keuangan "+keterangan(tot)+" dengan Nilai "+ifelses(tot)+" Skor "+tot);
        }else{
            card3.setVisibility(View.GONE);
        }

    }
    private String ifelses(String total){
        String skor="AAA";
        if (Double.parseDouble( total) >= 95){
            skor="AA";
        }else if(Double.parseDouble( total) >= 80 && Double.parseDouble( total) <95){
            skor="AA";
        }else if(Double.parseDouble( total) >= 65 && Double.parseDouble( total) <80){
            skor="A";
        }else if(Double.parseDouble( total) >= 50 && Double.parseDouble( total) <65){
            skor="BBB";
        }else if(Double.parseDouble( total) >= 40 && Double.parseDouble( total) <50){
            skor="BB";
        }else if(Double.parseDouble( total) >= 30 && Double.parseDouble( total) <40){
            skor="B";
        }else if(Double.parseDouble( total) >= 20 && Double.parseDouble( total) <30){
            skor="CCC";
        }else if(Double.parseDouble( total) >= 10 && Double.parseDouble( total) <20){
            skor="CC";
        }else if(Double.parseDouble( total) >= 0 && Double.parseDouble( total) <10){
            skor="C";
        }
        return skor;
    }
    private String keterangan(String total){
        String skor="Sehat";
        if (Double.parseDouble( total) >= 95){
            skor="Sehat";
        }else if(Double.parseDouble( total) >= 80 && Double.parseDouble( total) <95){
            skor="Sehat";
        }else if(Double.parseDouble( total) >= 65 && Double.parseDouble( total) <80){
            skor="Sehat";
        }else if(Double.parseDouble( total) >= 50 && Double.parseDouble( total) <65){
            skor="Kurang Sehat";
        }else if(Double.parseDouble( total) >= 40 && Double.parseDouble( total) <50){
            skor="Kurang Sehat";
        }else if(Double.parseDouble( total) >= 30 && Double.parseDouble( total) <40){
            skor="Kurang Sehat";
        }else if(Double.parseDouble( total) >= 20 && Double.parseDouble( total) <30){
            skor="Tidak Sehat";
        }else if(Double.parseDouble( total) >= 10 && Double.parseDouble( total) <20){
            skor="Tidak Sehat";
        }else if(Double.parseDouble( total) >= 0 && Double.parseDouble( total) <10){
            skor="Tidak Sehat";
        }
        return skor;
    }
}