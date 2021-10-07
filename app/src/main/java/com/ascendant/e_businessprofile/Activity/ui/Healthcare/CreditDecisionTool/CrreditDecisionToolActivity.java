package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FinancialStatementAnalysis.FinancialStatementAnalysisActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FiveC.FiveCActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.RequirementAnalysis.RequirementAnalysisActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.CreditDecisionToolModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class CrreditDecisionToolActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    CardView FiveC,RequirementAnalysis,FinancialStatementAnalysis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crredit_decision_tool);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(CreditDecisionToolModel.getListData());
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

        FiveC = findViewById(R.id.card5CAnalysis);
        RequirementAnalysis = findViewById(R.id.cardRequirementAnalysis);
        FinancialStatementAnalysis = findViewById(R.id.cardFinancialStatementAnalysis);

        FiveC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DB_Helper dbHelper = new DB_Helper(CrreditDecisionToolActivity.this);
                dbHelper.FiveC();
                Intent intent = new Intent(CrreditDecisionToolActivity.this, FiveCActivity.class);
                startActivity(intent);
            }
        });
        RequirementAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrreditDecisionToolActivity.this, RequirementAnalysisActivity.class);
                startActivity(intent);
            }
        });
        FinancialStatementAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrreditDecisionToolActivity.this, FinancialStatementAnalysisActivity.class);
                startActivity(intent);
            }
        });
    }
}