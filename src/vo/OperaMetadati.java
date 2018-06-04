package vo;

import controller.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Date;

public class OperaMetadati
{
    String titolo,autore,categoria,genere;
    Date datapubb = new Date();
   private Label view;

    public OperaMetadati(String titolo, String autore, String genere) throws IOException {
        this.titolo = titolo;
        this.autore = autore;
        this.genere=genere;
        this.view =new Label("view");
        view.setUnderline(true);
        view.setStyle("-fx-background-color: red");
        view.setOnMouseClicked((MouseEvent mouseEvent) ->
        {
            ViewOperaController.ViewOpera();
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

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
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
