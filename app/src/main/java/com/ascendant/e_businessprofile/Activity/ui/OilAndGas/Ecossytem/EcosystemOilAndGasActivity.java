package com.ascendant.e_businessprofile.Activity.ui.OilAndGas.Ecossytem;

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
import com.ascendant.e_businessprofile.Activity.ui.Contractor.Ecosystem.ListContractContractorActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.Model.StaticModel.OilAndGas.Ecosystem.OilAndGasEcosystem;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class EcosystemOilAndGasActivity extends AppCompatActivity {
    LinearLayout DaftarPerusahaan,EcosystemIndustry,DownstreamBusinessEntity,ListCompany,Commercial,BBM,OilAndGas,ListContract;
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecosystem_oil_and_gas);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        ListContract = findViewById(R.id.linearListContract);
        DaftarPerusahaan = findViewById(R.id.linearDaftarPerusahaan);
        EcosystemIndustry = findViewById(R.id.linearEcosystemIndustry);
        DownstreamBusinessEntity = findViewById(R.id.linearDownstreamBusinessEntitty);
        ListCompany = findViewById(R.id.linearListCompany);
        Commercial = findViewById(R.id.linearCommercial);
        BBM= findViewById(R.id.linearListBBM);
        OilAndGas = findViewById(R.id.linearOilAndGasTransportation);
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(OilAndGasEcosystem.getListData());
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

        DaftarPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(EcosystemOilAndGasActivity.this,"pdf","files/oil_and_gas/ekosistem/daftar_perusahaan_migas.pdf","Oil And Gas Regulation Outlook");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(EcosystemOilAndGasActivity.this, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/oil_and_gas/page/ekosistem/daftar_perusahaan_migas.php");
                        startActivity(i);
                    }
                });
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
                        ascendant.Download(EcosystemOilAndGasActivity.this,"pdf","files/oil_and_gas/ekosistem/ekosistem_industri.pdf","Oil And Gas Ecosystem Industry");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(EcosystemOilAndGasActivity.this, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/oil_and_gas/page/ekosistem/ekosistem_industri.php#features/");
                        startActivity(i);
                    }
                });
            }
        });
        DownstreamBusinessEntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(EcosystemOilAndGasActivity.this,DownstreamBusinessEntityOilAndGasActivity.class);
                startActivity(intent);
            }
        });
        ListCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(EcosystemOilAndGasActivity.this,ListCompanyOilAndGasActivity.class);
                startActivity(intent);
            }
        });
        Commercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(EcosystemOilAndGasActivity.this,OilAndGasCommercialActivity.class);
                startActivity(intent);
            }
        });
        BBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(EcosystemOilAndGasActivity.this,ListBBMOilAndGasActivity.class);
                startActivity(intent);
            }
        });
        OilAndGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/oil_and_gas/ekosistem/daftar-bu-status-izin-pengangkutan-aktif-2020.pdf"));
                startActivity(browserIntent);
            }
        });
        ListContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(EcosystemOilAndGasActivity.this, ListContractContractorActivity.class);
                startActivity(intent);
            }
        });
    }
}