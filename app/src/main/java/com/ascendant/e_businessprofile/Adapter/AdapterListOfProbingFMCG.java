package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListOfProbingFMCG extends RecyclerView.Adapter<AdapterListOfProbingFMCG.HolderData> implements Filterable {
    private List<DataModel> mList;
    private List<DataModel> mListFull;
    private Context ctx;
    Boolean clicked = false;
    String User,Person,Id,Level;
    Ascendant method = new Ascendant();
    public AdapterListOfProbingFMCG(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
        mListFull = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_probing,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterListOfProbingFMCG.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        holderData.judul.setText(dm.getJudul_probing());
        if (dm.getFile_probing().equals("-")||dm.getFile_probing().equals("")){
            holderData.lihat.setVisibility(View.GONE);
            holderData.image.setVisibility(View.VISIBLE);
            holderData.image.setImageResource(R.drawable.chevron_down);
        }else{
            holderData.lihat.setVisibility(View.VISIBLE);
            holderData.image.setVisibility(View.GONE);
        }
        holderData.web.loadData(dm.getIsi_probing(),"text/html","UTF-8");
        holderData.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dm.getFile_probing().equals("-") || dm.getFile_probing().equals("")){
                    if (clicked){
                        holderData.image.setImageResource(R.drawable.chevron_down);
                        holderData.web.setVisibility(View.GONE);
                        clicked=false;
                    }else{
                        holderData.image.setImageResource(R.drawable.chevron_up);
                        holderData.web.setVisibility(View.VISIBLE);
                        clicked=true;
                    }
                }else{
                    holderData.web.setVisibility(View.GONE);
                }
            }
        });
        holderData.lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holderData.web.setVisibility(View.GONE);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dm.getFile_probing()));
                ctx.startActivity(browserIntent);
            }
        });
        holderData.dm=dm;

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        DataModel dm;
        TextView judul;
        ImageView image;
        WebView web;
        LinearLayout list;
        Button lihat;
        HolderData(View v){
            super(v);
            judul = v.findViewById(R.id.tvJudul);
            image = v.findViewById(R.id.ivIcon);
            web = v.findViewById(R.id.webProbing);
            list = v.findViewById(R.id.LinearList);
            lihat = v.findViewById(R.id.btnLihat);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mListFull);
            }else{
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (DataModel dm : mListFull){
                    if (dm.getJudul_probing().toLowerCase().contains(fillterPattern)){
                        filteredList.add(dm);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}
