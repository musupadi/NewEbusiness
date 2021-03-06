package com.ascendant.e_businessprofile.Activity.ui.Mining.Ecosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Ecosystem.MiningEcosystemModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class EcosystemMiningActivity extends AppCompatActivity {
    LinearLayout Stakeholder,PerusahaanJasa,PerusahaanTambang,DaftarPerusahaan,MinerbaOneMap;
    Ascendant ascendant = new Ascendant();

    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    Dialog myDialog;
    Button View,Download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecosystem_mining);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        Stakeholder = findViewById(R.id.linearStakeHolder);
        PerusahaanJasa = findViewById(R.id.linearPerusahaanJasaPertambangan);
        PerusahaanTambang = findViewById(R.id.linearPerusahaanTambangBatubara);
        DaftarPerusahaan = findViewById(R.id.linearDaftarPerusahaanTambang);
        MinerbaOneMap = findViewById(R.id.linearMinerbaOneMap);
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningEcosystemModel.getListData());
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

        //Cut Here


        Stakeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(EcosystemMiningActivity.this,"pdf","files/mining/ekosistem/stakeholder_tambang_batubara.pdf","Stakeholder Mining");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(EcosystemMiningActivity.this, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/ekosistem/stakeholder_tambang_batubara.php");
                        startActivity(i);
                    }
                });
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