package raf.rs.WEB_Projekat.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Komentar {

    private Integer id;
    @NotNull(message = "Author name required")
    @NotEmpty(message = "Author name required")
    private String imeAutora;
    @NotNull(message = "Content required")
    @NotEmpty(message = "Content required")
    private String tekst;
    private LocalDate datumKreiranja;
    @NotNull(message = "Article id required")
    @NotEmpty(message = "Article id required")
    private Integer articleId;

    public Komentar() {}

    public Komentar(String imeAutora, String tekst, LocalDate datum, Integer articleId) {
        this();
        this.imeAutora = imeAutora;
        this.tekst = tekst;
        this.datumKreiranja = datum;
        this.articleId = articleId;
    }

    public Komentar(Integer id, String imeAutora, String tekst, LocalDate datum, Integer articleId) {
        this.id = id;
        this.imeAutora = imeAutora;
        this.tekst = tekst;
        this.datumKreiranja = datum;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImeAutora() {
        return imeAutora;
    }

    public String getTekst() {
        return tekst;
    }

    public LocalDate getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setImeAutora(String imeAutora) {
        this.imeAutora = imeAutora;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setDatumKreiranja(LocalDate datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
