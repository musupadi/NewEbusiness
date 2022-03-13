package com.ascendant.e_businessprofile.Activity.ui.Contractor.MarketInteligence;

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
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class MarketInteliganceContractorActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    Dialog myDialog;
    TextView Judul;
    LinearLayout MarketingPotential,Benchmarking;
    String JUDUL;
    String LinkPDF,LinkEbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_inteligance_contractor);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        Intent data = getIntent();
        JUDUL = data.getStringExtra("JUDUL");
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
        });//Cut Here
        Judul = findViewById(R.id.tvJudul);
        MarketingPotential = findViewById(R.id.linearMarketingPotential);
        Benchmarking = findViewById(R.id.linearBenchmarking);
        Judul.setText(JUDUL);



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

        MarketingPotential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                if (!JUDUL.equals("Procurement of Goods and Services")){
                    LinkPDF="files/contractor/market_intelligence/market_potential_jasa.pdf";
                    LinkEbook="https://ebuss-book.mandiri-ebuss.com/contractor/page/market_intelligence/market_potential_jasa.php";
                }else{
                    LinkPDF="files/contractor/market_intelligence/market_potential_pengadaan.pdf";
                    LinkEbook="https://ebuss-book.mandiri-ebuss.com/contractor/page/market_intelligence/market_potential_pengadaan.php";
                }
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(MarketInteliganceContractorActivity.this,"pdf",LinkPDF,"Market Potential "+JUDUL);
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        if (LinkEbook.equals("")){
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ascendant.BASE_URL()+LinkPDF));
                            startActivity(browserIntent);
                        }else{
                            Intent i = new Intent(MarketInteliganceContractorActivity.this, LandscapeWebViewEbookActivity.class);
                            i.putExtra("LINK", LinkEbook);
                            startActivity(i);
                        }
                    }
                });
            }
        });
        Benchmarking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                if (!JUDUL.equals("Procurement of Goods and Services")){
                    LinkPDF="http://mandiri-ebuss.com/files/contractor/market_intelligence/benchmarking_jasa.pdf";
                    LinkEbook="https://ebuss-book.mandiri-ebuss.com/contractor/page/market_intelligence/benchmarking_jasa_konstruksi.php";
                }else{
                    LinkPDF="http://mandiri-ebuss.com/files/contractor/market_intelligence/benchmarking_pengadaan.pdf";
                    LinkEbook="";
                }
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(MarketInteliganceContractorActivity.this,"pdf",LinkPDF,"Benchmarking "+JUDUL);
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        if (LinkEbook.equals("")){
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ascendant.BASE_URL()+LinkPDF));
                            startActivity(browserIntent);
                        }else{
                            Intent i = new Intent(MarketInteliganceContractorActivity.this, LandscapeWebViewEbookActivity.class);
                            i.putExtra("LINK", LinkEbook);
                            startActivity(i);
                        }
                    }
                });


            }
        });
    }
}