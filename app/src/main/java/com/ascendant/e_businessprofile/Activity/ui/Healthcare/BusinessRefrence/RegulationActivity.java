package com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.ui.ChatFragment;
import com.ascendant.e_businessprofile.Activity.ui.ForumFragment;
import com.ascendant.e_businessprofile.Activity.ui.HomeFragment;
import com.ascendant.e_businessprofile.Activity.ui.NavigatorFragment;
import com.ascendant.e_businessprofile.R;

public class RegulationActivity extends AppCompatActivity {
    LinearLayout LHome, LForum, LProfile;
    ImageView Home, Forum, Profile;
    Fragment fragment;
    Dialog dialog;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulation);
        Declaration();
        Home.setImageResource(R.drawable.home_active);
        fragment = new RegulationsFragment();
        ChangeFragment(fragment);
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
                    bundle.putString("Navigator", "Regulations");
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        fragment = new NavigatorFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        fragment = new RegulationsFragment();
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
        LProfile = findViewById(R.id.linearProfile);

        Home = findViewById(R.id.ivHome);
        Forum = findViewById(R.id.ivForum);
        Profile = findViewById(R.id.ivProfile);

        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
    }

    private void Default() {
        Home.setImageResource(R.drawable.home_inactive);
        Forum.setImageResource(R.drawable.forum_inactive);
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