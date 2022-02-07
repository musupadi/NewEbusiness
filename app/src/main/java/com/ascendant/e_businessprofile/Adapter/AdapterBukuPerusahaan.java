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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.HomeActivity;
import com.ascendant.e_businessprofile.Activity.LandscapeWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.LoginActivity;
import com.ascendant.e_businessprofile.Activity.MainActivity;
import com.ascendant.e_businessprofile.Activity.PortraitWebViewEbookActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterBukuPerusahaan extends RecyclerView.Adapter<AdapterBukuPerusahaan.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Boolean ONCLICK=true;
    Dialog myDialog;
    Button View,Download;
    public AdapterBukuPerusahaan(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_list_of_probing,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        myDialog = new Dialog(ctx);
        myDialog.setContentView(R.layout.dialog_view_download);
        holderData.Judul.setText(ascendant.SmallText(dm.getNama_provinsi()));
        holderData.web.setVisibility(View.VISIBLE);
        holderData.image.setImageResource(R.drawable.pdf_file);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
                Download = myDialog.findViewById(R.id.btnDownload);
                View = myDialog.findViewById(R.id.btnView);

                Download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        ascendant.Download(ctx,"pdf",ascendant.BASE_URL()+dm.getLink_konstruksi_2021(),"Buku Perusahaan "+dm.getNama_provinsi());
                    }
                });
                View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        try {
                            if (dm.getLink_ebook().equals("") || dm.getLink_ebook().isEmpty()){
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ascendant.BASE_URL()+dm.getLink_konstruksi_2021()));
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
                        }catch (Exception e){
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ascendant.BASE_URL()+dm.getLink_konstruksi_2021()));
                            ctx.startActivity(browserIntent);
                        }

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout card,scroll;
        TextView Judul;
        ImageView image;
        WebView web;
        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            scroll = v.findViewById(R.id.ivScroll);
            Judul = v.findViewById(R.id.tvJudul);
            image = v.findViewById(R.id.ivImage);
            web = v.findViewById(R.id.web);
        }
    }
}
