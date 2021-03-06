package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FiveC;

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

import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.CrreditDecisionToolActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.CreditDecisionToolModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FiveCModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FiveCActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();


    Button Character,Capacity,Capital,Colleteral,Condition,Total,Skor,Save;
    String kategori,scoreCaracter,scoreCapital,scoreCapacity,scoreColleteral,scoreCondition,caracter,capital,capacity,colleteral,condition;
    int hitung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_cactivity);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(FiveCModel.getListData());
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

        Character = findViewById(R.id.btnCharacter);
        Capacity = findViewById(R.id.btnCapacity);
        Capital = findViewById(R.id.btnCapital);
        Colleteral = findViewById(R.id.btnCollateral);
        Condition = findViewById(R.id.btnCondition);
        Total = findViewById(R.id.btnTotal);
        Skor = findViewById(R.id.btnSkor);
        Save = findViewById(R.id.btnSave);

        final DB_Helper dbHelper = new DB_Helper(FiveCActivity.this);
        //Character
        Cursor cursor = dbHelper.checkQuiz("CHARACTER");
        hitung = 0;
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                caracter = cursor.getString(0);
                scoreCaracter = cursor.getString(1);
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
                Intent goInput = new Intent(FiveCActivity.this,SoalActivity.class);
                goInput.putExtra("SCORE","0");
                goInput.putExtra("NO","0");
                goInput.putExtra("CATEGORY","CHARACTER");
                goInput.putExtra("SIMULASI","NOPE");
                goInput.putExtra("JAWABAN","YA");
                startActivities(new Intent[]{goInput});
            }
        });
        Capacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FiveCActivity.this,SoalActivity.class);
                goInput.putExtra("SCORE","0");
                goInput.putExtra("NO","0");
                goInput.putExtra("CATEGORY","CAPACITY");
                goInput.putExtra("SIMULASI","NOPE");
                goInput.putExtra("JAWABAN","YA");
                startActivities(new Intent[]{goInput});
            }
        });
        Capital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FiveCActivity.this,SoalActivity.class);
                goInput.putExtra("SCORE","0");
                goInput.putExtra("NO","0");
                goInput.putExtra("CATEGORY","CAPITAL");
                goInput.putExtra("SIMULASI","NOPE");
                goInput.putExtra("JAWABAN","YA");
                startActivities(new Intent[]{goInput});
            }
        });
        Colleteral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FiveCActivity.this,SoalActivity.class);
                goInput.putExtra("SCORE","0");
                goInput.putExtra("NO","0");
                goInput.putExtra("CATEGORY","COLLATERAL");
                goInput.putExtra("SIMULASI","NOPE");
                goInput.putExtra("JAWABAN","YA");
                startActivities(new Intent[]{goInput});
            }
        });
        Condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FiveCActivity.this,SoalActivity.class);
                goInput.putExtra("SCORE","0");
                goInput.putExtra("NO","0");
                goInput.putExtra("CATEGORY","CONDITION");
                goInput.putExtra("SIMULASI","NOPE");
                goInput.putExtra("JAWABAN","YA");
                startActivities(new Intent[]{goInput});
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FiveCActivity.this, CrreditDecisionToolActivity.class);
        startActivity(intent);
    }
}