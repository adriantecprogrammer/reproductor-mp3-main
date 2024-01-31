package com.example.application.views.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

//@PageTitle("Reproductor")
//@Route(value = "hello", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)

@Route("")
public class MainView extends HorizontalLayout {



    public MainView() {

        TextField nombre = new TextField("Nombre");
        Icon pauseIcon = new Icon(VaadinIcon.PAUSE);
        Icon playIcon = new Icon(VaadinIcon.PLAY);
       // Icon nextIcon = new Icon(VaadinIcon.CARET_RIGHT);
        Icon nextIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT);
       // Icon beforeIcon = new Icon(VaadinIcon.CARET_LEFT);
        Icon beforeIcon = new Icon(VaadinIcon.ANGLE_DOUBLE_LEFT);

        Button guardar = new Button("Guardar");
        HorizontalLayout horizontalLayout=new HorizontalLayout();
        VerticalLayout vertical = new VerticalLayout();
        Image imagen = new Image("https://media.istockphoto.com/id/517473610/es/foto/cassette-de-cinta-aislado-en-blanco.jpg?b=1&s=612x612&w=0&k=20&c=TJLWsAsG-uqvuA9aHqzZzMLawGNgpM6_alrFPawppdo=", "Descripción de la imagen");
imagen.setWidth("400px");
imagen.setHeight("250px");
pauseIcon.setSize("30px");
nextIcon.setSize("30px");
beforeIcon.setSize("30px");
       // vertical.getStyle().set("background-color", "#D8BFD8"); // Puedes usar un color hexadecimal


        add(vertical);
        add(horizontalLayout);

        vertical.setAlignItems(Alignment.CENTER);
        horizontalLayout.setAlignItems(Alignment.CENTER);


        add(vertical);
        vertical.add(
                new H1("My Music"),imagen,
                new HorizontalLayout(
                        beforeIcon,pauseIcon,nextIcon
                )

        );


    }

}
