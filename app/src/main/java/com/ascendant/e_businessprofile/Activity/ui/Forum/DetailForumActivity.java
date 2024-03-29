package com.ascendant.e_businessprofile.Activity.ui.Forum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.MainActivity;
import com.ascendant.e_businessprofile.Activity.NewsActivity;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance.DetailComplianceActivity;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterGambarForum;
import com.ascendant.e_businessprofile.Adapter.AdapterKomen;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailForumActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv,recyclerViewGambar,recyclerKomen;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pListt = new ArrayList<>();
    DB_Helper dbHelper;
    String Token,Level;
    String ID,CATEGORY,JUDUL,REPLY_NAME,REPLY,EDIT,KOMEN,SUBKOMEN;
    TextView Header,Nama,Jam;
    private List<DataModel> mItemsGambar = new ArrayList<>();
    private List<DataModel> mItemsKomen = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mManager2;
    Ascendant AscNet = new Ascendant();
    TextView web;
    CardView cardImg;
    ImageView GambarKomen,Upload,Send;
    LinearLayout cardKomen;
    EditText etKomen;
    TextView Reply,ReplyName,cancelReply;
    NestedScrollView scrollView;
    //Dellaroy Logic
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;


    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    String postFoto1= "";
    //ONCLICK
    Boolean Gambar1 = false;

    LinearLayout Delete,Report,Edit;
    String token;
    Dialog myDialog;
    EditText Note;
    Button Konfirmasi,Tutup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forum);
        try {
            myDialog = new Dialog(this);
            myDialog.setContentView(R.layout.dialog_report);
            Note = myDialog.findViewById(R.id.etNote);
            Konfirmasi = myDialog.findViewById(R.id.btnKonfirmasi);
            Tutup = myDialog.findViewById(R.id.btnTutup);
            Delete = findViewById(R.id.linearDelete);
            Report = findViewById(R.id.linearReport);
            Edit = findViewById(R.id.linearEdit);
            rv = findViewById(R.id.recyclerNav);
            Available = findViewById(R.id.linearAvailable);
            Navigator = findViewById(R.id.linearNavigator);
            ivMore = findViewById(R.id.ivMore);
            More = findViewById(R.id.linearMore);
            Back = findViewById(R.id.linearBack);
            Header = findViewById(R.id.tvHeader);
            Nama = findViewById(R.id.tvNama);
            Jam = findViewById(R.id.tvJam);
            web = findViewById(R.id.web);
            cardKomen = findViewById(R.id.cardKomen);
            etKomen = findViewById(R.id.etKomen);
            Reply = findViewById(R.id.ivReply);
            recyclerViewGambar = findViewById(R.id.recyclerGambar);
            recyclerKomen = findViewById(R.id.recyclerComment);
            GambarKomen = findViewById(R.id.ivKomenGambar);
            Upload = findViewById(R.id.ivUpload);
            Send = findViewById(R.id.ivSend);
            ReplyName = findViewById(R.id.tvReplyName);
            cancelReply = findViewById(R.id.tvCancelReply);
            cardImg = findViewById(R.id.cardImg);
            Available.setVisibility(View.VISIBLE);
            scrollView = findViewById(R.id.scrollView);
            pListt.addAll(CreditWorthinessModel.getListData());
            rv.setLayoutManager(new LinearLayoutManager(this));
            AdapterNavigator adapters = new AdapterNavigator(this,pListt);
            rv.setAdapter(adapters);
            dbHelper = new DB_Helper(this);
            Cursor cursor = dbHelper.checkUser();
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    Token = cursor.getString(0);
                    Level = cursor.getString(1);
                }
            }
            Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goInput = new Intent(DetailForumActivity.this, HomeActivity.class);
                    goInput.putExtra("FORUM","FORUM");
                    startActivities(new Intent[]{goInput});
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
            Intent intent = getIntent();
            Uri datas = this.getIntent().getData();
            if (datas != null && datas.isHierarchical()) {
                String uri = this.getIntent().getDataString();
                Log.i("MyApp", "Deep link clicked " + uri);
                List<String> params = datas.getPathSegments();
                String IDV2 = params.get(0); // "status"
                GetDetailPost(IDV2);
//            String mail = params.get(1);
//            Validasi(mail,fury);
            }else{
                ID = intent.getExtras().getString("ID");
                CATEGORY = intent.getExtras().getString("CATEGORY");
                JUDUL = intent.getExtras().getString("JUDUL");
                REPLY_NAME = intent.getExtras().getString("REPLY_NAME");
                REPLY = intent.getExtras().getString("REPLY");
                EDIT = intent.getExtras().getString("EDIT");
                KOMEN = intent.getExtras().getString("ISI_KOMEN");
                SUBKOMEN = intent.getExtras().getString("SUB_KOMEN");
                etKomen.setText(KOMEN);
                Header.setText(JUDUL);
                GetData();
            }




            if (REPLY_NAME==null || REPLY_NAME.equals("")){
                cardKomen.setVisibility(View.GONE);
                ReplyName.setVisibility(View.GONE);
                cancelReply.setVisibility(View.GONE);
            }else{
                scrollView.fullScroll(View.FOCUS_DOWN);
                cardKomen.setVisibility(View.VISIBLE);
                etKomen.requestFocus();
                if (EDIT.equals("NO")){
                    ReplyName.setText("Replying To "+REPLY_NAME);
                }else{
                    ReplyName.setText("Editing Comment "+REPLY_NAME);
                }

            }
            cancelReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ReplyName.setVisibility(View.GONE);
                    cancelReply.setVisibility(View.GONE);
                    REPLY_NAME = "";
                    REPLY = "";
                }
            });
            Reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scrollView.fullScroll(View.FOCUS_DOWN);
                    cardKomen.setVisibility(View.VISIBLE);
                    etKomen.requestFocus();
                }
            });
            Upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gambar1 = true;
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                    GambarKomen.setVisibility(View.VISIBLE);
                }
            });
            Send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Level.equals("trial")){
                        Toast.makeText(DetailForumActivity.this, "Cannot Comment", Toast.LENGTH_SHORT).show();
                    }else{
                        if (EDIT.equals("NO")){
                            if (REPLY.equals("")){
                                Komen();
                            }else{
                                SubKomen();
                            }
                        }else{
                            String IDS = intent.getExtras().getString("IDS");
                            if (SUBKOMEN.equals("NO")){
//                        Toast.makeText(DetailForumActivity.this, etKomen.getText().toString(), Toast.LENGTH_SHORT).show();
                                EditKomen(IDS);
                            }else{
                                EditSubKomen(IDS);
                            }
                        }
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(DetailForumActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DetailForumActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void GetDetailPost(String IDV2){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseObject> data =api.DetailPosting(Token,IDV2);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    ID = IDV2;
                    CATEGORY = response.body().getData().getKategori_post();
                    JUDUL = response.body().getData().getJudul_post();
                    REPLY_NAME = "";
                    REPLY = "";
                    EDIT = "NO";
                    KOMEN = "NO";
                    SUBKOMEN = "";
                    etKomen.setText(KOMEN);
                    Header.setText(JUDUL);
                    GetData();
                }catch (Exception e){
                    Toast.makeText(DetailForumActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailForumActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(DetailForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetData(){
        FirebaseApp.initializeApp(DetailForumActivity.this);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.d("Zyarga","Fetching FCM Failed",task.getException());
                    return;
                }
                // Get new FCM registration token
                token = task.getResult();
                Log.e("TAGSOO",token);
                ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                final Call<ResponseObject> data =api.Profil(Token,token);
                data.enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        try {
                            if (response.body().getKode().equals(200)){
                                Logic(response.body().getData().getNama_user());

                            }else{
                                response.body().getMessage();
                            }
                        }catch (Exception e){
                            Toast.makeText(DetailForumActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DetailForumActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {
                        Toast.makeText(DetailForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    private void Komen(){
        final ProgressDialog pd = new ProgressDialog(DetailForumActivity.this);
        pd.setMessage("Sedang Mengisi Komen");
        pd.show();
        pd.setCancelable(false);

        File file4 = new File(postFoto1);
        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
        MultipartBody.Part Foto = MultipartBody.Part.createFormData("img_komen", file4.getName(), fileReqBody4);
        Call<ResponseObject> data;
        if (Gambar1){
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.PostKomen(
                    RequestBody.create(MediaType.parse("text/plain"),Token),
                            RequestBody.create(MediaType.parse("text/plain"),ID),
                            RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()),
                            Foto);
        }else{
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.PostKomen(
                            RequestBody.create(MediaType.parse("text/plain"),Token),
                            RequestBody.create(MediaType.parse("text/plain"),ID),
                            RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()));
        }
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent goInput = new Intent(DetailForumActivity.this, DetailForumActivity.class);
                    goInput.putExtra("ID",ID);
                    goInput.putExtra("CATEGORY",CATEGORY);
                    goInput.putExtra("JUDUL",JUDUL);
                    goInput.putExtra("REPLY_NAME","");
                    goInput.putExtra("REPLY","");
                    goInput.putExtra("EDIT","NO");
                    goInput.putExtra("ISI_KOMEN","");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Toast.makeText(DetailForumActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailForumActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(DetailForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void EditKomen(String IDS){
        final ProgressDialog pd = new ProgressDialog(DetailForumActivity.this);
        pd.setMessage("Sedang Mengisi Komen");
        pd.show();
        pd.setCancelable(false);

        File file4 = new File(postFoto1);
        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
        MultipartBody.Part Foto = MultipartBody.Part.createFormData("img_komen", file4.getName(), fileReqBody4);
        Call<ResponseObject> data;
        if (Gambar1){
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.EditKomen(
                    RequestBody.create(MediaType.parse("text/plain"),Token),
                    RequestBody.create(MediaType.parse("text/plain"),IDS),
                    RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()),
                    Foto);
        }else{
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.EditKomen(
                    RequestBody.create(MediaType.parse("text/plain"),Token),
                    RequestBody.create(MediaType.parse("text/plain"),IDS),
                    RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()));
        }
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent goInput = new Intent(DetailForumActivity.this, DetailForumActivity.class);
                    goInput.putExtra("ID",ID);
                    goInput.putExtra("CATEGORY",CATEGORY);
                    goInput.putExtra("JUDUL",JUDUL);
                    goInput.putExtra("REPLY_NAME","");
                    goInput.putExtra("REPLY","");
                    goInput.putExtra("EDIT","NO");
                    goInput.putExtra("ISI_KOMEN","");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Toast.makeText(DetailForumActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailForumActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(DetailForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void EditSubKomen(String IDS){
        final ProgressDialog pd = new ProgressDialog(DetailForumActivity.this);
        pd.setMessage("Sedang Mengisi Komen");
        pd.show();
        pd.setCancelable(false);

        File file4 = new File(postFoto1);
        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
        MultipartBody.Part Foto = MultipartBody.Part.createFormData("img_komen", file4.getName(), fileReqBody4);
        Call<ResponseObject> data;
        if (Gambar1){
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.EditSubKomen(
                    RequestBody.create(MediaType.parse("text/plain"),Token),
                    RequestBody.create(MediaType.parse("text/plain"),IDS),
                    RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()),
                    Foto);
        }else{
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.EditSubKomen(
                    RequestBody.create(MediaType.parse("text/plain"),Token),
                    RequestBody.create(MediaType.parse("text/plain"),IDS),
                    RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()));
        }
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent goInput = new Intent(DetailForumActivity.this, DetailForumActivity.class);
                    goInput.putExtra("ID",ID);
                    goInput.putExtra("CATEGORY",CATEGORY);
                    goInput.putExtra("JUDUL",JUDUL);
                    goInput.putExtra("REPLY_NAME","");
                    goInput.putExtra("REPLY","");
                    goInput.putExtra("EDIT","NO");
                    goInput.putExtra("ISI_KOMEN","");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Toast.makeText(DetailForumActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailForumActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(DetailForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void SubKomen(){
        final ProgressDialog pd = new ProgressDialog(DetailForumActivity.this);
        pd.setMessage("Sedang Mengisi Komen");
        pd.show();
        pd.setCancelable(false);

        File file4 = new File(postFoto1);
        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
        MultipartBody.Part Foto = MultipartBody.Part.createFormData("img_komen", file4.getName(), fileReqBody4);
        Call<ResponseObject> data;
        if (Gambar1){
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.PostSubKomen(
                    RequestBody.create(MediaType.parse("text/plain"),Token),
                    RequestBody.create(MediaType.parse("text/plain"),ID),
                    RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),REPLY),
                    Foto);
        }else{
            ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
            data =api.PostSubKomen(
                    RequestBody.create(MediaType.parse("text/plain"),Token),
                    RequestBody.create(MediaType.parse("text/plain"),ID),
                    RequestBody.create(MediaType.parse("text/plain"),etKomen.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"),REPLY));
        }
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent goInput = new Intent(DetailForumActivity.this, DetailForumActivity.class);
                    goInput.putExtra("ID",ID);
                    goInput.putExtra("CATEGORY",CATEGORY);
                    goInput.putExtra("JUDUL",JUDUL);
                    goInput.putExtra("REPLY_NAME","");
                    goInput.putExtra("REPLY","");
                    goInput.putExtra("EDIT","NO");
                    goInput.putExtra("ISI_KOMEN","");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Toast.makeText(DetailForumActivity.this, "Anda Belum Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailForumActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(DetailForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(String NamaUser){
        mManager = new LinearLayoutManager(DetailForumActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewGambar.setLayoutManager(mManager);
        mManager2 = new LinearLayoutManager(DetailForumActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerKomen.setLayoutManager(mManager2);

        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseObject> data =api.DetailPosting(Token,ID);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.body().getKode().equals(200)){
                    if (NamaUser.equals(response.body().getData().getDetail().getNama_user())){
                        Report.setVisibility(View.GONE);
                        Delete.setVisibility(View.VISIBLE);
                        Edit.setVisibility(View.VISIBLE);
                        recyclerViewGambar.setVisibility(View.VISIBLE);
                        Edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent goInput = new Intent(DetailForumActivity.this, EditForumActivity.class);
                                goInput.putExtra("ID",response.body().getData().getDetail().getId_post());
                                goInput.putExtra("ISI",response.body().getData().getDetail().getIsi_post());
                                goInput.putExtra("JUDUL",response.body().getData().getDetail().getJudul_post());
                                goInput.putExtra("CATEGORY",response.body().getData().getDetail().getKategori_post());
                                startActivity(goInput);
                            }
                        });
                    }else{
                        recyclerViewGambar.setVisibility(View.GONE);
                        Report.setVisibility(View.VISIBLE);
                        Delete.setVisibility(View.GONE);
                        Edit.setVisibility(View.GONE);
                        Tutup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                myDialog.dismiss();
                            }
                        });
                        Report.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                myDialog.show();
                            }
                        });
                        Konfirmasi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (Note.getText().toString().isEmpty() || Note.getText().toString() == ""){
                                    Toast.makeText(DetailForumActivity.this, "Note Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                                }else{
                                    ReportKomen();
                                }
                            }
                        });
                    }
                    Nama.setText(response.body().getData().getDetail().getNama_user());
                    web.setText(response.body().getData().getDetail().getIsi_post());
                    mItemsGambar=response.body().getData().getImage();
                    mItemsKomen=response.body().getData().getKomen();
                    Jam.setText(response.body().getData().getTgl_post());
                    mAdapter = new AdapterGambarForum(DetailForumActivity.this,mItemsGambar,
                            response.body().getData().getDetail().getId_post(),
                            response.body().getData().getDetail().getId_post(),
                            response.body().getData().getDetail().getKategori_post(),
                            response.body().getData().getDetail().getJudul_post());
                    recyclerViewGambar.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                    mAdapter2 = new AdapterKomen(DetailForumActivity.this,mItemsKomen,ID,CATEGORY,JUDUL,NamaUser);
                    recyclerKomen.setAdapter(mAdapter2);
                    mAdapter2.notifyDataSetChanged();
                    Delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DetailForumActivity.this);

                            // Set a title for alert dialog
                            builder.setTitle("Pemberitahuan");

                            // Ask the final question
                            builder.setMessage("Apakah Anda Yakin Ingin Menghapus Komen ? ");

                            // Set the alert dialog yes button click listener
                            builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do something when user clicked the Yes button
                                    // Set the TextView visibility GONE
                                    DeleteKomen();
                                }
                            });

                            // Set the alert dialog no button click listener
                            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do something when No button clicked
                                }
                            });

                            AlertDialog dialog = builder.create();
                            // Display the alert dialog on interface
                            dialog.show();
                        }
                    });
                    if (REPLY_NAME==null || REPLY_NAME.equals("")){
                        cardKomen.setVisibility(View.GONE);
                        ReplyName.setVisibility(View.GONE);
                        cancelReply.setVisibility(View.GONE);
                    }else{
                        scrollView.fullScroll(View.FOCUS_DOWN);
                        cardKomen.setVisibility(View.VISIBLE);
                        etKomen.requestFocus();
                        ReplyName.setText("Repllying To "+REPLY_NAME);
                    }
                }else{
                    Toast.makeText(DetailForumActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
    private void ReportKomen(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.ReportPosting(Token,ID,"post",Note.getText().toString());
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                Toast.makeText(DetailForumActivity.this, "Post berhasil di Report", Toast.LENGTH_SHORT).show();
//                Intent goInput = new Intent(DetailForumActivity.this, DetailForumActivity.class);
//                goInput.putExtra("ID",ID);
//                goInput.putExtra("CATEGORY",CATEGORY);
//                goInput.putExtra("JUDUL",JUDUL);
//                goInput.putExtra("REPLY_NAME","");
//                goInput.putExtra("REPLY","");
//                goInput.putExtra("EDIT","NO");
//                goInput.putExtra("ISI_KOMEN","");
//                startActivities(new Intent[]{goInput});
                myDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {

            }
        });
    }
    private void DeleteKomen(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.DeletePosting(Token,ID,"post");
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                Toast.makeText(DetailForumActivity.this, "Post berhasil Terhapus", Toast.LENGTH_SHORT).show();
//                Intent goInput = new Intent(DetailForumActivity.this, DetailForumActivity.class);
//                goInput.putExtra("ID",ID);
//                goInput.putExtra("CATEGORY",CATEGORY);
//                goInput.putExtra("JUDUL",JUDUL);
//                goInput.putExtra("REPLY_NAME","");
//                goInput.putExtra("REPLY","");
//                goInput.putExtra("EDIT","NO");
//                goInput.putExtra("ISI_KOMEN","");
//                startActivities(new Intent[]{goInput});
                Intent goInput = new Intent(DetailForumActivity.this, HomeActivity.class);
                goInput.putExtra("FORUM","FORUM");
                startActivities(new Intent[]{goInput});
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {

            }
        });
    }
    //Dellaroy Logic
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("TEST", "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }
    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        // Here we specify the environment location and the exact path where we want to save the so-created file
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();

        // Here we create the file using a prefix, a suffix and a directory
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
            if (data != null) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                // Set the Image in ImageView for Previewing the Media

//                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();
                if(Gambar1) {
                    postFoto1 = mediaPath;
                    String filename = postFoto1.substring(postFoto1.lastIndexOf("/") + 1);
                    cardImg.setVisibility(View.VISIBLE);
                    GambarKomen.setVisibility(View.VISIBLE);
                    GambarKomen.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    Gambar1 = true;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }
        }}
    }

    @Override
    public void onBackPressed() {
        Intent goInput = new Intent(DetailForumActivity.this, HomeActivity.class);
        goInput.putExtra("FORUM","FORUM");
        startActivities(new Intent[]{goInput});
    }
}