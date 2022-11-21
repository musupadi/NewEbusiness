package com.ascendant.e_businessprofile.Activity.Tabbed;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.RegisterActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmailLoginFragment extends Fragment {
    TextView forgotPassword;
    LinearLayout login;
    EditText username,password;
    DB_Helper dbHelper;
    String token;
    Dialog myDialog;
    Button Konfrimasi,Tutup,confirm;
    EditText email;



    public EmailLoginFragment() {
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
        return inflater.inflate(R.layout.fragment_email_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_forgot_password);
        confirm = myDialog.findViewById(R.id.btnKonfirmasi);
        Tutup = myDialog.findViewById(R.id.btnClose);
        email = myDialog.findViewById(R.id.etEmail);
        Uri data = getActivity().getIntent().getData();
//        register = view.findViewById(R.id.tvRegister);
        forgotPassword = view.findViewById(R.id.tvForgotPassword);
        login = view.findViewById(R.id.linearLogin);
        username = view.findViewById(R.id.etUsername);

        password = view.findViewById(R.id.etPassword);
        if (data != null && data.isHierarchical()) {
            String uri = getActivity().getIntent().getDataString();
            Log.i("MyApp", "Deep link clicked " + uri);
//            List<String> params = data.getPathSegments();
//            String fury = params.get(0); // "status"
//            String mail = params.get(1);
//            Validasi(mail,fury);
            Toast.makeText(getActivity(), "Selamat Anda Berhak Login", Toast.LENGTH_SHORT).show();
        }


        dbHelper = new DB_Helper(getActivity());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logic();
            }
        });
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPassword();
            }
        });
        Tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.hide();
            }
        });
    }
    private void ForgotPassword(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Mohon Menunggu");
        pd.show();
        pd.setCancelable(false);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.ForgetPassword(email.getText().toString());
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                pd.hide();
                try {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    myDialog.hide();

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Mohon Konfirmasi kembali", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Validasi(String fury,String mail){
        final ProgressDialog pd = new ProgressDialog(getActivity());
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
                    Toast.makeText(getActivity(), "Terjadi kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Mohon Konfirmasi kembali", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mencoba Login");
        pd.show();
        pd.setCancelable(false);
        FirebaseApp.initializeApp(getActivity());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.d("Zyarga","Fetching FCM Failed",task.getException());
                    return;
                }
                // Get new FCM registration token
                token = task.getResult();
                Log.e("TAGSOO",token);
                dbHelper = new DB_Helper(getActivity());
                ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                final Call<ResponseArrayObject> data =api.login(username.getText().toString(),password.getText().toString(),token);
                data.enqueue(new Callback<ResponseArrayObject>() {
                    @Override
                    public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                        pd.hide();
                        try {
                            if (response.body().getKode().equals(200)){
                                dbHelper.SaveUser(response.body().getToken_user(),response.body().getNotif_id(),response.body().getLevel());
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Toast.makeText(getActivity(), "Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                        pd.hide();
                        Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}