package com.ascendant.e_businessprofile.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ascendant.e_businessprofile.Activity.Method.Ascendant;
import com.ascendant.e_businessprofile.Activity.SharedPreference.DB_Helper;
import com.ascendant.e_businessprofile.Activity.ui.FMCG.CreditDecisionTool.FiveC.FMCGFiveCActivity;
import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.models_fivec;
import com.ascendant.e_businessprofile.R;

import java.util.List;

public class Adapter5C extends RecyclerView.Adapter<Adapter5C.HolderData> {
    private List<DataModel> mList;
    private Context ctx;
    Dialog myDialog;
    String idfivec = "NOPE";
    String jawabanfivec = "NOPE";
    String scorefivec = "NOPE";
    boolean isOnClickYes = true;
    boolean isOnClickNo = true;
    Integer Total;
    Ascendant method = new Ascendant();
    public Adapter5C (Context ctx,List<DataModel> mList,Integer Total){
        this.ctx = ctx;
        this.mList = mList;
        this.Total = Total;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_5c,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter5C.HolderData holderData, int posistion) {
        final DataModel dm = mList.get(posistion);
        holderData.nomor.setText(String.valueOf(posistion+1)+".");
        holderData.soal.setText(dm.getIsi_fivec());
        final DB_Helper dbHelper = new DB_Helper(ctx);
        Cursor cursor = dbHelper.Checker(dm.getId_fmcg_fivec());
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                idfivec = cursor.getString(0);
                jawabanfivec = cursor.getString(1);
                scorefivec = cursor.getString(2);
            }
        }
        if (jawabanfivec.equals("YES")){
            holderData.yes.setBackgroundColor(Color.GREEN);
            holderData.no.setBackgroundColor(Color.BLUE);
        }else if(jawabanfivec.equals("NO")){
            holderData.yes.setBackgroundColor(Color.BLUE);
            holderData.no.setBackgroundColor(Color.GREEN);
        }else{
            holderData.yes.setBackgroundColor(Color.BLUE);
            holderData.no.setBackgroundColor(Color.BLUE);
        }
        holderData.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.resetFiveC(dm.getId_fmcg_fivec(),ctx);
                models_fivec user = new models_fivec();
                isOnClickYes=true;
                if (isOnClickNo){
                    isOnClickNo=false;
                    holderData.no.setBackgroundColor(Color.BLUE);
                    holderData.yes.setBackgroundColor(Color.GREEN);
                    if (dm.getNilai_no().equals("0")){
                        user = new models_fivec(dm.getId_fmcg_fivec(),"NO","0");
                    }else{
                        user = new models_fivec(dm.getId_fmcg_fivec(),"NO","2");
                    }
                    dbHelper.saveFiveC(user);
                }else{
                    if (dm.getNilai_yes().equals("0")){
                        user = new models_fivec(dm.getId_fmcg_fivec(),"YES","0");
                    }else{
                        user = new models_fivec(dm.getId_fmcg_fivec(),"YES","2");
                    }
                    isOnClickYes=true;
                    holderData.yes.setBackgroundColor(Color.GREEN);
                    dbHelper.saveFiveC(user);
                }
//                }
                Logic(dbHelper,dm.getKategori_fivec(),dm.getPernyataan_fivec());
            }
        });
        holderData.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isOnClickYes){
//                    isOnClickYes=false;
//                    holderData.yes.setBackgroundColor(Color.BLUE);
//                    holderData.no.setBackgroundColor(Color.BLUE);
//                    dbHelper.resetFiveC(dm.getId_fmcg_fivec(),ctx);
//                }else{
                dbHelper.resetFiveC(dm.getId_fmcg_fivec(),ctx);
                models_fivec user = new models_fivec();
                isOnClickNo=true;
                if (isOnClickYes){
                    isOnClickYes=false;
                    holderData.yes.setBackgroundColor(Color.BLUE);
                    holderData.no.setBackgroundColor(Color.GREEN);
                    if (dm.getNilai_yes().equals("0")){
                        user = new models_fivec(dm.getId_fmcg_fivec(),"YES","0");
                    }else{
                        user = new models_fivec(dm.getId_fmcg_fivec(),"YES","2");
                    }
                    dbHelper.saveFiveC(user);
                }else{
                    if (dm.getNilai_no().equals("0")){
                        user = new models_fivec(dm.getId_fmcg_fivec(),"NO","0");
                    }else{
                        user = new models_fivec(dm.getId_fmcg_fivec(),"NO","2");
                    }

                    isOnClickYes=true;
                    holderData.no.setBackgroundColor(Color.GREEN);
                }
                dbHelper.saveFiveC(user);
                if (dm.getKategori_fivec() == null){
                    Logic(dbHelper,"hospital",dm.getPernyataan_fivec());
                }else{
                    Logic(dbHelper,dm.getKategori_fivec(),dm.getPernyataan_fivec());
                }

//                }
            }
        });
    }

    private void Logic(DB_Helper dbHelper,String kategorifivec,String pernyataan){
        Cursor cursor3 = dbHelper.Available();
        String test="0";
        if (cursor3.getCount()>0){
            while (cursor3.moveToNext()){
                test = cursor3.getString(0);
            }
        }

        if (!test.equals("0")){
            Cursor cursor4 = dbHelper.SUMFiveC();
            String sum="0";
            if (cursor4.getCount()>0){
                while (cursor4.moveToNext()){
                    sum = cursor4.getString(0);
                }
            }
            if (test.equals(String.valueOf(Total))){
                if (kategorifivec.equals("hospital")){
                    Intent goInput = new Intent(ctx, FMCGFiveCActivity.class);
                    method.LOGICHOSPITAL5C(ctx,pernyataan.toUpperCase(),Integer.parseInt(sum));
                    goInput.putExtra("KATEGORI",kategorifivec.toUpperCase());
                    ctx.startActivity(goInput);
                }else{
                    Intent goInput = new Intent(ctx, FMCGFiveCActivity.class);
                    method.LOGICFMCG5C(ctx,kategorifivec.toUpperCase(),pernyataan.toUpperCase(),Integer.parseInt(sum));
                    goInput.putExtra("KATEGORI",kategorifivec.toUpperCase());
                    ctx.startActivity(goInput);
                }

            }
        }
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nomor,soal;
        Button yes,no;
        HolderData(View v){
            super(v);
            yes = v.findViewById(R.id.btnYes);
            no = v.findViewById(R.id.btnNo);
            nomor = v.findViewById(R.id.tvNomor);
            soal = v.findViewById(R.id.tvSoal);
        }
    }
}



