package com.ascendant.e_businessprofile.Activity.ui.Forum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostForumActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pListt = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    ImageView ivFoto1,ivFoto2,ivFoto3,ivFoto4;
    CardView cardFoto1,cardFoto2,cardFoto3,cardFoto4;
    RelativeLayout PlusFoto1,PlusFoto2,PlusFoto3,PlusFoto4;

    EditText etTitle,etForum;
    Button Post;
    Spinner Category;
    EditText Caption1,Caption2,Caption3,Caption4;
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
    String postFoto2= "";
    String postFoto3= "";
    String postFoto4= "";
    //ONCLICK
    Boolean Gambar1 = false;
    Boolean Gambar2 = false;
    Boolean Gambar3 = false;
    Boolean Gambar4 = false;
    int u = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_forum);
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
        Category = findViewById(R.id.spinnerCategory);
        Post = findViewById(R.id.btnPost);
        ivFoto1 = findViewById(R.id.ivPhoto1);
        ivFoto2 = findViewById(R.id.ivPhoto2);
        ivFoto3 = findViewById(R.id.ivPhoto3);
        ivFoto4 = findViewById(R.id.ivPhoto4);

        cardFoto1 = findViewById(R.id.cardFoto1);
        cardFoto2 = findViewById(R.id.cardFoto2);
        cardFoto3 = findViewById(R.id.cardFoto3);
        cardFoto4 = findViewById(R.id.cardFoto4);

        PlusFoto1 = findViewById(R.id.relativePlusFoto1);
        PlusFoto2 = findViewById(R.id.relativePlusFoto2);
        PlusFoto3 = findViewById(R.id.relativePlusFoto3);
        PlusFoto4 = findViewById(R.id.relativePlusFoto4);

        Caption1 = findViewById(R.id.etCaption1);
        Caption2 = findViewById(R.id.etCaption2);
        Caption3 = findViewById(R.id.etCaption3);
        Caption4 = findViewById(R.id.etCaption4);

        Caption1.setVisibility(View.GONE);
        Caption2.setVisibility(View.GONE);
        Caption3.setVisibility(View.GONE);
        Caption4.setVisibility(View.GONE);
        cardFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gambar1 = true;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });
        cardFoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gambar2 = true;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });
        cardFoto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gambar3 = true;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });
        cardFoto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gambar4 = true;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checker();
            }
        });
    }
    private void Logic(){
        if (u==1){
            OneImage();
        }else if (u==2){
            TwoImage();
        }else if (u==3){
            ThreeImage();
        }else if (u==4){
            FourImage();
        }else{
            NoImage();
        }
    }
    private void Checker(){
        if (etTitle.getText().toString().isEmpty() || etTitle.getText().toString().equals("")){
            Toast.makeText(PostForumActivity.this, "Title Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }else if (etForum.getText().toString().isEmpty() || etForum.getText().toString().equals("")){
            Toast.makeText(PostForumActivity.this, "Forum Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }else if (Category.getSelectedItem().toString().equals("Choose Category")){
            Toast.makeText(PostForumActivity.this, "Choose Category", Toast.LENGTH_SHORT).show();
        }else{
            Logic();
        }
    }
    private void NoImage(){
        final ProgressDialog pd = new ProgressDialog(PostForumActivity.this);
        pd.setMessage("Sedang Mengisi Post");
        pd.show();
        pd.setCancelable(false);

        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.PostingForum(
                RequestBody.create(MediaType.parse("text/plain"),Token),
                RequestBody.create(MediaType.parse("text/plain"),Category.getSelectedItem().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etTitle.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etForum.getText().toString()));
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent goInput = new Intent(PostForumActivity.this, HomeActivity.class);
                    goInput.putExtra("FORUM","FORUM");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(PostForumActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(PostForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void OneImage(){
        final ProgressDialog pd = new ProgressDialog(PostForumActivity.this);
        pd.setMessage("Sedang Mengisi Post");
        pd.show();
        pd.setCancelable(false);

        File file1= new File(postFoto1);
        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part Foto1 = MultipartBody.Part.createFormData("img_post[]", file1.getName(), fileReqBody1);
        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.PostingForum(
                RequestBody.create(MediaType.parse("text/plain"),Token),
                RequestBody.create(MediaType.parse("text/plain"),Category.getSelectedItem().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etTitle.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etForum.getText().toString()),
                Foto1,
                RequestBody.create(MediaType.parse("text/plain"),Caption1.getText().toString())
        );
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent intent = new Intent(PostForumActivity.this, HomeActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(PostForumActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(PostForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void TwoImage(){
        final ProgressDialog pd = new ProgressDialog(PostForumActivity.this);
        pd.setMessage("Sedang Mengisi Post");
        pd.show();
        pd.setCancelable(false);

        File file1= new File(postFoto1);
        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part Foto1 = MultipartBody.Part.createFormData("img_post[]", file1.getName(), fileReqBody1);

        File file2= new File(postFoto2);
        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
        MultipartBody.Part Foto2 = MultipartBody.Part.createFormData("img_post[]", file2.getName(), fileReqBody2);
        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.PostingForum(
                RequestBody.create(MediaType.parse("text/plain"),Token),
                RequestBody.create(MediaType.parse("text/plain"),Category.getSelectedItem().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etTitle.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etForum.getText().toString()),
                Foto1,
                RequestBody.create(MediaType.parse("text/plain"),Caption1.getText().toString()),
                Foto2,
                RequestBody.create(MediaType.parse("text/plain"),Caption2.getText().toString())
        );
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent intent = new Intent(PostForumActivity.this, HomeActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(PostForumActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(PostForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ThreeImage(){
        final ProgressDialog pd = new ProgressDialog(PostForumActivity.this);
        pd.setMessage("Sedang Mengisi Post");
        pd.show();
        pd.setCancelable(false);

        File file1= new File(postFoto1);
        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part Foto1 = MultipartBody.Part.createFormData("img_post[]", file1.getName(), fileReqBody1);

        File file2= new File(postFoto2);
        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
        MultipartBody.Part Foto2 = MultipartBody.Part.createFormData("img_post[]", file2.getName(), fileReqBody2);

        File file3= new File(postFoto3);
        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
        MultipartBody.Part Foto3 = MultipartBody.Part.createFormData("img_post[]", file3.getName(), fileReqBody3);

        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.PostingForum(
                RequestBody.create(MediaType.parse("text/plain"),Token),
                RequestBody.create(MediaType.parse("text/plain"),Category.getSelectedItem().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etTitle.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etForum.getText().toString()),
                Foto1,
                RequestBody.create(MediaType.parse("text/plain"),Caption1.getText().toString()),
                Foto2,
                RequestBody.create(MediaType.parse("text/plain"),Caption2.getText().toString()),
                Foto3,
                RequestBody.create(MediaType.parse("text/plain"),Caption3.getText().toString())
        );
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent intent = new Intent(PostForumActivity.this, HomeActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(PostForumActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(PostForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void FourImage(){
        final ProgressDialog pd = new ProgressDialog(PostForumActivity.this);
        pd.setMessage("Sedang Mengisi Post");
        pd.show();
        pd.setCancelable(false);

        File file1= new File(postFoto1);
        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part Foto1 = MultipartBody.Part.createFormData("img_post[]", file1.getName(), fileReqBody1);

        File file2= new File(postFoto2);
        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
        MultipartBody.Part Foto2 = MultipartBody.Part.createFormData("img_post[]", file2.getName(), fileReqBody2);

        File file3= new File(postFoto3);
        RequestBody fileReqBody3 = RequestBody.create(MediaType.parse("image/*"), file3);
        MultipartBody.Part Foto3 = MultipartBody.Part.createFormData("img_post[]", file3.getName(), fileReqBody3);

        File file4= new File(postFoto4);
        RequestBody fileReqBody4 = RequestBody.create(MediaType.parse("image/*"), file4);
        MultipartBody.Part Foto4 = MultipartBody.Part.createFormData("img_post[]", file4.getName(), fileReqBody4);

        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.PostingForum(
                RequestBody.create(MediaType.parse("text/plain"),Token),
                RequestBody.create(MediaType.parse("text/plain"),Category.getSelectedItem().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etTitle.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),etForum.getText().toString()),
                Foto1,
                RequestBody.create(MediaType.parse("text/plain"),Caption1.getText().toString()),
                Foto2,
                RequestBody.create(MediaType.parse("text/plain"),Caption2.getText().toString()),
                Foto3,
                RequestBody.create(MediaType.parse("text/plain"),Caption3.getText().toString()),
                Foto4,
                RequestBody.create(MediaType.parse("text/plain"),Caption4.getText().toString())
        );
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Intent intent = new Intent(PostForumActivity.this, HomeActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(PostForumActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(PostForumActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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
                    cardFoto2.setVisibility(View.VISIBLE);
                    Caption1.setVisibility(View.VISIBLE);
                    PlusFoto1.setVisibility(View.GONE);
                    ivFoto1.setVisibility(View.VISIBLE);
                    ivFoto1.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    Gambar1 = false;
                    u=1;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }else if (Gambar2){
                    Toast.makeText(PostForumActivity.this, "Gambar Ke 2", Toast.LENGTH_SHORT).show();
                    postFoto2 = mediaPath;
                    String filename = postFoto2.substring(postFoto2.lastIndexOf("/") + 1);
                    cardFoto3.setVisibility(View.VISIBLE);
                    Caption2.setVisibility(View.VISIBLE);
                    PlusFoto2.setVisibility(View.GONE);
                    ivFoto2.setVisibility(View.VISIBLE);
                    ivFoto2.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    Gambar2 = false;
                    u=2;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }else if (Gambar3){
                    postFoto3 = mediaPath;
                    String filename = postFoto3.substring(postFoto3.lastIndexOf("/") + 1);
                    cardFoto4.setVisibility(View.VISIBLE);
                    Caption3.setVisibility(View.VISIBLE);
                    PlusFoto3.setVisibility(View.GONE);
                    ivFoto3.setVisibility(View.VISIBLE);
                    ivFoto3.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    Gambar3 = false;
                    u=3;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }else if (Gambar4){
                    postFoto4 = mediaPath;
                    String filename = postFoto4.substring(postFoto4.lastIndexOf("/") + 1);
                    PlusFoto4.setVisibility(View.GONE);
                    Caption4.setVisibility(View.VISIBLE);
                    ivFoto4.setVisibility(View.VISIBLE);
                    ivFoto4.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    Gambar4 = false;
                    u=4;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }
            }}
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PostForumActivity.this, HomeActivity.class);
        startActivity(intent);;
    }
}