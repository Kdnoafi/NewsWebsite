package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.korisnik.InMemoryKorisnikrepository;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository;
import raf.rs.WEB_Projekat.security.PasswordHash;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

@WebServlet(name = "prijavaServlet", value = "/prijava")
public class PrijavaServlet extends HttpServlet {

    private KorisnikRepository mySqlKorisnikRepository;

    @Override
    public void init() {
        this.mySqlKorisnikRepository = new MySqlKorisnikRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String lozinka = request.getParameter("lozinka");
        this.mySqlKorisnikRepository.addUser(
                new Korisnik(email, ime, prezime, "kreator", PasswordHash.sha256(lozinka)));

        //response.sendRedirect(getServletContext().getContextPath() + "/users.jsp");
    }
}
