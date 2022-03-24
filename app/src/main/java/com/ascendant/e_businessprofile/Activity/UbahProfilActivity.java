package com.ascendant.e_businessprofile.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Forum.PostForumActivity;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.Outlook.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfilActivity extends AppCompatActivity {
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
    Dialog myDialog;
    Button View,Download;
    Ascendant ascendant = new Ascendant();
    DB_Helper dbHelper;
    String Token;
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerView;
    ImageView ivMore;
    EditText Nama,NIP,Telepon;
    Button Pilih;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);
        //Cut Here
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningOutlookModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv.setAdapter(adapters);

        Back.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
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

        DB_Helper dbHelper = new DB_Helper(UbahProfilActivity.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }

        Nama=findViewById(R.id.etNama);
        NIP=findViewById(R.id.etNIP);
        Telepon=findViewById(R.id.etNoTelepon);
        Pilih=findViewById(R.id.btnPilih);
        GetData();
        Pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Checker();
            }
        });
    }

    private void Checker(){
        if (Nama.getText().toString().isEmpty()){
            Toast.makeText(UbahProfilActivity.this, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (NIP.getText().toString().isEmpty()){
            Toast.makeText(UbahProfilActivity.this, "NIP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Telepon.getText().toString().isEmpty()){
            Toast.makeText(UbahProfilActivity.this, "Telepon Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else{
            Logic();
        }
    }
    private void Logic(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.EditProfil(Token,Nama.getText().toString(),NIP.getText().toString(),Telepon.getText().toString());
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    Intent goInput = new Intent(UbahProfilActivity.this, HomeActivity.class);
                    goInput.putExtra("FORUM","PROFIL");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Log.d("Zyarga Error",e.toString());
                    Toast.makeText(UbahProfilActivity.this, "Terjadi Kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(UbahProfilActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetData(){
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.d("Zyarga","Fetching FCM Failed",task.getException());
                    return;
                }
                // Get new FCM registration token
                token = task.getResult().toString();
                Log.e("TAGSOO",token);
                ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                final Call<ResponseObject> data =api.Profil(Token,token);
                data.enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        try {
                            if (response.body().getKode().equals(200)){
                                Nama.setText(response.body().getData().getNama_user());
                                NIP.setText(response.body().getData().getNip_user());
                                String MAGIC1 = response.body().getData().getNo_telpon_user().replace("+62","");
                                Telepon.setText(MAGIC1);
                            }else{
                                response.body().getMessage();
                            }
                        }catch (Exception e){
                            Log.d("ZYARGA : ",e.toString());
                            Toast.makeText(UbahProfilActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {
                        Intent intent = new Intent(UbahProfilActivity.this,LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(UbahProfilActivity.this, "Waktu Sesi habis harap coba Login Lagfi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}