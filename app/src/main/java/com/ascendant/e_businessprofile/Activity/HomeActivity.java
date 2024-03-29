package com.ascendant.e_businessprofile.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.ChatFragment;
import com.ascendant.e_businessprofile.Activity.ui.ForumFragment;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance.ComplianceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.HealthcareFragment;
import com.ascendant.e_businessprofile.Activity.ui.HomeFragment;
import com.ascendant.e_businessprofile.Activity.ui.ProfileFragment;
import com.ascendant.e_businessprofile.Activity.ui.SearchFragment;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.MyFirebaseMessagingService;
import com.ascendant.e_businessprofile.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.onesignal.OneSignal;

import java.io.File;
import java.io.IOException;

import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends AppCompatActivity {
    LinearLayout LHome, LForum, LProfile,LSearch;
    ImageView Home, Forum, Profile , Search;
    TextView tvSearch;
    Fragment fragment;
    private String[] galleryPermissions =
            {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_COARSE_LOCATION};
    Ascendant ascendant= new Ascendant();
    DB_Helper dbHelper;
    String Token,NotifID;
    String FORUM;
    String PROFIL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try {
            if(EasyPermissions.hasPermissions(HomeActivity.this, galleryPermissions)) {

            }else{
                EasyPermissions.requestPermissions(HomeActivity.this, "Access for storage",
                        101, galleryPermissions);
            }
            Declaration();
            OnClick();
            dbHelper = new DB_Helper(HomeActivity.this);
            Cursor cursor = dbHelper.checkUser();
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    Token = cursor.getString(0);
                    NotifID = cursor.getString(1);
                }
            }

            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
            // OneSignal Initialization
            OneSignal.initWithContext(this);
            OneSignal.setAppId(ascendant.OneSignalAppId());
            try {
                Intent intent = getIntent();
                FORUM = intent.getExtras().getString("FORUM");
                if (FORUM.equals("FORUM")){
                    Forum();
                }else{
                    Profile();
                }

            }catch (Exception e){
                Home();
            }
        }catch (Exception ex){
            Toast.makeText(HomeActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    private void OnClick() {
        LHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home();
            }
        });
        LForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forum();
            }
        });
        LProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile();
            }
        });
        LSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search();
            }
        });
    }

    private void Declaration() {
        LHome = findViewById(R.id.linearHome);
        LForum = findViewById(R.id.linearForum);
        LProfile = findViewById(R.id.linearProfile);
        Home = findViewById(R.id.ivHome);
        Forum = findViewById(R.id.ivForum);
        Profile = findViewById(R.id.ivProfile);
        LSearch = findViewById(R.id.linearSearch);
        Search = findViewById(R.id.ivSearch);
        tvSearch = findViewById(R.id.tvSearch);
    }
    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("Zyargas Error", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }
    private void Default() {
        Home.setImageResource(R.drawable.home_inactive);
        Forum.setImageResource(R.drawable.forum_inactive);
        Profile.setImageResource(R.drawable.profile_inactive);
        Search.setImageResource(R.drawable.search_innactive);
        tvSearch.setTextColor(Color.rgb(135,135,135));
    }
//    void getDeviceToken() {
//        new AsyncTask() {
//            @Override
//            protected Object doInBackground(Object[] objects) {
//                return null;
//            }
//
//            @Override
//            protected String doInBackground(Void... params) throws IOException {
//
//                GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
//
//                String deviceToken = gcm.register("632113338862");
//                Log.i("GCM", "Device token : " + deviceToken);
//
//                // update user profile document
//
//                return null;
//            }
//        }.execute(null, null, null);
//    }
    private void Home() {
        Default();
        Home.setImageResource(R.drawable.home_active);
        fragment = new HomeFragment();
        ChangeFragment(fragment);
    }
    private void Forum() {
        Default();
        Forum.setImageResource(R.drawable.forum_active);
        fragment = new ForumFragment();
        ChangeFragment(fragment);
    }
    private void Search() {
        Default();
        Search.setImageResource(R.drawable.search);
        tvSearch.setTextColor(Color.rgb(24,60,110));
        fragment = new SearchFragment();
        ChangeFragment(fragment);
    }
    private void Chat() {
        Default();
        fragment = new ChatFragment();
        ChangeFragment(fragment);
    }
    private void Profile() {
        Default();
        Profile.setImageResource(R.drawable.profile_active);
        fragment = new ProfileFragment();
        ChangeFragment(fragment);
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.Container,fragment);
            ft.commit();
        }
    }



    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

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
                DB_Helper db_helper = new DB_Helper(HomeActivity.this);
                db_helper.Logout();
                Toast.makeText(HomeActivity.this, "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
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
}