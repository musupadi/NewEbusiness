package com.ascendant.e_businessprofile.Activity.ui.Mining.MandiriUpdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterFile;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseDataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.Mining.MiningOutlookModel;
import com.ascendant.e_businessprofile.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

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

    String MODULE;
    RecyclerView rv;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    CardView cardYoutube;
    LinearLayout Available,Navigator;
    RecyclerView rv2,recyclerView;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pList = new ArrayList<>();
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
        web = findViewById(R.id.web);
        rv = findViewById(R.id.recycler);
        cardYoutube = findViewById(R.id.cardYoutube);
        ID = intent.getExtras().getString("ID");
        //Cut Here
        rv2 = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pList.addAll(MiningOutlookModel.getListData());
        rv2.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pList);
        rv2.setAdapter(adapters);

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
                    if (more){
                        more = false;
                        ivMore.setImageResource(R.drawable.close_concerate);
                        Available.setVisibility(View.GONE);
                        Navigator.setVisibility(View.VISIBLE);
                    }else{
                        more = true;
                        ivMore.setImageResource(R.drawable.more_vertical_concerate);
                        Available.setVisibility(View.VISIBLE);
                        Navigator.setVisibility(View.GONE);
                    }
                }catch (Exception e){

                }

            }
        });

        //Cut Here

        GetData();
    }

    private void GetData(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseDataModel> data =api.DetailMandiriUpdate(Token,ID);
        data.enqueue(new Callback<ResponseDataModel>() {
            @Override
            public void onResponse(Call<ResponseDataModel> call, Response<ResponseDataModel> response) {
                Judul.setText(response.body().getData().getDetail().getJudul_mandiri_update());
                web.loadData(response.body().getData().getDetail().getIsi_mandiri_update(),"text/html","UTF-8");
                Tanggal.setText(response.body().getData().getDetail().getCreated_at());
                if (!response.body().getData().getDetail().getLink_youtube().equals("")){
                    cardYoutube.setVisibility(View.VISIBLE);
                    youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            String videoId = ascendant.GetIDYoutube(response.body().getData().getDetail().getLink_youtube());
                            youTubePlayer.loadVideo(videoId, 0);
                        }
                    });
                }else{
                    cardYoutube.setVisibility(View.GONE);
                }
                mManager = new LinearLayoutManager(DetailMandiriUpdate.this, LinearLayoutManager.VERTICAL,false);
                rv.setLayoutManager(mManager);
                mItems=response.body().getData().getFiles();
                mAdapter = new AdapterFile(DetailMandiriUpdate.this,mItems,response.body().getData().getDetail().getCreated_at());
                rv.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseDataModel> call, Throwable t) {

            }
        });
    }
}