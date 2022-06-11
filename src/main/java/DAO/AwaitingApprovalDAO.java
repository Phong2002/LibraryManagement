package DAO;

import Entity.AwaitingApproval;
import Entity.Book;
import Entity.User;
import Form.FilterForm;
import Ultis.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phong Lu Minh
 */
public class AwaitingApprovalDAO {
    
    public List<AwaitingApproval> findAll (FilterForm filterForm) {
            String SQL = "SELECT * FROM `AwaitingApproval` ";
        if(filterForm.getTypeSearch().equals("Tên Độc Giả")&&!filterForm.getSearch().equals("")){
            SQL = "SELECT * FROM `AwaitingApproval` WHERE `libraryCard` IN "
                    + "(SELECT `libraryCard` FROM `User` WHERE `fullname` LIKE '%"+filterForm.getSearch()+"%');";
        }
        
        if(filterForm.getTypeSearch().equals("Thẻ Thư Viện")&&!filterForm.getSearch().equals("")){
            SQL = "SELECT * FROM `AwaitingApproval` WHERE `libraryCard` IN "
                    + "(SELECT `libraryCard` FROM `User` WHERE `libraryCard` LIKE '%"+filterForm.getSearch()+"%');";
        }
        
        List<AwaitingApproval> awaitingApprovalList = new ArrayList<>();
    
        BooksDAO booksDAO = new BooksDAO();
        UserDAO userDAO = new UserDAO();

        try {
            Connection connection = JDBC.Connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = userDAO.findByLibraryCard(resultSet.getString("libraryCard"));
                Book book = booksDAO.findByBookId(resultSet.getString("bookId"));
                AwaitingApproval awaitingApproval = new AwaitingApproval();
                awaitingApproval.setId(resultSet.getInt("id"));
                awaitingApproval.setBook(book);
                awaitingApproval.setUser(user);
                awaitingApproval.setBorrowedDay(resultSet.getInt("borrowedDay"));
                awaitingApprovalList.add(awaitingApproval);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return awaitingApprovalList;
    }

    public List<AwaitingApproval> findByLibraryCard(String libraryCard) {
        List<AwaitingApproval> awaitingApprovalList = new ArrayList<>();
        String SQL = "SELECT * FROM `AwaitingApproval` WHERE `libraryCard`=? ";
        BooksDAO booksDAO = new BooksDAO();
        UserDAO userDAO = new UserDAO();

        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, libraryCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = userDAO.findByLibraryCard(resultSet.getString("libraryCard"));
                Book book = booksDAO.findByBookId(resultSet.getString("bookId"));
                AwaitingApproval awaitingApproval = new AwaitingApproval();
                awaitingApproval.setId(resultSet.getInt("id"));
                awaitingApproval.setBook(book);
                awaitingApproval.setUser(user);
                awaitingApproval.setBorrowedDay(resultSet.getInt("borrowedDay"));
                awaitingApprovalList.add(awaitingApproval);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return awaitingApprovalList;
    }

    public void save(List<AwaitingApproval> awaitingApprovalList) {
     
        try {
            String SQL = "INSERT INTO `AwaitingApproval`(`id`,`libraryCard`,`bookId`,`borrowedDay`) VALUES(?,?,?,?)";
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            for (AwaitingApproval awaitingApproval : awaitingApprovalList) {
                preparedStatement.setInt(1, awaitingApproval.getId());
                preparedStatement.setString(2, awaitingApproval.getUser().getLibraryCard());
                preparedStatement.setString(3, awaitingApproval.getBook().getBookId());
                preparedStatement.setInt(4, awaitingApproval.getBorrowedDay());
                
                int row = preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByIds(List<Integer> ids) {
        try {
            String SQL = "DELETE FROM `AwaitingApproval` WHERE `id` = ?";
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            for (int id : ids) {
                preparedStatement.setInt(1, id);
                int row = preparedStatement.executeUpdate();
            }
             connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        public void deleteById(int id) {
        try {
            String SQL = "DELETE FROM `AwaitingApproval` WHERE `id` = ?";
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
             connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    public int numberOfAwtingApprovalByUser(String libraryCard){
        String SQL = "SELECT * FROM `AwaitingApproval` WHERE `libraryCard` = ?";
        int result=0;
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, libraryCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ++result;
            }
             connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
