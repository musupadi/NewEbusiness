package com.ascendant.e_businessprofile.Activity.ui.FMCG;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.BusinessRefrence.FMCGBusinessRefrenceActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.Compliance.FMCGComplianceActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.FMCGEcosystemActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.ListOfProbing.FMCGListOfProbingActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence.BusinessRefrenceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance.ComplianceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.CrreditDecisionToolActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.CreditWorthinessActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.EcosystemActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.ListOfProbing.ListOfProbingActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterBerita;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FMCGFragment extends Fragment {
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    RecyclerView rv;
    DB_Helper dbHelper;
    String Token;
    LinearLayout Back;
    RelativeLayout BusinessRefrence,ListOfProbing,Compliance,Ecosystem,CreditWorthiness,CreditDecisionTool;
    ScrollView scroll;


    public FMCGFragment() {
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
        return inflater.inflate(R.layout.fragment_f_m_c_g, container, false);
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
        Back = view.findViewById(R.id.linearBack);
        BusinessRefrence = view.findViewById(R.id.relativeBusinessRefrennce);
        ListOfProbing = view.findViewById(R.id.relativeListOfProbing);
        Compliance = view.findViewById(R.id.relativeCompliance);
        Ecosystem = view.findViewById(R.id.relativeEcosystem);
        CreditWorthiness = view.findViewById(R.id.relativeCreditWorthiness);
        CreditDecisionTool = view.findViewById(R.id.relativeCreditDecisionTool);
        Logic();
        BusinessRefrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FMCGBusinessRefrenceActivity.class);
                startActivity(intent);
            }
        });
        ListOfProbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FMCGListOfProbingActivity.class);
                startActivity(intent);
            }
        });
        Compliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FMCGComplianceActivity.class);
                startActivity(intent);
            }
        });
        Ecosystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FMCGEcosystemActivity.class);
                startActivity(intent);
            }
        });
        CreditWorthiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), CreditWorthinessActivity.class);
//                startActivity(intent);
            }
        });
        CreditDecisionTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), CrreditDecisionToolActivity.class);
//                startActivity(intent);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(View.FOCUS_UP);
            }
        });
    }
    private void Logic(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.Berita(Token,"FMCG","1");
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
}