package raf.rs.WEB_Projekat.servlets;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.entities.Tag;
import raf.rs.WEB_Projekat.entities.Vest;
import raf.rs.WEB_Projekat.repositories.kategorija.KategorijaRepository;
import raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository;
import raf.rs.WEB_Projekat.repositories.tag.MySqlTagRepository;
import raf.rs.WEB_Projekat.repositories.tag.TagRepository;
import raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository;
import raf.rs.WEB_Projekat.repositories.vest.VestRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "editArticleServlet", value = "/editArticleServlet/*")
public class EditArticleServlet extends HttpServlet {

    private VestRepository articleRepository;
    private TagRepository tagRepository;
    private KategorijaRepository categoryRepository;

    @Override
    public void init() {
        this.articleRepository = new MySqlVestRepository();
        this.tagRepository = new MySqlTagRepository();
        this.categoryRepository = new MySqlKategorijaRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //int id = Integer.parseInt(request.getPathInfo().substring(1));
        String naslov = request.getParameter("naslov");
        String kategorija = request.getParameter("kategorija");
        String tekst = request.getParameter("tekst");
        String tagovi = request.getParameter("tagovi");
        String articleId = request.getParameter("id");
        String[] split = tagovi.split(",");

        List<Tag> tags = new ArrayList<>();
        for(int i = 0; i < split.length; i++) {
            Tag tag = new Tag(split[i]);
            tags.add(tag);
            tagRepository.addTag(tag);
        }
        List<Integer> tagIds = new ArrayList<>();
        for(Tag t: tags) {
            tagIds.add(t.getId());
        }
        Kategorija category = categoryRepository.getByName(kategorija);

        Vest article = articleRepository.findArticle(Integer.parseInt(articleId));
        article.setNaslov(naslov);
        article.setTekst(tekst);
        //article.setTagovi(tagIds);
        article.setTagovi(tagovi);
        article.setCategoryId(category.getId());
        articleRepository.updateArticle(article);

       // response.sendRedirect(getServletContext().getContextPath() + "/articles");
    }
}
