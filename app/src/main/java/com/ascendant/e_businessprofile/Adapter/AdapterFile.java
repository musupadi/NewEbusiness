package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class AdapterFile extends RecyclerView.Adapter<AdapterFile.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    String Tanggal;
    public AdapterFile(Context ctx, List<DataModel> mList,String Tanggal){
        this.ctx = ctx;
        this.mList = mList;
        this.Tanggal=Tanggal;
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
        holderData.Tanggal.setText(ascendant.MagicDateChange(Tanggal));
        holderData.Nama.setText(ascendant.SmallText(dm.getLink_file_mandiri_update()));
        holderData.ID.setText(String.valueOf(posistion+1));
        holderData.DocumentName.setText("JPG");
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ascendant.Download(ctx,"jpg",dm.getLink_file_mandiri_update(),dm.getLink_file_mandiri_update());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        LinearLayout card;
        TextView Tanggal,Nama,ID,DocumentName;

        public HolderData(View v) {
            super(v);
            card = v.findViewById(R.id.linearCard);
            Tanggal = v.findViewById(R.id.tvTanggal);
            Nama = v.findViewById(R.id.tvNama);
            ID = v.findViewById(R.id.tvId);
            DocumentName = v.findViewById(R.id.tvDocumentName);
        }
    }
}
