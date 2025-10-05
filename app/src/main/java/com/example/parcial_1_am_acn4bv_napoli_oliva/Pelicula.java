package com.example.parcial_1_am_acn4bv_napoli_oliva;

public class Pelicula {
    private int id;
    private String titulo;
    private int anio;
    private String genero;
    private int imagen;
    private String descripcionCorta;

    public Pelicula(int id, String titulo, int anio, String genero, int imagen, String descripcionCorta){
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.genero = genero;
        this.imagen = imagen;
        this.descripcionCorta = descripcionCorta;
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

    public int getImagen(){
        return imagen;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }
    public String getDescripcion(){
        return titulo + " (" + anio + ") - Genero: " + genero;
    }

}