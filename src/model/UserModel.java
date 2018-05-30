package model;

import vo.UserInfo;
public class UserModel extends UserInfo {

    private String username;
    private String password;
    private boolean vip;
    private String privilegio;
    private Integer livello;
    private Boolean Trascrittore;

    public UserModel(String n,String c, String e)
    {
        super(n,c,e);
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
        return Trascrittore;
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
        Trascrittore = trascrittore;
    }
}
