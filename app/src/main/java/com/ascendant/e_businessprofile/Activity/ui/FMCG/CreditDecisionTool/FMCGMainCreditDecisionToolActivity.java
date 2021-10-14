package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool.SustainableFinancing.FMCGSustainableFinancingActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGMainCreditDecisionToolActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    CardView food,nonfood,tobacco,sustainablefinancing,supplychain;
    ImageView back,home;
    Dialog myDialog;
    Button View,Download;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcg_main_credit_decision_tool);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(FMCGCreditDecisionToolModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
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

        myDialog = new Dialog(FMCGMainCreditDecisionToolActivity.this);
        myDialog.setContentView(R.layout.dialog_view_download);
        nonfood=findViewById(R.id.cardNonFoodAndBeverage);
        food=findViewById(R.id.cardFoodAndBeverage);
        tobacco=findViewById(R.id.cardTobacco);
        sustainablefinancing=findViewById(R.id.cardSustainableFinancing);
        supplychain=findViewById(R.id.cardSupplyChain);
        View=myDialog.findViewById(R.id.btnView);
        Download=myDialog.findViewById(R.id.btnDownload);
        supplychain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/fmcg/business_refrences/ebook/materi_supply_chain_financing_&_trade_financing.pdf"));
                        startActivity(browserIntent);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FMCGMainCreditDecisionToolActivity.this);
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        method.DownloadPDF("https://fabakonsultan.com/uploads/fmcg/business_refrences/ebook/materi_supply_chain_financing_&_trade_financing.pdf","Supply Chain Financing & Trade Financing",FMCGMainCreditDecisionToolActivity.this);
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
        });
        nonfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGMainCreditDecisionToolActivity.this, FMCGCreditDecisionToolActivity.class);
                goInput.putExtra("KATEGORI","NON FOOD");
                startActivity(goInput);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGMainCreditDecisionToolActivity.this, FMCGCreditDecisionToolActivity.class);
                goInput.putExtra("KATEGORI","FOOD");
                startActivity(goInput);
            }
        });
        tobacco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGMainCreditDecisionToolActivity.this, FMCGCreditDecisionToolActivity.class);
                goInput.putExtra("KATEGORI","NON FOOD ROKOK");
                startActivity(goInput);
            }
        });
        sustainablefinancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGMainCreditDecisionToolActivity.this, FMCGSustainableFinancingActivity.class);
                startActivity(goInput);
            }
        });
    }
}