package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository;
import raf.rs.WEB_Projekat.repositories.vest.VestRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteArticleServlet", value = "/deleteArticleServlet/*")
public class DeleteArticleServlet extends HttpServlet {

    private VestRepository articleRepository;

    @Override
    public void init() {
        this.articleRepository = new MySqlVestRepository();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1));
        articleRepository.deleteArticle(id);

       // response.sendRedirect(getServletContext().getContextPath() + "/articles");
    }
}
