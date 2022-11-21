package com.ascendant.e_businessprofile.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterFile extends RecyclerView.Adapter<AdapterFile.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    String Tanggal;
    DB_Helper dbHelper;
    String Token,Level;
    public AdapterFile(Context ctx, List<DataModel> mList,String Tanggal){
        this.ctx = ctx;
        this.mList = mList;
        this.Tanggal=Tanggal;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_file,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
                Level = cursor.getString(1);
            }
        }
        holderData.Tanggal.setText(ascendant.MagicDateChange(dm.getCreated_at()));
        holderData.Nama.setText(ascendant.SmallText(dm.getCaption_file()));
        holderData.ID.setText(String.valueOf(posistion+1));
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastIndexExt = dm.getLink_file_mandiri_update().lastIndexOf(".");
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                // Set a title for alert dialog
                builder.setTitle("Pemberitahuan");

                // Ask the final question
                builder.setMessage("Apakah Anda ingin Mendownload File bernama "+dm.getCaption_file()+dm.getLink_file_mandiri_update().substring(lastIndexExt) + " ? ");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE

                        if (Level.equals("trial")){
                            Toast.makeText(ctx, "You Cannot Download File", Toast.LENGTH_SHORT).show();
                        }else{
                            ascendant.DownloadUniversal(dm.getLink_file_mandiri_update(),dm.getCaption_file(),ctx,dm.getLink_file_mandiri_update().substring(lastIndexExt));
                        }
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout card;
        TextView Tanggal,Nama,ID;

        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            Tanggal = v.findViewById(R.id.tvTanggal);
            Nama = v.findViewById(R.id.tvNama);
            ID = v.findViewById(R.id.tvId);
        }
    }
}
