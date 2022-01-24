package com.ascendant.e_businessprofile.Activity.ui.Farming.Outlook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ascendant.e_businessprofile.Activity.ui.Mining.Outlook.MiningNewsletterActivity;
import com.ascendant.e_businessprofile.Activity.ui.Mining.Outlook.OutlookActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class OutlookFarmingActivity extends AppCompatActivity {
    LinearLayout Regulation,BusinessStatus,BusinessProcess,BusinessReview,KeySuccess,RiskAndMitigation;
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlook_farming);
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
        BusinessReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/farming/outlook/business_review.pdf"));
                startActivity(browserIntent);
            }
        });
        KeySuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/farming/outlook/key_success_factor.pdf"));
                startActivity(browserIntent);
            }
        });
        RiskAndMitigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mandiri-ebuss.com/files/farming/outlook/business_review.pdf"));
                startActivity(browserIntent);
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