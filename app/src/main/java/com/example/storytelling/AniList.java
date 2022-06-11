package com.example.storytelling;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AniList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView noAniTextView;
    private AniModel ani;
    private Button aniEkleButton;
    private Button gosterButton;
    private AniListAdapter adapter;
    private ArrayList<AniModel> aniListe = new ArrayList<>();
    private static int ANIEKRAN_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani_list);

        recyclerView = findViewById(R.id.recycler_view);
        noAniTextView = findViewById(R.id.no_ani_text);
        aniEkleButton = findViewById(R.id.ani_ekle_button);
        gosterButton = findViewById(R.id.buttonGoster);
        //recyclerView.setHasFixedSize(true);

        ArrayList<AniModel> anilar = DBHelper.getInstance(this).listeAl();

            LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            adapter = new AniListAdapter(this,anilar);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(manager);

        aniEkleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AniList.this, AniEkran.class ));
            }
        });

    }

    class AniListAdapter extends RecyclerView.Adapter<AniListAdapter.ViewHolder>{
        ArrayList<AniModel> models;
        Context context;

        public AniListAdapter(Context context, ArrayList<AniModel> models) {
            this.context = context;
            this.models = models;
        }

        @NonNull
        @Override
        public AniListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AniListAdapter.ViewHolder holder, int position) {
            holder.baslik.setText(models.get(position).baslik);
            holder.tarih.setText(models.get(position).tarih);
            holder.lokasyon.setText(models.get(position).lokasyon);
            holder.mod.setText(models.get(position).mod);
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView baslik;
            TextView tarih;
            TextView lokasyon;
            TextView mod;
            AniModel model;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                baslik = itemView.findViewById(R.id.ani_baslik);
                tarih = itemView.findViewById(R.id.ani_tarih);
                lokasyon = itemView.findViewById(R.id.ani_location);
                mod = itemView.findViewById(R.id.ani_mod);
                gosterButton = itemView.findViewById(R.id.buttonGoster);
                itemView.setOnClickListener(this);
                baslik.setOnClickListener(this);
                gosterButton.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
            switch(view.getId()){
                /*case R.id.ani_baslik:
                    Toast.makeText(AniList.this, "Anı açılıyor",Toast.LENGTH_SHORT).show();
                    break;*/
                case R.id.buttonGoster:
                    Toast.makeText(AniList.this,"Anı açılıyor",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AniList.this, AniEkran.class);

            intent.putExtra("ani",models.get(getAdapterPosition()) );
            startActivityForResult(intent,ANIEKRAN_REQUEST);
            break;

}
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ANIEKRAN_REQUEST && resultCode == RESULT_OK){
            ArrayList<AniModel> anilar = DBHelper.getInstance(this).listeAl();
            adapter = new AniListAdapter(this,anilar);
            recyclerView.setAdapter(adapter);
        }
    }
}