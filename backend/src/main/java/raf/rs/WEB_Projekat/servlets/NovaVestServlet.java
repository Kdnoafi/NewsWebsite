package raf.rs.WEB_Projekat.servlets;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.json.JSONObject;
import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.entities.Tag;
import raf.rs.WEB_Projekat.entities.Vest;
import raf.rs.WEB_Projekat.repositories.kategorija.KategorijaRepository;
import raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository;
import raf.rs.WEB_Projekat.repositories.tag.MySqlTagRepository;
import raf.rs.WEB_Projekat.repositories.tag.TagRepository;
import raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository;
import raf.rs.WEB_Projekat.repositories.vest.VestRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "novaVestServlet", value = "/novaVest")
public class NovaVestServlet extends HttpServlet {

    private VestRepository articleRepository;
    private TagRepository tagRepository;
    private KategorijaRepository categoryRepository;
    private KorisnikRepository userRepository;

    @Override
    public void init() {
        this.articleRepository = new MySqlVestRepository();
        this.tagRepository = new MySqlTagRepository();
        this.categoryRepository = new MySqlKategorijaRepository();
        this.userRepository = new MySqlKorisnikRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String naslov = request.getParameter("naslov");
        String kategorija = request.getParameter("kategorija");
        String tekst = request.getParameter("tekst");
        String tagovi = request.getParameter("tagovi");
        String token = request.getParameter("jwt");
        String[] split = tagovi.split(",");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        Integer userId = jwt.getClaim("id").asInt();
        Korisnik user = this.userRepository.findUser(userId);

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

        Vest article = new Vest(naslov, tekst, user.getId(), LocalDate.now(), 0, category.getId(), tagovi);
        //article.setTagovi(tagIds);
        articleRepository.addArticle(article);

       // response.sendRedirect(getServletContext().getContextPath() + "/articles");
    }
}
