package vo;

import controller.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Date;

public class InfoUserTable
{
    String username, email, nome, cognome;

    public InfoUserTable(String username, String email, String nome, String cognome) throws IOException
    {
        this.username=username;
        this.email=email;
        this.nome=nome;
        this.cognome=cognome;

    }
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public void setCognome(String cognome)
    {
        this.cognome = cognome;
    }
}