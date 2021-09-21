package com.ascendant.e_businessprofile.Activity.ui;

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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.ModuleActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterBerita;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
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
    RelativeLayout Healtcare,FMCG;
    ScrollView scroll;
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
        scroll = view.findViewById(R.id.scroll);
        rv = view.findViewById(R.id.recycler);
        Healtcare = view.findViewById(R.id.relativeHealthcare);
        FMCG = view.findViewById(R.id.relativeFMCG);
        nama = view.findViewById(R.id.tvNama);
        divisi = view.findViewById(R.id.tvDivisi);
        poin = view.findViewById(R.id.tvPoin);

        scroll.fullScroll(View.FOCUS_UP);
        GetData();
        GetPoin();
        Logic();
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
                    Log.d("DONTOL : ",e.toString());
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
                    Log.d("DONTOL : ",e.toString());
                    Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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
                        divisi.setText(response.body().getData().getDivisi());
                    }else{
                        response.body().getMessage();
                    }
                }catch (Exception e){
                    Log.d("DONTOL : ",e.toString());
                    Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}