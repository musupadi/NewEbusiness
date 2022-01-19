package com.ascendant.e_businessprofile.Activity.ui.Farming.MarketingInteligence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.R;

public class MarketingIntelienceFarmingActivity extends AppCompatActivity {
    LinearLayout Benchmarking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_intelience_farming);
        Benchmarking = findViewById(R.id.linearBenchmarking);

        Benchmarking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/farming/market_intelligence/benchmark/LAPORAN_LABA_RUGI_DAN_NERACA_PETERNAKAN_AYAM.pdf"));
                startActivity(browserIntent);
            }
        });
    }
}