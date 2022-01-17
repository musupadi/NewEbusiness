package com.ascendant.e_businessprofile.Activity.ui.Farming.Ecosystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.ui.Farming.Ecosystem.Players.BeefFarmingActivity;
import com.ascendant.e_businessprofile.Activity.ui.Farming.Ecosystem.Players.PolutryFarmingActivity;
import com.ascendant.e_businessprofile.Activity.ui.Farming.Ecosystem.Players.SuportingIndustryFarmingActivity;
import com.ascendant.e_businessprofile.R;

public class EcosystemFarmingActivity extends AppCompatActivity {
    LinearLayout Beef,Polutry,Supporting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecosystem_farming);
        Beef = findViewById(R.id.linearBeef);
        Polutry = findViewById(R.id.linearPolutry);
        Supporting = findViewById(R.id.linearSupportingIndustries);

        Beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcosystemFarmingActivity.this, BeefFarmingActivity.class);
                startActivity(intent);
            }
        });
        Polutry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcosystemFarmingActivity.this, PolutryFarmingActivity.class);
                startActivity(intent);
            }
        });
        Supporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcosystemFarmingActivity.this, SuportingIndustryFarmingActivity.class);
                startActivity(intent);
            }
        });
    }
}