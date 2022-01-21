package com.ascendant.e_businessprofile.Activity.ui.Contractor.Outlook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.ui.Mining.Outlook.OutlookActivity;
import com.ascendant.e_businessprofile.Activity.ui.OilAndGas.Outlook.OutlookOilAndGasActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class OtulookActivity extends AppCompatActivity {
    LinearLayout Procurment,Construction;
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
        setContentView(R.layout.activity_otulook);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        Procurment = findViewById(R.id.linearProcurment);
        Construction = findViewById(R.id.linearConstruction);


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
        Procurment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OtulookActivity.this,"pdf","files/contractor/outlook/kontraktor_pengadaan.pdf","Procurement of Goods and Services");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(OtulookActivity.this, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/contractor/page/outlook/pengadaan_jasa_dan_barang.php");
                        startActivity(i);
                    }
                });
            }
        });
        Construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OtulookActivity.this,"pdf","files/contractor/outlook/kontraktor_jasa.pdf","Procurement of Goods and Services");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(OtulookActivity.this, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/contractor/page/outlook/jasa_konstruksi.php");
                        startActivity(i);
                    }
                });
            }
        });
    }
}