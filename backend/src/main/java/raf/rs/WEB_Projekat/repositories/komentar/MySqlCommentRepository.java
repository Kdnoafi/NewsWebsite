package raf.rs.WEB_Projekat.repositories.komentar;

import raf.rs.WEB_Projekat.entities.Komentar;
import raf.rs.WEB_Projekat.entities.Vest;
import raf.rs.WEB_Projekat.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {

    @Override
    public Komentar addComment(Komentar comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO comments (ime_autora, tekst, datum, articleId) " +
                            "VALUES(?, ?, ?, ?)",
                    generatedColumns);

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStatement.setString(1, comment.getImeAutora());
            preparedStatement.setString(2, comment.getTekst());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, comment.getArticleId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Komentar> allCommentsForArticle(Integer articleId) {
        List<Komentar> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from comments where articleId=?");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments.add(new Komentar(resultSet.getInt("id"), resultSet.getString("ime_autora"),
                        resultSet.getString("tekst"), resultSet.getDate("datum").toLocalDate(),
                        resultSet.getInt("articleId")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
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
