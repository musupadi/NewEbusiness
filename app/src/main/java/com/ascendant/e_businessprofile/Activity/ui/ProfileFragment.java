package com.ascendant.e_businessprofile.Activity.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.WebActivity;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    RelativeLayout Logout,Privacy,ChangePassword,ContactUs;
    Dialog myDialog;
    EditText OldPassword,NewPassword,ConfirmPassword;
    Button Confirm,Close;
    DB_Helper dbHelper;
    String Token;
    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
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
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_change_password);
        OldPassword = myDialog.findViewById(R.id.etOldPassword);
        NewPassword = myDialog.findViewById(R.id.etNewPassword);
        ConfirmPassword = myDialog.findViewById(R.id.etConfirmPassword);
        Confirm = myDialog.findViewById(R.id.btnKonfirmasi);
        Close = myDialog.findViewById(R.id.btnClose);
        Logout = view.findViewById(R.id.relativeLogout);
        Privacy = view.findViewById(R.id.relativePrivacy);
        ChangePassword = view.findViewById(R.id.relativeChangePassword);
        ContactUs = view.findViewById(R.id.relativeContactUs);
        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ebuss-raw.mandiri-ebuss.com/uploads/policy/m/syarat-dan-ketentuan-penggunaan.pdf"));
                startActivity(browserIntent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Set a title for alert dialog
                builder.setTitle("Pemberitahuan");

                // Ask the final question
                builder.setMessage("Apakah Anda Yakin Ingin Logout ? ");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        Logout();
                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked
                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checker();
            }
        });
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.hide();
            }
        });
        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
            }
        });
    }
    private void Checker(){
        if (OldPassword.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Old Password Must be filled", Toast.LENGTH_SHORT).show();
        }else if (NewPassword.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "New Password Must be filled", Toast.LENGTH_SHORT).show();
        }else if (ConfirmPassword.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Confirm Password Must be filled", Toast.LENGTH_SHORT).show();
        }else if(!NewPassword.getText().toString().equals(ConfirmPassword.getText().toString())){
            Toast.makeText(getActivity(), "New Password and Confirm Password Must Same", Toast.LENGTH_SHORT).show();
        }else{
            ChangePassword();
        }
    }
    private void ChangePassword(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.ChangePassword(Token,OldPassword.getText().toString(),NewPassword.getText().toString(),ConfirmPassword.getText().toString());
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getMessage().equals("Profil berhasil diupdate")){
                        myDialog.hide();

                    }
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    //Toast.makeText(getActivity(), "Kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logout(){
        DB_Helper db_helper = new DB_Helper(getActivity());
        db_helper.Logout();
        Toast.makeText(getActivity(), "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finishAffinity();
    }
}