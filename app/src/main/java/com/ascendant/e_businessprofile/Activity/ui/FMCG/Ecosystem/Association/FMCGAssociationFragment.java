package com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Association;

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
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.OldRetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Adapter.AdapterAssosiation;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus.Probing;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FMCGAssociationFragment extends Fragment {
    private List<DataModel> mItems = new ArrayList<>();
    RecyclerView recyclerView;
    ImageView back,home;
    Ascendant method = new Ascendant();

    public FMCGAssociationFragment() {
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
        return inflater.inflate(R.layout.fragment_f_m_c_g_association, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<Probing> assosiation = api.AssosiationFMCG(method.AUTH(),"FABAJakartaIndonesia2019kunci");
        assosiation.enqueue(new Callback<Probing>() {
            @Override
            public void onResponse(Call<Probing> call, Response<Probing> response) {
                mItems =response.body().getData();
                AdapterAssosiation adapter = new AdapterAssosiation(getActivity(),mItems);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Probing> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}