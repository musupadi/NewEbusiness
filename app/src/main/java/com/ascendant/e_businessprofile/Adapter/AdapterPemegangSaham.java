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

import com.ascendant.e_businessprofile.Model.StaticModel.FMCG.Perusahaan.PemegangSaham;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPemegangSaham extends RecyclerView.Adapter<AdapterPemegangSaham.HolderData> implements Filterable {
    private List<PemegangSaham> mList;
    private List<PemegangSaham> mListFull;
    private Context ctx;
    String User, Person, Id, Level;

    public AdapterPemegangSaham(Context ctx, List<PemegangSaham> mList) {
        this.ctx = ctx;
        this.mList = mList;
        mListFull = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public AdapterPemegangSaham.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_pemegang_saham, viewGroup, false);
        AdapterPemegangSaham.HolderData holder = new AdapterPemegangSaham.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPemegangSaham.HolderData holderData, int posistion) {
        final PemegangSaham dm = mList.get(posistion);
        holderData.pemegangsaham.setText(dm.nama_pemegang_saham);
        holderData.jumlahsaham.setText(dm.jumlah_saham);
        holderData.presentase.setText(dm.persentase);
        holderData.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView pemegangsaham,jumlahsaham,presentase;
        PemegangSaham dm;
        LinearLayout list;

        HolderData(View v) {
            super(v);
            pemegangsaham = v.findViewById(R.id.tvPemegangSaham);
            list = v.findViewById(R.id.linearList);
            jumlahsaham = v.findViewById(R.id.tvJumlahSaham);
            presentase = v.findViewById(R.id.tvPresentase);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PemegangSaham> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mListFull);
            } else {
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (PemegangSaham dm : mListFull) {
                    if (dm.nama_pemegang_saham.toLowerCase().contains(fillterPattern)) {
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
