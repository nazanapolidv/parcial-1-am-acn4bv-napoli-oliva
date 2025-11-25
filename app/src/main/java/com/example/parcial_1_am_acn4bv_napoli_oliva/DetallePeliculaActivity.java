package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.bumptech.glide.Glide;

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
        Pelicula pelicula = (Pelicula) getIntent().getSerializableExtra("PELICULA_SELECCIONADA");

        if (pelicula != null) {

            txtTitulo.setText(pelicula.getTitulo());
            txtDatos.setText(pelicula.getGenero() + " (" + pelicula.getAnio() + ")");

            Glide.with(this)
                    .load(pelicula.getUrlImagen())
                    .into(imgPoster);

            // urlDescripcion de Firebase
            txtDescripcion.setText(pelicula.getUrlDescripcion());

            btnVolver.setOnClickListener(v -> finish());

            // Agregar a favoritos com validacion
            btnFavorito.setOnClickListener(v -> {
                btnFavorito.setEnabled(false);
                btnFavorito.setText("Verificando...");

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Ahora se valida si existe esa pelicula en la lista con id
                db.collection("favoritos")
                        .whereEqualTo("id", pelicula.getId())
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                if (!task.getResult().isEmpty()) {
                                    Toast.makeText(this, "Ya agregaste esta película", Toast.LENGTH_SHORT).show();
                                    btnFavorito.setText("Ya agregada");
                                } else {
                                    guardarEnFirebase(db, pelicula, btnFavorito);
                                }
                            } else {
                                Toast.makeText(this, "Error al verificar", Toast.LENGTH_SHORT).show();
                                btnFavorito.setEnabled(true);
                                btnFavorito.setText("Reintentar");
                            }
                        });
            });
        }
    }

    private void guardarEnFirebase(FirebaseFirestore db, Pelicula p, Button btn) {
        btn.setText("Guardando...");
        db.collection("favoritos")
                .add(p)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "¡Tus favoritos se actualizaron!", Toast.LENGTH_SHORT).show();
                    btn.setText("Agregado a Favoritos");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    btn.setEnabled(true);
                    btn.setText("Intentar de nuevo");
                });
    }
}