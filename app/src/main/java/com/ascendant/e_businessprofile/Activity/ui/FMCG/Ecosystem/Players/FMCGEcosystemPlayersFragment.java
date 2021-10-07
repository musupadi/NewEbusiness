package com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Players;

import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.OldRetroServer;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterBusinessModel;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerKota;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerProvinsi;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.DataPerusahaan;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.Perusahaan;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FMCGEcosystemPlayersFragment extends Fragment {
    ImageView back,home;
    private RecyclerView rvFood;
    private List<DataPerusahaan> mItems = new ArrayList<>();
    private List<DataModel> mItemss = new ArrayList<>();
    private List<DataModel> mItemsss = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    AdapterBusinessModel adapter;
    Ascendant method = new Ascendant();
    EditText nama;
    TextView ID,IDKota;
    Spinner tipe,kategori,tbk,Provinsi,Kota;
    LinearLayout linearTipe,linearKategori,linearKota;
    String Type,Category;
    int loop = 0;
    Button cari;
    ProgressBar progressBar;
    private SpinnerProvinsi aProvinsi;
    private SpinnerKota aKota;

    private int page_number = 1;
    private int item_count = 20;
    private boolean isLoading = true;
    private int pastVisibleItems,visibleItemCount,totalItemCount,previous_total = 0;
    private int view_theresold = 20;
    DB_Helper dbHelper;
    String Token;
    public FMCGEcosystemPlayersFragment() {
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
        return inflater.inflate(R.layout.fragment_f_m_c_g_ecosystem_players, container, false);
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
        rvFood = view.findViewById(R.id.recycler);
        nama = view.findViewById(R.id.etNama);
        tipe = view.findViewById(R.id.spinnerTipe);
        linearTipe = view.findViewById(R.id.linearTipe);
        linearKategori = view.findViewById(R.id.linearKategori);
        linearKota = view.findViewById(R.id.linearKota);
        kategori = view.findViewById(R.id.spinnerKategori);
        tbk = view.findViewById(R.id.spinnerTBK);
        cari = view.findViewById(R.id.btnCari);
        progressBar = view.findViewById(R.id.progressBar);
        Provinsi = view.findViewById(R.id.spinnerProvinsi);
        ID = view.findViewById(R.id.tvIDProvinsi);
        IDKota = view.findViewById(R.id.tvIDKota);
        Kota = view.findViewById(R.id.spinnerKota);
        getProvinsi();
        aKota = new SpinnerKota(getActivity(),mItemsss);
        Provinsi.setAdapter(aProvinsi);
        Provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataModel clickedItem = (DataModel) parent.getItemAtPosition(position);
                String clickedItems = clickedItem.getId_provinsi();
                ID.setText(clickedItems);
                if (ID.getText().toString().equals("0")){
                    linearKota.setVisibility(View.GONE);
                    Logic();
                }else{
                    linearKota.setVisibility(View.VISIBLE);
                }
                getProvinsiKota(ID.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Kota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DataModel clickedItem2 = (DataModel) parent.getItemAtPosition(position);
                String clickedItems2 = clickedItem2.getId_kab_kota();
                IDKota.setText(clickedItems2);
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tbk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!tipe.getSelectedItem().equals("Distributor")){
                    linearKategori.setVisibility(View.VISIBLE);
                }else{
                    linearKategori.setVisibility(View.GONE);
                    kategori.setSelection(0);
                }
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Logic();
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic();
            }
        });
    }
    private String SpinnerChecker(){
        String checker="";
        if (tbk.getSelectedItem().toString().equals("TBK")){
            checker = "1";
        }else if(tbk.getSelectedItem().toString().equals("Non TBK")){
            checker = "0";
        }else{
            checker = "";
        }
        return checker;
    }
    private void Logic(){
        page_number = 1;
        Type = "";
        Category = "";
        if (tipe.getSelectedItem().toString().equals("Semua Tipe")){
            Type="";
        }else{
            Type=tipe.getSelectedItem().toString();
        }
        if (kategori.getSelectedItem().toString().equals("Semua Kategori")){
            Category="";
        }else{
            Category=kategori.getSelectedItem().toString();
        }
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mencoba Mengambil Data");
        pd.setCancelable(false);
        pd.show();
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<Perusahaan> food = api.DataPerusahaan(method.AUTH(),
                "FABAJakartaIndonesia2019kunci",
                nama.getText().toString(),Type.toLowerCase(),
                Category.toLowerCase(),String.valueOf(page_number),
                tbk.getSelectedItem().toString().toLowerCase(),ID.getText().toString(),
                IDKota.getText().toString());
        mManager = new LinearLayoutManager(getActivity());
        rvFood.setHasFixedSize(true);
        rvFood.setLayoutManager(mManager);

        progressBar.setVisibility(View.VISIBLE);
        food.enqueue(new Callback<Perusahaan>() {
            @Override
            public void onResponse(Call<Perusahaan> call, Response<Perusahaan> response) {
                try {
                    pd.hide();
                    rvFood.setVisibility(View.VISIBLE);
                    mItems=response.body().getData();
                    rvFood.setAdapter(adapter);
                    adapter = new AdapterBusinessModel(getActivity(),mItems);
                    rvFood.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }catch (Exception e){
                    pd.hide();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Perusahaan> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        rvFood.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mManager.getChildCount();
                totalItemCount = mManager.getItemCount();
                pastVisibleItems = mManager.findFirstVisibleItemPosition();

                if (dy>0){
                    if (isLoading){
                        if (totalItemCount>previous_total){
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    }
                    if (!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleItems+view_theresold)){
                        page_number++;
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }
    private void performPagination(){
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<Perusahaan> food = api.DataPerusahaan(method.AUTH(),"FABAJakartaIndonesia2019kunci",nama.getText().toString(),Type.toLowerCase(),Category.toLowerCase(),String.valueOf(page_number),tbk.getSelectedItem().toString().toLowerCase(),ID.getText().toString(),IDKota.getText().toString());
        progressBar.setVisibility(View.VISIBLE);
        food.enqueue(new Callback<Perusahaan>() {
            @Override
            public void onResponse(Call<Perusahaan> call, Response<Perusahaan> response) {
                try {
                    mItems=response.body().getData();
                    if (mItems.size() > 0){
                        adapter.LoadMore(mItems);
                        rvFood.setAdapter(adapter);
                    }else{
                        Toast.makeText(getActivity(), "Seluruh Halaman sudah di Load", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }catch (Exception e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Perusahaan> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getProvinsi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> getProvinsi = api.Provinsi(method.AUTH());
        getProvinsi.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    mItemss=response.body().getData();
                    SpinnerProvinsi adapter = new SpinnerProvinsi(getActivity(),mItemss);
                    Provinsi.setAdapter(adapter);
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(),"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getProvinsiKota(String id){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> getProvinsi = api.Kota(Token,id);
        getProvinsi.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    mItemsss=response.body().getData();
                    SpinnerKota adapter = new SpinnerKota(getActivity(),mItemsss);
                    Kota.setAdapter(adapter);
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(),"Koneksi Gagal",Toast.LENGTH_SHORT).show();
            }
        });
    }
}