package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl()  {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS user (" +
                    "user_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "user_name VARCHAR(30),user_lastname VARCHAR(30)," +
                    "user_age INT(3));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute("DROP TABLE IF EXISTS user;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("INSERT INTO user"
                + "(user_name,user_lastname,user_age) VALUES(?,?,?)")){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в таблицу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = Util.getConnection().prepareStatement("DELETE FROM user where user_id=?")){
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()){
                User user = new User(resultSet.getString("user_name"),resultSet.getString("user_lastname"),
                        resultSet.getByte("user_age"));
                user.setId(resultSet.getLong(1));
                userList.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute("DELETE FROM user;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}