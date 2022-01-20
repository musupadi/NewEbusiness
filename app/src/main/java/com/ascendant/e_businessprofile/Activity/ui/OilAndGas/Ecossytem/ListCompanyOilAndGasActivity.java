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
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class ListCompanyOilAndGasActivity extends AppCompatActivity {
    LinearLayout Industrial,NonConstruction,Construction;
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
        setContentView(R.layout.activity_list_company_oil_and_gas);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        Industrial = findViewById(R.id.linearIndustrialBusiness);
        NonConstruction = findViewById(R.id.linearNonConstruction);
        Construction = findViewById(R.id.linearConstructionBusiness);
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
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);

        Back.setOnClickListener(new android.view.View.OnClickListener() {
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
        Industrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/oil_and_gas/ekosistem/penunjang-bidang-usaha-industri-2018.pdf"));
                startActivity(browserIntent);
            }
        });
        NonConstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/oil_and_gas/ekosistem/penunjang-bidang-usaha-nonkonstruksi-2018.pdf"));
                startActivity(browserIntent);
            }
        });
        Construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/oil_and_gas/ekosistem/bu-penyimpanan-status-triwulan.pdf"));
                startActivity(browserIntent);
            }
        });
    }
}