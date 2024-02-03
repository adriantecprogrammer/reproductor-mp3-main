package com.example.application.views.view;

import com.example.application.reproduccion.Cancion;
import com.example.application.reproduccion.Reproduccion;
import com.github.javaparser.JavaToken;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.theme.lumo.Lumo;

import javax.swing.text.LabelView;
import java.util.concurrent.atomic.AtomicInteger;

//@PageTitle("Reproductor")
//@Route(value = "hello", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)

@Route("")
public class MainView extends HorizontalLayout {

    private boolean reproduciendo = false;

    public MainView() {

        Cancion cancion=new Cancion();
        Reproduccion reproduccion= new Reproduccion();

        Span tituloCancion = new Span();
        Span duracionSpan = new Span("Duración: 0:00");
        Span artista=new Span();

        TextField nombre = new TextField("Nombre");
        Icon pauseIcon = new Icon(VaadinIcon.PAUSE);
        Icon playIcon = new Icon(VaadinIcon.PLAY);
        Button botonPlayPause = new Button(playIcon);

        Icon nextIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
        Button botonNext = new Button(nextIcon);

        Icon beforeIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT);
        Button botonBefore = new Button(beforeIcon);
AtomicInteger contador= new AtomicInteger(0);


botonPlayPause.setHeight("50px");
botonBefore.setHeight("50px");
botonNext.setHeight("50px");

        botonPlayPause.setWidth("55px");
        botonBefore.setWidth("55px");
        botonNext.setWidth("55px");

        botonPlayPause.getElement().getThemeList().add("primary");
    /*
        Button botonFlechaDelante = new Button(nextIcon);
        Button botonBeforeIcon = new Button(beforeIcon);
        Button botonPause = new Button(pauseIcon);

         */

        Button guardar = new Button("Guardar");
        HorizontalLayout horizontalLayout=new HorizontalLayout();
        VerticalLayout vertical = new VerticalLayout();
        //Image imagen = new Image("https://media.istockphoto.com/id/517473610/es/foto/cassette-de-cinta-aislado-en-blanco.jpg?b=1&s=612x612&w=0&k=20&c=TJLWsAsG-uqvuA9aHqzZzMLawGNgpM6_alrFPawppdo=", "Descripción de la imagen");
        Image imagen2 = new Image("https://media1.tenor.com/m/okaOzA7E7N4AAAAC/cassette-tape.gif", "Descripción de la imagen");
       // imagen.setWidth("400px");
        //imagen.setHeight("250px");
        pauseIcon.setSize("30px");
        nextIcon.setSize("30px");
        beforeIcon.setSize("30px");

        tituloCancion.getStyle().set("font-size", "30px");
        artista.getStyle().set("Introduce local variable","25px");

       // vertical.getStyle().set("background-color", "#D8BFD8"); // Puedes usar un color hexadecimal

        add(vertical);
        add(horizontalLayout);

        vertical.setAlignItems(Alignment.CENTER);
        horizontalLayout.setAlignItems(Alignment.CENTER);

        add(vertical);
        vertical.add(
                new H1("My Music"),imagen2,tituloCancion,artista,
                new HorizontalLayout(
                        botonBefore,botonPlayPause,botonNext
                ),
                duracionSpan

        );

        ///////////////listeners//////////////////

        botonPlayPause.addClickListener(iconClickEvent -> {
            reproduciendo = !reproduciendo;

            String duracion = reproduciendo ? "Duración: 3:45" : "Duración: 0:00";

            duracionSpan.setText(duracion);

            if (reproduciendo==true){
                reproduccion.reproducir();
                tituloCancion.setText(reproduccion.obtenerTitulo(contador.get()));
                artista.setText(reproduccion.obtenerArtista(contador.get()));
                System.out.println("BBBBBBBBBBB "+ reproduciendo);
                Notification.show("AAAAAAA "+reproduciendo);
                botonPlayPause.setIcon(reproduciendo ? pauseIcon : playIcon);
            }else{
                System.out.println("XXXX "+reproduciendo);
                botonPlayPause.setIcon(reproduciendo ? pauseIcon : playIcon);
                reproduccion.pausarCancion(reproduciendo);
            }

            Notification.show("Contador "+contador);

            System.out.println("BBBB "+contador);
            //Notification.show("Contador "+contador);

        });


        nextIcon.addClickListener(iconClickEvent -> {
            contador.getAndIncrement();
            System.out.println("AAAA "+contador);
            reproduccion.iniciarCancion(contador.get());

            if(contador.get()==3){
                contador.set(0);
            }
            tituloCancion.setText(reproduccion.obtenerTitulo(contador.get()));
            artista.setText(reproduccion.obtenerArtista(contador.get()));
            reproduccion.reproducir();
            Notification.show("Contador "+contador);
        });
        beforeIcon.addClickListener(iconClickEvent -> {
            contador.getAndDecrement();
            if(contador.get()==-1){
                contador.set(2);
            }
            reproduccion.iniciarCancion(contador.get());
            reproduccion.reproducir();
            System.out.println("AAAA "+contador);
            Notification.show("Contador "+contador);
        });



    }

}
