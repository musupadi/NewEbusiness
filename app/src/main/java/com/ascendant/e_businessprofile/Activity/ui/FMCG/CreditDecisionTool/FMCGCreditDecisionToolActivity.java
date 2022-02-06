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
import android.widget.TextView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool.FinancialStatementAnalysis.FMCGFinancialStatementAnalysisActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool.FiveC.FMCGFiveCActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolFNBModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolNFNBModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolTobaccoModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGCreditDecisionToolActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    CardView linear5C,linearKreditInvestasi,linearModalKerja;
    TextView header;
    Ascendant method = new Ascendant();
    Dialog myDialog;
    Button View,Download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgcredit_decision_tool);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        Intent data = getIntent();
        final String KATEGORI = data.getStringExtra("KATEGORI");
        if (KATEGORI.equals("NON FOOD")){
            pList.addAll(FMCGCreditDecisionToolNFNBModel.getListData());
        }else if (KATEGORI.equals("FOOD")){
            pList.addAll(FMCGCreditDecisionToolFNBModel.getListData());
        }else{
            pList.addAll(FMCGCreditDecisionToolTobaccoModel.getListData());
        }

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

        myDialog = new Dialog(FMCGCreditDecisionToolActivity.this);
        myDialog.setContentView(R.layout.dialog_view_download);
        linear5C = findViewById(R.id.card5CAnalysis);
        linearKreditInvestasi = findViewById(R.id.cardRequirementAnalysis);
        linearModalKerja = findViewById(R.id.cardFinancialStatementAnalysis);
        View=myDialog.findViewById(R.id.btnView);
        Download=myDialog.findViewById(R.id.btnDownload);

        if (KATEGORI.equals("FOOD")){
            linearKreditInvestasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(FMCGCreditDecisionToolActivity.this, FMCGRequirementAnalysisActivity.class);
                    goInput.putExtra("TITTLE","Food");
                    startActivity(goInput);
                }
            });
        }else if (KATEGORI.equals("NON FOOD ROKOK")){
            linearKreditInvestasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.show();
                    View.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-tobacco.pdf"));
                            startActivity(browserIntent);
                        }
                    });
                    Download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FMCGCreditDecisionToolActivity.this);
                            builder.setMessage("Download File ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            method.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-tobacco.pdf","Rokok",FMCGCreditDecisionToolActivity.this);
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
//                    Intent goInput = new Intent(FMCGCreditDecisionToolActivity.this, KreditInvestasiFMCGActivity.class);
//                    goInput.putExtra("TITTLE","Tobacco");
//                    startActivity(goInput);
                }
            });
        }else if (KATEGORI.equals("NON FOOD")){
            linearKreditInvestasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.show();
                    View.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-non-fnb.pdf"));
                            startActivity(browserIntent);
                        }
                    });
                    Download.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FMCGCreditDecisionToolActivity.this);
                            builder.setMessage("Download File ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            method.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-non-fnb.pdf","Non Food",FMCGCreditDecisionToolActivity.this);
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
//                    Intent goInput = new Intent(FMCGCreditDecisionToolActivity.this, KreditInvestasiFMCGActivity.class);
//                    goInput.putExtra("TITTLE","Non Food");
//                    startActivity(goInput);
                }
            });
        }
        linear5C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGCreditDecisionToolActivity.this, FMCGFiveCActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI);
                DB_Helper dbHelper = new DB_Helper(FMCGCreditDecisionToolActivity.this);
                dbHelper.FiveC();
                startActivity(goInput);
            }
        });

        linearModalKerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGCreditDecisionToolActivity.this, FMCGFinancialStatementAnalysisActivity.class);
                goInput.putExtra("KATEGORI",KATEGORI);
                startActivity(goInput);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FMCGCreditDecisionToolActivity.this,FMCGMainCreditDecisionToolActivity.class);
        startActivity(intent);
    }
}