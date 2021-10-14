package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.Operational;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness.Financial.FMCGCreditWorthinessHitunganActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessFNBModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessFNBOperationalModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessNFNBModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessNFNBOperationalModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessTobaccoModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessTobaccoOperationalModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class FMCGCreditWorthinessOperationalActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    Button DOR,DOI,DOP,AssetTurnover,InventoryTurnover;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgcredit_worthiness_operational);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
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

        Intent data = getIntent();
        final String KATEGORI = data.getStringExtra("KATEGORI");
        if (KATEGORI.equals("fnb")){
            pList.addAll(FMCGCreditWorthinessFNBOperationalModel.getListData());
        }else if (KATEGORI.equals("non fnb")){
            pList.addAll(FMCGCreditWorthinessNFNBOperationalModel.getListData());
        }else{
            pList.addAll(FMCGCreditWorthinessTobaccoOperationalModel.getListData());
        }
        DOR = findViewById(R.id.btnDOR);
        DOI = findViewById(R.id.btnDOI);
        DOP = findViewById(R.id.btnDOP);
        AssetTurnover=findViewById(R.id.btnAssetTurnover);
        InventoryTurnover=findViewById(R.id.btnInventoryTurnover);

        DOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGCreditWorthinessOperationalActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","DOR");
                goInput.putExtra("KATEGORI","PRINCIPAL");
                goInput.putExtra("HEADER","FMCG / Credit Worthiness / Principal / Operational");
                startActivity(goInput);
            }
        });
        DOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGCreditWorthinessOperationalActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","DOI");
                goInput.putExtra("KATEGORI","PRINCIPAL");
                goInput.putExtra("HEADER","FMCG / Credit Worthiness / Principal / Operational");
                startActivity(goInput);
            }
        });
        DOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGCreditWorthinessOperationalActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","DOP");
                goInput.putExtra("KATEGORI","PRINCIPAL");
                goInput.putExtra("HEADER","FMCG / Credit Worthiness / Principal / Operational");
                startActivity(goInput);
            }
        });
        AssetTurnover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGCreditWorthinessOperationalActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","ASSET TURNOVER");
                goInput.putExtra("KATEGORI","PRINCIPAL");
                goInput.putExtra("HEADER","FMCG / Credit Worthiness / Principal / Operational");
                startActivity(goInput);
            }
        });
        InventoryTurnover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(FMCGCreditWorthinessOperationalActivity.this, FMCGCreditWorthinessHitunganActivity.class);
                goInput.putExtra("HITUNG","INVENTORY TURNOVER");
                goInput.putExtra("KATEGORI","PRINCIPAL");
                goInput.putExtra("HEADER","FMCG / Credit Worthiness / Principal / Operational");
                startActivity(goInput);
            }
        });
    }
}