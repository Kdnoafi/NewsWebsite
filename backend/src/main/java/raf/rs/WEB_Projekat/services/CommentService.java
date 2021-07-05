package raf.rs.WEB_Projekat.services;

import raf.rs.WEB_Projekat.entities.Komentar;
import raf.rs.WEB_Projekat.repositories.komentar.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    public CommentService() {}

    @Inject
    private CommentRepository commentRepository;

    public Komentar addComment(Komentar comment) {
        return this.commentRepository.addComment(comment);
    }

    public List<Komentar> allComments(Integer articleId) {
        return this.commentRepository.allCommentsForArticle(articleId);
    }
}
