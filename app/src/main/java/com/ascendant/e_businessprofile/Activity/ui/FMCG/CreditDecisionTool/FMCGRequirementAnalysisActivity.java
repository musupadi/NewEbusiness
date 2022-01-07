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

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolFNBRequirementAnalysisModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditDecisionTool.FMCGCreditDecisionToolNFNBRequirementAnalysisModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGRequirementAnalysisActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    TextView toolbar;
    ImageView back,home;
    CardView MakananGula,MinumanTeh,NonFood,Rokok;
    Dialog myDialog;
    Button View,Download;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcg_requirement_analysis);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        Intent data = getIntent();
        final String Tittle = data.getStringExtra("TITTLE");
        if (Tittle.equals("Food")){
            pList.addAll(FMCGCreditDecisionToolFNBRequirementAnalysisModel.getListData());
        }else if (Tittle.equals("Tobacco")){
            pList.addAll(CreditWorthinessModel.getListData());
        }else{
            pList.addAll(FMCGCreditDecisionToolNFNBRequirementAnalysisModel.getListData());
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

        myDialog = new Dialog(FMCGRequirementAnalysisActivity.this);
        myDialog.setContentView(R.layout.dialog_view_download);
        back = findViewById(R.id.ivBack);
        home=findViewById(R.id.ivHome);
        View=myDialog.findViewById(R.id.btnView);
        Download=myDialog.findViewById(R.id.btnDownload);
        MakananGula = findViewById(R.id.linearMakananGula);
        MinumanTeh = findViewById(R.id.linearMinumanTeh);
        NonFood = findViewById(R.id.linearNonFood);
        Rokok = findViewById(R.id.linearRokok);
        toolbar = findViewById(R.id.tvHeader);
        toolbar.setText("Requirment Analysis");
        if (Tittle.equals("Food")){
            NonFood.setVisibility(View.GONE);
            Rokok.setVisibility(View.GONE);
        }else if(Tittle.equals("Tobacco")){
            MakananGula.setVisibility(View.GONE);
            MinumanTeh.setVisibility(View.GONE);
            NonFood.setVisibility(View.GONE);
        }else{
            Rokok.setVisibility(View.GONE);
            MakananGula.setVisibility(View.GONE);
            MinumanTeh.setVisibility(View.GONE);
        }
        MakananGula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-makanan-gula.pdf"));
                        startActivity(browserIntent);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FMCGRequirementAnalysisActivity.this);
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        method.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-makanan-gula.pdf","Makanan Gula",FMCGRequirementAnalysisActivity.this);
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
        MinumanTeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-minuman-teh.pdf"));
                        startActivity(browserIntent);
                    }
                });
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(FMCGRequirementAnalysisActivity.this);
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        method.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-minuman-teh.pdf","Minuman Teh",FMCGRequirementAnalysisActivity.this);
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
        NonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(FMCGRequirementAnalysisActivity.this);
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        method.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-non-fnb.pdf","Non Food",FMCGRequirementAnalysisActivity.this);
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
        Rokok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(FMCGRequirementAnalysisActivity.this);
                        builder.setMessage("Download File ?")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        method.DownloadPDF("http://mandiri-ebusinessraw.the-urbandev.com/uploads/fmcg/kredit_investasi/ki-tobacco.pdf","Rokok",FMCGRequirementAnalysisActivity.this);
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
    }
}