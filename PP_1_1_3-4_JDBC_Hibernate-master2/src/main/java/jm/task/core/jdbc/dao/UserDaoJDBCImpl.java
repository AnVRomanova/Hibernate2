package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util2;
import jm.task.core.jdbc.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util2 implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)";

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Database has been created!");
        } catch(SQLException e) {
            System.out.println("Connection failed...");
            throw e;

        }

    }





    public void dropUsersTable() {

        try(Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("удалена");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();

        } catch(SQLException e) {
            throw e;
        }

    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM users where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql) ){
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }


    }

    public List<User> getAllUsers() throws SQLException {

        List<User> usersList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from users ";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user =  new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }

        } catch (SQLException e) {
            throw e;
        }
        return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "TRUNCATE TABLE mydbbase.users";

        try (Statement statement = connection.createStatement()){

            statement.execute(sql);

        } catch (SQLException e) {
            throw e;
        }
    }

}
