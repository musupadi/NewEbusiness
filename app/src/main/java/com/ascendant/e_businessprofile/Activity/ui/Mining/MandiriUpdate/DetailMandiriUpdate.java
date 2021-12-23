package com.ascendant.e_businessprofile.Activity.ui.Mining.MandiriUpdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseDataModel;
import com.ascendant.e_businessprofile.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMandiriUpdate extends AppCompatActivity {
    String ID;
    String Token;
    DB_Helper dbHelper;

    TextView Judul,Tanggal;
    WebView web;
    YouTubePlayerView youtube;
    Ascendant ascendant = new Ascendant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mandiri_update);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        Intent intent = getIntent();
        Judul = findViewById(R.id.tvJudul);
        Tanggal = findViewById(R.id.tvTanggal);
        youtube = findViewById(R.id.youtube);
        ID = intent.getExtras().getString("ID");
        GetData();
    }

    private void GetData(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseDataModel> data =api.DetailMandiriUpdate(Token,ID);
        data.enqueue(new Callback<ResponseDataModel>() {
            @Override
            public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                Judul.setText(response.body().getData().getDetail().getJudul_mandiri_update());
                Tanggal.setText(response.body().getData().getDetail().getCreated_at());
                youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = ascendant.GetIDYoutube(response.body().getData().getDetail().getLink_youtube());
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseDataModel> call, Throwable t) {

            }
        });
    }
}