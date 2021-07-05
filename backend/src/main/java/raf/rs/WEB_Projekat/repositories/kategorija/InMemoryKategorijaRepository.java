package raf.rs.WEB_Projekat.repositories.kategorija;

import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.entities.Vest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryKategorijaRepository implements KategorijaRepository {

    private static List<Kategorija> categories = new CopyOnWriteArrayList<>();

    public InMemoryKategorijaRepository() {}

    @Override
    public Kategorija addCategory(Kategorija category) {
        int id = categories.size();
        category.setId(id);
        categories.add(Math.toIntExact(id), category);

        return category;
    }

    @Override
    public List<Kategorija> allCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public Kategorija findCategory(Integer id) {
        return null;
    }

    @Override
    public void deleteCategory(Integer id) {

    }

    @Override
    public Kategorija updateCategory(Kategorija category) {
        return null;
    }

    @Override
    public Kategorija getByName(String name) {
        return null;
    }
}
