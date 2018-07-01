package model;

import controller.JavaFXController;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.Date;


public class ImmagineDati extends OperaMetadati
{
    private ImageView imageView;
    private String path;
    String nomeimg;
    private Label link;

    public ImmagineDati(String nomeimg,String path, ImageView imageView, String t, String a, String g, Date d)
    {
        super(t,a,g,d);
        this.nomeimg=nomeimg;
        this.path=path;
        this.imageView=imageView;
        this.link =new Label("view");
        link.setUnderline(true);
        link.setStyle("-fx-background-color: red");

        //se viene cliccato apre la finestra per la visualizzazione dell'opera
        link.setOnMouseClicked((MouseEvent mouseEvent) ->
        {
            JavaFXController.setViewImage(t);
        });
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
