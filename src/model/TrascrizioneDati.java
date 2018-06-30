package model;

import controller.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Blob;


public class TrascrizioneDati
{
    public String titolo;
    public String testo;
    public Blob image;
    private Label link;

    public TrascrizioneDati(String titolo, Blob image, String testo)
    {
        this.titolo = titolo;
        this.image=image;
        this.testo=testo;
        this.link =new Label("view");
        link.setUnderline(true);
        link.setStyle("-fx-background-color: red");

        //se viene cliccato apre la finestra per la visualizzazione dell'opera
        link.setOnMouseClicked((MouseEvent mouseEvent) ->
        {
            try {
                JavaFXController.setTeiEditor(this.getTitolo());
            } catch (IOException e) {
                e.printStackTrace();
            }
           /* try {
               TeiEditor.setscene(titolo);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        });

    }

    public  String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Label getLink() {
        return link;
    }

    public void setLink(Label link) {
        this.link = link;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
