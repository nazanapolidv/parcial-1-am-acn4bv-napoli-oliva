package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.bumptech.glide.Glide;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DetallePeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        TextView txtTitulo = findViewById(R.id.detalleTitulo);
        TextView txtDatos = findViewById(R.id.detalleDatos);
        TextView txtDescripcion = findViewById(R.id.detalleDescripcion);
        ImageView imgPoster = findViewById(R.id.detalleImagen);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnFavorito = findViewById(R.id.btnFavorito);

        // Recuperar el objeto Pelicula completo
        Pelicula pelicula = (Pelicula) getIntent().getSerializableExtra("PELICULA_SELECCIONADA");

        if (pelicula != null) {

            // datos cargados por Glide
            txtTitulo.setText(pelicula.getTitulo());
            txtDatos.setText(pelicula.getGenero() + " (" + pelicula.getAnio() + ")");

            Glide.with(this)
                    .load(pelicula.getUrlImagen())
                    .into(imgPoster);

            cargarDescripcionRemota(pelicula.getUrlDescripcion(), pelicula.getTitulo(), txtDescripcion);
            ;
            btnVolver.setOnClickListener(v -> {
                finish();
            });

            // ---FIREBASE---
            btnFavorito.setOnClickListener(v -> {
                btnFavorito.setEnabled(false);
                btnFavorito.setText("Guardando...");

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Guardamos la pelicula en la coleccion"favoritos"
                db.collection("favoritos")
                        .add(pelicula)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(this, "¡Tu lista de favoritos se actualizo!", Toast.LENGTH_SHORT).show();
                            btnFavorito.setText("Agregado a Favoritos");
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            btnFavorito.setEnabled(true);
                            btnFavorito.setText("Intentar de nuevo");
                        });
            });
        }
    }

    private void cargarDescripcionRemota(String url, String tituloPelicula, TextView targetTextView) {
        targetTextView.setText("Cargando descripción...");
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    String descripcionLarga = "La pelicula '" + tituloPelicula + "' trata sobre una historia epica de venganza en el espacio... (Texto simulado desde: " + url + ")";
                    targetTextView.setText(descripcionLarga);
                },
                error -> {
                    targetTextView.setText("Error al cargar descripción.");
                    Toast.makeText(this, "Error de red: " + error.getMessage(), Toast.LENGTH_LONG).show();
                });

        queue.add(stringRequest);
    }
}