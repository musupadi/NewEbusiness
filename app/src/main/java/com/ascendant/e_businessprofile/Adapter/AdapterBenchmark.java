package com.ascendant.e_businessprofile.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.PortraitWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.ui.Farming.MarketingInteligence.MarketingIntelienceFarmingActivity;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterBenchmark extends RecyclerView.Adapter<AdapterBenchmark.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Dialog myDialog;
    Button View,Download;
    public AdapterBenchmark(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
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
        myDialog = new Dialog(ctx);
        myDialog.setContentView(R.layout.dialog_view_download);
        holderData.Tanggal.setText(ascendant.MagicDateChange(dm.getTgl_upload()));
        holderData.Nama.setText(ascendant.SmallText(dm.getNama_benchmark()));
        holderData.ID.setText(String.valueOf(posistion+1));
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);
                int lastIndexExt = dm.getLink_file_benchmark().lastIndexOf(".");
                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Toast.makeText(ctx, dm.getLink_file_benchmark(), Toast.LENGTH_SHORT).show();
                        Log.d("Zyarga : ",dm.getLink_file_benchmark());
                        ascendant.DownloadUniversal(dm.getLink_file_benchmark(),dm.getNama_benchmark(),ctx,dm.getLink_file_benchmark().substring(lastIndexExt));
                    }
                });
               View.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(android.view.View view) {
                       try {
                           if (dm.getEbook_benchmark().equals("") || dm.getEbook_benchmark().isEmpty()){
                               Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getLink_file_benchmark()));
                               ctx.startActivity(browserIntent);
                           }else{
                               Intent i = new Intent(ctx, LandscapeWebViewEbookActivity.class);
                               i.putExtra("LINK", dm.getEbook_benchmark());
                               ctx.startActivity(i);
                           }
                       }catch (Exception e){
                           Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getLink_file_benchmark()));
                           ctx.startActivity(browserIntent);
                       }
                   }
               });
//                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
//
//                // Set a title for alert dialog
//                builder.setTitle("Pemberitahuan");
//
//                // Ask the final question
//                builder.setMessage("Apakah Anda ingin Mendownload File bernama "+dm.getNama_benchmark()+dm.getLink_file_benchmark().substring(lastIndexExt) + " ? ");
//
//                // Set the alert dialog yes button click listener
//                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Do something when user clicked the Yes button
//                        // Set the TextView visibility GONE
//
//
//                    }
//                });
//
//                // Set the alert dialog no button click listener
//                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Do something when No button clicked
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                // Display the alert dialog on interface
//                dialog.show();
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
