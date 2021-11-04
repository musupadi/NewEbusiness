package com.ascendant.e_businessprofile.Activity.ui.Healthcare.ListOfProbing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.ui.NavigatorFragment;
import com.ascendant.e_businessprofile.R;

import pub.devrel.easypermissions.EasyPermissions;

public class ListOfProbingActivity extends AppCompatActivity {
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    Fragment fragment;
    TextView eBook;
    private String[] galleryPermissions =
            {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_probing);
        if(EasyPermissions.hasPermissions(this, galleryPermissions)) {

        }else{
            EasyPermissions.requestPermissions(this, "Access for storage",
                    101, galleryPermissions);
        }
        Back = findViewById(R.id.linearBack);
        More = findViewById(R.id.linearMore);
        ivMore = findViewById(R.id.ivMore);
        fragment = new ListOfProbingFragment();
        ChangeFragment(fragment);
        OnClick();
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.container,fragment);
            ft.commit();
        }
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
                    bundle.putString("Navigator", "List of Probing");

                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        fragment = new NavigatorFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        fragment = new ListOfProbingFragment();
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
}