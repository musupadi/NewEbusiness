package com.ascendant.e_businessprofile.Activity.ui.FMCG.BusinessRefrence;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.OldRetroServer;
import com.ascendant.e_businessprofile.Adapter.AdapterRegulationFMCG;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.RegulationFMCGModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FMCGRegulationFragment extends Fragment {

    private RecyclerView rvRegulasiUmum,rvRegulasiFood,rvRegulasiHousehold,rvRegulasiRokok;
    private List<DataModel> mItemsUmum = new ArrayList<>();
    private List<DataModel> mItemsFood = new ArrayList<>();
    private List<DataModel> mItemsRokok = new ArrayList<>();
    private List<DataModel> mItemsHousehold = new ArrayList<>();
    TextView header;
    ImageView ivUmum,ivFood,ivHousehold,ivRokok;
    Boolean clickedUmum = false;
    Boolean clickedFood = false;
    Boolean clickedHousehold = false;
    Boolean clickedRokok = false;
    Ascendant method = new Ascendant();

    public FMCGRegulationFragment() {
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
        return inflater.inflate(R.layout.fragment_f_m_c_g_regulation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRegulasiUmum = view.findViewById(R.id.recyclerUmum);
        rvRegulasiFood = view.findViewById(R.id.recyclerFood);
        rvRegulasiHousehold = view.findViewById(R.id.recyclerHousehold);
        rvRegulasiRokok = view.findViewById(R.id.recyclerRokok);
        header = view.findViewById(R.id.tvHeader);
        ivUmum = view.findViewById(R.id.ivIconUmum);
        ivFood = view.findViewById(R.id.ivIconFood);
        ivRokok = view.findViewById(R.id.ivIconRokok);
        ivHousehold = view.findViewById(R.id.ivIconHoousehold);
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<RegulationFMCGModel> regulation = api.RegulationFMCG(method.AUTH(),"FABAJakartaIndonesia2019kunci");
        rvRegulasiUmum.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRegulasiFood.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRegulasiHousehold.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRegulasiRokok.setLayoutManager(new LinearLayoutManager(getActivity()));
        regulation.enqueue(new Callback<RegulationFMCGModel>() {
            @Override
            public void onResponse(Call<RegulationFMCGModel> call, Response<RegulationFMCGModel> response) {
                mItemsUmum=response.body().getData().getUmum();
                mItemsFood=response.body().getData().getFood();
                mItemsRokok=response.body().getData().getNonFnBRokok();
                mItemsHousehold=response.body().getData().getNonFnBHousehold();
                AdapterRegulationFMCG adapter1 = new AdapterRegulationFMCG(getActivity(),mItemsUmum);
                rvRegulasiUmum.setAdapter(adapter1);
                AdapterRegulationFMCG adapter2 = new AdapterRegulationFMCG(getActivity(),mItemsRokok);
                rvRegulasiRokok.setAdapter(adapter2);
                AdapterRegulationFMCG adapter3 = new AdapterRegulationFMCG(getActivity(),mItemsHousehold);
                rvRegulasiHousehold.setAdapter(adapter3);
                AdapterRegulationFMCG adapter4 = new AdapterRegulationFMCG(getActivity(),mItemsFood);
                rvRegulasiFood.setAdapter(adapter4);
            }

            @Override
            public void onFailure(Call<RegulationFMCGModel> call, Throwable t) {

            }
        });
        ivUmum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedUmum){
                    ivUmum.setImageResource(R.drawable.chevron_down);
                    rvRegulasiUmum.setVisibility(View.GONE);
                    clickedUmum=false;
                }else{
                    ivUmum.setImageResource(R.drawable.chevron_up);
                    rvRegulasiUmum.setVisibility(View.VISIBLE);
                    clickedUmum=true;
                }
            }
        });
        ivFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedFood){
                    ivFood.setImageResource(R.drawable.chevron_down);
                    rvRegulasiFood.setVisibility(View.GONE);
                    clickedFood=false;
                }else{
                    ivFood.setImageResource(R.drawable.chevron_up);
                    rvRegulasiFood.setVisibility(View.VISIBLE);
                    clickedFood=true;
                }
            }
        });
        ivHousehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedHousehold){
                    ivHousehold.setImageResource(R.drawable.chevron_down);
                    rvRegulasiHousehold.setVisibility(View.GONE);
                    clickedHousehold=false;
                }else{
                    ivHousehold.setImageResource(R.drawable.chevron_up);
                    rvRegulasiHousehold.setVisibility(View.VISIBLE);
                    clickedHousehold=true;
                }
            }
        });
        ivRokok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedRokok){
                    ivRokok.setImageResource(R.drawable.chevron_down);
                    rvRegulasiRokok.setVisibility(View.GONE);
                    clickedRokok=false;
                }else{
                    ivRokok.setImageResource(R.drawable.chevron_up);
                    rvRegulasiRokok.setVisibility(View.VISIBLE);
                    clickedRokok=true;
                }
            }
        });
    }
}