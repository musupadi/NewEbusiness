package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.HospitalOperationalPerfomanceIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.GraphLineModel;
import com.ascendant.e_businessprofile.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class GraphLineActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    LineChart chart;
    TextView staticText,skor,link;
    ImageView back,home,print;
    Ascendant ascendant = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_line);

        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);


        Available.setVisibility(View.VISIBLE);
        pList.addAll(GraphLineModel.getListData());
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


        chart = findViewById(R.id.chart);
        staticText = findViewById(R.id.tvStaticText);
        skor = findViewById(R.id.tvSkor);
        back = findViewById(R.id.ivBack);
        home = findViewById(R.id.ivHome);
        link = findViewById(R.id.tvLink);
        print = findViewById(R.id.ivPrint);
        //GET INTENT DATA
        Intent data = getIntent();
        String JAN = data.getStringExtra("JAN");
        String FEB = data.getStringExtra("FEB");
        String MAR = data.getStringExtra("MAR");
        String APR = data.getStringExtra("APR");
        String MEI = data.getStringExtra("MEI");
        String JUN = data.getStringExtra("JUN");
        String JUL = data.getStringExtra("JUL");
        String AGS = data.getStringExtra("AGS");
        String SEP = data.getStringExtra("SEP");
        String OKT = data.getStringExtra("OKT");
        String NOV = data.getStringExtra("NOV");
        String DES = data.getStringExtra("DES");
        final String TITTLE = data.getStringExtra("TITTLE");

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);




        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();

        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawLimitLinesBehindData(true);

        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getAxisRight().setEnabled(false);
        //DONE
        chart.animateY(1000);
        chart.setVisibleXRangeMaximum(20);
        chart.moveViewToX(10);

        Print(TITTLE.toLowerCase(),JAN,FEB,MAR,APR,MEI,JUN,JUL,AGS,SEP,OKT,NOV,DES);
        IFELSES(JAN,FEB,MAR,APR,MEI,JUN,JUL,AGS,SEP,OKT,NOV,DES,TITTLE);
        Hitung(JAN,FEB,MAR,APR,MEI,JUN,JUL,AGS,SEP,OKT,NOV,DES,TITTLE);
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GraphLineActivity.this);
                builder.setMessage("Download File ?")
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ascendant.DownloadPDF(link.getText().toString(),TITTLE,GraphLineActivity.this);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Set your icon here
                        .setTitle("Perhatian !!!")
                        .setIcon(R.drawable.ic_baseline_print_24);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void Print(final String type, String jan, String feb, String mar, String apr, String mei, String jun, String jul, String ags, String sep, String okt, String nov, String des){
//        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
//        Call<ResponseArrayObject> getData = api.SimulasiAnalisisKinerjaRS(method.AUTH(),type,jan,feb,mar,apr,mei,jun,jul,ags,sep,okt,nov,des,"FABAJakartaIndonesia2019kunci");
//        getData.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                link.setText(response.body().getLink());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(GraphLineActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    public class MyXAxisValueFormater extends ValueFormatter implements IAxisValueFormatter {
        private String[] mValues;

        public MyXAxisValueFormater(String[] values) {
            this.mValues=values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }
    private void Hitung(String Jan,String Feb,String Mar,String Apr,String Mei,String Jun,String Jul,String Ags,String Sep,String Okt,String Nov,String Des,String datasets){
        int size = 0;
        if (!Jan.equals("0")){
            size = size+1;
        }
        if (!Feb.equals("0")){
            size = size+1;
        }
        if (!Mar.equals("0")){
            size = size+1;
        }
        if (!Apr.equals("0")){
            size = size+1;
        }
        if (!Mei.equals("0")){
            size = size+1;
        }
        if (!Jun.equals("0")){
            size = size+1;
        }
        if (!Jul.equals("0")){
            size = size+1;
        }
        if (!Ags.equals("0")){
            size = size+1;
        }
        if (!Sep.equals("0")){
            size = size+1;
        }
        if (!Okt.equals("0")){
            size = size+1;
        }
        if (!Nov.equals("0")){
            size = size+1;
        }
        if (!Des.equals("0")){
            size = size+1;
        }
        int TotalSkor = 0;
        int rata2 = 0;
        if(size > 1){
            TotalSkor = Integer.parseInt(Jan)
                    +Integer.parseInt(Feb)
                    +Integer.parseInt(Mar)
                    +Integer.parseInt(Apr)
                    +Integer.parseInt(Mei)
                    +Integer.parseInt(Jun)
                    +Integer.parseInt(Jul)
                    +Integer.parseInt(Ags)
                    +Integer.parseInt(Sep)
                    +Integer.parseInt(Okt)
                    +Integer.parseInt(Nov)
                    +Integer.parseInt(Des);
            rata2 = TotalSkor/size;
        }else{
            rata2 = Integer.parseInt(Jan)
                    +Integer.parseInt(Feb)
                    +Integer.parseInt(Mar)
                    +Integer.parseInt(Apr)
                    +Integer.parseInt(Mei)
                    +Integer.parseInt(Jun)
                    +Integer.parseInt(Jul)
                    +Integer.parseInt(Ags)
                    +Integer.parseInt(Sep)
                    +Integer.parseInt(Okt)
                    +Integer.parseInt(Nov)
                    +Integer.parseInt(Des);
        }

        if (datasets.equals("BOR")){
            skor.setText("Skor Nilai BOR : "+rata2+"%");
            staticText.setText("Standar Nilai Rujukan BOR yang baik :\n* Kemenkes : 60-85 %\n" +
                    "* Grafik Barber Johnson : 75 - 85 %\n" +
                    "* Expertise FABA : 60 - 80 %");
        }else if(datasets.equals("AVLOS_BEDAH")){
            skor.setText("Skor Nilai AvLOS_BEDAH : "+rata2+" Hari");
            staticText.setText("Standar Nilai Rujukan AvLOS-BEDAH yang baik :\n* Kemenkes : 6-9 Hari %\n" +
                    "* Grafik Barber Johnson : 3 - 12 %\n" +
                    "* Expertise FABA : 4 - 7 %");
        }else if(datasets.equals("AVLOS_NON_BEDAH")){
            skor.setText("Skor Nilai AvLOS_NON_BEDAH : "+rata2+" Hari");
            staticText.setText("Standar Nilai Rujukan AvLOS-NON-BEDAH yang baik :\n* Kemenkes : 6-9 Hari\n" +
                    "* Grafik Barber Johnson : 3 - 12 Haro\n" +
                    "* Expertise FABA : 3 - 15 %");
        }else if(datasets.equals("BTO")){
            skor.setText("Skor Nilai BTO : "+rata2+" Kali Per Tahun");
            staticText.setText("Standar Nilai Rujukan BTO yang baik :\n* Kemenkes : 30-50 Kali\n" +
                    "* Grafik Barber Johnson : Lebih dari 30 Kali\n" +
                    "* Expertise FABA : 40 - 50 Kali");
        }else if(datasets.equals("TOI")){
            skor.setText("Skor Nilai TOI : "+rata2+" Hari");
            staticText.setText("Standar Nilai Rujukan TOI yang baik :\n* Kemenkes : 1-3 Hari %\n" +
                    "* Grafik Barber Johnson : 1 - 3 Hari\n" +
                    "* Expertise FABA : 1 - 3 Hari");
        }else if(datasets.equals("NDR")){
            skor.setText("Skor Nilai NDR : "+rata2+" Jiwa/1000 Pasien Keluar");
            staticText.setText("Standar Nilai Rujukan NDR yang baik :\n* Kemenkes : < 25 Jiwa/1000 Pasien Keluar\n" +
                    "* Grafik Barber Johnson : < 25 Jiwa/1000 Pasien Keluar\n" +
                    "* Expertise FABA : < 25 Jiwa/1000 Pasien Keluar");
        }
        else if(datasets.equals("GDR")){
            skor.setText("Skor Nilai GDR : "+rata2+" Jiwa/1000 Pasien Keluar");
            staticText.setText("Standar Nilai Rujukan GDR yang baik :\n Kemenkes : < 45 Jiwa/1000 Pasien Keluar\n" +
                    "Grafik Barber Johnson : < 45 Jiwa/1000 Pasien Keluar\n" +
                    "Expertise FABA : < 45 Jiwa/1000 Pasien Keluar");
        }
    }

    private void IFELSES(String Jan,String Feb,String Mar,String Apr,String Mei,String Jun,String Jul,String Ags,String Sep,String Okt,String Nov,String Des,String datasets){
        ArrayList<Entry> yValue = new ArrayList<>();
        yValue.add(new Entry(0,Float.parseFloat(Jan+"f")));
        yValue.add(new Entry(1,Float.parseFloat(Feb+"f")));
        yValue.add(new Entry(2,Float.parseFloat(Mar+"f")));
        yValue.add(new Entry(3,Float.parseFloat(Apr+"f")));
        yValue.add(new Entry(4,Float.parseFloat(Mei+"f")));
        yValue.add(new Entry(5,Float.parseFloat(Jun+"f")));
        yValue.add(new Entry(6,Float.parseFloat(Jul+"f")));
        yValue.add(new Entry(7,Float.parseFloat(Ags+"f")));
        yValue.add(new Entry(8,Float.parseFloat(Sep+"f")));
        yValue.add(new Entry(9,Float.parseFloat(Okt+"f")));
        yValue.add(new Entry(10,Float.parseFloat(Nov+"f")));
        yValue.add(new Entry(11,Float.parseFloat(Des+"f")));

        LineDataSet set1 = new LineDataSet(yValue,datasets);
        set1.setColor(Color.rgb(37,197,223));
        set1.setLineWidth(5f);
        set1.setFillAlpha(110);
        set1.setValueTextSize(10);
//        set1.setCircleColorHole(Color.WHITE);
        set1.setCircleRadius(5);
//        set1.setCircleColorHole(4);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        chart.setData(data);

        String[] values = new String[]{"JAN","FEB","MAR","APR","MEI","JUN","JUL","AGS","SEP","OKT","NOV","DES"};
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormater(values));
        xAxis.setGranularity(1);


    }
}