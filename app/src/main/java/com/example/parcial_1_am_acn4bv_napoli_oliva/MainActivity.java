package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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
        // Reemplazo la descripción corta por una URL de simulación
        peliculas.add(new Pelicula(2, "Batman Begins", 2005, "Acción", "https://www.originalfilmart.com/cdn/shop/products/batman_begins_2005_advance_original_film_art_9922c5af-d6bb-4f81-8487-19a4428f8600_5000x.jpg?v=1654194335",
                "https://api.simulacion.com/peliculas/batmanbegins/descripcion"));
        peliculas.add(new Pelicula(3, "Ready Player One", 2018, "Ciencia Ficción", "https://www.originalfilmart.com/cdn/shop/products/ready_player_one_2017_advance_original_film_art_5000x.jpg?v=1621459988",
                "https://api.simulacion.com/peliculas/readyplayerone/descripcion"));
        peliculas.add(new Pelicula(6, "Matrix", 1999, "Ciencia Ficción", "https://www.tuposter.com/pub/media/catalog/product/cache/71d04d62b2100522587d43c930e8a36b/m/a/matrix_posters.png",
                "https://api.simulacion.com/peliculas/matrix/descripcion"));
        peliculas.add(new Pelicula(1, "Kill Bill", 2003, "Acción", "https://www.originalfilmart.com/cdn/shop/products/KillBillvol1_2003_french_original_film_art_5000x.jpg?v=1617373568",
                "https://api.simulacion.com/peliculas/killbill/descripcion"));
        peliculas.add(new Pelicula(4, "Scott Pilgrim vs the World", 2010, "Comedia / Acción", "https://static.posters.cz/image/750/8320.jpg",
                "https://api.simulacion.com/peliculas/scottpilgrim/descripcion"));
        peliculas.add(new Pelicula(5, "Duro de Matar 4", 2007, "Acción", "https://cinefreaks.net/2023/wp-content/uploads/2007/08/Duro-de-matar-4.0-El-ultimo-vaquero-02.jpg",
                "https://api.simulacion.com/peliculas/durodematar/descripcion"));
        peliculas.add(new Pelicula(9, "La Naranja Mecánica", 1971, "Drama / Distopía", "https://www.originalfilmart.com/cdn/shop/files/a_clockwork_orange_1972_linen_x_rated_original_film_art_f_1600x.webp?v=1746466043",
                "https://api.simulacion.com/peliculas/naranjamecanica/descripcion"));
        peliculas.add(new Pelicula(8, "Shingeki no Kyoshin: The Last Attack", 2023, "Anime / Acción", "https://m.media-amazon.com/images/M/MV5BNjM4YWRmYmMtODdhNS00YzM4LWFiZDktYjJkN2U5MGQ4NmUwXkEyXkFqcGc@._V1_.jpg",
                "https://api.simulacion.com/peliculas/shingeki/descripcion"));
        peliculas.add(new Pelicula(7, "Akira", 1988, "Animación / Ciencia Ficción", "https://http2.mlstatic.com/D_NQ_NP_704441-MLA73743224227_012024-O.webp",
                "https://api.simulacion.com/peliculas/akira/descripcion"));

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
            // Glide para cargar la imagen desde la URL
            Glide.with(this)
                    .load(p.getUrlImagen())
                    .into(img);
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

            // Interaccion / Evento al tocar una tarjeta (nueva version)
            tarjeta.setOnClickListener(v -> {
                // Crear el intent
                Intent intent = new Intent(this, DetallePeliculaActivity.class);

                // Pasamos el objeto Pelicula completo
                intent.putExtra("PELICULA_SELECCIONADA", p);

                // Iniciar activity
                startActivity(intent);
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