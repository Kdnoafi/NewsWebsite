package raf.rs.WEB_Projekat.repositories.vest;

import raf.rs.WEB_Projekat.entities.Vest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryVestRepository implements VestRepository {

    private static List<Vest> articles = new CopyOnWriteArrayList<>();

    public InMemoryVestRepository() {}


    @Override
    public Vest addArticle(Vest article) {
        Integer id = articles.size();
        article.setId(id);
        articles.add(Math.toIntExact(id), article);

        return article;
    }

    @Override
    public List<Vest> allArticles() {
        return new ArrayList<>(articles);
    }

    @Override
    public Vest findArticle(Integer id) {
        return null;
    }

    @Override
    public void deleteArticle(Integer id) {

    }

    @Override
    public Vest updateArticle(Vest vest) {
        return null;
    }

    @Override
    public void updateVisits(Integer id) {

    }

    @Override
    public List<Vest> articlesByCategory(Integer categoryId) {
        return null;
    }
}
