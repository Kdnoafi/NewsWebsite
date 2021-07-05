package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;
import raf.rs.WEB_Projekat.repositories.korisnik.MySqlKorisnikRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "activateUserServlet", value = "/activateUserServlet/*")
public class UserActivationServlet extends HttpServlet {

    private KorisnikRepository userRepository;

    @Override
    public void init() {
        this.userRepository = new MySqlKorisnikRepository();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));

        Korisnik user = (Korisnik) request.getSession().getAttribute("loggedIn");
        if(user == null) {
            response.getWriter().write("You need to log in");
            return;
        }

        Korisnik korisnik = userRepository.findUser(id);
        String status = "1";
        if(korisnik.getStatus().equals("1")) {
            status = "0";
        }
        korisnik.setStatus(status);
        userRepository.updateUser(korisnik);

        //response.sendRedirect(getServletContext().getContextPath() + "/users");
    }
}
