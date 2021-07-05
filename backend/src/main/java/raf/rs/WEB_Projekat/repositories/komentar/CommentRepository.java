package raf.rs.WEB_Projekat.repositories.komentar;

import raf.rs.WEB_Projekat.entities.Komentar;
import raf.rs.WEB_Projekat.entities.Vest;

import java.util.List;

public interface CommentRepository {
    public Komentar addComment(Komentar comment);
    public List<Komentar> allCommentsForArticle(Integer articleId);
    public List<Komentar> allComments();
    public Komentar findComment(Integer id);
    public void deleteComment(Integer id);
}
