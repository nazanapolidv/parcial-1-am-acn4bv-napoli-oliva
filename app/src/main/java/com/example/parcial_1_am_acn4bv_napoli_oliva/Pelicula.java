package com.example.parcial_1_am_acn4bv_napoli_oliva;

public class Pelicula {
    private int id;
    private String titulo;
    private int anio;
    private String genero;

    public Pelicula(int id, String titulo, int anio, String genero){
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.genero = genero;
    }

    public int getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public int getAnio(){
        return anio;
    }

    public String getGenero(){
        return genero;
    }

    public String getDescripcion(){
        return titulo + " (" + anio + ") - Genero: " + genero;
    }

}