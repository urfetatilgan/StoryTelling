package com.example.storytelling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnaEkran extends AppCompatActivity {
    private String id, sifre;
    private Button butonCikis,butonAni,butonSifreDegis;
    private EditText yeniId,yeniSifre,eskiId,eskiSifre;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);
        yeniId = (EditText) findViewById(R.id.editTextYeniId);
        yeniSifre = (EditText) findViewById(R.id.editTextYeniSifre);
        eskiId = (EditText) findViewById(R.id.editTextEskiId);
        eskiSifre = (EditText) findViewById(R.id.editTextEskiSifre);
        butonSifreDegis = (Button) findViewById(R.id.buttonSifreDegis);
        butonCikis = (Button) findViewById(R.id.buttonCikis);
        butonAni = (Button) findViewById(R.id.buttonAni);
        sp = getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor =sp.edit();

        butonSifreDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // id = sp.getString("id", "kullanıcı adı yok");
                //sifre = sp.getString("sifre", "sifre yok");
                if(eskiId.getText().toString().equals(sp.getString("id","x")) && eskiSifre.getText().toString().equals(sp.getString("sifre","y"))){
                    editor.putString("id",yeniId.getText().toString());
                    editor.putString("sifre",yeniSifre.getText().toString());
                    editor.commit();
                    Toast.makeText(AnaEkran.this, "Şifreniz başarıyla değiştirildi!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AnaEkran.this, MainActivity.class));
                }else{
                    Toast.makeText(AnaEkran.this,"Eski id'nizi veya eski şifrenizi hatalı girdiniz!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        butonCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnaEkran.this, "Çıkış yapıldı!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnaEkran.this, MainActivity.class));
            }
        });

        butonAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnaEkran.this,"Anılar Gösteriliyor!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnaEkran.this, AniList.class));
            }
        });


    }
}