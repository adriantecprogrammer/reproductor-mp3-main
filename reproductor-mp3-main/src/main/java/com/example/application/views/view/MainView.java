package com.example.application.views.view;

import com.example.application.reproduccion.Reproduccion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.Route;

import java.util.concurrent.atomic.AtomicInteger;

//@PageTitle("Reproductor")
//@Route(value = "hello", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)

@Route("")
public class MainView extends HorizontalLayout {

    private boolean reproduciendo = false;
    public ProgressBar progressBar = new ProgressBar();

    public MainView() {
        progressBar.setMax(360);
        progressBar.setWidth("50%");

        Reproduccion reproduccion = new Reproduccion(this);

        Span tituloCancion = new Span();

        Span artista = new Span();

        Icon pauseIcon = new Icon(VaadinIcon.PAUSE);
        Icon playIcon = new Icon(VaadinIcon.PLAY);
        Button botonPlayPause = new Button(playIcon);

        Icon nextIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
        Button botonNext = new Button(nextIcon);

        Icon randomIcon = new Icon(VaadinIcon.RANDOM);
        Button botonRandom = new Button(randomIcon);

        Icon beforeIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT);
        Button botonBefore = new Button(beforeIcon);


        botonNext.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        botonBefore.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        AtomicInteger contador = new AtomicInteger(0);


        botonPlayPause.setHeight("50px");
        botonBefore.setHeight("50px");
        botonNext.setHeight("50px");
        botonRandom.setHeight("50px");

        botonPlayPause.setWidth("55px");
        botonBefore.setWidth("55px");
        botonNext.setWidth("55px");
        botonRandom.setWidth("55px");

        botonPlayPause.getElement().getThemeList().add("primary");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout vertical = new VerticalLayout();

        Image imagen2 = new Image();
        imagen2.setHeight("270px");
        imagen2.setWidth("270px");
        imagen2.setSrc("https://media1.tenor.com/m/okaOzA7E7N4AAAAC/cassette-tape.gif");

        pauseIcon.setSize("30px");
        nextIcon.setSize("30px");
        beforeIcon.setSize("30px");

        tituloCancion.getStyle().set("font-size", "30px");
        artista.getStyle().set("Introduce local variable", "26px");

        add(vertical);
        add(horizontalLayout);

        vertical.setAlignItems(Alignment.CENTER);
        horizontalLayout.setAlignItems(Alignment.CENTER);


        add(vertical);
        vertical.add(
                new H1("My Music")
                , imagen2, tituloCancion, artista,
                new HorizontalLayout(
                        botonBefore, botonPlayPause, botonNext, botonRandom
                ),
                progressBar

        );

        ///////////////listeners//////////////////

        botonPlayPause.addClickListener(iconClickEvent -> {
            reproduciendo = !reproduciendo;


            if (reproduciendo) {
                reproduccion.reproducir(progressBar);
                tituloCancion.setText(reproduccion.obtenerTitulo(contador.get()));
                artista.setText(reproduccion.obtenerArtista(contador.get()));
                imagen2.setSrc(reproduccion.obtenerCover(contador.get()));
                botonPlayPause.setIcon(reproduciendo ? pauseIcon : playIcon);
            } else {
                botonPlayPause.setIcon(reproduciendo ? pauseIcon : playIcon);
                reproduccion.pausarCancion(reproduciendo);
            }


        });

        nextIcon.addClickListener(iconClickEvent -> {
            reproduccion.detenerReproduccion();
            contador.getAndIncrement();
            reproduccion.iniciarCancion(contador.get());

            if (contador.get() == reproduccion.sizeArreglo()) {
                contador.set(0);
            }
            tituloCancion.setText(reproduccion.obtenerTitulo(contador.get()));
            artista.setText(reproduccion.obtenerArtista(contador.get()));
            imagen2.setSrc(reproduccion.obtenerCover(contador.get()));
            reproduccion.reproducir(progressBar);

        });
        beforeIcon.addClickListener(iconClickEvent -> {
            reproduccion.detenerReproduccion();
            contador.getAndDecrement();
            if (contador.get() == -1) {
                contador.set(reproduccion.sizeArreglo() - 1);
            }
            reproduccion.iniciarCancion(contador.get());
            tituloCancion.setText(reproduccion.obtenerTitulo(contador.get()));
            artista.setText(reproduccion.obtenerArtista(contador.get()));
            imagen2.setSrc(reproduccion.obtenerCover(contador.get()));
            reproduccion.reproducir(progressBar);


        });


    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

}
