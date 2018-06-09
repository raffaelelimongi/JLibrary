package model;

import controller.ViewImage;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.OperaMetadati;

import java.io.IOException;
import java.util.Date;

public class ImmagineDati extends OperaMetadati
{
    private ImageView imageView;
    String nomeimg;
    private Label link;

    public ImmagineDati(String nomeimg,ImageView imageView,String t,String a,String g,Date d) throws IOException
    {
        super(t,a,g,d);
        this.nomeimg=nomeimg;
        this.imageView=imageView;
        this.link =new Label("view");
        link.setUnderline(true);
        link.setStyle("-fx-background-color: red");

        //se viene cliccato apre la finestra per la visualizzazione dell'opera
        link.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new ViewImage().setscene(t);
            }
    }   ));
    }

    public String getNomeimg() {
        return nomeimg;
    }

    public void setNomeimg(String nomeimg) {
        this.nomeimg = nomeimg;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Label getLink() {
        return link;
    }

    public void setLink(Label link) {
        this.link = link;
    }
}
