package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance.DetailComplianceActivity;
import com.ascendant.e_businessprofile.Activity.ui.Mining.MandiriUpdate.DetailMandiriUpdate;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterMandiriUpdate extends RecyclerView.Adapter<AdapterMandiriUpdate.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    public AdapterMandiriUpdate(Context ctx, List<DataModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_compliance,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.Tanggal.setText(ascendant.MagicDateChange(dm.getCreated_at()));
        holderData.Nama.setText(ascendant.SmallText(dm.getJudul_mandiri_update()));
        Glide.with(ctx)
                .load(ascendant.BASE_URL()+dm.getCover_mandiri_update())
                .into(holderData.Gambar);
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx, DetailMandiriUpdate.class);
                goInput.putExtra("ID",dm.getId_mandiri_update());
                ctx.startActivity(goInput);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout card;
        TextView Tanggal,Nama;
        ImageView Gambar;

        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            Tanggal = v.findViewById(R.id.tvTanggal);
            Nama = v.findViewById(R.id.tvNama);
            Gambar = v.findViewById(R.id.ivImage);
        }
    }
}
