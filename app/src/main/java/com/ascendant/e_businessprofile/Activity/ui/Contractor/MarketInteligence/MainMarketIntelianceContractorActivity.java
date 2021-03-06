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

import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Contractor.MarketIntelligence.ContractorMarketIntelligence;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class MainMarketIntelianceContractorActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    Dialog myDialog;
    LinearLayout Procurment,Service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_market_inteliance_contractor);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(ContractorMarketIntelligence.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this, pList);
        rv.setAdapter(adapters);

        Back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                onBackPressed();
            }
        });
        More.setOnClickListener(new android.view.View.OnClickListener() {
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

        Procurment = findViewById(R.id.linearProcurment);
        Service = findViewById(R.id.linearSerice);

        Procurment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
//                Intent goInput = new Intent(MainMarketIntelianceContractorActivity.this, MarketInteliganceContractorActivity.class);
//                goInput.putExtra("JUDUL","Procurement of Goods and Services");
//                startActivities(new Intent[]{goInput});
                myDialog.show();
                String LinkPDF="files/contractor/market_intelligence/market_potential_pengadaan.pdf";
                String LinkEbook="https://ebuss-book.mandiri-ebuss.com/contractor/page/market_intelligence/market_potential_pengadaan.php";
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(MainMarketIntelianceContractorActivity.this,"pdf",LinkPDF,"Procurment of Good and Services");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        if (LinkEbook.equals("")){
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ascendant.BASE_URL()+LinkPDF));
                            startActivity(browserIntent);
                        }else{
                            Intent i = new Intent(MainMarketIntelianceContractorActivity.this, LandscapeWebViewEbookActivity.class);
                            i.putExtra("LINK", LinkEbook);
                            startActivity(i);
                        }
                    }
                });
            }
        });
        Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent goInput = new Intent(MainMarketIntelianceContractorActivity.this, MarketInteliganceContractorActivity.class);
                goInput.putExtra("JUDUL","Construction Services");
                startActivities(new Intent[]{goInput});
            }
        });
    }
}