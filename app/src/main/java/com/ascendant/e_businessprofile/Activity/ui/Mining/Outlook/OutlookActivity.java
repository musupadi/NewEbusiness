package com.ascendant.e_businessprofile.Activity.ui.Mining.Outlook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutlookActivity extends AppCompatActivity {
    RelativeLayout BusinesStatus,BusinessProcess,RiskAndMitigation,Regulation,Newsletter,PerkembanganNikel,SumberDayaNikel,ResikoPertambangan;
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Dialog myDialog;
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    DB_Helper dbHelper;
    String Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlook);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        BusinesStatus = findViewById(R.id.relativeBusinessStatus);
        BusinessProcess = findViewById(R.id.relativeBusinessProcess);
        RiskAndMitigation = findViewById(R.id.relativeRiskAndMitigation);
        Regulation = findViewById(R.id.relativeRegulations);
        Newsletter = findViewById(R.id.relativeNewsletter);
        PerkembanganNikel = findViewById(R.id.relativeIndustriNikel);
        SumberDayaNikel = findViewById(R.id.relativeSumberDayaNickel);
        ResikoPertambangan = findViewById(R.id.relativeResikoPertambanganNickel);
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
        pList.addAll(MiningOutlookModel.getListData());
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
        PerkembanganNikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookActivity.this,"pdf","files/mining/outlook/perkembangan_industri_nikel.pdf","Nickel Industry Development");
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
                                Intent i = new Intent(OutlookActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/outlook/perkembangan_industri_nikel/perkembangan_industri_nikel.php");
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
        SumberDayaNikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookActivity.this,"pdf","files/mining/outlook/sumber_daya_dan_cadangan_nikel.pdf","Nickel Resources and Reserves");
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
                                Intent i = new Intent(OutlookActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/outlook/sumber_daya_dan_cadangan_nikel/sumber_daya_dan_cadangan_nikel.php");
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
        ResikoPertambangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookActivity.this,"pdf","files/mining/outlook/risiko_pertambangan_nikel.pdf","Nickel Mining Risk");
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
                                Intent i = new Intent(OutlookActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/outlook/risiko_pertambangan_nikel/risiko_pertambangan_nikel.php");
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
        BusinesStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookActivity.this,"pdf","files/mining/outlook/business_status_and_prospects.pdf","Business Status & Prospects");
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
                                Intent i = new Intent(OutlookActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/outlook/business_status_and_prospects/business_status_and_prospects.php");
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
                        ascendant.Download(OutlookActivity.this,"pdf","files/mining/outlook/business_process.pdf","Mining Business Proccess");
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
                                Intent i = new Intent(OutlookActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/outlook/business_process/business_process.php");
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
                        ascendant.Download(OutlookActivity.this,"pdf","files/mining/outlook/risk_and_mitigation.pdf","Risk & Mitigation");
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
                                Intent i = new Intent(OutlookActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/outlook/risk_and_mitigation/risk_and_mitigation.php");
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
        Regulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookActivity.this,"pdf","files/mining/outlook/regulations.pdf","Regulations");
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
                                Intent i = new Intent(OutlookActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/mining/page/outlook/regulations/regulations.php");
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
        Newsletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OutlookActivity.this,MiningNewsletterActivity.class);
                startActivity(intent);
            }
        });
    }
}