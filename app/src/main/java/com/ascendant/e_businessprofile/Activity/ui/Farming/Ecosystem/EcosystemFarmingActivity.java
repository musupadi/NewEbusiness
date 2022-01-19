package com.ascendant.e_businessprofile.Activity.ui.Farming.Ecosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.PortraitWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Farming.Ecosystem.Players.PolutryFarmingActivity;
import com.ascendant.e_businessprofile.Activity.ui.Farming.Ecosystem.Players.SuportingIndustryFarmingActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterPerusahaanFarming;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EcosystemFarmingActivity extends AppCompatActivity {
    Spinner Kategori;
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    ProgressBar progressBar;
    DB_Helper dbHelper;
    String Token;

    LinearLayout Available,Navigator;
    RecyclerView rv2,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    LinearLayout Beef,Polutry,Supporting,AssociationFarmer,DirectoryFarmingCompany;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecosystem_farming);
        Beef = findViewById(R.id.linearBeef);
        Polutry = findViewById(R.id.linearPolutry);
        Supporting = findViewById(R.id.linearSupportingIndustries);
        AssociationFarmer = findViewById(R.id.linearLinkMemberAssociatioFarming);
        AssociationFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcosystemFarmingActivity.this,FarmingLinkAssociationActivity.class);
                startActivity(intent);
            }
        });
        Beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcosystemFarmingActivity.this, EcosystemFarmingActivity.class);
                startActivity(intent);
            }
        });
        Polutry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcosystemFarmingActivity.this, PolutryFarmingActivity.class);
                startActivity(intent);
            }
        });
        Supporting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcosystemFarmingActivity.this, SuportingIndustryFarmingActivity.class);
                startActivity(intent);
            }
        });
        DirectoryFarmingCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EcosystemFarmingActivity.this, PortraitWebViewEbookActivity.class);
                i.putExtra("LINK", "https://ebuss-book.mandiri-ebuss.com/farming/page/ekosistem/direktori_perusahaan_pertanian_peternakan_2021.php");
                startActivity(i);
            }
        });
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        rv = findViewById(R.id.recycler);


        //Cut Here
        progressBar = findViewById(R.id.progressBar);
        rv2 = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningOutlookModel.getListData());
        rv2.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv2.setAdapter(adapters);
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
    }
}