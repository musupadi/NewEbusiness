package com.ascendant.e_businessprofile.Activity.ui.Healthcare;

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
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.DetailBeritaActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence.BusinessRefrenceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance.ComplianceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditDecisionTool.CrreditDecisionToolActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.CreditWorthiness.CreditWorthinessActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.EcosystemActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.ListOfProbing.ListOfProbingActivity;
import com.ascendant.e_businessprofile.Activity.ui.Mining.MandiriUpdate.MiningMandiriUpdateActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterBerita;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HealthcareFragment extends Fragment {
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    RecyclerView rv;
    DB_Helper dbHelper;
    String Token;
    LinearLayout Back;
    RelativeLayout BusinessRefrence,ListOfProbing,Compliance,Ecosystem,CreditWorthiness,CreditDecisionTool;
    LinearLayout MandiriUpdate;
    ScrollView scroll;
    TextView View;
    public HealthcareFragment() {
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
        return inflater.inflate(R.layout.fragment_healthcare, container, false);
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
        View = view.findViewById(R.id.tvView);
        scroll = view.findViewById(R.id.scroll);
        rv = view.findViewById(R.id.recycler);
        Back = view.findViewById(R.id.linearBack);
        BusinessRefrence = view.findViewById(R.id.relativeBusinessRefrennce);
        ListOfProbing = view.findViewById(R.id.relativeListOfProbing);
        Compliance = view.findViewById(R.id.relativeCompliance);
        Ecosystem = view.findViewById(R.id.relativeEcosystem);
        CreditWorthiness = view.findViewById(R.id.relativeCreditWorthiness);
        CreditDecisionTool = view.findViewById(R.id.relativeCreditDecisionTool);
        MandiriUpdate = view.findViewById(R.id.linearMandiriUpdate);
        Logic();
        BusinessRefrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log("1");
                Intent intent = new Intent(getActivity(), BusinessRefrenceActivity.class);
                startActivity(intent);
            }
        });
        Ecosystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log("2");
                Intent intent = new Intent(getActivity(), EcosystemActivity.class);
                startActivity(intent);
            }
        });
        ListOfProbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log("5");
                Intent intent = new Intent(getActivity(), ListOfProbingActivity.class);
                startActivity(intent);
            }
        });
        CreditWorthiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log("3");
                Intent intent = new Intent(getActivity(), CreditWorthinessActivity.class);
                startActivity(intent);
            }
        });
        CreditDecisionTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log("4");
                Intent intent = new Intent(getActivity(), CrreditDecisionToolActivity.class);
                startActivity(intent);
            }
        });
        Compliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log("3");
                Intent intent = new Intent(getActivity(), ComplianceActivity.class);
                startActivity(intent);
            }
        });
        MandiriUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Log("38");
                Intent goInput = new Intent(getActivity(), MiningMandiriUpdateActivity.class);
                goInput.putExtra("KATEGORI","HEALTHCARE");
                startActivity(goInput);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        scroll.fullScroll(View.FOCUS_UP);
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent goInput = new Intent(getActivity(), DetailBeritaActivity.class);
                goInput.putExtra("KATEGORI","HEALTHCARE");
                startActivity(goInput);
            }
        });
    }
    private void Logic(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.Berita(Token,"HEALTHCARE","1");
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
    private void Log(String id){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.Log(Token,id);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {

            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}