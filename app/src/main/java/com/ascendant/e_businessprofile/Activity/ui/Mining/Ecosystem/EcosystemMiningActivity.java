package com.ascendant.e_businessprofile.Activity.ui.Mining.Ecosystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.R;

public class EcosystemMiningActivity extends AppCompatActivity {
    LinearLayout Stakeholder,PerusahaanJasa,PerusahaanTambang,DaftarPerusahaan,MinerbaOneMap;
    Ascendant ascendant = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecosystem_mining);
        Stakeholder = findViewById(R.id.linearStakeHolder);
        PerusahaanJasa = findViewById(R.id.linearPerusahaanJasaPertambangan);
        PerusahaanTambang = findViewById(R.id.linearPerusahaanTambangBatubara);
        DaftarPerusahaan = findViewById(R.id.linearDaftarPerusahaanTambang);
        MinerbaOneMap = findViewById(R.id.linearMinerbaOneMap);
        Stakeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ebuss-raw.the-urbandev.com/uploads/mining/ekosistem/stakeholder_tambang_batubara.pdf"));
                startActivity(browserIntent);
            }
        });
        PerusahaanJasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://aspindo-imsa.or.id/?wpbdp_category=anggota-aspindo"));
                startActivity(i);
            }
        });
        PerusahaanTambang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://apbi-icma.org/member"));
                startActivity(i);
            }
        });
        DaftarPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://modi.esdm.go.id/portal/dataPerusahaan"));
                startActivity(i);
            }
        });
        MinerbaOneMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://momi.minerba.esdm.go.id/public/"));
                startActivity(i);
            }
        });
    }
}