package com.ascendant.e_businessprofile.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.Forum.DetailForumActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKomen extends RecyclerView.Adapter<AdapterKomen.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    private List<DataModel> mItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    RecyclerView rv;
    String ID,CATEGORY,JUDUL;
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

    DB_Helper dbHelper;
    String Token;
    String NamaUser;
    public AdapterKomen(Context ctx, List<DataModel> mList,String ID,String CATEGORY,String JUDUL,String NamaUser){
        this.ctx = ctx;
        this.mList = mList;
        this.ID = ID;
        this.CATEGORY = CATEGORY;
        this.JUDUL = JUDUL;
        this.NamaUser = NamaUser;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_komen,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKomen.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        final Ascendant ascendant = new Ascendant();
        holderData.Komen.setText(dm.getIsi_komen());
        holderData.Nama.setText(dm.getNama_user());
        holderData.Jam.setText(dm.getTgl_komen());
        if (dm.getNama_user().equals(NamaUser)){
            holderData.Report.setVisibility(View.GONE);
            holderData.Delete.setVisibility(View.VISIBLE);
            holderData.Edit.setVisibility(View.VISIBLE);
        }else{
            holderData.Report.setVisibility(View.VISIBLE);
            holderData.Delete.setVisibility(View.GONE);
            holderData.Edit.setVisibility(View.GONE);
        }
        if (dm.getImg_komen().equals("")){
            holderData.Gambar.setVisibility(View.GONE);
        }else{
            Glide.with(ctx)
                    .load(ascendant.BASE_URL()+dm.getImg_komen())
                    .into(holderData.Gambar);
        }
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
            }
        }
        holderData.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

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
                        DeleteKomen(dm.getId_post_komen());
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
        mManager = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false);
        holderData.recyclerView.setLayoutManager(mManager);
        mAdapter = new AdapterSubKomen(ctx,dm.getSub_komen(),NamaUser,ID,CATEGORY,JUDUL);
        holderData.recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        holderData.cardKomen.setVisibility(View.GONE);
        holderData.Reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, DetailForumActivity.class);
                goInput.putExtra("ID",ID);
                goInput.putExtra("CATEGORY",CATEGORY);
                goInput.putExtra("JUDUL",JUDUL);
                goInput.putExtra("REPLY_NAME",dm.getNama_user());
                goInput.putExtra("REPLY",dm.getId_post_komen());
                goInput.putExtra("EDIT","NO");
                goInput.putExtra("ISI_KOMEN","");
                goInput.putExtra("SUB_KOMEN","NO");
                ctx.startActivities(new Intent[]{goInput});
            }
        });
        holderData.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, DetailForumActivity.class);
                goInput.putExtra("ID",ID);
                goInput.putExtra("CATEGORY",CATEGORY);
                goInput.putExtra("JUDUL",JUDUL);
                goInput.putExtra("REPLY_NAME",dm.getNama_user());
                goInput.putExtra("REPLY",dm.getId_post_komen());
                goInput.putExtra("EDIT","YES");
                goInput.putExtra("IDS",dm.getId_post_komen());
                goInput.putExtra("ISI_KOMEN",dm.getIsi_komen());
                goInput.putExtra("SUB_KOMEN","NO");
                ctx.startActivities(new Intent[]{goInput});
            }
        });
//        holderData.Upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                (galleryIntent,REQUEST_PICK_PHOTO, holderData.Gambar);
//                holderData.Gambar.setVisibility(View.VISIBLE);
//            }
//        });
    }
    private void DeleteKomen(String IDS){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseArrayObject> data =api.DeletePosting(Token,IDS,"komen");
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                Toast.makeText(ctx, "Komen berhasil Terhapus", Toast.LENGTH_SHORT).show();
                Intent goInput = new Intent(ctx, DetailForumActivity.class);
                goInput.putExtra("ID",ID);
                goInput.putExtra("CATEGORY",CATEGORY);
                goInput.putExtra("JUDUL",JUDUL);
                goInput.putExtra("REPLY_NAME","");
                goInput.putExtra("REPLY","");
                goInput.putExtra("EDIT","NO");
                goInput.putExtra("ISI_KOMEN","");
                ctx.startActivities(new Intent[]{goInput});
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {

            }
        });
    }
    private void ImageLogic(Intent data, int requestPickPhoto, ImageView gambar) {
        if (data != null) {
            Toast.makeText(ctx, "Clicked", Toast.LENGTH_SHORT).show();
            // Get the Image from data
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = ctx.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            mediaPath = cursor.getString(columnIndex);
            cursor.close();
            if(Gambar1) {
                postFoto1 = mediaPath;
                String filename = postFoto1.substring(postFoto1.lastIndexOf("/") + 1);
                gambar.setVisibility(View.VISIBLE);
                gambar.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                Gambar1 = false;
                Toast.makeText(ctx, filename, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama,Komen,Jam;
        ImageView Reply,Gambar,Upload;
        RecyclerView recyclerView;
        CardView cardKomen;
        EditText etKomen;
        LinearLayout Delete,Report,Edit;
        HolderData(View v){
            super(v);
            Nama = v.findViewById(R.id.tvNama);
            Komen = v.findViewById(R.id.tvKomen);
            Jam = v.findViewById(R.id.tvJam);
            Reply = v.findViewById(R.id.ivReply);
            recyclerView = v.findViewById(R.id.recycler);
            Gambar = v.findViewById(R.id.ivGambar);
            cardKomen = v.findViewById(R.id.cardKomen);
            etKomen = v.findViewById(R.id.etKomen);
            Upload = v.findViewById(R.id.ivUpload);
            Delete = v.findViewById(R.id.linearDelete);
            Report = v.findViewById(R.id.linearReport);
            Edit = v.findViewById(R.id.linearEdit);
        }
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
}

