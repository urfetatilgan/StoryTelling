package com.example.storytelling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView baslik;
    private Button buttonGiris;
    private EditText editTextId, editTextParola;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String id, sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baslik =(TextView) findViewById(R.id.textViewBaslik);
        buttonGiris = (Button) findViewById(R.id.buttonGiris);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextParola = (EditText) findViewById(R.id.editTextParola);

        sp = getSharedPreferences("GirisBilgi",MODE_PRIVATE);
        editor =sp.edit();
        if(sp.getString("id","x").equals("x")&&sp.getString("sifre","y").equals("y")){
            editor.putString("id","admin");
            editor.putString("sifre","12345");
            editor.commit();
        }

        id= sp.getString("id","x");
        sifre = sp.getString("sifre","y");
        buttonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextId.getText().toString().equals(id) && editTextParola.getText().toString().equals(sifre)){
                    startActivity(new Intent(MainActivity.this,AnaEkran.class));
                }else{
                    Toast.makeText(MainActivity.this, "Hatalı kullanıcı adı veya şifre", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}