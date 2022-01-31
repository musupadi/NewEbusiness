package com.ascendant.e_businessprofile.Activity.ui.Contractor.Ecosystem;

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
import com.ascendant.e_businessprofile.Activity.ui.Contractor.Outlook.OtulookActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class EcosystemContractorActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    Dialog myDialog;

    LinearLayout PlayersInConstruction,PlayersInSupplyMaterial,SupportingPlayers,EcosystemIndustry,Executive,Consultant,DataBadanUsaha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecosystem_contractor);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        PlayersInConstruction = findViewById(R.id.linearPlayersInConstruction);
        PlayersInSupplyMaterial = findViewById(R.id.linearPlayersInSupplyingMaterials);
        SupportingPlayers = findViewById(R.id.linearSupportingPlayers);
        EcosystemIndustry = findViewById(R.id.linearEcosystemIndustry);
        Executive = findViewById(R.id.linearExecutive);
        Consultant = findViewById(R.id.linearConsultant);
        DataBadanUsaha = findViewById(R.id.linearBinakonstruksi);
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningOutlookModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this, pList);
        rv.setAdapter(adapters);

        Back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                onBackPressed();
            }
        });
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (more) {
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        Available.setVisibility(View.GONE);
                        Navigator.setVisibility(View.VISIBLE);
                    } else {
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        Available.setVisibility(View.VISIBLE);
                        Navigator.setVisibility(View.GONE);
                    }
                } catch (Exception e) {

                }

            }
        });
        //Cut Here
        DataBadanUsaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://binakonstruksi.pu.go.id/data-badan-usaha-jasa-konstruksi/"));
                startActivity(browserIntent);
            }
        });
        PlayersInConstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/contractor/ekosistem/players_in_construction.pdf"));
//                startActivity(browserIntent);
                Intent intent = new Intent(EcosystemContractorActivity.this,PlayersInConstructionActivity.class);
                startActivity(intent);
            }
        });
        PlayersInSupplyMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/contractor/ekosistem/players_in_supply_material.pdf"));
                startActivity(browserIntent);
            }
        });
        SupportingPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/contractor/ekosistem/supporting_players.pdf"));
                startActivity(browserIntent);
            }
        });
        EcosystemIndustry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(EcosystemContractorActivity.this,"pdf","files/contractor/ekosistem/ekosistem_pengadaan.pdf","Ecosystem Industry");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(EcosystemContractorActivity.this, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/contractor/page/ekosistem/proses_bisnis_pengadaan_barang_dan_jasa.php");
                        startActivity(i);
                    }
                });
            }
        });
        Executive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(EcosystemContractorActivity.this,ExecutiveEcosystemContractorActivity.class);
                startActivity(intent);
            }
        });
        Consultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(EcosystemContractorActivity.this,ConsultantEcosystemContractorActivity.class);
                startActivity(intent);
            }
        });
    }
}