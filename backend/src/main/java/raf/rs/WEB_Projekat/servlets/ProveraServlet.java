package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.korisnik.InMemoryKorisnikrepository;
import raf.rs.WEB_Projekat.repositories.korisnik.KorisnikRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "proveraServlet", value = "/provera")
public class ProveraServlet extends HttpServlet {

    private KorisnikRepository userRepository;

    @Override
    public void init() {
        this.userRepository = new InMemoryKorisnikrepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        for(Korisnik user: userRepository.alUsers()) {
            if(user.getEmail().equals(email)) {
                request.getSession().setAttribute("email", 1);
                response.sendRedirect(getServletContext().getContextPath() + "/prijava.jsp");
                return;
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("prijava");
        rd.forward(request, response);
    }
}
