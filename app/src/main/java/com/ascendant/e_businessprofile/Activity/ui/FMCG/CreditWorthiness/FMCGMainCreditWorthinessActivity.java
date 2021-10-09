package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.MarketPotential.FMCGMarketPotentialActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGMainCreditWorthinessActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    CardView FnB,NonFnB,Tobacco,MarketPotential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgmain_credit_worthiness);
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

        FnB = findViewById(R.id.cardFoodAndBeverage);
        NonFnB = findViewById(R.id.cardNonFoodAndBeverage);
        Tobacco = findViewById(R.id.cardTobacco);
        MarketPotential = findViewById(R.id.cardMarketPotential);

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
        FnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(FMCGMainCreditWorthinessActivity.this, FMCGCreditWorthinessActivity.class);
                goInput.putExtra("KATEGORI","fnb");
                startActivity(goInput);
            }
        });
        NonFnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(FMCGMainCreditWorthinessActivity.this, FMCGCreditWorthinessActivity.class);
                goInput.putExtra("KATEGORI","non fnb");
                startActivity(goInput);
            }
        });
        Tobacco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(FMCGMainCreditWorthinessActivity.this, FMCGCreditWorthinessActivity.class);
                goInput.putExtra("KATEGORI","rokok");
                startActivity(goInput);
            }
        });
        MarketPotential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FMCGMainCreditWorthinessActivity.this, FMCGMarketPotentialActivity.class);
                startActivity(intent);
            }
        });
    }
}