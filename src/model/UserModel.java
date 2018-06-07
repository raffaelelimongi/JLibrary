package model;

import vo.UserInfo;

import java.util.Iterator;

//Classe contenente tutti i dati principali dell'Utente iscritto
public class UserModel extends UserInfo
{
    private  String username;
    private String password;
    private boolean vip;
    private String privilegio;
    private Integer livello;
    private Boolean trascrittore,rictrascrittore;

    //setto la classe in questo modo per estendere il concetto di Singleton che permette l’accesso a una e una sola istanza di una specifica classe
    private static final UserModel instance = new UserModel("","",false,"",0,false,false,"","","");

    private UserModel(String user, String pass, Boolean vip, String privilegio, Integer liv, Boolean trascrittore,Boolean rictrascrittore,String n, String c, String e)
    {
        super(n, c, e);
        this.username = user;
        this.password = pass;
        this.vip = vip;
        this.privilegio = privilegio;
        this.livello = liv;
        this.trascrittore = trascrittore;
        this.rictrascrittore=rictrascrittore;
    }

    public static UserModel getInstance()
    {
        return instance;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVip() {
        return vip;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public Integer getLivello() {
        return livello;
    }

    public Boolean getTrascrittore() {
        return trascrittore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    public void setLivello(Integer livello) {
        this.livello = livello;
    }

    public void setTrascrittore(Boolean trascrittore) {
        this.trascrittore = trascrittore;
    }

    public Boolean getRictrascrittore() {
        return rictrascrittore;
    }

    public void setRictrascrittore(Boolean rictrascrittore) {
        this.rictrascrittore = rictrascrittore;
    }
}
