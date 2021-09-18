package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.InputSimulationHospitalOperationalndicatorModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class InputSimulationHospitalOperationalActivity extends AppCompatActivity {
    TextView tittle;
    EditText jan,feb,mar,apr,mei,jun,jul,ags,sep,okt,nov,des;
    TextView sJan,sFeb,sMar,sApr,sMei,sJun,sJul,sAgs,sSep,sOkt,sNov,sDes;
    Button hitung;
    ImageView back,home;
    boolean accept;

    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    CardView Parameters,Simulation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_simulation_hospital_operational);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);

        Available.setVisibility(View.VISIBLE);
        pList.addAll(InputSimulationHospitalOperationalndicatorModel.getListData());
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
        final String TITTLE_PERHITUNGAN = data.getStringExtra("KEY_TITTLE_PERHITUNGAN");
        back = findViewById(R.id.ivBack);
        home = findViewById(R.id.ivHome);
        tittle = findViewById(R.id.tvTittle);
        jan = findViewById(R.id.etJan);
        feb = findViewById(R.id.etFeb);
        mar = findViewById(R.id.etMar);
        apr = findViewById(R.id.etApr);
        mei = findViewById(R.id.etMei);
        jun = findViewById(R.id.etJun);
        jul = findViewById(R.id.etJul);
        ags = findViewById(R.id.etAgt);
        sep = findViewById(R.id.etSep);
        okt = findViewById(R.id.etOkt);
        nov = findViewById(R.id.etNov);
        des = findViewById(R.id.etDes);
        sJan = findViewById(R.id.tvSatuanJan);
        sFeb = findViewById(R.id.tvSatuanFeb);
        sMar = findViewById(R.id.tvSatuanMar);
        sApr = findViewById(R.id.tvSatuanApr);
        sMei = findViewById(R.id.tvSatuanMei);
        sJun = findViewById(R.id.tvSatuanJun);
        sJul = findViewById(R.id.tvSatuanJul);
        sAgs = findViewById(R.id.tvSatuanAgt);
        sSep = findViewById(R.id.tvSatuanSep);
        sOkt = findViewById(R.id.tvSatuanOkt);
        sNov = findViewById(R.id.tvSatuanNov);
        sDes = findViewById(R.id.tvSatuanDes);
        hitung = findViewById(R.id.btnHitung);

        if (TITTLE_PERHITUNGAN.equals("BOR")){
            tittle.setText("Masukan Angka BOR Perbulan (%)");
            Persen();
        }else if(TITTLE_PERHITUNGAN.equals("AVLOS_BEDAH")){
            tittle.setText("Masukan Angka AvLOS perb ulan (hari)");
            Hari();
        }else if(TITTLE_PERHITUNGAN.equals("AVLOS_NON_BEDAH")){
            tittle.setText("Masukan Angka AvLOS perbulan (hari)");
            Hari();
        }else if(TITTLE_PERHITUNGAN.equals("BTO")){
            tittle.setText("Masukan Angka BTO Perbulan (Jumlah Pemakaian)");
            Kali();
        }else if(TITTLE_PERHITUNGAN.equals("TOI")){
            tittle.setText("Masukan Angka TOI Perbulan (Hari)");
            Hari();
        }else if(TITTLE_PERHITUNGAN.equals("NDR")){
            tittle.setText("Masukan Angka NDR perbulan \n(Jiwa/1000 Pasien Keluar)");
            Jiwa();
        }else if(TITTLE_PERHITUNGAN.equals("GDR")){
            tittle.setText("Masukan Angka GDR perbulan \n(Jiwa/1000 Pasien Keluar)");
            Jiwa();
        }
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checker().equals("SUCCES")){
                    LOGIC(TITTLE_PERHITUNGAN);
                }else{
                    Toast.makeText(InputSimulationHospitalOperationalActivity.this, checker(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        jan.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode ==  KeyEvent.KEYCODE_ENTER) {
                    feb.requestFocus();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private String checker(){
        String message = "SUCCES";
        if (jan.getText().toString().isEmpty()){
            message = "Bulan Januari Kosong";
        }else if(feb.getText().toString().isEmpty()){
            message = "Bulan Februari Kosong";
        }else if(mar.getText().toString().isEmpty()){
            message = "Bulan Maret Kosong";
        }else if(apr.getText().toString().isEmpty()){
            message = "Bulan April Kosong";
        }else if(mei.getText().toString().isEmpty()){
            message = "Bulan Mei Kosong";
        }else if(jun.getText().toString().isEmpty()){
            message = "Bulan Juni Kosong";
        }else if(jul.getText().toString().isEmpty()){
            message = "Bulan Juli Kosong";
        }else if(ags.getText().toString().isEmpty()){
            message = "Bulan Agustus Kosong";
        }else if(sep.getText().toString().isEmpty()){
            message = "Bulan September Kosong";
        }else if(okt.getText().toString().isEmpty()){
            message = "Bulan Oktober Kosong";
        }else if(nov.getText().toString().isEmpty()){
            message = "Bulan November Kosong";
        }else if (des.getText().toString().isEmpty()){
            message = "Bulan Desember Kosong";
        }
        return message;
    }


    public void Hari(){
        sJan.setText("Hari");
        sFeb.setText("Hari");
        sMar.setText("Hari");
        sApr.setText("Hari");
        sMei.setText("Hari");
        sJun.setText("Hari");
        sJul.setText("Hari");
        sAgs.setText("Hari");
        sSep.setText("Hari");
        sOkt.setText("Hari");
        sNov.setText("Hari");
        sDes.setText("Hari");
    }

    public void Kali(){
        sJan.setText("Kali");
        sFeb.setText("Kali");
        sMar.setText("Kali");
        sApr.setText("Kali");
        sMei.setText("Kali");
        sJun.setText("Kali");
        sJul.setText("Kali");
        sAgs.setText("Kali");
        sSep.setText("Kali");
        sOkt.setText("Kali");
        sNov.setText("Kali");
        sDes.setText("Kali");
    }

    public void Persen(){
        sJan.setText("%");
        sFeb.setText("%");
        sMar.setText("%");
        sApr.setText("%");
        sMei.setText("%");
        sJun.setText("%");
        sJul.setText("%");
        sAgs.setText("%");
        sSep.setText("%");
        sOkt.setText("%");
        sNov.setText("%");
        sDes.setText("%");
    }

    public void Jiwa(){
        sJan.setVisibility(View.GONE);
        sFeb.setVisibility(View.GONE);
        sMar.setVisibility(View.GONE);
        sApr.setVisibility(View.GONE);
        sMei.setVisibility(View.GONE);
        sJun.setVisibility(View.GONE);
        sJul.setVisibility(View.GONE);
        sAgs.setVisibility(View.GONE);
        sSep.setVisibility(View.GONE);
        sOkt.setVisibility(View.GONE);
        sNov.setVisibility(View.GONE);
        sDes.setVisibility(View.GONE);
    }

    private void LOGIC(String perhitungan){
        Intent goInput = new Intent(this, GraphLineActivity.class);
        goInput.putExtra("JAN",IFELSES(jan));
        goInput.putExtra("FEB",IFELSES(feb));
        goInput.putExtra("MAR",IFELSES(mar));
        goInput.putExtra("APR",IFELSES(apr));
        goInput.putExtra("MEI",IFELSES(mei));
        goInput.putExtra("JUN",IFELSES(jun));
        goInput.putExtra("JUL",IFELSES(jul));
        goInput.putExtra("AGS",IFELSES(ags));
        goInput.putExtra("SEP",IFELSES(sep));
        goInput.putExtra("OKT",IFELSES(okt));
        goInput.putExtra("NOV",IFELSES(nov));
        goInput.putExtra("DES",IFELSES(des));
        goInput.putExtra("TITTLE",perhitungan);
        startActivity(goInput);
    }
    private String IFELSES(EditText data){
        String done="0";
        if (data.getText().toString().isEmpty()){
            done = data.getText().toString();
        }else{
            done = data.getText().toString();
        }
        return done;
    }
}