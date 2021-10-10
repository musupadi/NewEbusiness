package com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Ecosystem.EcosystemFragment;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.NavigatorFragment;
import com.ascendant.e_businessprofile.R;

public class FMCGEcosystemActivity extends AppCompatActivity {
    Fragment fragment;
    Dialog dialog;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcgecosystem);
        Declaration();
        fragment = new FMCGEcosystemFragment();
        ChangeFragment(fragment);
        OnClick();
    }
    private void OnClick() {
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
                    bundle.putString("Navigator", "Ecosystem");
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        fragment = new NavigatorFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        fragment = new EcosystemFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }
                }catch (Exception e){

                }

            }
        });
    }

    private void Declaration() {

        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
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