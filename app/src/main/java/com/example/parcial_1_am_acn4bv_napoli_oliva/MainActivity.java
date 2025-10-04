package com.example.parcial_1_am_acn4bv_napoli_oliva;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peliculas = new ArrayList<>();

        peliculas.add(new Pelicula(2, "Batman Begins", 2005, "Acción", R.drawable.Batman_Begin));
        peliculas.add(new Pelicula(3, "Ready Player One", 2018, "Ciencia Ficción", R.drawable.Ready_Player));
        peliculas.add(new Pelicula(6, "Matrix", 1999, "Ciencia Ficción", R.drawable.Matrix));
        peliculas.add(new Pelicula(1, "Kill Bill", 2003, "Acción", R.drawable.Kill_Bill));
        peliculas.add(new Pelicula(4, "Scott Pilgrim vs the World", 2010, "Comedia / Acción", R.drawable.scott_pilgrim));
        peliculas.add(new Pelicula(5, "Duro de Matar 4", 2007, "Acción", R.drawable.Duro_De_Matar));
        peliculas.add(new Pelicula(9, "La Naranja Mecánica", 1971, "Drama / Distopía", R.drawable.Naranja));
        peliculas.add(new Pelicula(8, "Shingeki no Kyoshin: The Last Attack", 2023, "Anime / Acción", R.drawable.Shingeki));
        peliculas.add(new Pelicula(7, "Akira", 1988, "Animación / Ciencia Ficción", R.drawable.Akira));

    }

}