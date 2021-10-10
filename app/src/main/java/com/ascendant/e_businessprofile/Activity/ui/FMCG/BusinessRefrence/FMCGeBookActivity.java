package com.ascendant.e_businessprofile.Activity.ui.FMCG.BusinessRefrence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.ui.Healthcare.BusinessRefrence.eBookFragment;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.NavigatorFragment;
import com.ascendant.e_businessprofile.R;

import pub.devrel.easypermissions.EasyPermissions;

public class FMCGeBookActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    Fragment fragment;
    String MODULE;
    TextView eBook;
    private String[] galleryPermissions =
            {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmcge_book);
        if(EasyPermissions.hasPermissions(this, galleryPermissions)) {

        }else{
            EasyPermissions.requestPermissions(this, "Access for storage",
                    101, galleryPermissions);
        }
        eBook = findViewById(R.id.tvEbook);
        Back = findViewById(R.id.linearBack);
        More = findViewById(R.id.linearMore);
        ivMore = findViewById(R.id.ivMore);
        Intent intent = getIntent();
        MODULE = intent.getExtras().getString("MODULE");
        Bundle bundle = new Bundle();
        bundle.putString("MODULE", MODULE);
        fragment = new FMCGeBookFragment();
        fragment.setArguments(bundle);
        ChangeFragment(fragment);
        String Mod=MODULE;
        if (MODULE.equals("ebook")){
            Mod = "e-Book";
        }else if (MODULE.equals("newsletter")){
            Mod = "Newsletter";
        }else if (MODULE.equals("business_review")){
            Mod = "Business Review";
        }else if (MODULE.equals("umum")){
            Mod = "Acts";
        }else if (MODULE.equals("fmcg")){
            Mod = "Government Regulations";
        }else if (MODULE.equals("non fnb rokok")){
            Mod = "Presidential Decree";
        }else if (MODULE.equals("perpres")){
            Mod = "Health Minister Regulations";
        }
        eBook.setText(Mod);
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
                    if (MODULE.equals("ebook")){
                        bundle.putString("Navigator", "e-Book");
                    }else if (MODULE.equals("newsletter")){
                        bundle.putString("Navigator", "Newsletter");
                    }else if (MODULE.equals("business_review")){
                        bundle.putString("Navigator", "Business Review");
                    }else if (MODULE.equals("regulations_uu")){
                        bundle.putString("Navigator", "Acts");
                    }else if (MODULE.equals("regulations_pp")){
                        bundle.putString("Navigator", "Government Regulations");
                    }else if (MODULE.equals("regulations_pmk")){
                        bundle.putString("Navigator", "Health Minister Decree/Regulations");
                    }else if (MODULE.equals("regulations_perpres")){
                        bundle.putString("Navigator", "Presidential Decree");
                    }else if (MODULE.equals("uu")){
                        bundle.putString("Navigator", "Acts");
                    }else if (MODULE.equals("pp")){
                        bundle.putString("Navigator", "Government Regulations");
                    }else if (MODULE.equals("pmk")){
                        bundle.putString("Navigator", "Presidential Decree");
                    }else if (MODULE.equals("perpres")){
                        bundle.putString("Navigator", "Health Minister Regulations");
                    }

                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        fragment = new NavigatorFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        bundle.putString("MODULE", MODULE);
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        fragment = new eBookFragment();
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