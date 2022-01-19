package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.ui.ChatFragment;
import com.ascendant.e_businessprofile.Activity.ui.Contractor.ContractorFragment;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.FMCGFragment;
import com.ascendant.e_businessprofile.Activity.ui.Farming.FarmingFragment;
import com.ascendant.e_businessprofile.Activity.ui.ForumFragment;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.HealthcareFragment;
import com.ascendant.e_businessprofile.Activity.ui.HomeFragment;
import com.ascendant.e_businessprofile.Activity.ui.Mining.MiningFragment;
import com.ascendant.e_businessprofile.Activity.ui.OilAndGas.OilAndGasFragment;
import com.ascendant.e_businessprofile.Activity.ui.ProfileFragment;
import com.ascendant.e_businessprofile.R;

public class ModuleActivity extends AppCompatActivity {
    LinearLayout LHome, LForum,LProfile;
    ImageView Home, Forum, Profile;
    Fragment fragment;
    String MODULE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        Declaration();
        Home();
        OnClick();
        try {
            Intent intent = getIntent();
            MODULE = intent.getExtras().getString("MODULE");
            if (MODULE.equals("HEALTHCARE")){
                fragment = new HealthcareFragment();
                ChangeFragment(fragment);
            }else if (MODULE.equals("FMCG")){
                fragment = new FMCGFragment();
                ChangeFragment(fragment);
            }else if (MODULE.equals("Mining")){
                fragment = new MiningFragment();
                ChangeFragment(fragment);
            }else if (MODULE.equals("Contractor")){
                fragment = new ContractorFragment();
                ChangeFragment(fragment);
            }else if (MODULE.equals("Oil & Gas")){
                fragment = new OilAndGasFragment();
                ChangeFragment(fragment);
            }else if (MODULE.equals("Farming")){
                fragment = new FarmingFragment();
                ChangeFragment(fragment);
            }
        }catch (Exception e){

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
    }

    private void Declaration() {
        LHome = findViewById(R.id.linearHome);
        LForum = findViewById(R.id.linearForum);
        LProfile = findViewById(R.id.linearProfile);

        Home = findViewById(R.id.ivHome);
        Forum = findViewById(R.id.ivForum);
        Profile = findViewById(R.id.ivProfile);
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
        Intent intent = new Intent(ModuleActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}