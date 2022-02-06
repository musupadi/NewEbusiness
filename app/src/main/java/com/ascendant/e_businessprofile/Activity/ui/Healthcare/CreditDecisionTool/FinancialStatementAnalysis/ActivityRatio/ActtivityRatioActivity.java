package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FinancialStatementAnalysis.ActivityRatio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FinancialStatementAnalysis.LiquidityRatio.CurrentRatioActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FinancialStatementAnalysis.LiquidityRatio.LquidityRatioActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FinancialStatementAnalysis.LiquidityRatio.QuickRatioActivityActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FinancialStatementAnalysisModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class ActtivityRatioActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    CardView Receivable,Inventory,Payable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acttivity_ratio);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(FinancialStatementAnalysisModel.getListData());
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
       Receivable = findViewById(R.id.cardReceiableTurnover);
       Inventory = findViewById(R.id.cardInventoryTurnOver);
       Payable = findViewById(R.id.cardPayableTurnover);
       Receivable.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(ActtivityRatioActivity.this,ReceivableTurnoverActivity.class);
               startActivity(intent);
           }
       });
       Inventory.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(ActtivityRatioActivity.this,InventoryTurnoverActivity.class);
               startActivity(intent);
           }
       });
       Payable.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(ActtivityRatioActivity.this,PayableTurnoverActivity.class);
               startActivity(intent);
           }
       });
    }
}