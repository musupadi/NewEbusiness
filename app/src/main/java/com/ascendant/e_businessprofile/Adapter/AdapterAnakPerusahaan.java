package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.AnakPerusahaan;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterAnakPerusahaan extends RecyclerView.Adapter<AdapterAnakPerusahaan.HolderData> implements Filterable {
    private List<AnakPerusahaan> mList;
    private List<AnakPerusahaan> mListFull;
    private Context ctx;
    String User, Person, Id, Level;

    public AdapterAnakPerusahaan(Context ctx, List<AnakPerusahaan> mList) {
        this.ctx = ctx;
        this.mList = mList;
        mListFull = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public AdapterAnakPerusahaan.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_anak_perusahaan, viewGroup, false);
        AdapterAnakPerusahaan.HolderData holder = new AdapterAnakPerusahaan.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAnakPerusahaan.HolderData holderData, int posistion) {
        final AnakPerusahaan dm = mList.get(posistion);
        holderData.nama.setText(dm.nama_anak_perusahaan);
        holderData.jenis.setText(dm.jenis_anak_perusahaan);
        holderData.asset.setText(dm.asset_total_perusahaan);
        holderData.presentase.setText(dm.persentase_total_perusahaan);
        holderData.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView nama,jenis,asset,presentase;
        AnakPerusahaan dm;
        LinearLayout list;

        HolderData(View v) {
            super(v);
            nama = v.findViewById(R.id.tvNama);
            list = v.findViewById(R.id.linearList);
            jenis = v.findViewById(R.id.tvJenis);
            presentase = v.findViewById(R.id.tvPresentase);
            asset = v.findViewById(R.id.tvAsset);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<AnakPerusahaan> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mListFull);
            } else {
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (AnakPerusahaan dm : mListFull) {
                    if (dm.nama_anak_perusahaan.toLowerCase().contains(fillterPattern)) {
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
            mList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}



