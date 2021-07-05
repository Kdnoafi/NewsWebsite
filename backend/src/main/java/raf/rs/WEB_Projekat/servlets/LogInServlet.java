package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository;
import raf.rs.WEB_Projekat.security.PasswordHash;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

@WebServlet(name = "loginServlet", value = "/login")
public class LogInServlet extends HttpServlet {

    private KorisnikRepository mySqlKorisnikRepository;

    @Override
    public void init() {
        this.mySqlKorisnikRepository = new MySqlKorisnikRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String lozinka = request.getParameter("lozinka");
        String result = mySqlKorisnikRepository.checkCredentials(email, PasswordHash.sha256(lozinka));
        request.getSession().setAttribute("lEmail", 0);
        request.getSession().setAttribute("lPass", 0);
        request.getSession().setAttribute("status", 0);
        request.setAttribute("a", 0);

        switch (result) {
            case "login":
                Korisnik user = mySqlKorisnikRepository.findByEmail(email);
                request.getSession().setAttribute("loggedIn", user);
                response.sendRedirect(getServletContext().getContextPath() + "/articles");
                break;
            case "email":
                request.getSession().setAttribute("lEmail", 1);
                response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                break;
            case "password":
                request.getSession().setAttribute("lPass", 1);
                response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                break;
            case "status":
                request.getSession().setAttribute("status", 1);
                response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
                break;
        }
    }
}
