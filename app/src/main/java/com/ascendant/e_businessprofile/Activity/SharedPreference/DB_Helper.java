package com.ascendant.e_businessprofile.Activity.SharedPreference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ascendant.e_businessprofile.Model.DataModel;
import com.ascendant.e_businessprofile.Model.StaticModel.AnalisisInput;
import com.ascendant.e_businessprofile.Model.StaticModel.Quis;
import com.ascendant.e_businessprofile.Model.StaticModel.models_fivec;

import java.util.LinkedList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ebusinessprofile.db";
    private static final int DATABASE_VERSION = 3;


    //Account
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    //Account
    public static final String TABLE_NAME_ACCOUNT = "account";
    public static final String COLUMN_TOKEN = "token";
    public static final String NOTIF_ID = "notif_id";

    //Analisis
    public static final String TABLE_ANALISIS = "tableanalis";
    public static final String COLUMN_KE = "kolumke";
    public static final String COLUMN_ROI = "roi";
    public static final String COLUMN_RATIO_KAS = "rasiokas";
    public static final String COLUMN_CURRENT_RATIO = "currentratio";
    public static final String COLUMN_CP = "cp";
    public static final String COLUMN_PP = "pp";
    public static final String COLUMN_TATO = "tato";
    public static final String COLUMN_AKTIVA_BERSIH = "aktivabersih";
    //Quiz
    public static final String TABLE_QUIZ = "quis";
    public static final String COLUMN_KATEGORI = "kategori";
    public static final String COLUMN_SKOR = "skor";
    //Notification
    public static final String TABLE_NOTIFICATION = "notification";
    public static final String COLUMN_NOTIF_ACTIVE = "active";

    //Notification Click
    public static final String TABLE_CLICK_NOTIFICATION = "clicknotif";
    public static final String COLUMN_CLICK = "click";

    //Analisis 5 C
    public static final String TABLE_5C = "fivec";
    public static final String COLUMN_ID_FIVE_C = "idfivec";
    public static final String COLUMN_JAWABAN = "jawabanfivec";
    public static final String COLUMN_SCORE_FIVE_C = "scorefivec";

    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_ACCOUNT+" (" +
                COLUMN_TOKEN+" TEXT NOT NULL," +
                NOTIF_ID+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_ANALISIS+" (" +
                COLUMN_KE+" TEXT NOT NULL, "+
                COLUMN_ROI+" TEXT NOT NULL, "+
                COLUMN_RATIO_KAS+" TEXT NOT NULL, "+
                COLUMN_CURRENT_RATIO+" TEXT NOT NULL, "+
                COLUMN_CP+" TEXT NOT NULL, "+
                COLUMN_PP+" TEXT NOT NULL, "+
                COLUMN_TATO+" TEXT NOT NULL, "+
                COLUMN_AKTIVA_BERSIH+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_QUIZ+" (" +
                COLUMN_KATEGORI+" TEXT NOT NULL, "+
                COLUMN_SKOR+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_NOTIFICATION+" (" +
                COLUMN_NOTIF_ACTIVE+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_5C+" (" +
                COLUMN_ID_FIVE_C+" TEXT PRIMARY KEY, "+
                COLUMN_JAWABAN+" TEXT NOT NULL, "+
                COLUMN_SCORE_FIVE_C+" TEXT NOT NULL);"
        );
        db.execSQL("CREATE TABLE "+TABLE_CLICK_NOTIFICATION+" (" +
                COLUMN_CLICK+" TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANALISIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_5C);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLICK_NOTIFICATION);
        this.onCreate(db);
    }

    public void saveClick(String click){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLICK, click);
        db.insert(TABLE_CLICK_NOTIFICATION,null,values);
        db.close();
    }

    public void resetClick(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_CLICK_NOTIFICATION);
    }
    public void saveScore(Quis quis){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KATEGORI, quis.getKategori());
        values.put(COLUMN_SKOR, quis.getSkor());
        db.insert(TABLE_QUIZ,null,values);
        db.close();
    }
    public Cursor getClick(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_CLICK_NOTIFICATION;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public void saveFiveC(models_fivec model){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_FIVE_C, model.getIdfivec());
        values.put(COLUMN_JAWABAN, model.getJawabanfivec());
        values.put(COLUMN_SCORE_FIVE_C, model.getScorefivec());
        db.insert(TABLE_5C,null,values);
        db.close();
    }

    public void resetFiveC(String id,Context ctx){
        if (id.equals("0")){
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM "+TABLE_5C);
        }else{
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM "+TABLE_5C+" WHERE "+COLUMN_ID_FIVE_C+" = "+id);
        }
    }
    public void updateFiveC(String id,String jawaban){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_5C+" SET "+COLUMN_JAWABAN+" = "+jawaban+" WHERE "+COLUMN_ID_FIVE_C+" = "+id);
    }
    public void deleteUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_ACCOUNT+" WHERE "+COLUMN_EMAIL+" = '"+username+"'");
    }


    public void SaveUser(String token,String notif){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOKEN, token);
        values.put(NOTIF_ID,notif);
        db.insert(TABLE_NAME_ACCOUNT,null,values);
        db.close();
    }
    //CHECKER
    public Cursor checkUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ACCOUNT;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;

    }
    //delete
    public void Logout(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME_ACCOUNT+"");
    }

    public Cursor Checker(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_5C+" WHERE "+COLUMN_ID_FIVE_C+" = "+id;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void saveNotif(String notif){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTIF_ACTIVE,notif);
        db.insert(TABLE_NOTIFICATION,null,values);
        db.close();
    }
    public Cursor checkNotif(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NOTIFICATION+"";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public void deleteNotif(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NOTIFICATION+"");
    }


    public Cursor checkQuiz(String character){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_QUIZ+" WHERE "+COLUMN_KATEGORI+" = '"+character+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor checkAnalis(String kolum){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_ANALISIS+" WHERE "+COLUMN_KE+" = '"+kolum+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void InputData(AnalisisInput analisisInput){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_KE, analisisInput.getKolumKe());
        values.put(COLUMN_ROI,analisisInput.getROI());
        values.put(COLUMN_RATIO_KAS,analisisInput.getCashRatio());
        values.put(COLUMN_CURRENT_RATIO,analisisInput.getCurrentRatio());
        values.put(COLUMN_CP,analisisInput.getColectivePeriod());
        values.put(COLUMN_PP,analisisInput.getPP());
        values.put(COLUMN_TATO,analisisInput.getTATO());
        values.put(COLUMN_AKTIVA_BERSIH,analisisInput.getAktivaBersih());

        db.insert(TABLE_ANALISIS,null,values);
        db.close();
    }

    public void Hitung(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_ANALISIS+"");
    }
    public void FiveC(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_QUIZ+"");
    }

    public Cursor Available(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT COUNT(*) AS "+COLUMN_ID_FIVE_C+" FROM "+TABLE_5C;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor SUMFiveC(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT SUM("+COLUMN_SCORE_FIVE_C+") FAJARQONTOL FROM "+TABLE_5C;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
