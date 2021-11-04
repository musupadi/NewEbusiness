package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ascendant.e_businessprofile.Activity.ui.NavigatorFragment;
import com.ascendant.e_businessprofile.Activity.ui.NewsFragment;
import com.ascendant.e_businessprofile.R;

public class NewsActivity extends AppCompatActivity {
    Fragment fragment;
    Dialog dialog;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    String JUDUL,KATEGORI,TGL_UPLOAD,COVER,ISI_BERITA;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Declaration();
        Intent data = getIntent();
        JUDUL = data.getStringExtra("JUDUL");
        KATEGORI = data.getStringExtra("KATEGORI");
        TGL_UPLOAD = data.getStringExtra("TGL_UPLOAD");
        COVER = data.getStringExtra("COVER");
        ISI_BERITA = data.getStringExtra("ISI_BERITA");

        bundle = new Bundle();
        bundle.putString("JUDUL", JUDUL);
        bundle.putString("KATEGORI", KATEGORI);
        bundle.putString("TGL_UPLOAD", TGL_UPLOAD);
        bundle.putString("COVER", COVER);
        bundle.putString("ISI_BERITA", ISI_BERITA);
        fragment = new NewsFragment();
        fragment.setArguments(bundle);
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
                    bundle.putString("Navigator", "News");
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        fragment = new NavigatorFragment();
                        fragment.setArguments(bundle);
                        ChangeFragment(fragment);
                    }else{
                        more = true;
                        bundle = new Bundle();
                        bundle.putString("JUDUL", JUDUL);
                        bundle.putString("KATEGORI", KATEGORI);
                        bundle.putString("TGL_UPLOAD", TGL_UPLOAD);
                        bundle.putString("COVER", COVER);
                        bundle.putString("ISI_BERITA", ISI_BERITA);
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        fragment = new NewsFragment();
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