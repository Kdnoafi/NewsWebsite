package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.repositories.kategorija.KategorijaRepository;
import raf.rs.WEB_Projekat.repositories.kategorija.MySqlKategorijaRepository;
import raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository;
import raf.rs.WEB_Projekat.repositories.vest.VestRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteCategoryServlet", value = "/deleteCategoryServlet/*")
public class DeleteCategoryServlet extends HttpServlet {

    private KategorijaRepository categoryRepository;

    @Override
    public void init() {
        this.categoryRepository = new MySqlKategorijaRepository();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1));
        categoryRepository.deleteCategory(id);

        //response.sendRedirect(getServletContext().getContextPath() + "/categories");
    }
}
