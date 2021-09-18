package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalFinanceIndicator.HospitalFinanceIndicatorActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalKeyOfSucces.HospitalKeyOfSuccessActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator.HospitalOperationalPerfomanceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalRequirementRatio.HospitalRequirementRatioActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class CreditWorthinessActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    CardView HospitalOperational,HospitalFinanceIndicator,HospitalRequirementRatio,BusinessOppurtinityForBank,HospitalKeyOfSuccess,RiskAndMitigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_worthiness);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        HospitalOperational = findViewById(R.id.cardHospitalOperational);
        HospitalFinanceIndicator = findViewById(R.id.cardHospitalFinanceIndicator);
        HospitalRequirementRatio = findViewById(R.id.cardHosoutakRequirementRatio);
        BusinessOppurtinityForBank = findViewById(R.id.cardBusinessOppurtinity);
        HospitalKeyOfSuccess = findViewById(R.id.cardKeyOfSuccess);
        RiskAndMitigation = findViewById(R.id.cardRiskAndMitigation);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(CreditWorthinessModel.getListData());
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

        HospitalOperational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CreditWorthinessActivity.this, HospitalOperationalPerfomanceActivity.class);
                startActivity(intent);
            }
        });
        HospitalFinanceIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CreditWorthinessActivity.this, HospitalFinanceIndicatorActivity.class);
                startActivity(intent);
            }
        });
        HospitalRequirementRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CreditWorthinessActivity.this, HospitalRequirementRatioActivity.class);
                startActivity(intent);
            }
        });
        BusinessOppurtinityForBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/hospital/credit_worthiness/peluang_bank_di_ekosistem_rumah_sakit.pdf"));
                startActivity(browserIntent);
            }
        });
        HospitalKeyOfSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CreditWorthinessActivity.this, HospitalKeyOfSuccessActivity.class);
                startActivity(intent);
            }
        });
        RiskAndMitigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fabakonsultan.com/uploads/hospital/credit_worthiness/risk-and-mitigation.pdf"));
                startActivity(browserIntent);
            }
        });
    }
}