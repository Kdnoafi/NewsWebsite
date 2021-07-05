package raf.rs.WEB_Projekat.servlets;

import raf.rs.WEB_Projekat.repositories.vest.MySqlVestRepository;
import raf.rs.WEB_Projekat.repositories.vest.VestRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SingleArticleServlet", value = "/articles/*")
public class SingleArticleServlet extends HttpServlet {

    private VestRepository articleRepository;

    @Override
    public void init() {
        this.articleRepository = new MySqlVestRepository();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(request.getPathInfo().lastIndexOf('/') + 1));
        articleRepository.updateVisits(id);
        //request.getSession().setAttribute("postId", id);

       // request.getRequestDispatcher("/single-article.jsp").forward(request, response);
    }
}
