package com.ascendant.e_businessprofile.Activity.ui.Farming.Outlook;

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
import com.ascendant.e_businessprofile.Activity.ui.Mining.Outlook.OutlookActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Farming.Outlook.FarmingOutlook;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutlookFarmingActivity extends AppCompatActivity {
    LinearLayout Regulation,BusinessStatus,BusinessProcess,BusinessReview,KeySuccess,RiskAndMitigation;
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
        setContentView(R.layout.activity_outlook_farming);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_view_download);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }

        Regulation = findViewById(R.id.linearRegulations);
        BusinessStatus = findViewById(R.id.linearBusinessStatus);
        BusinessProcess = findViewById(R.id.linearBusinessProcess);
        BusinessReview = findViewById(R.id.linearBusinessReview);
        KeySuccess = findViewById(R.id.linearKeySucces);
        RiskAndMitigation = findViewById(R.id.linearRiskAndMitigation);
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(FarmingOutlook.getListData());
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
        BusinessReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/farming/outlook/business_review.pdf"));
//                startActivity(browserIntent);
                Intent intent = new Intent(OutlookFarmingActivity.this,FarmingBusinessReviewActivity.class);
                startActivity(intent);
            }
        });
        KeySuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(OutlookFarmingActivity.this,"pdf","files/farming/outlook/key_success_factor.pdf","Key Of Succes Farming");
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
                                Intent i = new Intent(OutlookFarmingActivity.this, LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/farming/page/outlook/key_of_success_factor/key_of_success_factor.php");
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
                        ascendant.Download(OutlookFarmingActivity.this,"pdf","files/farming/outlook/risk_mitigasi.pdf","Business Status & Prospects");
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
                                Intent i = new Intent(OutlookFarmingActivity.this,LandscapeWebViewEbookActivity.class);
                                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/farming/page/outlook/risk_mitigasi/risk_mitigasi.php");
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
                Intent intent= new Intent(OutlookFarmingActivity.this,RegulationsOutlookFarmingActivity.class);
                startActivity(intent);
            }
        });
        BusinessProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(OutlookFarmingActivity.this,FarmingBusinessProccessActivity.class);
                startActivity(intent);
            }
        });
        BusinessStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(OutlookFarmingActivity.this,FarmingBusinessStatusAndProspectActivity.class);
                startActivity(intent);
            }
        });
    }
}