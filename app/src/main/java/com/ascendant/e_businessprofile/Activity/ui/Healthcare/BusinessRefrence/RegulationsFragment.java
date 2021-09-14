package com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ascendant.e_businessprofile.R;

public class RegulationsFragment extends Fragment {
    RelativeLayout Acts,GovernmentRegulations,PresidentialDecree,HealthMinisterDecree;
    public RegulationsFragment() {
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
        return inflater.inflate(R.layout.fragment_regulations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Acts = view.findViewById(R.id.relativeActs);
        GovernmentRegulations = view.findViewById(R.id.relativeGovernmentRegulation);
        PresidentialDecree = view.findViewById(R.id.relativePresidentialDecree);
        HealthMinisterDecree = view.findViewById(R.id.relativeHealthMinisterRegulations);
        Acts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), eBookActivity.class);
                i.putExtra("MODULE", "uu");
                getActivity().startActivity(i);
            }
        });
        GovernmentRegulations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), eBookActivity.class);
                i.putExtra("MODULE", "pp");
                getActivity().startActivity(i);
            }
        });
        PresidentialDecree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), eBookActivity.class);
                i.putExtra("MODULE", "pmk");
                getActivity().startActivity(i);
            }
        });
        HealthMinisterDecree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), eBookActivity.class);
                i.putExtra("MODULE", "perpres");
                getActivity().startActivity(i);
            }
        });
    }
}