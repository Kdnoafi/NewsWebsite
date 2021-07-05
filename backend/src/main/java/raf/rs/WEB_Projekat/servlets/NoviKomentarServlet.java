package raf.rs.WEB_Projekat.servlets;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import raf.rs.WEB_Projekat.entities.Komentar;
import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.komentar.CommentRepository;
import raf.rs.WEB_Projekat.repositories.komentar.MySqlCommentRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "noviKomentarServlet", value = "/noviKomentar")
public class NoviKomentarServlet extends HttpServlet {

    private CommentRepository commentRepository;
    private KorisnikRepository userRepository;

    @Override
    public void init() {
        this.commentRepository = new MySqlCommentRepository();
        this.userRepository = new MySqlKorisnikRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //String imeAutora = request.getParameter("imeAutora");
        String tekst = request.getParameter("tekst");
        String articleId = request.getParameter("articleId");
        String token = request.getParameter("jwt");

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        Integer userId = jwt.getClaim("id").asInt();
        Korisnik user = this.userRepository.findUser(userId);

        Komentar comment = new Komentar(user.getIme() + " " + user.getPrezime(), tekst, LocalDate.now(), Integer.valueOf(articleId));
        commentRepository.addComment(comment);

        //response.sendRedirect(getServletContext().getContextPath() + "/articles");
    }
}
