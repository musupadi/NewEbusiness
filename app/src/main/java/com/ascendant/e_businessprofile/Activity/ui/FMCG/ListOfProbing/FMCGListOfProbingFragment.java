package com.ascendant.e_businessprofile.Activity.ui.FMCG.ListOfProbing;

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
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.ListOfProbing.FMCGProbing.FoodAndBeverageProbingActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.ListOfProbing.FMCGProbing.NonFoodAndBeverageProbingActivity;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.ListOfProbing.FMCGProbing.TobaccoProbingActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterListOfProbing;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FMCGListOfProbingFragment extends Fragment {
    RelativeLayout FnB,NFnB,Tobacco;
    public FMCGListOfProbingFragment() {
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
        return inflater.inflate(R.layout.fragment_list_of_probing_f_m_c_g, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FnB = view.findViewById(R.id.relativeFNB);
        NFnB = view.findViewById(R.id.relativeNFNB);
        Tobacco = view.findViewById(R.id.relativeTobacco);
        FnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), FoodAndBeverageProbingActivity.class);
                startActivity(intent);
            }
        });
        NFnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), NonFoodAndBeverageProbingActivity.class);
                startActivity(intent);
            }
        });
        Tobacco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), TobaccoProbingActivity.class);
                startActivity(intent);
            }
        });
    }
}