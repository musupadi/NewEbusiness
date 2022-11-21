package com.ascendant.e_businessprofile.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.Tabbed.EmailLoginFragment;
import com.ascendant.e_businessprofile.Activity.Tabbed.NIPLoginFragment;
import com.ascendant.e_businessprofile.Adapter.Pager.TabPagerAdapter;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.MyFirebaseMessagingService;
import com.ascendant.e_businessprofile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TabLayout Table;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Table=findViewById(R.id.tableLayout);
        viewPager = findViewById(R.id.viewpager);
//        loginButton = findViewById(R.id.loginButton);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new NIPLoginFragment(),"NIP");
        adapter.AddFragment(new EmailLoginFragment(),"Email");
        viewPager.setAdapter(adapter);
        Table.setupWithViewPager(viewPager);
    }
}