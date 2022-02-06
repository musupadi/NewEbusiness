package com.ascendant.e_businessprofile.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterRegulationFMCG extends RecyclerView.Adapter<AdapterRegulationFMCG.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant method = new Ascendant();
    Dialog myDialog;
    public AdapterRegulationFMCG (Context ctx,List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_newsletter,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRegulationFMCG.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        holderData.judul.setText(dm.getNama_peraturan());
        holderData.judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getLink_file_regulation()));
                ctx.startActivity(browserIntent);
            }
        });
        holderData.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Download File ?")
                        .setCancelable(false)
                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                method.DownloadPDF(dm.getLink_file_regulation(),dm.getNama_peraturan(),ctx);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        //Set your icon here
                        .setTitle("Perhatian !!!")
                        .setIcon(R.drawable.ic_action_print);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView judul;
        ImageView download,icon;
        HolderData(View v){
            super(v);
            judul = v.findViewById(R.id.tvJudul);
            download = v.findViewById(R.id.ivDownloadBook);
            icon = v.findViewById(R.id.ivIcon);
        }
    }
}
