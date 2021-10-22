package com.ascendant.e_businessprofile.Activity.ui.Forum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.Activity.API.ApiRequest;
import com.ascendant.e_businessprofile.Activity.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Adapter.AdapterEBook;
import com.ascendant.e_businessprofile.Adapter.AdapterGambarForum;
import com.ascendant.e_businessprofile.Adapter.AdapterKomen;
import com.ascendant.e_businessprofile.Adapter.Static.AdapterNavigator;
import com.ascendant.e_businessprofile.BuildConfig;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.Model.StaticModel.Healthcare.CreditWorthiness.CreditWorthinessModel;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

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
    String Token;
    String ID,CATEGORY,JUDUL,REPLY_NAME,REPLY;
    TextView Header,Nama,Jam;
    private List<DataModel> mItemsGambar = new ArrayList<>();
    private List<DataModel> mItemsKomen = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mManager2;
    Ascendant AscNet = new Ascendant();
    WebView web;
    CardView cardImg;
    ImageView Reply,GambarKomen,Upload,Send;
    LinearLayout cardKomen;
    EditText etKomen;
    TextView ReplyName,cancelReply;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forum);
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

        Intent intent = getIntent();
        ID = intent.getExtras().getString("ID");
        CATEGORY = intent.getExtras().getString("CATEGORY");
        JUDUL = intent.getExtras().getString("JUDUL");
        REPLY_NAME = intent.getExtras().getString("REPLY_NAME");
        REPLY = intent.getExtras().getString("REPLY");
        Header.setText(JUDUL);
        Logic();
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
                if (REPLY.equals("")){
                    Komen();
                }else{
                    SubKomen();
                }
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
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){

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
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(DetailForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(){
        mManager = new LinearLayoutManager(DetailForumActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewGambar.setLayoutManager(mManager);
        mManager2 = new LinearLayoutManager(DetailForumActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerKomen.setLayoutManager(mManager2);

        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseObject> data =api.DetailPosting(Token,ID);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        Nama.setText(response.body().getData().getDetail().getNama_user());
                        web.loadData(response.body().getData().getDetail().getIsi_post(),"text/html","UTF-8");
                        mItemsGambar=response.body().getData().getImage();
                        mItemsKomen=response.body().getData().getKomen();
                        mAdapter = new AdapterGambarForum(DetailForumActivity.this,mItemsGambar);
                        recyclerViewGambar.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                        mAdapter2 = new AdapterKomen(DetailForumActivity.this,mItemsKomen,ID,CATEGORY,JUDUL);
                        recyclerKomen.setAdapter(mAdapter2);
                        mAdapter2.notifyDataSetChanged();

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
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(DetailForumActivity.this, "Terjadi Kesaqlahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

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
}