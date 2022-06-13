package DAO;

import Entity.Book;
import java.util.ArrayList;
import java.util.List;
import Form.FilterForm;
import Ultis.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Phong Lu Minh
 */
public class BooksDAO {

    public List<Book> findAll(FilterForm filter) {
        List<Book> listBook = new ArrayList<>();
        String where = "";
        if (!filter.getSearch().equals("") && !filter.getTypeSearch().equals("")) {
            where += "where " + filter.getTypeSearch() + " LIKE '%" + filter.getSearch() + "%'";

        }

        if (!filter.getTypeFilter().equals("") && !filter.getFilter().equals("")) {
            if (where.equals("")) {
                where += " where " + filter.getTypeFilter() + " = '" + filter.getFilter() + "'";
            } else {
                where += " AND " + filter.getTypeFilter() + " = '" + filter.getFilter() + "'";
            }
        }

        String SQL = "SELECT * FROM `Book` " + where;
        try {
            Connection connection = JDBC.Connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getString("bookId"));
                book.setName(resultSet.getString("name"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYearOfPublisher(resultSet.getInt("yearOfPublisher"));
                book.setAuthor(resultSet.getString("author"));
                book.setSubject(resultSet.getString("subject"));
                book.setPrice(resultSet.getInt("price"));
                book.setTotalQuantity(resultSet.getInt("totalQuantity"));
                book.setSummary(resultSet.getString("summary"));

                listBook.add(book);

            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBook;
    }

    public Book findByBookId(String bookId) {
        Book book = new Book();
        String SQL = "SELECT * FROM `Book` WHERE `bookId` = ?";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book.setBookId(resultSet.getString("bookId"));
                book.setName(resultSet.getString("name"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setYearOfPublisher(resultSet.getInt("yearOfPublisher"));
                book.setAuthor(resultSet.getString("author"));
                book.setSubject(resultSet.getString("subject"));
                book.setPrice(resultSet.getInt("price"));
                book.setTotalQuantity(resultSet.getInt("totalQuantity"));
                book.setSummary(resultSet.getString("summary"));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;

    }

    public boolean save(Book book) {
        String SQL = "INSERT INTO `Book`(`name`,`publisher`,`yearOfPublisher`,`author`,`subject`,`price`,`totalQuantity`,`summary`)VALUES(?,?,?,?,?,?,?,?)";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getPublisher());
            preparedStatement.setInt(3, book.getYearOfPublisher());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setString(5, book.getSubject());
            preparedStatement.setInt(6, book.getPrice());
            preparedStatement.setInt(7, book.getTotalQuantity());
            preparedStatement.setString(8, book.getSummary());
            int result = preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Book book) {
        String SQL = "UPDATE `Book`"
                + "SET `name`=?,`publisher`=?,`yearOfPublisher`=?,`author`=?,`subject`=?,`price`=?,"
                + "`totalQuantity`=?,`summary`=? WHERE `bookId`=?";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getPublisher());
            preparedStatement.setInt(3, book.getYearOfPublisher());
            preparedStatement.setString(4, book.getAuthor());
            preparedStatement.setString(5, book.getSubject());
            preparedStatement.setInt(6, book.getPrice());
            preparedStatement.setInt(7, book.getTotalQuantity());
            preparedStatement.setString(8, book.getSummary());
            preparedStatement.setString(9, book.getBookId());
            int result = preparedStatement.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void delete(String bookId){
        String SQL = "DELETE FROM `Book` WHERE `bookId` = ?" ;  
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,bookId);
            int result = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
