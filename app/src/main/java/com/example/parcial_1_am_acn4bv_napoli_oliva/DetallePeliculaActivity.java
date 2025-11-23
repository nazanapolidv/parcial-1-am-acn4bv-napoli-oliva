package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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
        }
    }

    private void cargarDescripcionRemota(String url, String tituloPelicula, TextView targetTextView) {

        // Muestra un mensaje temporal mientras carga
        targetTextView.setText("Cargando descripción...");
        RequestQueue queue = Volley.newRequestQueue(this);

        // Crear la petición de String
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            // Listener de respuesta exitosa
            response -> {
                String descripcionLarga = "La pelicula '" + tituloPelicula + "' trata sobre una historia epica de venganza en el espacio, donde el protagonista debe luchar contra su destino. Esta descripción es dinámica, cargada desde: " + url;
                    targetTextView.setText(descripcionLarga);
                    },
                // Listener de error
                error -> {
                    targetTextView.setText("Error al cargar la descripción. Mostrando URL de fallback.");
                    Toast.makeText(this, "Error de red: " + error.getMessage(), Toast.LENGTH_LONG).show();
                });

        queue.add(stringRequest);
    }
}