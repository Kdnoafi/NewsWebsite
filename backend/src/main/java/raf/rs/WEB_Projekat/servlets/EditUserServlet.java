package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editUserServlet", value = "/editUserServlet/*")
public class EditUserServlet extends HttpServlet {

    private KorisnikRepository userRepository;

    @Override
    public void init() {
        this.userRepository = new MySqlKorisnikRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        String email = request.getParameter("email");
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String tip = request.getParameter("tip");

        Korisnik korisnik = userRepository.findUser(id);
        korisnik.setEmail(email);
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setTip(tip);
        userRepository.updateUser(korisnik);

       // response.sendRedirect(getServletContext().getContextPath() + "/users");
    }
}
