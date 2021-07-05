package raf.rs.WEB_Projekat.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vest {

    private Integer id;
    @NotNull(message = "Title required")
    @NotEmpty(message = "Title required")
    private String naslov;
    @NotNull(message = "Text required")
    @NotEmpty(message = "Text required")
    private String tekst;
    private LocalDate vremeKreiranja;
    private int brojPoseta;
    @NotNull(message = "Author id required")
    @NotEmpty(message = "Author id required")
    private Integer autorId;
    @NotNull(message = "Category id required")
    @NotEmpty(message = "Category id required")
    private Integer categoryId;

    private List<Integer> komentari;
    private String tagovi;

    public Vest() {}

    public Vest(String naslov, String tekst, Integer autorId, LocalDate datum, int brojPoseta, Integer categoryId, String tagovi) {
        this();
        this.naslov = naslov;
        this.tekst = tekst;
        this.autorId = autorId;
        this.vremeKreiranja = datum;
        this.brojPoseta = brojPoseta;
        this.categoryId = categoryId;
        this.tagovi = tagovi;
        komentari = new ArrayList<>();
    }

    public Vest(Integer id, String naslov, String tekst, Integer autorId, LocalDate datum, int brojPoseta, Integer categoryId, String tagovi) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.autorId = autorId;
        this.vremeKreiranja = datum;
        this.brojPoseta = brojPoseta;
        this.categoryId = categoryId;
        this.tagovi = tagovi;
        komentari = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public LocalDate getVremeKreiranja() {
        return vremeKreiranja;
    }

    public int getBrojPoseta() {
        return brojPoseta;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public List<Integer> getKomentari() {
        return komentari;
    }

    public String getTagovi() {
        return tagovi;
    }

    public void setTagovi(String tagovi) {
        this.tagovi = tagovi;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setVremeKreiranja(LocalDate vremeKreiranja) {
        this.vremeKreiranja = vremeKreiranja;
    }

    public void setBrojPoseta(int brojPoseta) {
        this.brojPoseta = brojPoseta;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public void setKomentari(List<Integer> komentari) {
        this.komentari = komentari;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
