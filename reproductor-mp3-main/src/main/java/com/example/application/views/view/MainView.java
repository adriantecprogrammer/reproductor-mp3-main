package com.example.application.views.view;

import com.example.application.reproduccion.Cancion;
import com.example.application.reproduccion.Reproduccion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;

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

       // reproduccion.reproducir();

        Paragraph tituloCancion = new Paragraph("HHHHHH");
        Paragraph artista=new Paragraph("OOOOO");
        TextField nombre = new TextField("Nombre");
        Icon pauseIcon = new Icon(VaadinIcon.PAUSE);
        Icon playIcon = new Icon(VaadinIcon.PLAY);
        Button botonPlayPause = new Button(playIcon);

        Icon nextIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
        Button botonNext = new Button(nextIcon);

        Icon beforeIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT);
        Button botonBefore = new Button(beforeIcon);
AtomicInteger contador= new AtomicInteger();
        /*
        Button botonFlechaDelante = new Button(nextIcon);
        Button botonBeforeIcon = new Button(beforeIcon);
        Button botonPause = new Button(pauseIcon);

         */

        Button guardar = new Button("Guardar");
        HorizontalLayout horizontalLayout=new HorizontalLayout();
        VerticalLayout vertical = new VerticalLayout();
        Image imagen = new Image("https://media.istockphoto.com/id/517473610/es/foto/cassette-de-cinta-aislado-en-blanco.jpg?b=1&s=612x612&w=0&k=20&c=TJLWsAsG-uqvuA9aHqzZzMLawGNgpM6_alrFPawppdo=", "Descripción de la imagen");
        imagen.setWidth("400px");
        imagen.setHeight("250px");
        pauseIcon.setSize("30px");
        nextIcon.setSize("30px");
        beforeIcon.setSize("30px");

        tituloCancion.getStyle().set("font-size", "25px");
        artista.getStyle().set("Introduce local variable","20px");

       // vertical.getStyle().set("background-color", "#D8BFD8"); // Puedes usar un color hexadecimal


        add(vertical);
        add(horizontalLayout);

        vertical.setAlignItems(Alignment.CENTER);
        horizontalLayout.setAlignItems(Alignment.CENTER);


        add(vertical);
        vertical.add(
                new H1("My Music"),imagen,tituloCancion,artista,
                new HorizontalLayout(
                        botonBefore,botonPlayPause,botonNext
                )

        );


        ///////////////listeners//////////////////

        botonPlayPause.addClickListener(iconClickEvent -> {
            reproduciendo = !reproduciendo;

            if (reproduciendo==true){
                reproduccion.reproducir(contador.get());
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

        /*
        nextIcon.addClickListener(iconClickEvent -> {
            contador.getAndIncrement();
            System.out.println("AAAA "+contador);
            reproduccion.reproducir(contador.get());
            Notification.show("Contador "+contador);
        });
        beforeIcon.addClickListener(iconClickEvent -> {
            contador.getAndDecrement();
            reproduccion.reproducir(contador.get());
            System.out.println("AAAA "+contador);
            Notification.show("Contador "+contador);
        });

         */

    }

}
