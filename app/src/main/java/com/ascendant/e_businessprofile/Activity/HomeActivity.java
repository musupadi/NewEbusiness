package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.ChatFragment;
import com.ascendant.e_businessprofile.Activity.ui.ForumFragment;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.HealthcareFragment;
import com.ascendant.e_businessprofile.Activity.ui.HomeFragment;
import com.ascendant.e_businessprofile.R;

import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends AppCompatActivity {
    LinearLayout LHome, LForum, LChat, LProfile;
    ImageView Home, Forum, Chat, Profile;
    Fragment fragment;
    private String[] galleryPermissions =
            {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(EasyPermissions.hasPermissions(HomeActivity.this, galleryPermissions)) {

        }else{
            EasyPermissions.requestPermissions(HomeActivity.this, "Access for storage",
                    101, galleryPermissions);
        }
        Declaration();
        Home();
        OnClick();
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
        LChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chat();
            }
        });
        LProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile();
            }
        });
    }

    private void Declaration() {
        LHome = findViewById(R.id.linearHome);
        LForum = findViewById(R.id.linearForum);
        LChat = findViewById(R.id.linearChat);
        LProfile = findViewById(R.id.linearProfile);

        Home = findViewById(R.id.ivHome);
        Forum = findViewById(R.id.ivForum);
        Chat = findViewById(R.id.ivChat);
        Profile = findViewById(R.id.ivProfile);
    }

    private void Default() {
        Home.setImageResource(R.drawable.home_inactive);
        Forum.setImageResource(R.drawable.forum_inactive);
        Chat.setImageResource(R.drawable.chat_inactive);
        Profile.setImageResource(R.drawable.profile_inactive);
    }

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
    private void Chat() {
        Default();
        Chat.setImageResource(R.drawable.chat_active);
        fragment = new ChatFragment();
        ChangeFragment(fragment);
    }
    private void Profile() {
        Default();
        Profile.setImageResource(R.drawable.profile_active);
        fragment = new ChatFragment();
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