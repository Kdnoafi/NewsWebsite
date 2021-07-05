package raf.rs.WEB_Projekat.repositories.vest;

import raf.rs.WEB_Projekat.entities.Vest;
import raf.rs.WEB_Projekat.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVestRepository extends MySqlAbstractRepository implements VestRepository {
    @Override
    public Vest addArticle(Vest article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO articles (naslov, tekst, vremeKreiranja, brojPoseta, autorId, categoryId, tagovi) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)",
                    generatedColumns);

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStatement.setString(1, article.getNaslov());
            preparedStatement.setString(2, article.getTekst());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, article.getAutorId());
            preparedStatement.setInt(6, article.getCategoryId());
            preparedStatement.setString(7, article.getTagovi());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public List<Vest> allArticles() {
        List<Vest> articles = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from articles");
            while (resultSet.next()) {
                articles.add(new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("tekst"),
                        resultSet.getInt("autorId"), resultSet.getDate("vremeKreiranja").toLocalDate(),
                        resultSet.getInt("brojposeta"), resultSet.getInt("categoryId"), resultSet.getString("tagovi")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public Vest findArticle(Integer id) {
        Vest article = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int articleId = resultSet.getInt("id");
                String naslov = resultSet.getString("naslov");
                String tekst = resultSet.getString("tekst");
                java.sql.Date datum = resultSet.getDate("vremeKreiranja");
                int brojPoseta = resultSet.getInt("brojPoseta");
                int autorId = resultSet.getInt("autorId");
                int categoryId = resultSet.getInt("categoryId");
                String tagovi = resultSet.getString("tagovi");
                article = new Vest(articleId, naslov, tekst, autorId, datum.toLocalDate(), brojPoseta, categoryId, tagovi);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public void deleteArticle(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM articles where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public Vest updateArticle(Vest article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "UPDATE articles SET naslov=?, tekst=?, vremeKreiranja=?, categoryId=? " +
                            "WHERE id=?",
                    generatedColumns);

            java.util.Date date = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStatement.setString(1, article.getNaslov());
            preparedStatement.setString(2, article.getTekst());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setInt(4, article.getId());
            preparedStatement.setInt(5, article.getCategoryId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public void updateVisits(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "UPDATE articles SET brojPoseta=? " +
                            "WHERE id=?",
                    generatedColumns);

            Vest article = findArticle(id);

            preparedStatement.setInt(1, article.getBrojPoseta() + 1);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Vest> articlesByCategory(Integer categoryId) {
        List<Vest> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from articles where categoryId=?");
            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();
            //resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                articles.add(new Vest(resultSet.getInt("id"), resultSet.getString("naslov"), resultSet.getString("tekst"),
                        resultSet.getInt("autorId"), resultSet.getDate("vremeKreiranja").toLocalDate(),
                        resultSet.getInt("brojposeta"), resultSet.getInt("categoryId"), resultSet.getString("tagovi")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }
}
