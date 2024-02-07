package com.example.application.reproduccion;

import com.example.application.views.view.MainView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.progressbar.ProgressBar;


import javax.sound.sampled.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Reproduccion {

    Cancion[] canciones=new Cancion[5];
    AudioInputStream audioInputStream;
    Clip clip ;
   int  contador=0;

   private MainView mainView;

    public Reproduccion(MainView mainView){
        this.mainView = mainView;
        canciones[0]=new Cancion();
        canciones[1]=new Cancion();
        canciones[2]=new Cancion();
        canciones[3]=new Cancion();
        canciones[4]=new Cancion();

        llenarArchivos();
        llenarArtistas();
        llenarTitulos();
        llenarCovers();
        iniciarCancion(0);

        this.clip=clip;

    }
    public void llenarArchivos(){
        try {
            canciones[0].llenarCanciones("C:\\Users\\agera\\Music\\Twenty One Pilots - Level of Concern (Audio).wav");
            canciones[1].llenarCanciones("C:\\Users\\agera\\Music\\Cage The Elephant - Neon Pill (Audio).wav");
            canciones[2].llenarCanciones("C:\\Users\\agera\\Music\\The Strokes - The Adults Are Talking (Official Audio).wav");
            canciones[3].llenarCanciones("C:\\Users\\agera\\Music\\Taylor Swift - Wildest Dreams (Taylor’s Version) (Official Audio).wav");
            canciones[4].llenarCanciones("C:\\Users\\agera\\Music\\Lana Del Rey - Brooklyn Baby (Official Audio).wav");
        }catch (Exception e ){
            System.out.println("ERROR");
        }

    }
    public void llenarTitulos(){

        try {
            canciones[0].setTitulo("Level of concern");
            canciones[1].setTitulo("Neon Pill");
            canciones[2].setTitulo("The Adults Are Talking");
            canciones[3].setTitulo("Wildest Dreams (Taylor’s Version)");
            canciones[4].setTitulo("Brooklyn Baby");
        }catch (Exception e){
            Notification.show("Error en titulos");
        }

    }
    public void llenarArtistas(){
        try {
            canciones[0].setArtista("Twenty one Pilots");
            canciones[1].setArtista("Cage the Elephant");
            canciones[2].setArtista("The Strokes");
            canciones[3].setArtista("Taylor Swift");
            canciones[4].setArtista("Lana del Rey");
        }catch (Exception e){
            Notification.show("Error en artistas ");
        }
    }
    public void llenarCovers(){
        canciones[0].llenarCovers("https://upload.wikimedia.org/wikipedia/en/4/40/Twenty_One_Pilots_-_Level_of_Concern.png");
        canciones[1].llenarCovers("https://i.scdn.co/image/ab67616d0000b273de2ded59681149bcc8cdbc11");
        canciones[2].llenarCovers("https://m.media-amazon.com/images/I/91PbdrEjhaL._UF1000,1000_QL80_.jpg");
        canciones[3].llenarCovers("https://upload.wikimedia.org/wikipedia/en/d/d5/Taylor_Swift_-_1989_%28Taylor%27s_Version%29.png");
        canciones[4].llenarCovers("https://i.scdn.co/image/ab67616d0000b2731624590458126fc8b8c64c2f");
    }
    public void iniciarCancion(int indice){
        try {

            if(contador!=indice){

                detenerReproduccion();
            }
            if(indice==canciones.length){
                indice=0;
            }
            audioInputStream=AudioSystem.getAudioInputStream(canciones[indice].getArchivos());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

        }catch (Exception e){
            Notification.show("No se inicio correctamente la cancion");
        }
    }
    public void reproducir(ProgressBar progressBar){
try {
    clip.start();

    new Thread(() -> {
        while (clip.isRunning()) {
            long currentTime = clip.getMicrosecondPosition() / 1000000;
            mainView.getUI().ifPresent(ui -> ui.access(() -> mainView.progressBar.setValue((int) currentTime)));
            try {
                Thread.sleep(1000); // Ajusta el intervalo según tus necesidades
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();



}catch (Exception e){
    Notification.show( e.getMessage());
}
    }
        public void pausarCancion(boolean pausar){
        try {
            clip.stop();
        }catch (Exception e){
            Notification.show(e.getMessage());
        }

    }
    public void detenerReproduccion(){
        try {
            clip.close();
            audioInputStream.close();
        }catch (Exception e){

        }

    }
    public String obtenerArtista(int indice){
        return  canciones[indice].getArtista();
    }
    public String obtenerTitulo(int indice){
        return  canciones[indice].getTitulo();
    }
    public String obtenerCover(int indice){
        return canciones[indice].getCover();
    }

    public int sizeArreglo(){
        return canciones.length;
    }


}
