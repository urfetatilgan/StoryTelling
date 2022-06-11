package com.example.storytelling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AniEkran extends AppCompatActivity implements View.OnClickListener {
    private EditText aniBaslik, aniYazi;
    private TextView lokasyon, tarih;
    private CheckBox mod1,mod2,mod3,mod4;
    private Button resimEkle, paylas, pdfYap, kaydet, sil;
    private boolean update=false;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani_ekran);

        aniBaslik = (EditText) findViewById(R.id.textViewAniBaslik);
        aniYazi = (EditText) findViewById(R.id.AniYazim);
        lokasyon = (TextView) findViewById(R.id.textViewAniLokasyon);
        tarih = (TextView) findViewById(R.id.textViewAniTarih);
        mod1 = (CheckBox) findViewById(R.id.checkBoxMod1);
        mod2 = (CheckBox) findViewById(R.id.checkBoxMod2);
        mod3 = (CheckBox) findViewById(R.id.checkBoxMod3);
        mod4 = (CheckBox) findViewById(R.id.checkBoxMod4);
        resimEkle = (Button) findViewById(R.id.buttonResimEkle);
        paylas = (Button) findViewById(R.id.buttonPaylas);
        pdfYap = (Button) findViewById(R.id.buttonPDF);
        kaydet = (Button) findViewById(R.id.buttonKaydet);
        sil = (Button) findViewById(R.id.buttonSil);

        kaydet.setOnClickListener(this);
        sil.setOnClickListener(this);
        Intent intent = getIntent();

        AniModel model = (AniModel) intent.getSerializableExtra("ani");

        if (model !=null){
            update = true;
            id = model.id;
            aniBaslik.setText(model.baslik);
            aniYazi.setText((model.yazi));
            tarih.setText(model.tarih);
            lokasyon.setText(model.lokasyon);
            if(model.getMod().equals(mod1.getText().toString())){
                mod1.setChecked(true);
            }else if(model.getMod().equals(mod2.getText().toString())){
                mod2.setChecked(true);
            }else if(model.getMod().equals(mod3.getText().toString())){
                mod3.setChecked(true);
            }else if(model.getMod().equals(mod4.getText().toString())){
                mod4.setChecked(true);
            }
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonKaydet: {

                String mod;
                String baslik;
                String tarih;
                String lokasyon;
                String yazi;
                int i = 1;
                if (mod1.isChecked()) {
                    mod = mod1.getText().toString();
                } else if (mod2.isChecked()) {
                    mod = mod2.getText().toString();
                } else if (mod3.isChecked()) {
                    mod = mod3.getText().toString();
                } else if (mod4.isChecked()) {
                    mod = mod4.getText().toString();
                } else {
                    Toast.makeText(AniEkran.this, "Mod seçmediniz!", Toast.LENGTH_SHORT).show();
                    return;
                }
                tarih = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                baslik = aniBaslik.getText().toString();
                yazi = aniYazi.getText().toString();
                lokasyon = "İstanbul";


                if (update) {

                    AniModel ani = new AniModel(id, baslik, tarih, lokasyon, yazi, mod);
                    boolean b = DBHelper.getInstance(this).aniGuncelle(ani);

                    if (b) {
                        Toast.makeText(AniEkran.this, "Anı Başarıyla Guncellendi :)", Toast.LENGTH_SHORT).show();
                        finishActivity(RESULT_OK);
                        startActivity(new Intent(AniEkran.this,AniList.class));

                        return;
                    }
                    Toast.makeText(AniEkran.this, "Anı Guncellenemedi :(", Toast.LENGTH_SHORT).show();
                    finishActivity(RESULT_OK);
                    return;
                }

                AniModel ani = new AniModel(0, baslik, tarih, lokasyon, yazi, mod);
                boolean b = DBHelper.getInstance(this).aniEkle(ani);
                if (b) {
                    Toast.makeText(AniEkran.this, "Anı Başarıyla Eklendi :)", Toast.LENGTH_SHORT).show();
                    finishActivity(RESULT_OK);
                    startActivity(new Intent(AniEkran.this,AniList.class));

                    return;
                }
                Toast.makeText(AniEkran.this, "Anı Eklenemedi :(", Toast.LENGTH_SHORT).show();
                finishActivity(RESULT_OK);

            }
                break;
            case R.id.buttonSil: {

                boolean b = DBHelper.getInstance(this).aniSil(id);
                if (b) {
                    Toast.makeText(AniEkran.this, "Anı Başarıyla Silindi :)", Toast.LENGTH_SHORT).show();
                    finishActivity(RESULT_OK);
                    startActivity(new Intent(AniEkran.this,AniList.class));
                    return;
                }
                Toast.makeText(AniEkran.this, "Anı Silinemedi :(", Toast.LENGTH_SHORT).show();
                finishActivity(RESULT_OK);
            }
                break;
        }

    }
}