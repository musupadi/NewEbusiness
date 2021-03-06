package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.FullScreenActivity;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.HospitalAssociation.HospitalAssociationActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital.HospitalListActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.SupportingIndustries.SupportingInduestriesActivity;
import com.ascendant.e_businessprofile.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EcosystemFragment extends Fragment {

    PDFView photoView;
    CardView ListOfHospital,SupportingIndustries,HospitalAssociation;
    Ascendant ascendant = new Ascendant();
    TextView lihat;
    public EcosystemFragment() {
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
        return inflater.inflate(R.layout.fragment_ecosystem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListOfHospital = view.findViewById(R.id.cardListOfHospital);
        SupportingIndustries = view.findViewById(R.id.cardSupportingINdustries);
        HospitalAssociation = view.findViewById(R.id.cardHospitalAssociation);
        photoView =view.findViewById(R.id.ivEcosystem);
        lihat = view.findViewById(R.id.tvView);

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(getActivity(), FullScreenActivity.class);
                goInput.putExtra("IMAGE","https://fabakonsultan.com/uploads/hospital/ekosistem/map_ekosistem.jpg");
                startActivity(goInput);
            }
        });
        new RetreivePDFStreamsss().execute(ascendant.BASE_URL()+"files/healthcare/ekosistem/map_ekosistem.pdf");
        ListOfHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HospitalListActivity.class);
                startActivity(intent);
            }
        });
        HospitalAssociation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HospitalAssociationActivity.class);
                startActivity(intent);
            }
        });
        SupportingIndustries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SupportingInduestriesActivity.class);
                startActivity(intent);
            }
        });
    }
    class RetreivePDFStreamsss extends AsyncTask<String,Void, InputStream> {
        InputStream inputStream;
        @Override
        protected InputStream doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            photoView.fromStream(inputStream).load();
        }
    }
}