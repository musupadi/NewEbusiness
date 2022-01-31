package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Forum.PostForumActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterForum;
import com.ascendant.e_businessprofile.Adapter.AdapterPercakapanUser;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PertanyaanUserActvity extends AppCompatActivity {
    ImageView Search;
    EditText etSearch;
    Spinner Category;
    DB_Helper dbHelper;
    String Token;
    Button Post;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pewrtanyaan_user_actvity);
        Search = findViewById(R.id.ivSearch);
        etSearch = findViewById(R.id.etSearch);
        Category = findViewById(R.id.spinnerCategory);
        rv = findViewById(R.id.recycler);
        Post = findViewById(R.id.btnPost);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PertanyaanUserActvity.this, PostForumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Logic(){
        mManager = new LinearLayoutManager(PertanyaanUserActvity.this, LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.DaftarPercakapanExpert(Token,Checker(Category.getSelectedItem().toString()));
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterPercakapanUser(PertanyaanUserActvity.this,mItems,true);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(PertanyaanUserActvity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("DONTOL : ",e.toString());
                    Toast.makeText(PertanyaanUserActvity.this, "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(PertanyaanUserActvity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String Checker(String text){
        if (text.equals("All Category")){
            text = "";
        }else{
            text = text.toLowerCase(Locale.ROOT);
        }

        return text;
    }
}