package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterListOfProbing extends RecyclerView.Adapter<AdapterListOfProbing.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Boolean ONCLICK=true;
    public AdapterListOfProbing(Context ctx, List<DataModel> mList){
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
        final DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.Judul.setText(ascendant.SmallText(dm.getJudul_probing()));
        if (dm.getFile_probing().equals("-")){
            holderData.web.loadData(dm.getIsi_probing(),"text/html","UTF-8");
            holderData.web.setVisibility(View.GONE);
            holderData.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ONCLICK){
                        holderData.image.setImageResource(R.drawable.chevron_up);
                        holderData.web.setVisibility(View.VISIBLE);
                        ONCLICK = false;
                    }else{
                        holderData.image.setImageResource(R.drawable.chevron_down);
                        holderData.web.setVisibility(View.GONE);
                        ONCLICK = true;
                    }
                }
            });
        }else{
            holderData.web.setVisibility(View.VISIBLE);
            holderData.image.setImageResource(R.drawable.pdf_file);
            holderData.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(ctx, ascendant.BASE_URL()+dm.getFile_probing(), Toast.LENGTH_SHORT).show();
                    ascendant.Download(ctx,"pdf",dm.getFile_probing(),dm.getJudul_probing());
                }
            });
        }
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
