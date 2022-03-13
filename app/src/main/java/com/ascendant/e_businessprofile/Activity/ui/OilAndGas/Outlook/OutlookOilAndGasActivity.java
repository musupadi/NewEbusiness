package com.ascendant.e_businessprofile.Activity.ui.OilAndGas.Outlook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.Model.StaticModel.OilAndGas.Outlook.OilAndGasOutlook;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutlookOilAndGasActivity extends AppCompatActivity {
    LinearLayout Regulation,BusinessStatus,BusinessProcess,RiskAndMitigation,KeySucces;
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    Dialog myDialog;
    DB_Helper dbHelper;
    String Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlook_oil_and_gas);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        Regulation = findViewById(R.id.linearRegulations);
        BusinessStatus = findViewById(R.id.linearBusinessStatus);
        BusinessProcess = findViewById(R.id.linearBusinessProcess);
        RiskAndMitigation = findViewById(R.id.linearRiskAndMitigation);
        KeySucces = findViewById(R.id.linearKeySucces);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(OilAndGasOutlook.getListData());
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

        //Cut Here
        Regulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookOilAndGasActivity.this,"pdf","files/oil_and_gas/outlook/regulations.pdf","Oil And Gas Regulation Outlook");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        final Call<ResponseObject> data =api.OpenEbook(Token);
                        data.enqueue(new Callback<ResponseObject>() {
                            @Override
                            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                                Intent i = new Intent(OutlookOilAndGasActivity.this, LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/oil_and_gas/page/outlook/regulations/regulations.php");
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(Call<ResponseObject> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        });
        BusinessProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookOilAndGasActivity.this,"pdf","files/oil_and_gas/outlook/business_process.pdf","Oil And Gas Business Process Outlook");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Intent i = new Intent(OutlookOilAndGasActivity.this, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/oil_and_gas/page/outlook/business_process/business_process.php");
                        startActivity(i);
                    }
                });
            }
        });
        BusinessStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookOilAndGasActivity.this,"pdf","files/oil_and_gas/outlook/business_status_and_prospects.pdf","Oil And Gas Business Status & Prospects Outlook");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        final Call<ResponseObject> data =api.OpenEbook(Token);
                        data.enqueue(new Callback<ResponseObject>() {
                            @Override
                            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                                Intent i = new Intent(OutlookOilAndGasActivity.this, LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/oil_and_gas/page/outlook/business_status_and_prospects/business_status_and_prospects.php");
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(Call<ResponseObject> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        });
        RiskAndMitigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookOilAndGasActivity.this,"pdf","files/oil_and_gas/outlook/risk_and_mitigations.pdf","Oil And Gas Risk & Mitigation Outlook");
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                        final Call<ResponseObject> data =api.OpenEbook(Token);
                        data.enqueue(new Callback<ResponseObject>() {
                            @Override
                            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                                Intent i = new Intent(OutlookOilAndGasActivity.this, LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/oil_and_gas/page/outlook/risk_and_mitigation/risk_and_mitigation.php");
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(Call<ResponseObject> call, Throwable t) {

                            }
                        });
                    }
                });
            }
        });
        KeySucces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/oil_and_gas/outlook/key_success_factor.pdf"));
                startActivity(browserIntent);
            }
        });
    }
}