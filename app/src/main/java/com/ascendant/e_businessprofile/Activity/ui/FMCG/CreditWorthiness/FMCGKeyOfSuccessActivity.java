package com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditWorthiness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.OldRetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Adapter.AdapterKeyOfSuccess;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessFNBKeyOfSuccessModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessNFNBKeyOfSuccessModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Navigator.CreditWorthiness.FMCGCreditWorthinessTobaccoKeyOfSuccessModel;
import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Rumus.Probing;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FMCGKeyOfSuccessActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();

    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.LayoutManager mManager;
    AdapterKeyOfSuccess adapter;
    ProgressBar pd;
    ImageView back,home;
    TextView header;
    Ascendant method = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgkey_of_success);
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);

        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);
        recyclerView = findViewById(R.id.recycler);
        Intent data = getIntent();
        String KOS = data.getStringExtra("KOS");
        if (KOS.equals("fnb")){
            pList.addAll(FMCGCreditWorthinessFNBKeyOfSuccessModel.getListData());
        }else if (KOS.equals("non fnb")){
            pList.addAll(FMCGCreditWorthinessNFNBKeyOfSuccessModel.getListData());
        }else{
            pList.addAll(FMCGCreditWorthinessTobaccoKeyOfSuccessModel.getListData());
        }
        getData(KOS);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        Available.setVisibility(View.GONE);
                        Navigator.setVisibility(View.VISIBLE);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        Available.setVisibility(View.VISIBLE);
                        Navigator.setVisibility(View.GONE);
                    }
                }catch (Exception e){

                }

            }
        });
    }

    private void getData(String KOS){
        final ProgressDialog pd = new ProgressDialog(FMCGKeyOfSuccessActivity.this);
        pd.setMessage("Sedang Mengambil Data Key Of Succes");
        pd.setCancelable(false);
        pd.show();
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<Probing> probing = api.KosFMCG(method.AUTH(),"FABAJakartaIndonesia2019kunci",KOS);
        probing.enqueue(new Callback<Probing>() {
            @Override
            public void onResponse(Call<Probing> call, Response<Probing> response) {
                pd.hide();
                try {
                    mManager = new LinearLayoutManager(FMCGKeyOfSuccessActivity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(mManager);
                    mItems=response.body().getData();
                    adapter = new AdapterKeyOfSuccess(FMCGKeyOfSuccessActivity.this,mItems);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(FMCGKeyOfSuccessActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Probing> call, Throwable t) {
                pd.hide();
                Toast.makeText(FMCGKeyOfSuccessActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}