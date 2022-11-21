package com.ascendant.e_businessprofile.Activity.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.OilAndGas.Outlook.OilAndGasNewsletterActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterOutlook;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    //Category
    ImageView ivHealthcare,ivFMCG,ivMining,ivContractor,ivOilAndGas,ivFarming;
    RelativeLayout Healthcare,FMCG,Mining,Contractor,OilAndGas,Farming;
    Spinner spinnerCategory;
    LinearLayout AllCategory;
    TextView textCategory;
    //Main
    EditText etSearch;
    ImageView ivSearch;
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    DB_Helper dbHelper;
    String Token;
    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Declaration
        //Category
        ivHealthcare = view.findViewById(R.id.ivHealthcare);
        ivFMCG = view.findViewById(R.id.ivFMCG);
        ivMining = view.findViewById(R.id.ivMining);
        ivContractor = view.findViewById(R.id.ivContractor);
        ivOilAndGas = view.findViewById(R.id.ivOilGas);
        ivFarming = view.findViewById(R.id.ivFarming);
        Healthcare = view.findViewById(R.id.relativeHealthcare);
        FMCG = view.findViewById(R.id.relativeFMCG);
        Mining = view.findViewById(R.id.relativeMining);
        Contractor = view.findViewById(R.id.relativeContractor);
        OilAndGas = view.findViewById(R.id.relativeOilAndGas);
        Farming = view.findViewById(R.id.relativeFarm);
        textCategory = view.findViewById(R.id.tvCategory);
        spinnerCategory = view.findViewById(R.id.spPilihCategory);
        AllCategory = view.findViewById(R.id.linearCategory);

        //Main
        etSearch = view.findViewById(R.id.etSearch);
        ivSearch = view.findViewById(R.id.ivSearch);
        rv = view.findViewById(R.id.recycler);
        Logic();
        onClick();
        Search();
    }
    private void Logic(){
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        AllCategory.setVisibility(View.GONE);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerCategory.getSelectedItem().toString().equals("Select Category")){
                    AllCategory.setVisibility(View.VISIBLE);
                    textCategory.setText("");
                }else{
                    AllCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void Search(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.Search(etSearch.getText().toString(),textCategory.getText().toString(),Token);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        LayoutAnimationController Animation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation2);
                        rv.setLayoutAnimation(Animation);
                        rv.scheduleLayoutAnimation();
                        mItems=response.body().getData();
                        mAdapter = new AdapterOutlook(getActivity(),mItems);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClick(){
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search();
            }
        });
        Healthcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Healthcare();
            }
        });
        FMCG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FMCG();
            }
        });
        Mining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mining();
            }
        });
        Contractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contractor();
            }
        });
        OilAndGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OilAndGas();
            }
        });
        Farming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Farming();
            }
        });
    }
    private void Farming(){
        Default();
        ivFarming.setImageResource(R.drawable.peternakan_active);
        textCategory.setText("farming");
    }
    private void OilAndGas(){
        Default();
        ivOilAndGas.setImageResource(R.drawable.oil_gass_active);
        textCategory.setText("oil_and_gas");
    }
    private void Contractor(){
        Default();
        ivContractor.setImageResource(R.drawable.contractor_active);
        textCategory.setText("contractor");
    }
    private void Mining(){
        Default();
        ivMining.setImageResource(R.drawable.mining_active);
        textCategory.setText("mining");
    }
    private void FMCG(){
        Default();
        ivFMCG.setImageResource(R.drawable.fmcg_active);
        textCategory.setText("fmcg");
    }
    private void Healthcare(){
        Default();
        ivHealthcare.setImageResource(R.drawable.healthcare_active);
        textCategory.setText("healthcare");
    }
    private void Default(){
        ivHealthcare.setImageResource(R.drawable.healthcare_inactive);
        ivFMCG.setImageResource(R.drawable.fmcg_inactive);
        ivContractor.setImageResource(R.drawable.contractor_inactive);
        ivOilAndGas.setImageResource(R.drawable.oil_gass_inactive);
        ivMining.setImageResource(R.drawable.mining_inactive);
        ivFarming.setImageResource(R.drawable.peternakan_inactive);
    }
}