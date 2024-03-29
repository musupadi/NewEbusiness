package com.ascendant.e_businessprofile.Activity.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.DetailBeritaActivity;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.UnitKerjaActivity;
import com.ascendant.e_businessprofile.Activity.ui.Forum.PostForumActivity;
import com.ascendant.e_businessprofile.Adapter.AdapterForum;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForumFragment extends Fragment {
    ImageView Search;
    EditText etSearch;
    Spinner Category;
    DB_Helper dbHelper;
    String Token;
    String token;
    Button Post;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    RecyclerView rv;
    ImageView Left,Right;
    TextView Paging;
    String Page = "1";
    String Jduul = "";
    LinearLayout linearMT,linearVisible;
    public ForumFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Left = view.findViewById(R.id.ivLeft);
        Right = view.findViewById(R.id.ivRight);
        Paging = view.findViewById(R.id.tvPaging);
        Search = view.findViewById(R.id.ivSearch);
        etSearch = view.findViewById(R.id.etSearch);
        Category = view.findViewById(R.id.spinnerCategory);
        rv = view.findViewById(R.id.recycler);
        Post = view.findViewById(R.id.btnPost);
        linearMT = view.findViewById(R.id.linearMT);
        linearVisible = view.findViewById(R.id.linearVisible);
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        GetData();
        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Logic();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostForumActivity.class);
                startActivity(intent);
            }
        });
        Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page=String.valueOf(Integer.parseInt(Page)+1);
                Logic();
                Paging.setText(Page);
            }
        });
        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page=String.valueOf(Integer.parseInt(Page)-1);
                Logic();
                Paging.setText(Page);
            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search();
            }
        });
    }
    private void GetData(){
        FirebaseApp.initializeApp(getActivity());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.d("Zyarga","Fetching FCM Failed",task.getException());
                    return;
                }
                // Get new FCM registration token
                token = task.getResult().toString();
                Log.e("TAGSOO",token);
                ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                final Call<ResponseObject> data =api.Profil(Token,token);
                data.enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        try {
                            if (response.body().getKode().equals(200)){
                                if (response.body().getData().getNama_user().equals("Agares")){
                                    linearVisible.setVisibility(View.GONE);
                                    linearMT.setVisibility(View.VISIBLE);
                                }else{
                                    linearVisible.setVisibility(View.VISIBLE);
                                    linearMT.setVisibility(View.GONE);
                                }
                            }else{
                                response.body().getMessage();
                            }
                        }catch (Exception e){
                            Log.d("ZYARGA : ",e.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "Waktu Sesi habis harap coba Login Lagi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void Search(){
        Jduul=etSearch.getText().toString();
        Logic();
    }
    private void Logic(){
        mManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mManager);
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.Daftar_Postingan(Token,Checker(Category.getSelectedItem().toString()),Page,Jduul);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        mItems=response.body().getData();
                        mAdapter = new AdapterForum(getActivity(),mItems);
                        rv.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        if (Integer.parseInt(Page)>Integer.parseInt(response.body().getMax_page())-1){
                            Right.setVisibility(View.INVISIBLE);
                        }else{
                            Right.setVisibility(View.VISIBLE);
                        }
                        if (Integer.parseInt(Page)==1){
                            Left.setVisibility(View.INVISIBLE);
                        }else{
                            Left.setVisibility(View.VISIBLE);
                        }
                    }else{
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("DONTOL : ",e.toString());
//                    Toast.makeText(getActivity(), "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String Checker(String text){
        if (text.equals("All Category")){
            text = "";
        }else{
            text = text.toLowerCase(Locale.ROOT);
        }

        return text;
    }
}