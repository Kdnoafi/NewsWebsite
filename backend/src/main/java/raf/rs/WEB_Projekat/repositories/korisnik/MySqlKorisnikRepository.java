package raf.rs.WEB_Projekat.repositories.korisnik;

import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlKorisnikRepository extends MySqlAbstractRepository implements KorisnikRepository {

    @Override
    public Korisnik addUser(Korisnik user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO users (email, ime, prezime, tip, lozinka, uStatus) VALUES(?, ?, ?, ?, ?, ?)",
                    generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getIme());
            preparedStatement.setString(3, user.getPrezime());
            preparedStatement.setString(4, user.getTip());
            preparedStatement.setString(5, user.getLozinka());
            preparedStatement.setString(6, user.getStatus());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public List<Korisnik> alUsers() {
        List<Korisnik> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                users.add(new Korisnik(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("ime"),
                        resultSet.getString("prezime"), resultSet.getString("tip"), resultSet.getString("lozinka")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public Korisnik findUser(Integer id) {
        Korisnik user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int userId = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("ime");
                String lastName = resultSet.getString("prezime");
                String type = resultSet.getString("tip");
                String password = resultSet.getString("lozinka");
                user = new Korisnik(userId, email, firstName, lastName, type, password);
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

        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM users where id = ?");
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
    public String checkCredentials(String email, String password) {
        boolean exists = false;
        String result = "";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                exists = true;
                String dbPassword = resultSet.getString("lozinka");

                if(password.equals(dbPassword)) {
                    boolean status = resultSet.getBoolean("uStatus");
                    if(status) {
                        result = "login";
                    } else {
                        result = "status";
                    }
                } else {
                    result = "password";
                }
            }
            if(!exists) {
                result = "email";
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

        return result;
    }

    @Override
    public Korisnik findByEmail(String email) {
        Korisnik user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int userId = resultSet.getInt("id");
                String uEmail = resultSet.getString("email");
                String firstName = resultSet.getString("ime");
                String lastName = resultSet.getString("prezime");
                String type = resultSet.getString("tip");
                String password = resultSet.getString("lozinka");
                user = new Korisnik(userId, uEmail, firstName, lastName, type, password);
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

        return user;
    }

    @Override
    public Korisnik updateUser(Korisnik user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "UPDATE users SET email=?, ime=?, prezime=?, tip=?, uStatus=? " +
                            "WHERE id=?",
                    generatedColumns);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getIme());
            preparedStatement.setString(3, user.getPrezime());
            preparedStatement.setString(4, user.getTip());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.setString(6, user.getStatus());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }
}
