package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.HospitalOperationalIndicatorModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class SimulationHospitalOperationalActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();


    Button BOR,AVLOSBedah,AVLOSNonBedah,BTO,TOI,NDR,GDR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_hospital_operational);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);


        Available.setVisibility(View.VISIBLE);
        pList.addAll(HospitalOperationalIndicatorModel.getListData());
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


        BOR = findViewById(R.id.btnBor);
        AVLOSBedah = findViewById(R.id.btnAvlosBedah);
        AVLOSNonBedah = findViewById(R.id.btnAvlosNonBedah);
        BTO = findViewById(R.id.btnBTO);
        TOI = findViewById(R.id.btnTOI);
        NDR = findViewById(R.id.btnNDR);
        GDR = findViewById(R.id.btnGDR);

        BOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(SimulationHospitalOperationalActivity.this, InputSimulationHospitalOperationalActivity.class);
                goInput.putExtra("KEY_TITTLE_PERHITUNGAN","BOR");
                startActivity(goInput);
            }
        });
        AVLOSBedah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(SimulationHospitalOperationalActivity.this, InputSimulationHospitalOperationalActivity.class);
                goInput.putExtra("KEY_TITTLE_PERHITUNGAN","AVLOS_BEDAH");
                startActivity(goInput);
            }
        });
        AVLOSNonBedah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(SimulationHospitalOperationalActivity.this, InputSimulationHospitalOperationalActivity.class);
                goInput.putExtra("KEY_TITTLE_PERHITUNGAN","AVLOS_NON_BEDAH");
                startActivity(goInput);
            }
        });
        BTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(SimulationHospitalOperationalActivity.this, InputSimulationHospitalOperationalActivity.class);
                goInput.putExtra("KEY_TITTLE_PERHITUNGAN","BTO");
                startActivity(goInput);
            }
        });
        TOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(SimulationHospitalOperationalActivity.this, InputSimulationHospitalOperationalActivity.class);
                goInput.putExtra("KEY_TITTLE_PERHITUNGAN","TOI");
                startActivity(goInput);
            }
        });
        NDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(SimulationHospitalOperationalActivity.this, InputSimulationHospitalOperationalActivity.class);
                goInput.putExtra("KEY_TITTLE_PERHITUNGAN","NDR");
                startActivity(goInput);
            }
        });
        GDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(SimulationHospitalOperationalActivity.this, InputSimulationHospitalOperationalActivity.class);
                goInput.putExtra("KEY_TITTLE_PERHITUNGAN","GDR");
                startActivity(goInput);
            }
        });
    }
}