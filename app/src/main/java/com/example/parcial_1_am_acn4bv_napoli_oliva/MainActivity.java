package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Pelicula> peliculas;
    private LinearLayout listaPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Lista de Peliculas
        peliculas = new ArrayList<>();
        peliculas.add(new Pelicula(1,"The Batman",2022, "Accion"));
        peliculas.add(new Pelicula(2,"Scott Pilgrim vs the world",2010,"Ciencia Ficcion"));
        peliculas.add(new Pelicula(3,"Ready Player One",2018, "Ciencia Ficcion"));
        peliculas.add(new Pelicula(4, "Duro de Matar 4",2007, "Accion"));

        listaPeliculas = findViewById(R.id.listaPeliculas);

        // Mensaje de test
        Toast.makeText(this, "Cartelera start", Toast.LENGTH_LONG).show();

    }
}