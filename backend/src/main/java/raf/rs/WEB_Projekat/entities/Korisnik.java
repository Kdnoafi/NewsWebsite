package raf.rs.WEB_Projekat.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Korisnik {

    private Integer id;
    @NotNull(message = "Email required")
    @NotEmpty(message = "Email required")
    private String email;
    @NotNull(message = "First name required")
    @NotEmpty(message = "First name required")
    private String ime;
    @NotNull(message = "Last name required")
    @NotEmpty(message = "Last name required")
    private String prezime;
    @NotNull(message = "Type required")
    @NotEmpty(message = "Type required")
    private String tip;
    private String status;
    @NotNull(message = "Password required")
    @NotEmpty(message = "Password required")
    private String lozinka;

    public Korisnik() {

    }

    public Korisnik(String email, String ime, String prezime, String tip, String lozinka) {
        this();
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
        this.status = "1";
        this.lozinka = lozinka;
    }

    public Korisnik(Integer id, String email, String ime, String prezime, String tip, String lozinka) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
        this.status = "1";
        this.lozinka = lozinka;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getTip() {
        return tip;
    }

    public String getStatus() {
        return status;
    }

    public String getLozinka() {
        return lozinka;
    }

    @Override
    public String toString() {
        return getIme() + " " + getPrezime();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
