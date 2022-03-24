package com.ascendant.e_businessprofile.Activity.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.ChangeUnitKerjaActivity;
import com.ascendant.e_businessprofile.Activity.DetailBeritaActivity;
import com.ascendant.e_businessprofile.Activity.HistoryPoinActivity;
import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.UbahProfilActivity;
import com.ascendant.e_businessprofile.Activity.UnitKerjaActivity;
import com.ascendant.e_businessprofile.Activity.WebActivity;
import com.ascendant.e_businessprofile.Activity.ui.Forum.PostForumActivity;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.Model.ResponseObject;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    RelativeLayout Logout,Privacy,ChangePassword,ContactUs,UnitKerjas,ChangeProfile;
    Dialog myDialog;
    EditText OldPassword,NewPassword,ConfirmPassword;
    Button Confirm,Close;
    DB_Helper dbHelper;
    String Token;
    TextView Nama,UnitKerja,NIP,NoTelpon;
    String token;
    ImageView user;
    TextView poin;
    LinearLayout Background;
    ImageView image;
    TextView tvQuiz;
    LinearLayout Lpoin;
    TextView tvHistory;
    CardView cardHistory;

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
    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DB_Helper(getActivity());
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        UnitKerjas = view.findViewById(R.id.relativeUnitKerja);
        ChangeProfile = view.findViewById(R.id.relativeEditProfile);
        tvHistory = view.findViewById(R.id.tvHistory);
        Lpoin = view.findViewById(R.id.poin);
        tvQuiz = view.findViewById(R.id.tvQuiz);
        poin = view.findViewById(R.id.tvPoin);
        Background = view.findViewById(R.id.linearQuizBackgorund);
        image = view.findViewById(R.id.ivQuiz);
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_change_password);
        OldPassword = myDialog.findViewById(R.id.etOldPassword);
        NewPassword = myDialog.findViewById(R.id.etNewPassword);
        ConfirmPassword = myDialog.findViewById(R.id.etConfirmPassword);
        Confirm = myDialog.findViewById(R.id.btnKonfirmasi);
        Close = myDialog.findViewById(R.id.btnClose);
        Logout = view.findViewById(R.id.relativeLogout);
        Privacy = view.findViewById(R.id.relativePrivacy);
        ChangePassword = view.findViewById(R.id.relativeChangePassword);
        ContactUs = view.findViewById(R.id.relativeContactUs);
        Nama = view.findViewById(R.id.tvNama);
        UnitKerja = view.findViewById(R.id.tvUnitKerja);
        NIP = view.findViewById(R.id.tvNip);
        NoTelpon = view.findViewById(R.id.tvNoTelpon);
        user = view.findViewById(R.id.ivUser);
        cardHistory = view.findViewById(R.id.cardPointHistory);
        CheckQuiz();
        GetPoin();
        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ebuss-raw.mandiri-ebuss.com/uploads/policy/m/syarat-dan-ketentuan-penggunaan.pdf"));
                startActivity(browserIntent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // Set a title for alert dialog
                builder.setTitle("Pemberitahuan");

                // Ask the final question
                builder.setMessage("Apakah Anda Yakin Ingin Logout ? ");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE
                        Logout();
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
        ChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UbahProfilActivity.class);
                startActivity(intent);
            }
        });
        UnitKerjas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangeUnitKerjaActivity.class);
                startActivity(intent);
            }
        });
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
            }
        });
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checker();
            }
        });
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.hide();
            }
        });
        ContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeProfileUser();
            }
        });
        cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), HistoryPoinActivity.class);
                i.putExtra("POIN", poin.getText().toString());
                startActivity(i);
            }
        });
        GetData();
    }
    private void CheckQuiz(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.check_quiz(Token);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getMessage().equals("Quiz tersedia")){
                        image.setImageResource(R.drawable.wrong);
                        Background.setBackgroundColor(Color.rgb(241,0,0));
                        tvQuiz.setText("Quiz Belum Dikerjakan");
                    }else{
                        image.setImageResource(R.drawable.check);
                        Background.setBackgroundColor(Color.rgb(0,241,0));
                        tvQuiz.setText("Quiz Sudah Dikerjakan");
                    }
                }catch (Exception e){
                    //Toast.makeText(getActivity(), "Kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetPoin(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseObject> data =api.Poin(Token);
        data.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                try {
                    if (response.body().getKode().equals(200)){
                        poin.setText(String.valueOf(response.body().getData().getTotal_poin()));
                    }else{
                        response.body().getMessage();
                    }
                }catch (Exception e){
                    Log.d("ZYARGA : ",e.toString());
                    Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ChangeProfileUser(){
        Gambar1 = true;
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
    }
    private void Checker(){
        if (OldPassword.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Old Password Must be filled", Toast.LENGTH_SHORT).show();
        }else if (NewPassword.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "New Password Must be filled", Toast.LENGTH_SHORT).show();
        }else if (ConfirmPassword.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Confirm Password Must be filled", Toast.LENGTH_SHORT).show();
        }else if(!NewPassword.getText().toString().equals(ConfirmPassword.getText().toString())){
            Toast.makeText(getActivity(), "New Password and Confirm Password Must Same", Toast.LENGTH_SHORT).show();
        }else{
            ChangePassword();
        }
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
                                Nama.setText(response.body().getData().getNama_user());
                                UnitKerja.setText(response.body().getData().getUnit_kerja()+"\n"+response.body().getData().getAddinfo());
                                NIP.setText(response.body().getData().getNip_user());
                                NoTelpon.setText(response.body().getData().getNo_telpon_user());
                                if (!response.body().getData().getFoto_user().equals("")){
                                    Glide.with(getActivity())
                                            .load(response.body().getData().getFoto_user())
                                            .into(user);
                                }
//                                Cut Here
                                if (response.body().getData().getAddinfo().equals("")){
                                    myDialog.show();
                                }
                            }else{
                                response.body().getMessage();
                            }
                        }catch (Exception e){
                            Log.d("ZYARGA : ",e.toString());
                            Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {
                        Intent intent = new Intent(getActivity(),LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "Waktu Sesi habis harap coba Login Lagfi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void ChangePassword(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.ChangePassword(Token,OldPassword.getText().toString(),NewPassword.getText().toString(),ConfirmPassword.getText().toString());
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    if (response.body().getMessage().equals("Profil berhasil diupdate")){
                        myDialog.hide();

                    }
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    //Toast.makeText(getActivity(), "Kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
//                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logout(){
        DB_Helper db_helper = new DB_Helper(getActivity());
        db_helper.Logout();
        Toast.makeText(getActivity(), "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finishAffinity();
    }

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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
            if (data != null) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
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

                    Gambar1 = false;
                    final ProgressDialog pd = new ProgressDialog(getActivity());
                    pd.setMessage("Sedang Mengisi Post");
                    pd.show();
                    pd.setCancelable(false);
                    File file1= new File(postFoto1);
                    RequestBody fileReqBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part Foto1 = MultipartBody.Part.createFormData("img_profil", file1.getName(), fileReqBody1);
                    Call<ResponseObject> datas;
                    ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
                    datas =api.EditFoto(
                            RequestBody.create(MediaType.parse("text/plain"),Token),
                            Foto1);
                    datas.enqueue(new Callback<ResponseObject>() {
                        @Override
                        public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                            pd.hide();
                            try {
                                Toast.makeText(getActivity(), "Gambar Berhasil Diubah", Toast.LENGTH_SHORT).show();
                                user.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                            }catch (Exception e){
                                Log.d("Zyarga : ",e.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseObject> call, Throwable t) {
                            pd.hide();
                            Toast.makeText(getActivity(), "Connection Failed Please Wait Later", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }}
    }
}