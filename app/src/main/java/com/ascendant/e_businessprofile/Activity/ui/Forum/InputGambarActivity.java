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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class InputGambarActivity extends AppCompatActivity {
    LinearLayout Available,Navigator;
    RecyclerView rv;
    ImageView ivMore;
    LinearLayout More,Back;
    Boolean more=true;
    private ArrayList<DataModel> pListt = new ArrayList<>();
    DB_Helper dbHelper;
    String Token;

    ImageView ivFoto1;
    CardView cardFoto1;
    RelativeLayout PlusFoto1;
    Button Post;
    int u = 0;
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
    String IDS,ID,CATEGORY,JUDUL,REPLY_NAME,REPLY,EDIT,KOMEN,SUBKOMEN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_gambar);
        Intent intent = getIntent();
        IDS = intent.getExtras().getString("IDS");
        ID = intent.getExtras().getString("ID");
        CATEGORY = intent.getExtras().getString("CATEGORY");
        JUDUL = intent.getExtras().getString("JUDUL");
        REPLY_NAME = intent.getExtras().getString("REPLY_NAME");
        REPLY = intent.getExtras().getString("REPLY");
        EDIT = intent.getExtras().getString("EDIT");
        KOMEN = intent.getExtras().getString("ISI_KOMEN");
        SUBKOMEN = intent.getExtras().getString("SUB_KOMEN");
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

        Post = findViewById(R.id.btnPost);
        ivFoto1 = findViewById(R.id.ivPhoto1);

        cardFoto1 = findViewById(R.id.cardFoto1);

        PlusFoto1 = findViewById(R.id.relativePlusFoto1);
        cardFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gambar1 = true;
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OneImage();
            }
        });
    }

    private void OneImage(){
        final ProgressDialog pd = new ProgressDialog(InputGambarActivity.this);
        pd.setMessage("Sedang Mengisi Gambar Post");
        pd.show();
        pd.setCancelable(false);

        File file1= new File(postFoto1);
        RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part Foto1 = MultipartBody.Part.createFormData("img_post", file1.getName(), fileReqBody1);
        Call<ResponseObject> data;
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        data =api.TambahImagePost(
                RequestBody.create(MediaType.parse("text/plain"),Token),
                RequestBody.create(MediaType.parse("text/plain"),ID),
                Foto1
        );
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                pd.hide();
                try {
                    Toast.makeText(InputGambarActivity.this, "Komen berhasil Terhapus", Toast.LENGTH_SHORT).show();
                    Intent goInput = new Intent(InputGambarActivity.this, DetailForumActivity.class);
                    goInput.putExtra("ID",ID);
                    goInput.putExtra("CATEGORY",CATEGORY);
                    goInput.putExtra("JUDUL",JUDUL);
                    goInput.putExtra("REPLY_NAME","");
                    goInput.putExtra("REPLY","");
                    startActivities(new Intent[]{goInput});
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(InputGambarActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                pd.hide();
                Toast.makeText(InputGambarActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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
                    PlusFoto1.setVisibility(View.GONE);
                    ivFoto1.setVisibility(View.VISIBLE);
                    ivFoto1.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    Gambar1 = false;
                    u=1;
                    Toast.makeText(this, filename, Toast.LENGTH_SHORT).show();
                }
            }}
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InputGambarActivity.this, HomeActivity.class);
        startActivity(intent);;
    }
}