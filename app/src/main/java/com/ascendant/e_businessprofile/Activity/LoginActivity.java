package com.ascendant.e_businessprofile.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.MyFirebaseMessagingService;
import com.ascendant.e_businessprofile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView register;
    LinearLayout login;
    EditText username,password;
    DB_Helper dbHelper;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Uri data = this.getIntent().getData();
        register = findViewById(R.id.tvRegister);
        login = findViewById(R.id.linearLogin);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        if (data != null && data.isHierarchical()) {
            String uri = this.getIntent().getDataString();
            Log.i("MyApp", "Deep link clicked " + uri);
//            List<String> params = data.getPathSegments();
//            String fury = params.get(0); // "status"
//            String mail = params.get(1);
//            Validasi(mail,fury);
            Toast.makeText(LoginActivity.this, "Selamat Anda Berhak Login", Toast.LENGTH_SHORT).show();
        }


        dbHelper = new DB_Helper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void Validasi(String fury,String mail){
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Mohon Menunggu");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.validasi_regist(fury);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                pd.hide();
                try {

                    username.setText(mail);
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Terjadi kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(LoginActivity.this, "Mohon Konfirmasi kembali", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("Sedang Mencoba Login");
        pd.show();
        pd.setCancelable(false);
        FirebaseApp.initializeApp(LoginActivity.this);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.d("Zyarga","Fetching FCM Failed",task.getException());
                    return;
                }


                // Get new FCM registration token
                token = task.getResult();
//                Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                // Log and toast
                Log.e("TAGSOO",token);
            }
        });
        dbHelper = new DB_Helper(LoginActivity.this);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.login(username.getText().toString(),password.getText().toString(),token);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                pd.hide();
                try {
                    if (response.body().getKode().equals(200)){
                        dbHelper.SaveUser(response.body().getToken_user(),response.body().getNotif_id());
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, "Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(LoginActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}