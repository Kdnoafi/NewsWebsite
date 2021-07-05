package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.kategorija.KategorijaRepository;
import raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editCategoryServlet", value = "/editCategoryServlet/*")
public class EditCategoryServlet extends HttpServlet {

    private KategorijaRepository categoryRepository;

    @Override
    public void init() {
        this.categoryRepository = new MySqlKategorijaRepository();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        String ime = request.getParameter("ime");
        String opis = request.getParameter("opis");

        Kategorija category = categoryRepository.findCategory(id);
        category.setIme(ime);
        category.setOpis(opis);
        categoryRepository.updateCategory(category);

       // response.sendRedirect(getServletContext().getContextPath() + "/categories");
    }
}
