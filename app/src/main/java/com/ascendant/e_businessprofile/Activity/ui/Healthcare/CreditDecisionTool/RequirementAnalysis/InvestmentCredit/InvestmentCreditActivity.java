package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.RequirementAnalysis.InvestmentCredit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.NumberTextWatcher;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.InvestmentCreditModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.RequirmentAnalysisModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class InvestmentCreditActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    Button hitung,parameter;
    Spinner kelas;
    EditText bangunan,sarana,peralatan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_credit);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(InvestmentCreditModel.getListData());
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


        hitung = findViewById(R.id.btnHitung);
        kelas = findViewById(R.id.spinnerKelas);
        bangunan = findViewById(R.id.etBangunan);
        sarana = findViewById(R.id.etSaranaPrasarana);
        peralatan = findViewById(R.id.etPeralatan);
        parameter = findViewById(R.id.btnParameter);

        peralatan.addTextChangedListener(new NumberTextWatcher(peralatan));
        bangunan.addTextChangedListener(new NumberTextWatcher(bangunan));
        sarana.addTextChangedListener(new NumberTextWatcher(sarana));


        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checker();
            }
        });
        parameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mandiri-ebusinessraw.the-urbandev.com/uploads/hospital/param/Perhitungan_Investasi.pdf"));
                startActivity(browserIntent);
            }
        });
    }
    private void Checker(){
        if (sarana.getText().toString().isEmpty()){
            Toast.makeText(InvestmentCreditActivity.this, "Sarana Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (peralatan.getText().toString().isEmpty()){
            Toast.makeText(InvestmentCreditActivity.this, "PeralatanTidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (bangunan.getText().toString().isEmpty()){
            Toast.makeText(InvestmentCreditActivity.this, "Bangunan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{
            Intent goInput = new Intent(InvestmentCreditActivity.this, ResultInvestmentCreditActivity.class);
            String Bangunan = bangunan.getText().toString().replace(".","");
            String Sarana = sarana.getText().toString().replace(".","");
            String Peralatann = peralatan.getText().toString().replace(".","");
            goInput.putExtra("KELAS",kelas.getSelectedItem().toString());
            goInput.putExtra("BANGUNAN",Bangunan.replace(",",""));
            goInput.putExtra("SARANA",Sarana.replace(",",""));
            goInput.putExtra("PERALATAN",Peralatann.replace(",",""));
            startActivities(new Intent[]{goInput});
        }
    }
}