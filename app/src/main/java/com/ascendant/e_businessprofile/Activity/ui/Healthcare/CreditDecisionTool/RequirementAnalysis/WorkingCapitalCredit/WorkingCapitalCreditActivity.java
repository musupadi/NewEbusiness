package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.RequirementAnalysis.WorkingCapitalCredit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.NumberTextWatcher;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.RequirmentAnalysisModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditDecisionTool.WorkingCapitalCreditModel;
import com.ascendant.e_businessprofile.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class WorkingCapitalCreditActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    EditText Piutang,Persediaan,Utang;
    TextView KebutuhanModalKerja,Plafond;
    Button Hitung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_capital_credit);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(WorkingCapitalCreditModel.getListData());
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

        Piutang = findViewById(R.id.etPiutang);
        Persediaan = findViewById(R.id.etPersediaan);
        Utang = findViewById(R.id.etUtang);
        Hitung = findViewById(R.id.btnHitung);
        KebutuhanModalKerja = findViewById(R.id.tvKebutuhanModalKerja);
        Plafond = findViewById(R.id.tvPlafond);



        Piutang.addTextChangedListener(new NumberTextWatcher(Piutang));
        Persediaan.addTextChangedListener(new NumberTextWatcher(Persediaan));
        Utang.addTextChangedListener(new NumberTextWatcher(Utang));

        Hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checker();
            }
        });
    }
    private void Checker(){
        if (Piutang.getText().toString().isEmpty()){
            Toast.makeText(WorkingCapitalCreditActivity.this, "Piutang Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Persediaan.getText().toString().isEmpty()){
            Toast.makeText(WorkingCapitalCreditActivity.this, "Persediaan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Utang.getText().toString().isEmpty()){
            Toast.makeText(WorkingCapitalCreditActivity.this, "Utang Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{
            Locale localeID = new Locale("in", "ID");
            final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            final String piutang = Piutang.getText().toString().replace(".","");
            final String persediaan = Persediaan.getText().toString().replace(".","");
            final String utang = Utang.getText().toString().replace(".","");
            double modalKerja = (Double.parseDouble(piutang.replace(",",""))+Double.parseDouble(persediaan.replace(",",""))) - Double.parseDouble(utang.replace(",",""));
            double plafond = 0.8 * modalKerja;
            KebutuhanModalKerja.setText(": "+String.valueOf(formatRupiah.format(modalKerja).replace("Rp","Rp ")));
            Plafond.setText(": "+String.valueOf(formatRupiah.format(plafond).replace("Rp","Rp ")));
        }
    }
}