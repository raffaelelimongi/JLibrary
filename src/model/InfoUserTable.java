package model;

import vo.UserInfo;

import java.io.IOException;

public class InfoUserTable extends UserInfo {
    String username,privilegio;

    public InfoUserTable(String username, String privilegio,String nome,String cognome,String email) throws IOException
    {
        super(nome,cognome,email);
        this.username=username;
        this.privilegio=privilegio;

    }
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

}
