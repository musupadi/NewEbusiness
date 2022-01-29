package com.ascendant.e_businessprofile.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.API.ApiRequest;
import com.ascendant.e_businessprofile.API.RetroServer;
import com.ascendant.e_businessprofile.Activity.NewsActivity;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Method.Ascendant;
import com.ascendant.e_businessprofile.Method.NumberTextWatcher;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.ResponseArrayObject;
import com.ascendant.e_businessprofile.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDaftarHadiah extends RecyclerView.Adapter<AdapterDaftarHadiah.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Ascendant ascendant;
    String POIN;
    String Token,NotifID;
    DB_Helper dbHelper;
    public AdapterDaftarHadiah(Context ctx, List<DataModel> mList,String POIN){
        this.ctx = ctx;
        this.mList = mList;
        this.POIN = POIN;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_hadiah,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderData holderData, int posistion) {
        DataModel dm = mList.get(posistion);
        ascendant = new Ascendant();
        dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Token = cursor.getString(0);
                NotifID = cursor.getString(1);
            }
        }
        holderData.id.setText(String.valueOf(posistion+1));
        holderData.nama.setText(dm.getNama_hadiah());
        holderData.poin.setText(ascendant.RPEraser(ascendant.MagicRP(Double.parseDouble(dm.getPoin_dibutuhkan()))));
        holderData.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(POIN)<Integer.parseInt(dm.getPoin_dibutuhkan())){
                    Toast.makeText(ctx, "Poin Anda Belum Cukup", Toast.LENGTH_SHORT).show();
                }else{
                    tukar(dm.getId_poin_hadiah());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id,nama,poin;
        LinearLayout card;
        HolderData(View v){
            super(v);
            id = v.findViewById(R.id.tvID);
            nama = v.findViewById(R.id.tvNama);
            poin = v.findViewById(R.id.tvPoin);
            card = v.findViewById(R.id.linearHadiah);
        }
    }
    private void tukar(String id){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        final Call<ResponseArrayObject> data =api.PenukaranHadiah(Token,id);
        data.enqueue(new Callback<ResponseArrayObject>() {
            @Override
            public void onResponse(Call<ResponseArrayObject> call, Response<ResponseArrayObject> response) {
                try {
                    Toast.makeText(ctx, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(ctx, "Terjadi Kesalahan pada : "+e.toString(), Toast.LENGTH_SHORT).show();
                }
                    
            }

            @Override
            public void onFailure(Call<ResponseArrayObject> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
