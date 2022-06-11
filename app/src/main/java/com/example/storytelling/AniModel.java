package com.example.storytelling;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

import java.io.Serializable;
import java.util.ArrayList;

public class AniModel implements Serializable {
    Integer  id;
    String  baslik;
    String  tarih;
    String  lokasyon;
    String  yazi;
    String  mod;

    public AniModel(Integer id, String baslik, String tarih, String lokasyon, String yazi, String mod){
        this.id = id;
        this.baslik = baslik;
        this.lokasyon = lokasyon;
        this.tarih = tarih;
        this.yazi = yazi;
        this.mod = mod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public String getYazi() {
        return yazi;
    }

    public void setYazi(String yazi) {
        this.yazi = yazi;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(String lokasyon) {
        this.lokasyon = lokasyon;
    }

    /*static public ArrayList<AniModel> getData(Context context){
        ArrayList<AniModel> aniList = new ArrayList<>();
        ArrayList<String> baslikList = new ArrayList<>();
        ArrayList<String> tarihList = new ArrayList<>();
        ArrayList<String> lokasyonList = new ArrayList<>();
        ArrayList<String> modList = new ArrayList<>();
        try{
            SQLiteDatabase database = context.openOrCreateDatabase("StoryTellerDB", Context.MODE_PRIVATE, null);

        }catch(Exception e){
            e.printStackTrace();
        }

    }*/
}
