package raf.rs.WEB_Projekat.repositories.vest;

import raf.rs.WEB_Projekat.entities.Vest;

import java.util.List;

public interface VestRepository {
    public Vest addArticle(Vest vest);
    public List<Vest> allArticles();
    public Vest findArticle(Integer id);
    public void deleteArticle(Integer id);
    public Vest updateArticle(Vest vest);
    public void updateVisits(Integer id);
    public List<Vest> articlesByCategory(Integer categoryId);
}
