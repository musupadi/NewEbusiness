package com.ascendant.e_businessprofile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.NavigatorFragment;
import com.ascendant.e_businessprofile.Activity.ui.NewsFragment;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    Fragment fragment;
    Dialog dialog;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    String JUDUL,KATEGORI,TGL_UPLOAD,COVER,ISI_BERITA;
    Bundle bundle;
    DB_Helper dbHelper;
    String Token,NotifID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        dbHelper = new DB_Helper(this);
        Declaration();
        dbHelper = new DB_Helper(NewsActivity.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
                NotifID = cursor.getString(1);
            }
        }
        Uri datas = this.getIntent().getData();
        if (datas != null && datas.isHierarchical()) {
            String uri = this.getIntent().getDataString();
            Log.i("MyApp", "Deep link clicked " + uri);
            List<String> params = datas.getPathSegments();
            String IDS = params.get(0); // "status"
            GetDetailBerita(IDS);
//            String mail = params.get(1);
//            Validasi(mail,fury);
        }else{
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
    }
    private void GetDetailBerita(String IDS){
        final ProgressDialog pd = new ProgressDialog(NewsActivity.this);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.BeritaDetail(Token,IDS);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                bundle = new Bundle();
                bundle.putString("JUDUL", response.body().getData().getJudul_berita());
                bundle.putString("KATEGORI", response.body().getData().getKategori_berita());
                bundle.putString("TGL_UPLOAD", response.body().getData().getCreated_at());
                bundle.putString("COVER", response.body().getData().getCover_berita());
                bundle.putString("ISI_BERITA", response.body().getData().getIsi_berita());
                fragment = new NewsFragment();
                fragment.setArguments(bundle);
                ChangeFragment(fragment);
                OnClickV2();
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void OnClickV2(){
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