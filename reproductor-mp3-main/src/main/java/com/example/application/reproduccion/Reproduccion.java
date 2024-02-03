package com.example.application.reproduccion;

import com.vaadin.flow.component.notification.Notification;
import org.atmosphere.config.service.Message;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
public class Reproduccion {
//"C:\Users\agera\Music\Twenty One Pilots - Level of Concern (Audio).wav"

    Cancion[] canciones=new Cancion[3];
    AudioInputStream audioInputStream;
    Clip clip ;
    Boolean pausar=true;
   int  contador=0;

    public Reproduccion(){
        canciones[0]=new Cancion();
        canciones[1]=new Cancion();
        canciones[2]=new Cancion();
        llenarArchivos();
        llenarArtistas();
        llenarTitulos();
        iniciarCancion(0);
        this.clip=clip;
    }

    public void llenarArchivos(){
        try {
            canciones[0].llenarCanciones("C:\\Users\\agera\\Music\\Twenty One Pilots - Level of Concern (Audio).wav");
            canciones[1].llenarCanciones("C:\\Users\\agera\\Music\\Cage The Elephant - Neon Pill (Audio).wav");
            canciones[2].llenarCanciones("C:\\Users\\agera\\Music\\The Strokes - The Adults Are Talking (Official Audio).wav");
        }catch (Exception e ){
            System.out.println("ERROR");
        }

    }
    public void llenarTitulos(){

        try {
            canciones[0].setTitulo("Level of concern");
            canciones[1].setTitulo("Neon Pill");
            canciones[2].setTitulo("The Adults Are Talking");
        }catch (Exception e){
            Notification.show("Error en titulos");
        }

    }
    public void llenarArtistas(){
        try {
            canciones[0].setArtista("Twenty one Pilots");
            canciones[1].setArtista("Cage the Elephant");
            canciones[2].setArtista("The Strokes");
        }catch (Exception e){
            Notification.show("Error en artistas ");
        }
    }
    public void iniciarCancion(int indice){


        try {

            if(contador!=indice){
                clip.close();
                audioInputStream.close();
            }
            if(indice==3){
                indice=0;
                Notification.show("AHORA ES CERO");
            }
            audioInputStream=AudioSystem.getAudioInputStream(canciones[indice].getArchivos());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                Notification.show("Se inicio correctamente la cancion");


        }catch (Exception e){
            Notification.show("No se inicio correctamente la cancion");
        }
    }
    public void reproducir(){
try {
    Notification.show("COMENZOOO A SONAR");
    clip.start();

}catch (Exception e){
    Notification.show("UUUUU "+ e.getMessage());
}

    }
    public void pausarCancion(boolean pausar){
        try {
            clip.stop();
        }catch (Exception e){
            Notification.show(e.getMessage()+"ZZZZZ");
        }

    }

    public String obtenerArtista(int indice){
        return  canciones[indice].getArtista();
    }
    public String obtenerTitulo(int indice){
        return  canciones[indice].getTitulo();
    }

    public void repr(int contador,boolean pausar){
        int indice=contador;
        boolean bandera=true;

do {

        try {
            // Crea un objeto AudioInputStream que representa el flujo de entrada de audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(canciones[indice].getArchivos());

            // Obtiene el formato de audio del archivo
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            if(pausar==true){

                // Reproduce el sonido
                clip.start();


                // Espera hasta que se complete la reproducción
                while (!clip.isRunning()) {
                    Thread.sleep(10);
                }
                while (clip.isRunning()) {
                    Thread.sleep(10);
                }


                // Cierra el flujo de audio
                clip.close();
                audioInputStream.close();
                indice++;

                if(indice>canciones.length-1){
                    indice=0;
                }
            }else{
                clip.stop();
            }


        }catch (Exception e){

            e.printStackTrace();
        }
        }while (bandera);

    }


}
