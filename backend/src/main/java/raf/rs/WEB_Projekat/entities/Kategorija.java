package raf.rs.WEB_Projekat.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Kategorija {

    private Integer id;
    @NotNull(message = "Category name required")
    @NotEmpty(message = "Category name required")
    private String ime;
    @NotNull(message = "Description required")
    @NotEmpty(message = "Description required")
    private String opis;

    public Kategorija() {}

    public Kategorija(String ime, String opis) {
        this();
        this.ime = ime;
        this.opis = opis;
    }

    public Kategorija(Integer id, String ime, String opis) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
