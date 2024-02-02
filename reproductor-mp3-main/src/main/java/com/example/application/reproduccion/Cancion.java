package com.example.application.reproduccion;

import java.io.File;

public class Cancion {

   private  String titulo;
   private String artista;
   private File archivos;

    public Cancion() {

    }
    public void llenarCanciones(String url){
        archivos=new File(url);
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public File getArchivos() {
        return archivos;
    }

    public void setArchivos(File archivos) {
        this.archivos = archivos;
    }
}
