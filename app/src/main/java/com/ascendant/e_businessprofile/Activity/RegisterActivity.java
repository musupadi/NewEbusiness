package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerDivisi;
import com.ascendant.e_businessprofile.Adapter.Spinner.SpinnerWilayah;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private List<DataModel> mItems = new ArrayList<>();
    Spinner Divisi,Wilayah;
    TextView idDivisi,idWIlayah;
    EditText Name,Email,NIP,NoTelp,Password,Confirmassword;
    LinearLayout Confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Divisi = findViewById(R.id.spinnerDivisi);
        Wilayah = findViewById(R.id.spinnerWilayah);
        idDivisi = findViewById(R.id.tvidDivisi);
        idWIlayah = findViewById(R.id.tvidWilayah);
        Name = findViewById(R.id.etName);
        Email = findViewById(R.id.etUsername);
        NIP = findViewById(R.id.etNIP);
        NoTelp = findViewById(R.id.etPhoneNumber);
        Password = findViewById(R.id.etPassword);
        Confirmassword = findViewById(R.id.etConfirmPassword);
        Confirm = findViewById(R.id.linearConfirm);
        DataDivisi();
        GetWilayah();
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        Wilayah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_wilayah_mandiri());
                    idWIlayah.setText(String.valueOf(clickedItems));
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Divisi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    DataModel clickedItem = (DataModel) adapterView.getItemAtPosition(i);
                    int clickedItems = Integer.parseInt(clickedItem.getId_divisi_mandiri());
                    idDivisi.setText(String.valueOf(clickedItems));
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void GetWilayah(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Wilayah();
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        SpinnerWilayah adapter = new SpinnerWilayah(RegisterActivity.this,mItems);
                        Wilayah.setAdapter(adapter);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String Checker(){
        String OK = "OK";
        if (Name.getText().toString().isEmpty()){
            OK = "NOPE";
            Toast.makeText(RegisterActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if (Email.getText().toString().isEmpty()){
            OK = "NOPE";
            Toast.makeText(RegisterActivity.this, "Email tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (NIP.getText().toString().isEmpty()){
            OK = "NOPE";
            Toast.makeText(RegisterActivity.this, "NIP tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (NoTelp.getText().toString().isEmpty()){
            OK = "NOPE";
            Toast.makeText(RegisterActivity.this, "No Telpon tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Password.getText().toString().isEmpty()){
            OK = "NOPE";
            Toast.makeText(RegisterActivity.this, "Password tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (Confirmassword.getText().toString().isEmpty()){
            OK = "NOPE";
            Toast.makeText(RegisterActivity.this, "Konfirmasi password tidak boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if (!Password.getText().toString().equals(Confirmassword.getText().toString())){
            OK = "NOPE";
            Toast.makeText(RegisterActivity.this, "Password dan Konfirmasi Password Tidak sama", Toast.LENGTH_SHORT).show();
        }
        return OK;
    }
    private void Register(){
        if (Checker().equals("OK")){
            final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
            pd.setMessage("Sedang Mencoba Mendaftar");
            pd.show();
            pd.setCancelable(false);
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            final Call<ResponseArrayObject> data =api.Register(
                    Name.getText().toString(),
                    Email.getText().toString(),
                    NIP.getText().toString(),
                    "+62 "+NoTelp.getText().toString(),
                    idDivisi.getText().toString(),
                    Password.getText().toString(),
                    idWIlayah.getText().toString());
            data.enqueue(new Callback<ResponseArrayObject>() {
                @Override
                public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                    pd.hide();
                    try {
                        if (response.body().getKode().equals(200)){
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Log.d("ZYARGA : ",e.toString());
                        Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                    pd.hide();
                    Toast.makeText(RegisterActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void DataDivisi(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> Data =api.Divisi();
        Data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                if (response.body().getKode().equals(200)){
                    mItems=response.body().getData();
                    SpinnerDivisi adapter = new SpinnerDivisi(RegisterActivity.this,mItems);
                    Divisi.setAdapter(adapter);
                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}