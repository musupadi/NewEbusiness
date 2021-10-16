package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool.FiveC;

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
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.Analisis5CActivity;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolFNB5CModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolNFNB5CModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolTobacco5CModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGFiveCActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    Button Character,Capacity,Capital,Colleteral,Condition,Total,Skor,Save;
    String kategori,scoreCaracter,scoreCapital,scoreCapacity,scoreColleteral,scoreCondition,caracter,capital,capacity,colleteral,condition;
    int hitung;
    ImageView back,home;
    TextView header;
    String KATEGORI;
    String cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgfive_cactivity);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        Intent data = getIntent();
        KATEGORI = data.getStringExtra("KATEGORI");
        if (KATEGORI.equals("NON FOOD")){
            pList.addAll(FMCGCreditDecisionToolNFNB5CModel.getListData());
        }else if (KATEGORI.equals("FOOD")){
            pList.addAll(FMCGCreditDecisionToolFNB5CModel.getListData());
        }else{
            pList.addAll(FMCGCreditDecisionToolTobacco5CModel.getListData());
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

        header = findViewById(R.id.tvHeader);
        Character = findViewById(R.id.btnCharacter);
        Capacity = findViewById(R.id.btnCapacity);
        Capital = findViewById(R.id.btnCapital);
        Colleteral = findViewById(R.id.btnCollateral);
        Condition = findViewById(R.id.btnCondition);
        Total = findViewById(R.id.btnTotal);
        Skor = findViewById(R.id.btnSkor);
        Save = findViewById(R.id.btnSave);
        back = findViewById(R.id.ivBack);
        home = findViewById(R.id.ivHome);
        this.cat = KATEGORI;
        final DB_Helper dbHelper = new DB_Helper(FMCGFiveCActivity.this);
        if (KATEGORI.equals("FOOD")){
            header.setText("Analysis 5 C");
            LOGICBACK(KATEGORI);
        }else if(KATEGORI.equals("NON FOOD ROKOK")){
            header.setText("Analysis 5 C");
            LOGICBACK(KATEGORI);
        }else if(KATEGORI.equals("NON FOOD")){
            header.setText("Analysis 5 C");
            LOGICBACK(KATEGORI);
        }else{
            header.setText("Analysis 5 C");
            LOGICBACK(KATEGORI);
        }
        //Character
        Cursor cursor1 = dbHelper.checkQuiz("CHARACTER");
        hitung = 0;
        if (cursor1.getCount()>0){
            while (cursor1.moveToNext()){
                caracter = cursor1.getString(0);
                scoreCaracter = cursor1.getString(1);
            }
        }
        if (caracter != null){
            Character.setText(scoreCaracter+"%");
            Character.setEnabled(false);
            hitung = hitung+Integer.parseInt(scoreCaracter);
        }
        //Capacity
        Cursor cursor2 = dbHelper.checkQuiz("CAPACITY");
        if (cursor2.getCount()>0){
            while (cursor2.moveToNext()){
                capacity = cursor2.getString(0);
                scoreCapacity = cursor2.getString(1);
            }
        }
        if (capacity != null){
            Capacity.setText(scoreCapacity+"%");
            Capacity.setEnabled(false);
            hitung = hitung+Integer.parseInt(scoreCapacity);
        }
        //Capital
        Cursor cursor3 = dbHelper.checkQuiz("CAPITAL");
        if (cursor3.getCount()>0){
            while (cursor3.moveToNext()){
                capital = cursor3.getString(0);
                scoreCapital = cursor3.getString(1);
            }
        }
        if (capital != null){
            Capital.setText(scoreCapital+"%");
            Capital.setEnabled(false);
            hitung = hitung+Integer.parseInt(scoreCapital);
        }
        //Collateral
        Cursor cursor4 = dbHelper.checkQuiz("COLLATERAL");
        if (cursor4.getCount()>0){
            while (cursor4.moveToNext()){
                colleteral = cursor4.getString(0);
                scoreColleteral = cursor4.getString(1);
            }
        }
        if (colleteral != null){
            Colleteral.setText(scoreColleteral+"%");
            Colleteral.setEnabled(false);
            hitung = hitung+Integer.parseInt(scoreColleteral);
        }
        //Collateral
        Cursor cursor5 = dbHelper.checkQuiz("CONDITION");
        if (cursor5.getCount()>0){
            while (cursor5.moveToNext()){
                condition = cursor5.getString(0);
                scoreCondition = cursor5.getString(1);
            }
        }
        if (condition != null){
            Condition.setText(scoreCondition+"%");
            Condition.setEnabled(false);
            hitung = hitung+Integer.parseInt(scoreCondition);
        }

        if (caracter != null && capacity != null && capital != null && colleteral != null && condition != null){
            Total.setText(String.valueOf(hitung)+"%");
            if (hitung>=0 && hitung<40){
                Skor.setText("TIDAK BAIK");
                Skor.setBackgroundResource(R.drawable.button_red_rounded);
                Total.setBackgroundResource(R.drawable.button_red_rounded);
            }else if(hitung>=41 && hitung <= 60){
                Skor.setText("CUKUP");
                Skor.setBackgroundResource(R.drawable.button_orange_rounded);
                Total.setBackgroundResource(R.drawable.button_orange_rounded);
            }else if(hitung>=61 && hitung <= 80){
                Skor.setText("BAIK");
                Skor.setBackgroundResource(R.drawable.button_yellow_rounded);
                Total.setBackgroundResource(R.drawable.button_yellow_rounded);
            }else if(hitung>=81 && hitung <= 100){
                Skor.setText("SANGAT BAIK");
                Skor.setBackgroundResource(R.drawable.button_green_rounded);
                Total.setBackgroundResource(R.drawable.button_green_rounded);
            }
        }
        Character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGFiveCActivity.this, Analisis5CActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI.toLowerCase());
                goInput.putExtra("PERNYATAAN","character");
                goInput.putExtra("TIPE","fmcg");
                startActivities(new Intent[]{goInput});
