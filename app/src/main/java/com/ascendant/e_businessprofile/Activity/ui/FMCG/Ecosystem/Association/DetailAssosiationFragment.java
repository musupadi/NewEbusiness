package com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Association;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.R;


public class DetailAssosiationFragment extends Fragment {
    CardView cardPengurus,cardAnggota;
    TextView tvPengurus,tvAnggota;
    WebView webPengurus,webAnggota;
    Ascendant method = new Ascendant();
    public DetailAssosiationFragment() {
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
        return inflater.inflate(R.layout.fragment_detail_assosiation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        String ID = bundle.getString("ID");
        String PENGURUS = bundle.getString("PENGURUS");
        String ANGGOTA = bundle.getString("ANGGOTA");

        cardAnggota=view.findViewById(R.id.cardAnggota);
        cardPengurus=view.findViewById(R.id.cardPengurus);
        tvPengurus=view.findViewById(R.id.tvPengurus);
        tvAnggota=view.findViewById(R.id.tvAnggota);
        webPengurus=view.findViewById(R.id.webPengurus);
        webAnggota=view.findViewById(R.id.webAnggota);
        if (PENGURUS.equals("-")){
            tvPengurus.setVisibility(View.VISIBLE);
            webPengurus.setVisibility(View.GONE);
        }else{
            tvPengurus.setVisibility(View.GONE);
            webPengurus.setVisibility(View.VISIBLE);
            webPengurus.loadData(PENGURUS,"text/html","UTF-8");
        }
        if (ANGGOTA.equals("-")){
            tvAnggota.setVisibility(View.VISIBLE);
            webAnggota.setVisibility(View.GONE);
        }else{
            tvAnggota.setVisibility(View.GONE);
            webAnggota.setVisibility(View.VISIBLE);
            webAnggota.loadData(ANGGOTA,"text/html","UTF-8");
        }
    }
}