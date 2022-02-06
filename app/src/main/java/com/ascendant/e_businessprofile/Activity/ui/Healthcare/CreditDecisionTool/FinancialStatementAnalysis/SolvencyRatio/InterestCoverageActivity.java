package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.FinancialStatementAnalysis.SolvencyRatio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.NumberTextWatcher;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.FinancialStatementAnalysisModel;
import com.ascendant.e_businessprofile.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InterestCoverageActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    EditText AsetLancar, LiabilitasJangkaPendek;
    Button hitung;
    TextView hasil;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_coverage);
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
        AsetLancar = findViewById(R.id.etAsetLancar);
        LiabilitasJangkaPendek = findViewById(R.id.etJangkaPendek);
        hitung = findViewById(R.id.btnHitung);
        hasil = findViewById(R.id.tvHasil);
        cardView = findViewById(R.id.cardView);
        cardView.setVisibility(View.GONE);

        AsetLancar.addTextChangedListener(new NumberTextWatcher(AsetLancar));
        LiabilitasJangkaPendek.addTextChangedListener(new NumberTextWatcher(LiabilitasJangkaPendek));

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hitung();
            }
        });
    }
    private void Hitung() {
        cardView.setVisibility(View.VISIBLE);
        String asetLancar = AsetLancar.getText().toString().replace(",", "");
        String liabilitas = LiabilitasJangkaPendek.getText().toString().replace(",", "");
        double Hasil = (Double.parseDouble(asetLancar.replace(".", "")) / Double.parseDouble(liabilitas.replace(".", "")))*100;
        DecimalFormat df2 = new DecimalFormat("#.##");
        hasil.setText("Total aset perusahaan dibiayai " + String.valueOf(df2.format(Hasil)) + " Persen dengan utang");
    }
}