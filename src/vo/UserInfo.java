package vo;

public class UserInfo {

    private String nome;
    private String cognome;
    private String email;

    public UserInfo(String n, String c, String e) {
        nome=n;
        cognome=c;
        email=e;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {

        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
         this.nome = nome;
    }

    public void setCognome(String cognome) {

         this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
