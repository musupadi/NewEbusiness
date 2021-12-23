package com.ascendant.e_businessprofile.Activity.ui.Mining.ListOfProbing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterListOfProbing;
import com.ascendant.e_businessprofile.Adapter.AdapterListOfProbing2;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfProbingMiningActivity extends AppCompatActivity {
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    DB_Helper dbHelper;
    String Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_probing_mining);

        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        rv = findViewById(R.id.recycler);
        Logic();
    }
    private void Logic(){
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.Mining_List_of_Probing(Token);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterListOfProbing2(ListOfProbingMiningActivity.this,mItems);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(ListOfProbingMiningActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(ListOfProbingMiningActivity.this, "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(ListOfProbingMiningActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}