package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.Financial.FMCGSimulationFinancialActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.Operational.FMCGCreditWorthinessOperationalActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessFNBModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessNFNBModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessTobaccoModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGCreditWorthinessActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    CardView Operational,Financial,Parameters,KeyOfSuccess,RiskAndMitigation;


    Dialog myDialog;
    Button download,view;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgcredit_worthiness);

        //Dialog
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        view = myDialog.findViewById(R.id.btnView);
        download = myDialog.findViewById(R.id.btnDownload);
        //Dialog

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Operational = findViewById(R.id.cardOperational);
        Financial = findViewById(R.id.cardFinancial);
        Parameters = findViewById(R.id.cardParameters);
        KeyOfSuccess = findViewById(R.id.cardKeyOfSuccess);
        RiskAndMitigation = findViewById(R.id.cardRiskAndMitigation);
        Available.setVisibility(View.VISIBLE);

        Intent data = getIntent();
        final String KATEGORI = data.getStringExtra("KATEGORI");
        if (KATEGORI.equals("fnb")){
            pList.addAll(FMCGCreditWorthinessFNBModel.getListData());
        }else if (KATEGORI.equals("non fnb")){
            pList.addAll(FMCGCreditWorthinessNFNBModel.getListData());
        }else{
            pList.addAll(FMCGCreditWorthinessTobaccoModel.getListData());
        }
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

        Operational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(FMCGCreditWorthinessActivity.this, FMCGCreditWorthinessOperationalActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        Financial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(FMCGCreditWorthinessActivity.this, FMCGSimulationFinancialActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
        Parameters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (KATEGORI.equals("fnb")){
                    ShowMethod("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/param/food.pdf","Food & Beverage");
                }else if(KATEGORI.equals("non fnb")){
                    ShowMethod("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/param/non_food.pdf","Non Food & Beverage");
                }else{
                    ShowMethod("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/param/tobacco.pdf","Tobacco");
                }
            }
        });
        KeyOfSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (KATEGORI.equals("non fnb")){
                    Intent goInput = new Intent(FMCGCreditWorthinessActivity.this, FMCGKeyOfSuccessActivity.class);
                    goInput.putExtra("KOS","household");
                    startActivity(goInput);
                }else{
                    Intent goInput = new Intent(FMCGCreditWorthinessActivity.this, FMCGKeyOfSuccessActivity.class);
                    goInput.putExtra("KOS",KATEGORI);
                    startActivity(goInput);
                }
            }
        });
        RiskAndMitigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (KATEGORI.equals("fnb")){
                    ShowMethod("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/risk-mitigasi-fnb.pdf","Food & Beverage");
                }else if(KATEGORI.equals("non fnb")){
                    ShowMethod("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/risk-mitigasi-household.pdf","Non Food & Beverage");
                }else{
                    ShowMethod("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/risk-mitigasi-rokok.pdf","Tobacco");
                }
            }
        });
    }
    private void ShowMethod(final String url,final String judul){
        myDialog.show();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FMCGCreditWorthinessActivity.this);
                builder.setMessage("Download File ?")
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                method.DownloadPDF(url,judul,FMCGCreditWorthinessActivity.this);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Set your icon here
                        .setTitle("Perhatian !!!")
                        .setIcon(R.drawable.print_primary);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}