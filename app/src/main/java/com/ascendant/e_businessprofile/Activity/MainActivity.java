package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.ResponseQuiz;
import com.ascendant.e_businessprofile.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    DB_Helper db_helper;
    Ascendant ascendant = new Ascendant();
    Dialog dialogPesan;
    TextView Pesan,View;
    Button KonfirmasiPesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogPesan = new Dialog(this);
        dialogPesan.setContentView(R.layout.dialog_message);
        Pesan = dialogPesan.findViewById(R.id.tvPesan);
        KonfirmasiPesan = dialogPesan.findViewById(R.id.btnKonfirmasi);
        CheckVersion();
    }
    private void CheckVersion(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.Version();
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    Double num = Double.parseDouble(response.body().getData().getVer());
                    if (4.1<num){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Harap Update Aplikasi Anda")
                                .setCancelable(false)
                                .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String urltest = "https://play.google.com/store/apps/details?id=com.ascendant.e_businessprofile";
                                        try {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urltest)));
                                        } catch (Exception e) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urltest)));
                                        }
                                    }
                                })
                                //Set your icon here
                                .setTitle("Perhatian !!!")
                                .setIcon(R.drawable.ic_action_home);
                        AlertDialog alert = builder.create();
                        alert.show();
                    }else{
                        final Handler handler = new Handler();
                        db_helper = new DB_Helper(MainActivity.this);
                        Cursor cursor = db_helper.checkUser();
                        if (cursor.getCount()>0){
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 0000);
                        }else{
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 3000); //3000 L = 3 detik
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Terjadi Kesalahan : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                dialogPesan.show();
                Pesan.setText("Mohon Periksa Jaringan Anda..\nTekan Konfirmasi Untuk coba lagi");
                KonfirmasiPesan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        dialogPesan.dismiss();
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });
//                Toast.makeText(MainActivity.this, "Mohon Periksa Jaringan Anda", Toast.LENGTH_SHORT).show();
//                final Handler handlers = new Handler();
//                handlers.postDelayed(new Runnable() {
//                    public void run() {
//
//                    }
//                }, 3000);
            }
        });
    }
    private void test(){
        final Handler handler = new Handler();
        db_helper = new DB_Helper(MainActivity.this);
        Cursor cursor = db_helper.checkUser();
        if (cursor.getCount()>0){
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 0000);
        }else{
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000); //3000 L = 3 detik
        }
    }
}