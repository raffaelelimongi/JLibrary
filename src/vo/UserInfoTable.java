package vo;

import java.util.Iterator;

//Classe per le informazioni piu utilizzate dell'utente iscritto al portale
public class UserInfoTable
{
    private  String username;
    private  String nome;
    private  String cognome;
    private  String email;

    public UserInfoTable(String u, String n, String c, String e)
    {
        username = u;
        nome=n;
        cognome=c;
        email=e;
    }

    public UserInfoTable(String s, String s1, String s2)
    {

    }

    public  String getUsername() { return username; }

    public String getNome()
    {
        return nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setUsername(String username) { this.username = username; }

    public  void setNome(String nome)
    {
        this.nome = nome;
    }

    public void setCognome(String cognome)
    {
        this.cognome = cognome;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
