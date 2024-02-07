package com.example.application.reproduccion;

import com.vaadin.flow.component.html.Image;

import java.io.File;

public class Cancion {

   private  String titulo;
   private String artista;
   private File archivos;

   private String cover;

    public Cancion() {

    }
    public void llenarCanciones(String url){
        archivos=new File(url);
    }
    public void llenarCovers(String url){
        cover=url;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
