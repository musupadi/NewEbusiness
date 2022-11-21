package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.ui.ChatFragment;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.Compliance.FMCGComplianceActivity;
import com.ascendant.e_businessprofile.Activity.ui.ForumFragment;
import com.ascendant.e_businessprofile.Activity.ui.HomeFragment;
import com.ascendant.e_businessprofile.Activity.ui.NavigatorFragment;
import com.ascendant.e_businessprofile.R;

public class ComplianceActivity extends AppCompatActivity {
    LinearLayout LHome, LForum, LChat, LProfile;
    ImageView Home, Forum, Chat, Profile;
    Fragment fragment;
    Dialog dialog;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliance);
        try {
            Uri datas = this.getIntent().getData();
            Declaration();
            Home.setImageResource(R.drawable.home_active);
            fragment = new ComplianceFragment();
            ChangeFragment(fragment);
            OnClick();
        }catch (Exception e){
            Toast.makeText(ComplianceActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ComplianceActivity.this, LoginActivity.class);
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
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("Navigator", "Compliance");
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        fragment = new NavigatorFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        fragment = new ComplianceFragment();
                        ChangeFragment(fragment);
                    }
                }catch (Exception e){

                }

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

        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
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
        super.onBackPressed();
    }
}