package raf.rs.WEB_Projekat.repositories.komentar;

import raf.rs.WEB_Projekat.entities.Komentar;
import raf.rs.WEB_Projekat.entities.Vest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryCommentRepository implements CommentRepository {

    private static List<Komentar> comments = new CopyOnWriteArrayList<>();

    public InMemoryCommentRepository() {}

    @Override
    public Komentar addComment(Komentar comment) {
        Integer id = comments.size();
        comment.setId(id);
        comments.add(Math.toIntExact(id), comment);

        return comment;
    }

    @Override
    public List<Komentar> allCommentsForArticle(Integer articleId) {
        return null;
    }

    @Override
    public List<Komentar> allComments() {
        return null;
    }

    @Override
    public Komentar findComment(Integer id) {
        return null;
    }

    @Override
    public void deleteComment(Integer id) {

    }
}
