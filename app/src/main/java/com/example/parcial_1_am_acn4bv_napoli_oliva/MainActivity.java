package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pelicula> peliculas;
    private LinearLayout listaPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listaPeliculas = findViewById(R.id.listaPeliculas);

        peliculas = new ArrayList<>();
        peliculas.add(new Pelicula(1, "The Batman", 2022, "Accion", "batman"));
        peliculas.add(new Pelicula(2, "Scott Pilgrim vs the world", 2010, "Ciencia Ficcion", "scott_pilgrim"));
        peliculas.add(new Pelicula(3, "Ready Player One", 2018, "Ciencia Ficcion", "ready_player_one"));
        peliculas.add(new Pelicula(4, "Duro de Matar 4", 2007, "Accion", "duro_matar_4"));

        renderLista();

        Toast.makeText(this, "Cartelera start", Toast.LENGTH_LONG).show();
    }

    private void renderLista() {
        LayoutInflater inflater = LayoutInflater.from(this);
        listaPeliculas.removeAllViews();

        for (Pelicula p : peliculas) {
            View item = inflater.inflate(R.layout.item_pelicula, listaPeliculas, false);

            ImageView imgPoster = item.findViewById(R.id.imgPoster);
            TextView txtTitulo = item.findViewById(R.id.txtTitulo);
            TextView txtInfo   = item.findViewById(R.id.txtInfo);
            Button btnVer       = item.findViewById(R.id.btnVer);

            txtTitulo.setText(p.getTitulo());
            txtInfo.setText(p.getAnio() + " · " + p.getGenero());

            int posterRes = getDrawableByName(p.getPortada());
            if (posterRes != 0) {
                imgPoster.setImageResource(posterRes);
            } else {
                int placeholder = getDrawableByName("placeholder");
                if (placeholder != 0) imgPoster.setImageResource(placeholder);
            }

            item.setOnClickListener(v ->
                    Toast.makeText(this, p.getDescripcion(), Toast.LENGTH_SHORT).show()
            );

            if (btnVer != null) {
                btnVer.setOnClickListener(v ->
                        Toast.makeText(this, "Ver: " + p.getTitulo(), Toast.LENGTH_SHORT).show()
                );
            }

            listaPeliculas.addView(item);
        }
    }

    private int getDrawableByName(String name) {
        if (name == null) return 0;
        name = name.trim();
        if (name.isEmpty()) return 0;
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }
}