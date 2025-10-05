package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pelicula> peliculas;
    private LinearLayout listaPeliculas;
    private EditText inputBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPeliculas = findViewById(R.id.listaPeliculas);
        inputBusqueda = findViewById(R.id.inputBusqueda);

        // Creo el Array de Peliculas
        peliculas = new ArrayList<>();
        peliculas.add(new Pelicula(2, "Batman Begins", 2005, "Acción", R.drawable.batmanbegin,
                "La historia del origen de Batman, desde su infancia traumática."));
        peliculas.add(new Pelicula(3, "Ready Player One", 2018, "Ciencia Ficción", R.drawable.readyplayer,
                "En un futuro distópico, un joven compite en un universo virtual lleno de desafíos."));
        peliculas.add(new Pelicula(6, "Matrix", 1999, "Ciencia Ficción", R.drawable.matrix,
                "Neo descubre que la realidad es una simulación y se une a la resistencia."));
        peliculas.add(new Pelicula(1, "Kill Bill", 2003, "Acción", R.drawable.killbill,
                "Una ex-asesina busca venganza contra su antiguo equipo y su líder."));
        peliculas.add(new Pelicula(4, "Scott Pilgrim vs the World", 2010, "Comedia / Acción", R.drawable.scottpilgrim,
                "Scott Pilgrim debe enfrentarse a los siete malvados exnovios de Ramona."));
        peliculas.add(new Pelicula(5, "Duro de Matar 4", 2007, "Acción", R.drawable.durodematar,
                "John McClane vuelve a la acción."));
        peliculas.add(new Pelicula(9, "La Naranja Mecánica", 1971, "Drama / Distopía", R.drawable.naranja,
                "En un futuro distópico, Alex y su banda cometen actos violentos."));
        peliculas.add(new Pelicula(8, "Shingeki no Kyoshin: The Last Attack", 2023, "Anime / Acción", R.drawable.shingeki,
                "La humanidad lucha contra gigantes devoradores de personas."));
        peliculas.add(new Pelicula(7, "Akira", 1988, "Animación / Ciencia Ficción", R.drawable.akira,
                "En un Tokio post-apocalíptico, Tetsuo desarrolla poderes destructivos."));

        // Llamo a mostrar peliculas
        mostrarPeliculas(peliculas);

        // Filtro de busqueda
        inputBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarPeliculas(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    // Mostrar peliculas
    private void mostrarPeliculas(ArrayList<Pelicula> lista) {
        listaPeliculas.removeAllViews(); // limpiar la lista

        for (Pelicula p : lista) {
            LinearLayout tarjeta = new LinearLayout(this);
            tarjeta.setOrientation(LinearLayout.VERTICAL);
            tarjeta.setPadding(16, 16, 16, 16);
            tarjeta.setBackgroundResource(R.drawable.card_background);

            // Imagen
            ImageView img = new ImageView(this);
            img.setImageResource(p.getImagen());
            img.setAdjustViewBounds(true);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1500
            ));

            // Titulo
            TextView titulo = new TextView(this);
            titulo.setText(p.getTitulo());
            titulo.setTextColor(getResources().getColor(android.R.color.white));
            titulo.setTextSize(20);
            titulo.setPadding(0, 8, 0, 0);

            // Descripcion
            TextView desc = new TextView(this);
            desc.setText(p.getGenero() + " • " + p.getAnio());
            desc.setTextColor(getResources().getColor(android.R.color.darker_gray));
            desc.setTextSize(14);

            // Tarjeta
            tarjeta.addView(img);
            tarjeta.addView(titulo);
            tarjeta.addView(desc);

            // Margen inferior entre tarjetas
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 40);
            tarjeta.setLayoutParams(params);

            // Agregar la tarjeta al contenedor
            listaPeliculas.addView(tarjeta);

            // Interaccion / Evento al tocar una tarjeta
            tarjeta.setOnClickListener(v -> {
                Toast.makeText(
                        this,
                        p.getDescripcionCorta(),
                        Toast.LENGTH_LONG
                ).show();
            });

        }
    }

    // Filtro
    private void filtrarPeliculas(String texto) {
        ArrayList<Pelicula> filtradas = new ArrayList<>();
        for (Pelicula p : peliculas) {
            if (p.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                filtradas.add(p);
            }
        }
        mostrarPeliculas(filtradas);
    }

}