package raf.rs.WEB_Projekat.repositories.kategorija;

import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.entities.Vest;

import java.util.List;

public interface KategorijaRepository {
    public Kategorija addCategory(Kategorija category);
    public List<Kategorija> allCategories();
    public Kategorija findCategory(Integer id);
    public void deleteCategory(Integer id);
    public Kategorija updateCategory(Kategorija category);
    public Kategorija getByName(String name);
}
