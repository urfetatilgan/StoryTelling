package com.example.storytelling;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "StoryTellerDB";
    private static SQLiteDatabase database;
    private static DBHelper instance;

    public static DBHelper getInstance( Context context){
        if(instance==null){
            instance = new DBHelper(  context);
        }
        return instance;
    }

    public DBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "Anılar(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "aniIsmi varchar(20), " +
                "aniTarih DATE, " +
                "aniLokasyon varchar(20), " +
                "aniYazi varchar(240), " +
                "aniMod varChar(10)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean aniSil(int id){
        try{
            database.execSQL("DELETE FROM Anılar WHERE id="+id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean aniGuncelle(AniModel ani) {
        try{
            database.execSQL("UPDATE Anılar SET aniIsmi='"+ani.baslik+"',aniTarih='"+ani.tarih+"',aniLokasyon='"+ani.lokasyon+"',aniYazi='"+ani.yazi+"',aniMod='"+ani.mod+"' WHERE id="+ani.id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean aniEkle(AniModel ani){
        try {
            database.execSQL("INSERT INTO Anılar(aniIsmi, aniTarih, aniLokasyon, aniYazi, aniMod) VALUES ('"+ ani.baslik +"','"+ ani.tarih +"','"+ ani.lokasyon +"','"+ ani.yazi +"','"+ ani.mod +"')");
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public ArrayList<AniModel> listeAl(){
        try {
            Cursor cursor = database.query("Anılar", new String[]{"id","aniIsmi", "aniTarih", "aniLokasyon","aniYazi", "aniMod"},null,null,null,null,"aniTarih");
            ArrayList<AniModel> result = new ArrayList<>();
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String baslik = cursor.getString(1);
                String tarih = cursor.getString(2);
                String lokasyon = cursor.getString(3);
                String yazi = cursor.getString(4);
                String mod = cursor.getString(5);
               AniModel ani = new  AniModel( id, baslik, tarih, lokasyon, yazi, mod);
               result.add(ani);
            }
            return result;
        }catch(Exception e){
            return null;
        }
    }

}
