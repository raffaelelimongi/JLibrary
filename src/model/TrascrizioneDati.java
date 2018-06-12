package model;

import controller.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.Blob;


public class TrascrizioneDati
{
    public String titolo;
    public Blob image;
    private Label link;

    public TrascrizioneDati(String titolo, Blob image) throws IOException
    {
        this.titolo = titolo;
        this.image=image;
        this.link =new Label("view");
        link.setUnderline(true);
        link.setStyle("-fx-background-color: red");

        //se viene cliccato apre la finestra per la visualizzazione dell'opera
        link.setOnMouseClicked((MouseEvent mouseEvent) ->
        {
            try {
                JavaFXController.setTeiEditor();
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
}
