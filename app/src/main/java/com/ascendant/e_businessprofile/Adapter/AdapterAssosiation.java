package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.OldRetroServer;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.Ecosystem.Association.DetailAssosiationActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAssosiation extends RecyclerView.Adapter<AdapterAssosiation.HolderData> implements Filterable {
    private List<DataModel> mList;
    private List<DataModel> mListFull;
    private Context ctx;
    String User, Person, Id, Level;
    Ascendant method = new Ascendant();

    public AdapterAssosiation(Context ctx, List<DataModel> mList) {
        this.ctx = ctx;
        this.mList = mList;
        mListFull = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public AdapterAssosiation.HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_association, viewGroup, false);
        AdapterAssosiation.HolderData holder = new AdapterAssosiation.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAssosiation.HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        holderData.id.setText(dm.getId_asosiasi());
        holderData.nama.setText(dm.getNama());
        holderData.alamat.setText(dm.getAlamat());
        holderData.telepon.setText("Telp : "+dm.getTelepon());
        holderData.email.setText("Email : "+dm.getEmail());
        holderData.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detail(dm.getId_asosiasi());
            }
        });
        holderData.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder {
        TextView id,nama,alamat,telepon,email;
        DataModel dm;
        LinearLayout linearAssosiation;
        Button detail;
        HolderData(View v) {
            super(v);
            id = v.findViewById(R.id.tvID);
            nama = v.findViewById(R.id.tvNamaAsosiasi);
            alamat = v.findViewById(R.id.tvAlamat);
            telepon = v.findViewById(R.id.tvTelpon);
            email = v.findViewById(R.id.tvEmail);
            linearAssosiation = v.findViewById(R.id.linearAssosiation);
            detail = v.findViewById(R.id.btnDetail);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mListFull);
            } else {
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (DataModel dm : mListFull) {
                    if (dm.getNama().toLowerCase().contains(fillterPattern)) {
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

    private void Detail(final String id){
        ApiRequest api = OldRetroServer.getClient().create(ApiRequest.class);
        Call<DataModel> assosiation = api.DetailAssosiationFMCG(method.AUTH(),
                "FABAJakartaIndonesia2019kunci",
                id);
        assosiation.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                Intent goInput = new Intent(ctx, DetailAssosiationActivity.class);
                goInput.putExtra("ID",id);
                goInput.putExtra("PENGURUS",response.body().getPengurus());
                goInput.putExtra("ANGGOTA",response.body().getAnggota());
                ctx.startActivity(goInput);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
    }
}




