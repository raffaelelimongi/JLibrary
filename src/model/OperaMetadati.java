package model;

import controller.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.Date;

public class OperaMetadati
{
    String titolo,autore,genere;
    Date datapubb ;
    private Label view;

    public OperaMetadati(String titolo, String autore, String genere, Date datapubb)
    {
        this.titolo = titolo;
        this.autore = autore;
        this.genere=genere;
        this.datapubb=datapubb;
        this.view =new Label("view");
        view.setUnderline(true);
        view.setStyle("-fx-background-color: red");

        //se viene cliccato apre la finestra per la visualizzazione dell'opera
        view.setOnMouseClicked((MouseEvent mouseEvent) ->
        {
            JavaFXController.setViewOpera(titolo);
        });

    }

    public String getTitolo()
    {
        return titolo;
    }

    public void setTitolo(String titolo)
    {
        this.titolo = titolo;
    }

    public String getAutore()
    {
        return autore;
    }

    public void setAutore(String autore)
    {
        this.autore = autore;
    }

    public Date getDatapubb()
    {
        return datapubb;
    }

    public void setDatapubb(Date datapubb)
    {
        this.datapubb = datapubb;
    }

    public Label getView()
    {
    return view;
    }

    public void setView(Label view)
    {
        this.view = view;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

}
