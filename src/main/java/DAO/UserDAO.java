package DAO;

import Entity.Book;
import Entity.User;
import Form.FilterForm;
import Ultis.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phong Lu Minh
 */
public class UserDAO {

    public User Login(String username, String password) {
        User user = new User();
        String SQL = "SELECT * FROM `User` WHERE `username`=? AND `password`=?";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setLibraryCard(resultSet.getString("libraryCard"));
                user.setFullname(resultSet.getString("fullname"));
                user.setGender(resultSet.getString("gender"));
                user.setDateOfBirth(LocalDate.parse(resultSet.getString("dateOfBirth")));
                user.setRole(resultSet.getString("role"));
                user.setAddress(resultSet.getString("address"));
                user.setUsername(resultSet.getString("username"));
                 user.setNumberPhone(resultSet.getString("numberPhone"));
             user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setDateOfBirth(LocalDate.parse(resultSet.getDate("dateOfBirth").toString()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findByLibraryCard(String libraryCard) {
        User user = new User();
        String SQL = "SELECT * FROM `User` WHERE `libraryCard`=? ";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, libraryCard);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setLibraryCard(resultSet.getString("libraryCard"));
                user.setFullname(resultSet.getString("fullname"));
                user.setGender(resultSet.getString("gender"));
                user.setDateOfBirth(LocalDate.parse(resultSet.getString("dateOfBirth")));
                user.setRole(resultSet.getString("role"));
                user.setAddress(resultSet.getString("address"));
                 user.setNumberPhone(resultSet.getString("numberPhone"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setDateOfBirth(LocalDate.parse(resultSet.getDate("dateOfBirth").toString()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> findAll(FilterForm filter) {
        List<User> listUser = new ArrayList<>();
        String where = "";
        if (!filter.getSearch().equals("") && !filter.getTypeSearch().equals("")) {
            where += "AND " + filter.getTypeSearch() + " LIKE '%" + filter.getSearch() + "%' ";

        }

        String SQL = "SELECT * FROM `User` WHERE `role`='borrower'  " + where;
        System.out.println(SQL);
        try {
            Connection connection = JDBC.Connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setLibraryCard(resultSet.getString("libraryCard"));
                user.setFullname(resultSet.getString("fullname"));
                user.setGender(resultSet.getString("gender"));
                user.setDateOfBirth(LocalDate.parse(resultSet.getString("dateOfBirth")));
                user.setRole(resultSet.getString("role"));
                user.setEmail(resultSet.getString("email"));
                user.setNumberPhone(resultSet.getString("numberPhone"));
                user.setAddress(resultSet.getString("address"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setDateOfBirth(LocalDate.parse(resultSet.getDate("dateOfBirth").toString()));
                listUser.add(user);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listUser;
    }

    public boolean save(User user) {
        String SQL = "INSERT INTO `User` (`fullname`,`gender`,`dateOfBirth`,`address`,`numberPhone`,`email`,`username`,`password`,`role`)VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getGender());
            preparedStatement.setString(3, user.getDateOfBirth().toString());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getNumberPhone());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getUsername());
            preparedStatement.setString(8, user.getPassword());
            preparedStatement.setString(9, user.getRole());
            int result = preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
      public boolean update(User user) {
        String SQL = "UPDATE `User` "
                + "SET `fullname`=?,`gender`=?,`dateOfBirth`=?,`address`=?,"
                + "`numberPhone`=?,`email`=?,`username`=?,`password`=?,`role`=? WHERE `libraryCard` = ?";
          System.out.println(SQL);
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getGender());
            preparedStatement.setString(3, user.getDateOfBirth().toString());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getNumberPhone());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getUsername());
            preparedStatement.setString(8, user.getPassword());
            preparedStatement.setString(9, user.getRole());
            preparedStatement.setString(10, user.getLibraryCard());
            int result = preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
      
      public void deleteByLibraryCard(String libraryCard){
          String SQL = "DELETE FROM `User` where libraryCard = ?";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,libraryCard);
            int result = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      }

}
