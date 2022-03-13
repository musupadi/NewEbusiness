package com.ascendant.e_businessprofile.Activity.ui.Mining.MarketInteligence.Benchmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.ascendant.e_businessprofile.R;

import java.text.DecimalFormat;

public class MiningBenchmarkActivity extends AppCompatActivity {
    //A
    EditText SROB,CostOB,MiningCostRaw;
    //B
    EditText KMCoalHauling,TotalCoalHauling,KMRoadMaintenance,RoadMaintenance,TotalCostPortMGT;
    Double a=0.00;
    Double b=0.00;
    Double c = 38.03;
    DecimalFormat formatData;

    //Total
    EditText SubTotalMiningDelivery,SubtotalCostBeforeVAT,VAT,SubtotalCostAfterVAT,IndicativeProfit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mining_benchmark);
        formatData = new DecimalFormat("#.##");
        SROB = findViewById(R.id.etSROB);
        CostOB = findViewById(R.id.etCostTotalOB);
        MiningCostRaw = findViewById(R.id.etTotalMiningCostRaw);
        KMCoalHauling = findViewById(R.id.etKMCoalHauling);
        TotalCoalHauling = findViewById(R.id.etTotalCoalHauling);
        KMRoadMaintenance = findViewById(R.id.etKMRoarMaintenance);
        RoadMaintenance = findViewById(R.id.etRoadMaintenance);
        TotalCostPortMGT = findViewById(R.id.etTotalCostAndPortMgt);
        //
        SubTotalMiningDelivery = findViewById(R.id.etSubTotalMiningDelivery);
        SubtotalCostBeforeVAT = findViewById(R.id.etSubTotalBeforeVAT);
        VAT = findViewById(R.id.etVAT);
        SubtotalCostAfterVAT= findViewById(R.id.etTotalCostAfterTax);
        IndicativeProfit = findViewById(R.id.etIndencitiveProfit);
        //
        SROB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                HitungOB();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        KMCoalHauling.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                HitungCoalHauling();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        KMRoadMaintenance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                HitungRoadMaintenance();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void HitungOB(){
        if (!SROB.getText().toString().isEmpty()){
            Integer OB = Integer.parseInt(SROB.getText().toString());
            CostOB.setText("$ "+String.valueOf(OB*2));
            MiningCostRaw.setText("$ "+String.valueOf(formatData.format((OB*2)+1+3.5)));
            a=(OB*2)+1+3.5;
            Hitungan();
        }else{
            CostOB.setText("$ "+String.valueOf(0));
            MiningCostRaw.setText("$ "+String.valueOf(formatData.format(1+3.5)));
            a=1+3.5;
            Hitungan();
        }
    }
    private void HitungCoalHauling(){
        if (!KMCoalHauling.getText().toString().isEmpty()){
            Double CoalHauling = Double.parseDouble(KMCoalHauling.getText().toString());
            TotalCoalHauling.setText("$ "+String.valueOf(formatData.format(CoalHauling*0.15)));
            HitungStockpile();
            Hitungan();
        }else{
            TotalCoalHauling.setText("$ 0");
            Hitungan();
        }
    }
    private void HitungRoadMaintenance(){
        if (!KMRoadMaintenance.getText().toString().isEmpty()){
            Double Road = Double.parseDouble(KMRoadMaintenance.getText().toString());
            RoadMaintenance.setText("$ "+String.valueOf(formatData.format(Road*0.025)));
            HitungStockpile();
            Hitungan();
        }else{
            RoadMaintenance.setText("$ 0");
            Hitungan();
        }
    }
    private void HitungStockpile(){
        if (!KMRoadMaintenance.getText().toString().isEmpty() && !KMCoalHauling.getText().toString().isEmpty()){
            Double CoalHauling = Double.parseDouble(KMCoalHauling.getText().toString());
            Double Road = Double.parseDouble(KMRoadMaintenance.getText().toString());
            TotalCostPortMGT.setText("$ "+String.valueOf(formatData.format((CoalHauling*0.15)+(Road*0.25)+1.50+2.50)));
            b=(CoalHauling*0.15)+(Road*0.25)+1.50+2.50;
            Hitungan();
        }else if (!KMRoadMaintenance.getText().toString().isEmpty() && KMCoalHauling.getText().toString().isEmpty()){
//            Double CoalHauling = Double.parseDouble(KMCoalHauling.getText().toString());
            Double Road = Double.parseDouble(KMRoadMaintenance.getText().toString());
            TotalCostPortMGT.setText("$ "+String.valueOf(formatData.format((Road*0.25)+1.50+2.50)));
            b=(Road*0.25)+1.50+2.50;
            Hitungan();
        }else if (KMRoadMaintenance.getText().toString().isEmpty() && !KMCoalHauling.getText().toString().isEmpty()){
            Double CoalHauling = Double.parseDouble(KMCoalHauling.getText().toString());
//            Double Road = Double.parseDouble(KMRoadMaintenance.getText().toString());
            TotalCostPortMGT.setText("$ "+String.valueOf(formatData.format((CoalHauling*0.15)+1.50+2.50)));
            b=(CoalHauling*0.15)+1.50+2.50;
            Hitungan();
        }else if (KMRoadMaintenance.getText().toString().isEmpty() && !KMCoalHauling.getText().toString().isEmpty()){
            TotalCostPortMGT.setText("$ "+String.valueOf(formatData.format(1.50+2.50)));
            b=1.50+2.50;
            Hitungan();
        }else{
            TotalCostPortMGT.setText("$ 0");
            b=0.0;
            Hitungan();
        }
    }
    private void SubTotalMiningDelivery(){
        SubTotalMiningDelivery.setText("$ "+String.valueOf(formatData.format(a+b)));
    }
    private void SubTotalBeforeVAT(){
        SubtotalCostBeforeVAT.setText("$ "+String.valueOf(formatData.format(a+b+c)));
    }
    private void VAT(){
        VAT.setText("$ "+formatData.format((a+b+c)*10/100));
        SubTotalAfterVAT(formatData.format((a+b+c)*10/100),formatData.format((a+b+c)));
    }
    private void SubTotalAfterVAT(String VAT,String BeforeVAT){
        Double Cost = Double.parseDouble(BeforeVAT)-Double.parseDouble(VAT);
        SubtotalCostAfterVAT.setText("$ "+formatData.format(Cost));
        IndiciativeProfit(Cost);
    }
    private void IndiciativeProfit(Double Cost){
        IndicativeProfit.setText("$ "+formatData.format(139.91-Cost));
    }
    private void Hitungan(){
        SubTotalMiningDelivery();
        SubTotalBeforeVAT();
        VAT();
    }
}