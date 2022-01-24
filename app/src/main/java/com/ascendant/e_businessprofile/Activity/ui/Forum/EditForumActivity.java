package com.ascendant.e_businessprofile.Activity.ui.Forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditForumActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pListt = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;
    TextView Category;

    String ID,JUDUL,ISI,CATEGORY;
    EditText etTitle,etForum;
    Button Post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_forum);
        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        ISI = intent.getExtras().getString("ISI");
        JUDUL = intent.getExtras().getString("JUDUL");
        CATEGORY = intent.getExtras().getString("CATEGORY");
        Toast.makeText(this, ID, Toast.LENGTH_SHORT).show();
        rv = findViewById(R.id.recyclerNav);
        Available = findViewById(R.id.linearAvailable);
        Navigator = findViewById(R.id.linearNavigator);
        ivMore = findViewById(R.id.ivMore);
        More = findViewById(R.id.linearMore);
        Back = findViewById(R.id.linearBack);
        Available.setVisibility(View.VISIBLE);
        pListt.addAll(CreditWorthinessModel.getListData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterNavigator adapters = new AdapterNavigator(this,pListt);
        rv.setAdapter(adapters);
        dbHelper = new DB_Helper(this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
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


        etTitle = findViewById(R.id.etJudul);
        etForum = findViewById(R.id.etForum);
        Category = findViewById(R.id.tvCategory);
        Post = findViewById(R.id.btnPost);
        etTitle.setText(JUDUL);
        etForum.setText(ISI);
        Category.setText(CATEGORY);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checker();
            }
        });
    }
    private void Checker(){
        if (etTitle.getText().toString().isEmpty() || etTitle.getText().toString().equals("")){
            Toast.makeText(EditForumActivity.this, "Title Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }else if (etForum.getText().toString().isEmpty() || etForum.getText().toString().equals("")){
            Toast.makeText(EditForumActivity.this, "Forum Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }else{
            Logic();
        }
    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(EditForumActivity.this);
        pd.setMessage("Sedang Mengisi Post");
        pd.show();
        pd.setCancelable(false);

        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.EditForum(
                RequestBody.create(MediaType.parse("text/plain"),Token),
                RequestBody.create(MediaType.parse("text/plain"),ID),
                RequestBody.create(MediaType.parse("text/plain"),etTitle.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etForum.getText().toString()));
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Toast.makeText(EditForumActivity.this, "Komen berhasil Terhapus", Toast.LENGTH_SHORT).show();
                    Intent goInput = new Intent(EditForumActivity.this, DetailForumActivity.class);
                    goInput.putExtra("ID",ID);
                    goInput.putExtra("CATEGORY",CATEGORY);
                    goInput.putExtra("JUDUL",JUDUL);
                    goInput.putExtra("REPLY_NAME","");
                    goInput.putExtra("REPLY","");
                    goInput.putExtra("EDIT","NO");
                    goInput.putExtra("ISI_KOMEN","");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(EditForumActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(EditForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}