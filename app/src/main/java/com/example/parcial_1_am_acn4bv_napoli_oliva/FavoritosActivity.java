package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class FavoritosActivity extends AppCompatActivity {

    private LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        contenedor = findViewById(R.id.contenedorListaFavoritos);
        Button btnVolver = findViewById(R.id.btnVolverMain);

        btnVolver.setOnClickListener(v -> finish());

        cargarFavoritosDeFirebase();
    }

    private void cargarFavoritosDeFirebase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("favoritos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        contenedor.removeAllViews();

                        // recorre los resultados
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Pelicula p = document.toObject(Pelicula.class);
                            agregarVistaPelicula(p);
                        }

                        if (contenedor.getChildCount() == 0) {
                            Toast.makeText(this, "Aún no tienes favoritos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void agregarVistaPelicula(Pelicula p) {
        TextView texto = new TextView(this);
        texto.setText("★ " + p.getTitulo() + " (" + p.getAnio() + ")");
        texto.setTextSize(18);
        texto.setTextColor(getResources().getColor(android.R.color.white));
        texto.setPadding(0, 20, 0, 20);

        contenedor.addView(texto);
    }
}