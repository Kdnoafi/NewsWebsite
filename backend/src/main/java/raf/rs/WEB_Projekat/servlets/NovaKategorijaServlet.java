package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.repositories.kategorija.InMemoryKategorijaRepository;
import raf.rs.WEB_Projekat.repositories.kategorija.KategorijaRepository;
import raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "novaKategorijaServlet", value = "/novaKategorija")
public class NovaKategorijaServlet extends HttpServlet {

    private KategorijaRepository categoryRepository;

    @Override
    public void init() {
        this.categoryRepository = new MySqlKategorijaRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ime = request.getParameter("ime");
        String opis = request.getParameter("opis");
        this.categoryRepository.addCategory(new Kategorija(ime, opis));

       // response.sendRedirect(getServletContext().getContextPath() + "/categories.jsp");
    }
}
