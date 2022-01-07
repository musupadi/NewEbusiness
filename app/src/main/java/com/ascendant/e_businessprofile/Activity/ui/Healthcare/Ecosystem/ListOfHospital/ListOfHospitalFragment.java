package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.ListOfHospital;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerKota;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerProvinsi;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListOfHospitalFragment extends Fragment{
    private GoogleMap mMap;
    Spinner Provinsi,Kota,Kelas;
    TextView IdProvinsi,IdKota;
    EditText Cari;
    private List<DataModel> mItems = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;
    LinearLayout LList,LMap;
    TextView List,Map;
    Fragment fragment;
    String Choice = "List";
    Bundle bundle;
    public ListOfHospitalFragment() {
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
        return inflater.inflate(R.layout.fragment_list_of_hospital, container, false);
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
        Cari = view.findViewById(R.id.etSearchHospital);
        Provinsi = view.findViewById(R.id.spinnerProvinsi);
        Kota = view.findViewById(R.id.spinnerKota);
        Kelas = view.findViewById(R.id.spinnerKelas);
        IdProvinsi = view.findViewById(R.id.tvIdProvinsi);
        IdKota = view.findViewById(R.id.tvIdKota);
        LList = view.findViewById(R.id.linearList);
        LMap = view.findViewById(R.id.linearMap);
        List = view.findViewById(R.id.tvList);
        Map = view.findViewById(R.id.tvMap);
        GetProvinsi();
        Provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_provinsi());
                    IdProvinsi.setText(String.valueOf(clickedItems));
                    GetKota();
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_kab_kota());
                    IdKota.setText(String.valueOf(clickedItems));
                    bundle = new Bundle();
                    bundle.putString("PROVINSI_RS", IdProvinsi.getText().toString());
                    bundle.putString("KAB_KOTA_RS", IdKota.getText().toString());
                    bundle.putString("KELAS", Kelas.getSelectedItem().toString());
                    bundle.putString("SEARCH", Cari.getText().toString());
                    if (Choice.equals("List")){
                        fragment = new ListHospitalFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        fragment = new MapsHospitalFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        LList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choice = "List";
                List.setTextColor(Color.WHITE);
                Map.setTextColor(Color.rgb(31,83,137));
                LList.setBackgroundColor(Color.rgb(31,83,137));
                LMap.setBackgroundColor(Color.WHITE);
                fragment = new ListHospitalFragment();
                fragment.setArguments(bundle);
                ChangeFragment(fragment);
            }
        });
        LMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Choice = "Map";
                List.setTextColor(Color.rgb(31,83,137));
                Map.setTextColor(Color.WHITE);
                LList.setBackgroundColor(Color.WHITE);
                LMap.setBackgroundColor(Color.rgb(31,83,137));
                fragment = new MapsHospitalFragment();
                fragment.setArguments(bundle);
                ChangeFragment(fragment);
            }
        });

    }
    private void GetKota(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Kota(Token,IdProvinsi.getText().toString());
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.body().getKode().equals(200)){
                    mItems=response.body().getData();
                    SpinnerKota adapter = new SpinnerKota(getActivity(),mItems);
                    Kota.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetProvinsi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Provinsi(Token);
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.body().getKode().equals(200)){
                    mItems=response.body().getData();
                    SpinnerProvinsi adapter = new SpinnerProvinsi(getActivity(),mItems);
                    Provinsi.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.Container,fragment);
            ft.commit();
        }
    }
}