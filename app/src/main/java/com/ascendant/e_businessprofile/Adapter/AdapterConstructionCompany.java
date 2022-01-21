package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterConstructionCompany extends RecyclerView.Adapter<AdapterConstructionCompany.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    public AdapterConstructionCompany(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_perusahaan_contractor,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.id.setText(String.valueOf(posistion+1));
        holderData.nama.setText(dm.getNama_perusahaan());
        holderData.jenis.setText("Kind : "+dm.getJenis());
        holderData.kelompok.setText("Group : "+dm.getKelompok());
        holderData.sektor.setText("Sector : "+dm.getSektor());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id,nama,kelompok,jenis,sektor;
        HolderData(View v){
            super(v);
            id = v.findViewById(R.id.tvID);
            nama = v.findViewById(R.id.tvNama);
            kelompok = v.findViewById(R.id.tvKelompok);
            jenis = v.findViewById(R.id.tvJenis);
            sektor = v.findViewById(R.id.tvSektor);
        }
    }
}