//                Intent goInput = new Intent(FMCGFiveCActivity.this, SoalFMCGActivity.class);
//                goInput.putExtra("SCORE","0");
//                goInput.putExtra("NO","0");
//                goInput.putExtra("CATEGORY","CHARACTER");
//                goInput.putExtra("SIMULASI","NOPE");
//                goInput.putExtra("JAWABAN","YA");
//                goInput.putExtra("KATEGORI",KATEGORI);
//                startActivities(new Intent[]{goInput});
            }
        });
        Capacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGFiveCActivity.this, Analisis5CActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI.toLowerCase());
                goInput.putExtra("PERNYATAAN","capacity");
                goInput.putExtra("TIPE","fmcg");
                startActivities(new Intent[]{goInput});

//                Intent goInput = new Intent(FMCGFiveCActivity.this,SoalFMCGActivity.class);
//                goInput.putExtra("SCORE","0");
//                goInput.putExtra("NO","0");
//                goInput.putExtra("CATEGORY","CAPACITY");
//                goInput.putExtra("SIMULASI","NOPE");
//                goInput.putExtra("JAWABAN","YA");
//                goInput.putExtra("KATEGORI",KATEGORI);
//                startActivities(new Intent[]{goInput});
            }
        });
        Capital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGFiveCActivity.this, Analisis5CActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI.toLowerCase());
                goInput.putExtra("PERNYATAAN","capital");
                goInput.putExtra("TIPE","fmcg");
                startActivities(new Intent[]{goInput});


//                Intent goInput = new Intent(FMCGFiveCActivity.this,SoalFMCGActivity.class);
//                goInput.putExtra("SCORE","0");
//                goInput.putExtra("NO","0");
//                goInput.putExtra("CATEGORY","CAPITAL");
//                goInput.putExtra("SIMULASI","NOPE");
//                goInput.putExtra("JAWABAN","YA");
//                goInput.putExtra("KATEGORI",KATEGORI);
//                startActivities(new Intent[]{goInput});
            }
        });
        Colleteral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGFiveCActivity.this, Analisis5CActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI.toLowerCase());
                goInput.putExtra("PERNYATAAN","collateral");
                goInput.putExtra("TIPE","fmcg");
                startActivities(new Intent[]{goInput});

//                Intent goInput = new Intent(FMCGFiveCActivity.this,SoalFMCGActivity.class);
//                goInput.putExtra("SCORE","0");
//                goInput.putExtra("NO","0");
//                goInput.putExtra("CATEGORY","COLLATERAL");
//                goInput.putExtra("SIMULASI","NOPE");
//                goInput.putExtra("JAWABAN","YA");
//                goInput.putExtra("KATEGORI",KATEGORI);
//                startActivities(new Intent[]{goInput});
            }
        });
        Condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGFiveCActivity.this, Analisis5CActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI.toLowerCase());
                goInput.putExtra("PERNYATAAN","condition");
                goInput.putExtra("TIPE","fmcg");
                startActivities(new Intent[]{goInput});


//                Intent goInput = new Intent(FMCGFiveCActivity.this,SoalFMCGActivity.class);
//                goInput.putExtra("SCORE","0");
//                goInput.putExtra("NO","0");
//                goInput.putExtra("CATEGORY","CONDITION");
//                goInput.putExtra("SIMULASI","NOPE");
//                goInput.putExtra("JAWABAN","YA");
//                goInput.putExtra("KATEGORI",KATEGORI);
//                startActivities(new Intent[]{goInput});
            }
        });

    }

    public void LOGICBACK(String kat){
        if (kat.equals("hospital")){

        }else{

        }
    }
}