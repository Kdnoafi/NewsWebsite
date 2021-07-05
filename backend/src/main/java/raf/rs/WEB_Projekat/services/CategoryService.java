package raf.rs.WEB_Projekat.services;

import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.repositories.kategorija.KategorijaRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    public CategoryService() {}

    @Inject
    private KategorijaRepository categoryRepository;

    public Kategorija addCategory(Kategorija category) {
        return this.categoryRepository.addCategory(category);
    }

    public List<Kategorija> allCategories() {
        return this.categoryRepository.allCategories();
    }

    public Kategorija findCategory(Integer id) {
        return this.categoryRepository.findCategory(id);
    }

    public void deleteCategory(Integer id) {
        this.categoryRepository.deleteCategory(id);
    }
}
