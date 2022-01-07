package com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.RequirementAnalysis.InvestmentCredit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ResultInvestmentCreditActivity extends AppCompatActivity {
    TextView RencanaBangunan, BawahBangunan, AtasBangunan, KeteranganBangunan;
    TextView RencanaSarana, BawahSarana, AtasSarana, KeteranganSarana;
    TextView RencanaPeralatan, BawahPeralatan, AtasPeralatan, KeteranganPeralatan;
    TextView RencanaTotal, BawahTotal, AtasTotal, KeteranganTotal;
    TextView KeteranganTotalPlafon,PlafondText;
    TextView link;
    Button kembali,print;
    Ascendant ascendant = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_investment_credit);

        kembali = findViewById(R.id.btnKembali);
        print = findViewById(R.id.btnPrint);
        link = findViewById(R.id.tvLink);
        RencanaBangunan = findViewById(R.id.tvRencanaBangunanAnggaran);
        BawahBangunan = findViewById(R.id.tvBatasBawahBangunan);
        AtasBangunan = findViewById(R.id.tvBatasAtasBangunan);
        KeteranganBangunan = findViewById(R.id.tvKeteranganBangunan);
        RencanaSarana = findViewById(R.id.tvRencanaSaranaPrasaranaAnggaran);
        BawahSarana = findViewById(R.id.tvBatasBawahSaranaPrasarana);
        AtasSarana = findViewById(R.id.tvBatasAtasSaranaPrasarana);
        KeteranganSarana = findViewById(R.id.tvKeteranganSaranaPrasarana);
        RencanaPeralatan = findViewById(R.id.tvRencanaPeralatanDanAlkesAnggaran);
        BawahPeralatan = findViewById(R.id.tvBatasBawahPeralatanDanAlkes);
        AtasPeralatan = findViewById(R.id.tvBatasAtasPeralatanDanAlkes);
        KeteranganPeralatan = findViewById(R.id.tvKeteranganPeralatanDanAlkes);
        RencanaTotal = findViewById(R.id.tvRencanaTotalBiaya);
        BawahTotal = findViewById(R.id.tvBatasBawahTotalBiaya);
        AtasTotal = findViewById(R.id.tvBatasAtasTotalBiaya);
        KeteranganTotal = findViewById(R.id.tvKeteranganTotalBiaya);
        KeteranganTotalPlafon = findViewById(R.id.tvKeteranganJumlah);
        PlafondText = findViewById(R.id.tvPLafondText);
        Intent data = getIntent();
        String KELAS = data.getStringExtra("KELAS");
        String BANGUNAN = data.getStringExtra("BANGUNAN");
        String SARANA = data.getStringExtra("SARANA");
        String PERALATAN = data.getStringExtra("PERALATAN");
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        RencanaBangunan.setText(formatRupiah.format(Double.parseDouble(BANGUNAN)).replace("Rp","Rp "));
        RencanaSarana.setText(formatRupiah.format(Double.parseDouble(SARANA)).replace("Rp","Rp "));
        RencanaPeralatan.setText(formatRupiah.format(Double.parseDouble(PERALATAN)).replace("Rp","Rp "));
        double total = Double.parseDouble(BANGUNAN) + Double.parseDouble(SARANA) + Double.parseDouble(PERALATAN);
        RencanaTotal.setText(String.valueOf(formatRupiah.format(total).replace("Rp","Rp ")));
        BEFORE(KELAS);
        KeteranganBangunan.setText(Keterangan(BANGUNAN, BawahBangunan.getText().toString(), AtasBangunan.getText().toString()));
        KeteranganSarana.setText(Keterangan(SARANA, BawahSarana.getText().toString(), AtasSarana.getText().toString()));
        KeteranganPeralatan.setText(Keterangan(PERALATAN, BawahPeralatan.getText().toString(), AtasPeralatan.getText().toString()));
        KeteranganTotal.setText(Keterangan(String.valueOf(total), BawahTotal.getText().toString(), AtasTotal.getText().toString()));
        if (KeteranganTotal.getText().toString().equals("Over Budget")){
            double hitungs = (70.0 / 100.0) * Double.parseDouble(AtasTotal.getText().toString());
            DecimalFormat df2 = new DecimalFormat("#");
            KeteranganTotalPlafon.setText(String.valueOf(formatRupiah.format(hitungs).replace("Rp","Rp ")));
            PlafondText.setText("Plafon (70% dari Batas Atas Total)");
        }else{
            double hitungs = (70.0 / 100.0) * total;
            DecimalFormat df2 = new DecimalFormat("#");
            KeteranganTotalPlafon.setText(String.valueOf(formatRupiah.format(hitungs).replace("Rp","Rp ")));
            PlafondText.setText("Plafon (70% dari Total Biaya)");
        }
        Print(KELAS,BANGUNAN,SARANA,PERALATAN);
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultInvestmentCreditActivity.this);
                builder.setMessage("Download File ?")
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ascendant.DownloadPDF(link.getText().toString(),"Kredit Investasi",ResultInvestmentCreditActivity.this);
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
        AFTER(KELAS);
    }
    private void Print(String rencana,String bangunan,String sarana,String alkes){
//        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
//        Call<ResponseModel> getData = api.KreditInvestasi(method.AUTH(),rencana.toLowerCase(),bangunan,sarana,alkes,"FABAJakartaIndonesia2019kunci");
//        getData.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                link.setText(response.body().getLink());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(ResultKreditInvestasiActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    public String Keterangan(String total, String param1, String param2) {
        String keterangan = "";
        if (Double.parseDouble(total) >= Double.parseDouble(param1) && Double.parseDouble(total) <= Double.parseDouble(param2)) {
            keterangan = "Ideal";
        } else if (Double.parseDouble(total) > Double.parseDouble(param1)) {
            keterangan = "Over Budget";
        } else if (Double.parseDouble(total) < Double.parseDouble(param1)) {
            keterangan = "Under Budget";
        }
        return keterangan;
    }

    private void BEFORE(String KELAS) {
        if (KELAS.equals("A")) {
            BawahBangunan.setText("520000000000");
            AtasBangunan.setText(("780000000000"));
            BawahSarana.setText("509600000000");
            AtasSarana.setText("1115400000000");
            BawahPeralatan.setText("1437030000000");
            AtasPeralatan.setText("2083693500000");
            BawahTotal.setText("2466630000000");
            AtasTotal.setText("3979093500000");
        } else if (KELAS.equals("B")) {
            BawahBangunan.setText("208000000000");
            AtasBangunan.setText("312000000000");
            BawahSarana.setText("203840000000");
            AtasSarana.setText("446160000000");
            BawahPeralatan.setText("479010000000");
            AtasPeralatan.setText("694564500000");
            BawahTotal.setText("890850000000");
            AtasTotal.setText("1452724500000");
        } else if (KELAS.equals("C")) {
            BawahBangunan.setText("78000000000");
            AtasBangunan.setText("208000000000");
            BawahSarana.setText("76440000000");
            AtasSarana.setText("203840000000");
            BawahPeralatan.setText("159670000000");
            AtasPeralatan.setText("479010000000");
            BawahTotal.setText("314110000000");
            AtasTotal.setText("890850000000");
        } else if (KELAS.equals("D")) {
            BawahBangunan.setText("32500000000");
            AtasBangunan.setText("48750000000");
            BawahSarana.setText("31850000000");
            AtasSarana.setText("203840000000");
            BawahPeralatan.setText("79835000000");
            AtasPeralatan.setText("115760750000");
            BawahTotal.setText("144185000000");
            AtasTotal.setText("234223250000");
        }
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void AFTER(String KELAS) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        if (KELAS.equals("A")) {
            BawahBangunan.setText(formatRupiah.format(Double.parseDouble("520000000000")).replace("Rp","Rp "));
            AtasBangunan.setText(formatRupiah.format(Double.parseDouble("780000000000")).replace("Rp","Rp "));
            BawahSarana.setText(formatRupiah.format(Double.parseDouble("509600000000")).replace("Rp","Rp "));
            AtasSarana.setText(formatRupiah.format(Double.parseDouble("1115400000000")).replace("Rp","Rp "));
            BawahPeralatan.setText(formatRupiah.format(Double.parseDouble("1437030000000")).replace("Rp","Rp "));
            AtasPeralatan.setText(formatRupiah.format(Double.parseDouble("2083693500000")).replace("Rp","Rp "));
            BawahTotal.setText(formatRupiah.format(Double.parseDouble("2466630000000")).replace("Rp","Rp "));
            AtasTotal.setText(formatRupiah.format(Double.parseDouble("3979093500000")).replace("Rp","Rp "));
        } else if (KELAS.equals("B")) {
            BawahBangunan.setText(formatRupiah.format(Double.parseDouble("208000000000")).replace("Rp","Rp "));
            AtasBangunan.setText(formatRupiah.format(Double.parseDouble("312000000000")).replace("Rp","Rp "));
            BawahSarana.setText(formatRupiah.format(Double.parseDouble("203840000000")).replace("Rp","Rp "));
            AtasSarana.setText(formatRupiah.format(Double.parseDouble("446160000000")).replace("Rp","Rp "));
            BawahPeralatan.setText(formatRupiah.format(Double.parseDouble("479010000000")).replace("Rp","Rp "));
            AtasPeralatan.setText(formatRupiah.format(Double.parseDouble("694564500000")).replace("Rp","Rp "));
            BawahTotal.setText(formatRupiah.format(Double.parseDouble("890850000000")).replace("Rp","Rp "));
            AtasTotal.setText(formatRupiah.format(Double.parseDouble("1452724500000")).replace("Rp","Rp "));
        } else if (KELAS.equals("C")) {
            BawahBangunan.setText(formatRupiah.format(Double.parseDouble("78000000000")).replace("Rp","Rp "));
            AtasBangunan.setText(formatRupiah.format(Double.parseDouble("208000000000")).replace("Rp","Rp "));
            BawahSarana.setText(formatRupiah.format(Double.parseDouble("76440000000")).replace("Rp","Rp "));
            AtasSarana.setText(formatRupiah.format(Double.parseDouble("203840000000")).replace("Rp","Rp "));
            BawahPeralatan.setText(formatRupiah.format(Double.parseDouble("159670000000")).replace("Rp","Rp "));
            AtasPeralatan.setText(formatRupiah.format(Double.parseDouble("479010000000")).replace("Rp","Rp "));
            BawahTotal.setText(formatRupiah.format(Double.parseDouble("314110000000")).replace("Rp","Rp "));
            AtasTotal.setText(formatRupiah.format(Double.parseDouble("890850000000")).replace("Rp","Rp "));
        } else if (KELAS.equals("D")) {
            BawahBangunan.setText(formatRupiah.format(Double.parseDouble("32500000000")).replace("Rp","Rp "));
            AtasBangunan.setText(formatRupiah.format(Double.parseDouble("48750000000")).replace("Rp","Rp "));
            BawahSarana.setText(formatRupiah.format(Double.parseDouble("31850000000")).replace("Rp","Rp "));
            AtasSarana.setText(formatRupiah.format(Double.parseDouble("203840000000")).replace("Rp","Rp "));
            BawahPeralatan.setText(formatRupiah.format(Double.parseDouble("79835000000")).replace("Rp","Rp "));
            AtasPeralatan.setText(formatRupiah.format(Double.parseDouble("115760750000")).replace("Rp","Rp "));
            BawahTotal.setText(formatRupiah.format(Double.parseDouble("144185000000")).replace("Rp","Rp "));
            AtasTotal.setText(formatRupiah.format(Double.parseDouble("234223250000")).replace("Rp","Rp "));
        }

    }
}