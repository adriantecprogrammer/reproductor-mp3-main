package com.example.application.reproduccion;

import org.atmosphere.config.service.Message;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
public class Reproduccion {
//"C:\Users\agera\Music\Twenty One Pilots - Level of Concern (Audio).wav"

    File archivos []=new File[3];

    public Reproduccion(){
        this.archivos=archivos;
        llenarArchivos();
    }

    public void llenarArchivos(){
        try {
            archivos[0]=new File("C:\\Users\\agera\\Music\\Twenty One Pilots - Level of Concern (Audio).wav");
            archivos[1]=new File("C:\\Users\\agera\\Music\\Cage The Elephant - Neon Pill (Audio).wav");
            archivos[2]=new File("C:\\Users\\agera\\Music\\The Strokes - The Adults Are Talking (Official Audio).wav");
        }catch (Exception e ){
            System.out.println("ERROR");
        }

    }

    public void reproducir(int contador){
        int indice=contador;
        boolean bandera=true;

do {

        try {

            // Crea un objeto AudioInputStream que representa el flujo de entrada de audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivos[indice]);
/*
            if (indice>contador){
                //clip.close();
                audioInputStream.close();
            }

 */

            // Obtiene el formato de audio del archivo
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

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

            if(indice>archivos.length-1){
                indice=0;
            }

        }catch (Exception e){

            e.printStackTrace();
        }
        }while (bandera);

    }


}
