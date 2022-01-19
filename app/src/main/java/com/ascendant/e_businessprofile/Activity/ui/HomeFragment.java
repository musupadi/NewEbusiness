package com.ascendant.e_businessprofile.Activity.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.DetailBeritaActivity;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.ModuleActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Mining.MandiriUpdate.DetailMandiriUpdate;
import com.ascendant.e_businessprofile.Adapter.AdapterBerita;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerDivisi;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerWilayah;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.ResponseQuiz;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    RecyclerView rv;
    DB_Helper dbHelper;
    String Token;
    TextView divisi,nama,poin;
    RelativeLayout Healtcare,FMCG,Mining,Contractor,OilAndGas,Farm;
    ScrollView scroll;
    Dialog myDialog,quizDialog,dialogPesan;
    TextView KategoriSoal,Soal;
    Button A,B,C,D,Konfirmasi;
    Button PilihDivisi;
    Spinner spDivisi,spWilayah;
    TextView tvDivisi,tvWilayah;
    LottieAnimationView lottie;
    Boolean Quiz=false;
    LinearLayout linearQuiz;
    String JawabQuiz="";
    TextView idQuiz,jawabQuiz;
    TextView Pesan,View;
    Button KonfirmasiPesan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        dialogPesan = new Dialog(getActivity());
        dialogPesan.setContentView(R.layout.dialog_message);
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_divisi);
        quizDialog = new Dialog(getActivity());
        quizDialog.setContentView(R.layout.dialog_quiz_harian);
        View = view.findViewById(R.id.tvView);
        Pesan = dialogPesan.findViewById(R.id.tvPesan);
        KonfirmasiPesan = dialogPesan.findViewById(R.id.btnKonfirmasi);
        KategoriSoal = quizDialog.findViewById(R.id.tvKategori);
        Soal = quizDialog.findViewById(R.id.tvSoal);
        A = quizDialog.findViewById(R.id.btnA);
        B = quizDialog.findViewById(R.id.btnB);
        C = quizDialog.findViewById(R.id.btnC);
        D = quizDialog.findViewById(R.id.btnD);
        Konfirmasi = quizDialog.findViewById(R.id.btnKonfirmasi);
        spDivisi = myDialog.findViewById(R.id.spDivisi);
        spWilayah = myDialog.findViewById(R.id.spWilayah);
        PilihDivisi = myDialog.findViewById(R.id.btnPilih);
        linearQuiz = view.findViewById(R.id.linearQuiz);
        scroll = view.findViewById(R.id.scroll);
        rv = view.findViewById(R.id.recycler);
        Healtcare = view.findViewById(R.id.relativeHealthcare);
        FMCG = view.findViewById(R.id.relativeFMCG);
        Mining = view.findViewById(R.id.relativeMining);
        Contractor = view.findViewById(R.id.relativeContractor);
        OilAndGas = view.findViewById(R.id.relativeOilAndGas);
        Farm = view.findViewById(R.id.relativeFarm);
        nama = view.findViewById(R.id.tvNama);
        divisi = view.findViewById(R.id.tvDivisi);
        poin = view.findViewById(R.id.tvPoin);
        tvDivisi = view.findViewById(R.id.tvIdDivisi);
        tvWilayah = view.findViewById(R.id.tvIdWilayah);
        idQuiz = view.findViewById(R.id.tvIdQuizz);
        jawabQuiz = view.findViewById(R.id.tvJawabQuiz);
        lottie = view.findViewById(R.id.lottie);
        scroll.fullScroll(View.FOCUS_UP);
        lottie.setVisibility(View.GONE);
        GetData();
        GetPoin();
        Logic();
        GetDivisi();
        GetWilayah();
        CheckQuiz();
        IsiQuiz();
        Healtcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ModuleActivity.class);
                i.putExtra("MODULE", "HEALTHCARE");
                startActivity(i);
            }
        });
        FMCG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ModuleActivity.class);
                i.putExtra("MODULE", "FMCG");
                startActivity(i);
            }
        });
        Mining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ModuleActivity.class);
                i.putExtra("MODULE", "Mining");
                startActivity(i);
            }
        });
        Contractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ModuleActivity.class);
                i.putExtra("MODULE", "Contractor");
                startActivity(i);
            }
        });
        OilAndGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ModuleActivity.class);
                i.putExtra("MODULE", "Oil & Gas");
                startActivity(i);
            }
        });
        Farm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ModuleActivity.class);
                i.putExtra("MODULE", "Farming");
                startActivity(i);
            }
        });
        spDivisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_divisi_mandiri());
                    tvDivisi.setText(String.valueOf(clickedItems));
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spWilayah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_wilayah_mandiri());
                    tvWilayah.setText(String.valueOf(clickedItems));
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        PilihDivisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateDivisi();
            }
        });
        linearQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Quiz){
                        quizDialog.show();
                    }else{
                        Toast.makeText(getActivity(), "Quiz Sudah Dikerjakan", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("AscNet : ",e.toString());
                }
            }
        });
        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JawabA();
            }
        });
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JawabB();
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JawabC();
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JawabD();
            }
        });
        Konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JawabQuiz();
            }
        });
    }
    private void UpdateDivisi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.UpdateDivisi(Token,tvDivisi.getText().toString(),tvWilayah.getText().toString());
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                myDialog.hide();
                try {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(getActivity(),"Koneksi Gagal ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void JawabQuiz(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.JawabQuizHarian(Token,idQuiz.getText().toString(),jawabQuiz.getText().toString());
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                quizDialog.hide();
                try {
                    dialogPesan.show();
                    Pesan.setText(response.body().getMessage());
                    KonfirmasiPesan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(),HomeActivity.class);
                            startActivity(intent);
                            getActivity().finishAffinity();
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Terjadi Kesalahan : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(getActivity(),"Koneksi Gagal ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void QuizDefault(){
        A.setBackgroundResource(R.drawable.button_blue_rounded);
        B.setBackgroundResource(R.drawable.button_blue_rounded);
        C.setBackgroundResource(R.drawable.button_blue_rounded);
        D.setBackgroundResource(R.drawable.button_blue_rounded);
    }
    private void JawabA(){
        QuizDefault();
        A.setBackgroundResource(R.drawable.btn_rounded_accent_2);
        JawabQuiz = "a";
        jawabQuiz.setText("a");
    }
    private void JawabB(){
        QuizDefault();
        B.setBackgroundResource(R.drawable.btn_rounded_accent_2);
        JawabQuiz = "b";
        jawabQuiz.setText("b");
    }
    private void JawabC(){
        QuizDefault();
        C.setBackgroundResource(R.drawable.btn_rounded_accent_2);
        JawabQuiz = "c";
        jawabQuiz.setText("c");
    }
    private void JawabD(){
        QuizDefault();
        D.setBackgroundResource(R.drawable.btn_rounded_accent_2);
        JawabQuiz = "d";
        jawabQuiz.setText("d");
    }
    private void IsiQuiz(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseQuiz> data =api.quiz_harian(Token);
        data.enqueue(new Callback<ResponseQuiz>() {
            @Override
            public void onResponse(Call<ResponseQuiz> call, Response<ResponseQuiz> response) {
                try {
                    idQuiz.setText(response.body().getData().getId_quiz().toString());
                    Soal.setText(response.body().getData().getSoal_quiz());
                    KategoriSoal.setText(response.body().getData().getKategori());
                    A.setText("A."+response.body().getData().getJawaban().get(0).getIsi_jawaban());
                    B.setText("B."+response.body().getData().getJawaban().get(1).getIsi_jawaban());
                    C.setText("C."+response.body().getData().getJawaban().get(2).getIsi_jawaban());
                    D.setText("D."+response.body().getData().getJawaban().get(3).getIsi_jawaban());
                }catch (Exception e){
//                    Toast.makeText(getActivity(), "Terjadi Kesalahan : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseQuiz> call, Throwable t) {

            }
        });
    }
    private void CheckQuiz(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.check_quiz(Token);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getMessage().equals("Quiz tersedia")){
                        lottie.setVisibility(View.VISIBLE);
                        Quiz=true;
                    }else{
                        lottie.setVisibility(View.GONE);
                        Quiz=false;
                    }
                }catch (Exception e){
                    //Toast.makeText(getActivity(), "Kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.Berita(Token,"all","1");
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterBerita(getActivity(),mItems);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(getActivity(), "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetPoin(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.Poin(Token);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        poin.setText(String.valueOf(response.body().getData().getTotal_poin()));
                    }else{
                        response.body().getMessage();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetWilayah(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Wilayah();
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        SpinnerWilayah adapter = new SpinnerWilayah(getActivity(),mItems);
                        spWilayah.setAdapter(adapter);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetDivisi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Divisi();
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        SpinnerDivisi adapter = new SpinnerDivisi(getActivity(),mItems);
                        spDivisi.setAdapter(adapter);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetData(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.Profil(Token);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        nama.setText(response.body().getData().getNama_user());
                        divisi.setText(response.body().getData().getDivisi()+" ("+response.body().getData().getWilayah()+")");
                        if (response.body().getData().getDivisi().equals("")){
                            myDialog.show();
                        }
                    }else{
                        response.body().getMessage();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent goInput = new Intent(getActivity(), DetailBeritaActivity.class);
                goInput.putExtra("KATEGORI","all");
                startActivity(goInput);
            }
        });
    }
}