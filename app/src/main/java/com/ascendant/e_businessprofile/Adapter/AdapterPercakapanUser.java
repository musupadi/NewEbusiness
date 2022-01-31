package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.ui.Forum.DetailForumActivity;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterPercakapanUser extends RecyclerView.Adapter<AdapterPercakapanUser.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    Boolean expert;
    public AdapterPercakapanUser(Context ctx, List<DataModel> mList,Boolean expert){
        this.ctx = ctx;
        this.mList = mList;
        this.expert = expert;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_forum,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        holderData.Tanggal.setText(ascendant.MagicDateChange(dm.getCreated_at()));
        holderData.Nama.setText(ascendant.SmallText(dm.getTopik_pertanyaan()));
        holderData.Username.setText(dm.getNama_user());
        holderData.Isi.setVisibility(View.GONE);
        holderData.Isi.setText("Total Belasan : "+dm.getTotal_balasan());
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent goInput = new Intent(ctx, DetailForumActivity.class);
//                goInput.putExtra("ID",dm.getId_post());
//                goInput.putExtra("CATEGORY",dm.getKategori_post());
//                goInput.putExtra("JUDUL",dm.getJudul_post());
//                goInput.putExtra("REPLY_NAME","");
//                goInput.putExtra("REPLY","");
//                goInput.putExtra("EDIT","NO");
//                goInput.putExtra("ISI_KOMEN","");
//                ctx.startActivities(new Intent[]{goInput});
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout card;
        TextView Tanggal,Nama,Username,Isi;

        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            Tanggal = v.findViewById(R.id.tvTanggal);
            Nama = v.findViewById(R.id.tvNama);
            Username = v.findViewById(R.id.tvNamaUser);
            Isi = v.findViewById(R.id.tvIsi);
        }
    }
}
