package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.bumptech.glide.Glide;

public class DetallePeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        // Recibir los datos
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String titulo = extras.getString("EXTRA_TITULO");
            int anio = extras.getInt("EXTRA_ANIO");
            String genero = extras.getString("EXTRA_GENERO");
            String urlImagen = extras.getString("EXTRA_IMAGEN_URL");
            String descripcionCorta = extras.getString("EXTRA_DESCRIPCION_CORTA");

            // Obtener referencias
            TextView txtTitulo = findViewById(R.id.detalleTitulo);
            TextView txtDatos = findViewById(R.id.detalleDatos);
            TextView txtDescripcion = findViewById(R.id.detalleDescripcion);
            ImageView imgPoster = findViewById(R.id.detalleImagen);
            Button btnVolver = findViewById(R.id.btnVolver);

            // Asignar valores
            txtTitulo.setText(titulo);
            txtDatos.setText(genero + " (" + anio + ")");
            txtDescripcion.setText(descripcionCorta);
            // Glide para cargar la imagen desde la URL
            Glide.with(this)
                    .load(urlImagen)
                    .into(imgPoster);

            btnVolver.setOnClickListener(v -> {
                finish(); // cierra Activity y vuelve a la anterior (MainActivity)
            });
        }

    }
}