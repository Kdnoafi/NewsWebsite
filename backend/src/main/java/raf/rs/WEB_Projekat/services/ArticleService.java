package raf.rs.WEB_Projekat.services;

import raf.rs.WEB_Projekat.entities.Vest;
import raf.rs.WEB_Projekat.repositories.vest.VestRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    public ArticleService() {}

    @Inject
    private VestRepository articleRepository;

    public Vest addArticle(Vest article) {
        return this.articleRepository.addArticle(article);
    }

    public List<Vest> allArticles() {
        return this.articleRepository.allArticles();
    }

    public List<Vest> articlesByCategory(Integer id) {
        return this.articleRepository.articlesByCategory(id);
    }

    public Vest findArticle(Integer id) {
        return this.articleRepository.findArticle(id);
    }

    public void deleteArticle(Integer id) {
        this.articleRepository.deleteArticle(id);
    }
}
