package raf.rs.WEB_Projekat.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    private Integer id;
    @NotNull(message = "Tag text required")
    @NotEmpty(message = "Tag text required")
    private String tekst;

    public Tag() {}

    public Tag(String tekst) {
        this();
        this.tekst = tekst;
    }

    public Tag(Integer id, String tekst) {
        this.id = id;
        this.tekst = tekst;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }
}
