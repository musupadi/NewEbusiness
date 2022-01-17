package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.PortraitWebViewEbookActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterEBook extends RecyclerView.Adapter<AdapterEBook.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Dialog myDialog;
    Button View,Download;
    public AdapterEBook(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_ebook,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();

        myDialog = new Dialog(ctx);
        myDialog.setContentView(R.layout.dialog_view_download);
        holderData.Tanggal.setText(ascendant.MagicDateChange(dm.getTgl_upload_business_refrence()));
        holderData.Nama.setText(ascendant.SmallText(dm.getNama_business_refrence()));
        holderData.ID.setText(String.valueOf(posistion+1));
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
            }
        });


        Download = myDialog.findViewById(R.id.btnDownload);
        View = myDialog.findViewById(R.id.btnView);
        Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                ascendant.Download(ctx,dm.getExt_file(),dm.getLink_file_ebook(),dm.getNama_ebook());
            }
        });
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if (dm.getLink_ebook().equals("") || dm.getLink_ebook().isEmpty()){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ascendant.BASE_URL()+dm.getLink_file_ebook()));
                    ctx.startActivity(browserIntent);
                }else{
                    if (dm.getMode_ebook().equals("P")){
                        Intent i = new Intent(ctx, PortraitWebViewEbookActivity.class);
                        i.putExtra("LINK", dm.getLink_ebook());
                        ctx.startActivity(i);
                    }else{
                        Intent i = new Intent(ctx, LandscapeWebViewEbookActivity.class);
                        i.putExtra("LINK", dm.getLink_ebook());
                        ctx.startActivity(i);
                    }
                }
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
