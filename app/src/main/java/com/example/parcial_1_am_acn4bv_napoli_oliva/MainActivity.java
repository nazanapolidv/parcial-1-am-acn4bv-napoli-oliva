package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.Button;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

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
        Button btnIrFavoritos = findViewById(R.id.btnIrFavoritos);

        peliculas = new ArrayList<>();

        btnIrFavoritos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritosActivity.class);
            startActivity(intent);
        });

        // 4. CARGAR DATOS DESDE FIREBASE
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Toast.makeText(this, "Cargando estrenos", Toast.LENGTH_SHORT).show();

        db.collection("cartelera")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        peliculas.clear();

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Pelicula p = document.toObject(Pelicula.class);
                            peliculas.add(p);
                        }

                        mostrarPeliculas(peliculas);

                    } else {
                        Toast.makeText(this, "Error al cargar cartelera", Toast.LENGTH_LONG).show();
                    }
                });

        //Filtro de busqueda
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

    private void mostrarPeliculas(ArrayList<Pelicula> lista) {
        listaPeliculas.removeAllViews();

        for (Pelicula p : lista) {
            LinearLayout tarjeta = new LinearLayout(this);
            tarjeta.setOrientation(LinearLayout.VERTICAL);
            tarjeta.setPadding(16, 16, 16, 16);
            tarjeta.setBackgroundResource(R.drawable.card_background);

            ImageView img = new ImageView(this);
            Glide.with(this)
                    .load(p.getUrlImagen())
                    .into(img);
            img.setAdjustViewBounds(true);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1500
            ));

            TextView titulo = new TextView(this);
            titulo.setText(p.getTitulo());
            titulo.setTextColor(getResources().getColor(android.R.color.white));
            titulo.setTextSize(20);
            titulo.setPadding(0, 8, 0, 0);

            TextView desc = new TextView(this);
            desc.setText(p.getGenero() + " • " + p.getAnio());
            desc.setTextColor(getResources().getColor(android.R.color.darker_gray));
            desc.setTextSize(14);

            tarjeta.addView(img);
            tarjeta.addView(titulo);
            tarjeta.addView(desc);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 40);
            tarjeta.setLayoutParams(params);

            listaPeliculas.addView(tarjeta);

            tarjeta.setOnClickListener(v -> {
                Intent intent = new Intent(this, DetallePeliculaActivity.class);
                intent.putExtra("PELICULA_SELECCIONADA", p);
                startActivity(intent);
            });
        }
    }

    private void filtrarPeliculas(String texto) {
        ArrayList<Pelicula> filtradas = new ArrayList<>();
        if (peliculas != null) {
            for (Pelicula p : peliculas) {
                if (p.getTitulo() != null && p.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                    filtradas.add(p);
                }
            }
            mostrarPeliculas(filtradas);
        }
    }
}