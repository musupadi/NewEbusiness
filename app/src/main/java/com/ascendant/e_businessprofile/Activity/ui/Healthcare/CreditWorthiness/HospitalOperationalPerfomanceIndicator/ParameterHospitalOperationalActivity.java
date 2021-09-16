package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.CreditWorthiness.HospitalOperationalIndicatorModel;
import com.ascendant.e_businessprofile.Model.StaticModel.CreditWorthiness.ParametersHospitalOperationalIndicatorModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

public class ParameterHospitalOperationalActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    WebView web1,web2,web3,web4,web5,web6,web7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_hospital_operational);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);

        Available.setVisibility(View.VISIBLE);
        pList.addAll(ParametersHospitalOperationalIndicatorModel.getListData());
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

        String param1 = "<p><strong>Indikator : BOR</strong></p>" +
                "<p>Nilai Rujukan :</p>" +
                "<ul>" +
                " <li>Kemenkes -> 60% - 80%</li>" +
                " <li>Grafik Barber Johnson -> 75% - 85%</li>" +
                " <li>Expertiese FABA -> 60-80%</li>" +
                "</ul><br>" +
                "<p>Keterangan : BOR merupakan berapa persen tingkat pemanfaatan sarana rawat inap suatu rumah sakit</p>";
        web1 = findViewById(R.id.webview1);
        web1.loadData(param1,"text/html","UTF-8");

        String param2 = "<p><strong>Indikator : AvLOS - Bedah</strong></p>" +
                "<p>Nilai Rujukan : </p>" +
                "<ul>" +
                " <li>Kemenkes -> 6 - 9 Hari</li>" +
                " <li>Grafik Barber Johnson -> 3 - 12 Hari</li>" +
                " <li>Expertiese FABA -> 4 - 7 Hari%</li>" +
                "</ul>" +
                "<br>" +
                "<p>Keterangan : AvLOS - Bedah merupakan gambaran berapa hari seorang pasien rata-rata dirawat dengan tindakan Bedah</p>";
        web2 = findViewById(R.id.webview2);
        web2.loadData(param2,"text/html","UTF-8");

        String param3 = "<p><strong>Indikator : AvLOS - Non Bedah</strong></p>" +
                "<p>Nilai Rujukan : </p>" +
                "<ul>" +
                " <li>Kemenkes -> 6 - 9 Hari</li>" +
                " <li>Grafik Barber Johnson -> 3 - 12 Hari</li>" +
                " <li>Expertiese FABA -> 3 - 5 Hari%</li>" +
                "</ul><br>" +
                "<p>Keterangan : AvLOS - Non Bedah merupakan gambaran berapa hari seorang pasien rata-rata dirawat dengan tindakan Non Bedah</p>";
        web3 = findViewById(R.id.webview3);
        web3.loadData(param3,"text/html","UTF-8");

        String param4 = "<p>Indikator : BTO</p>" +
                "<p>Nilai Rujukan : </p>" +
                "<ul>" +
                " <li>Kemenkes -> 40 - 50 Kali</li>" +
                " <li>Grafik Barber Johnson -> >30 Kali</li>" +
                " <li>Expertiese FABA -> 40 - 50 Kali</li>" +
                "</ul>" +
                "<br>" +
                "<p>Keterangan : BTO merupakan gambaran berapa kali dalam setahun sebuah tempat tidur digunakan berbagai penderita</p>";
        web4 = findViewById(R.id.webview4);
        web4.loadData(param4,"text/html","UTF-8");

        String param5 = "<p><strong>Indikator : TOI</strong></p>" +
                "<p>Nilai Rujukan : </p>" +
                "<ul>" +
                " <li>Kemenkes -> 1 - 3 Hari</li>" +
                " <li>Grafik Barber Johnson -> 1 - 3 Hari</li>" +
                " <li>Expertiese FABA -> 1 - 3 Hari</li>" +
                "</ul>" +
                "<br>" +
                "<p>Keterangan : TOI Merupakan gambaran berapa lama sebuah tempat tidur berada dalam keadaan kosong sebelum digunakan kembali pleh pasien lain</p>";
        web5 = findViewById(R.id.webview5);
        web5.loadData(param5,"text/html","UTF-8");

        String param6 = "<p><strong>Indikator : NDR</strong></p>" +
                "<p>Nilai Rujukan :</p>" +
                "<ul>" +
                " <li>Kemenkes -> Lebih Kecil Dari 25/100 Penderita/Pasien</li>" +
                " <li>Grafik Barber Johnson ->  25/1000 Penderita/Pasien</li>" +
                " <li>Expertiese FABA -> Lebih Kecil Dari 25/1000 Penderita/Pasien</li>" +
                "</ul>" +
                "<br>" +
                "<p>Keterangan : NDR Merupakan jumlah angka kematian pasien selama lebih dari 48 jam setelah d irawat untuk tiap-tiap 1000 pasien keluar</p>";
        web6 = findViewById(R.id.webview6);
        web6.loadData(param6,"text/html","UTF-8");

        String param7 = "<p><strong>Indikator : GDR</strong></p>" +
                "<p>Nilai Rujukan : </p>" +
                "<ul>" +
                " <li>Kemenkes -> Lebih Kecil Dari 45/1000 Penderita/Pasien</li>" +
                " <li>Grafik Barber Johnson -> Lebih Kecil Dari 45/1000 Penderita/Pasien</li>" +
                " <li>Expertiese FABA -> Lebih Kecil Dari 45/1000 Penderita/Pasien</li>" +
                "</ul>" +
                "<br>" +
                "<p>Keterangan : GDR Merupakan Jumlah angka kematian umum untuk tiap 1000 Pasien Keluar</p>";
        web7 = findViewById(R.id.webview7);
        web7.loadData(param7,"text/html","UTF-8");
    }
}