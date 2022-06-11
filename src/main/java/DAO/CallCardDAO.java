package DAO;

import Entity.Book;
import Entity.CallCard;
import Entity.User;
import Form.FilterForm;
import Ultis.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phong Lu Minh
 */
public class CallCardDAO {

    public void save(CallCard callCard) {
        String SQL = "INSERT INTO `CallCard`(`libraryCard`,`bookId`,`borrowedDay`,`borrowedDate`) VALUES(?,?,?,?)";
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, callCard.getUser().getLibraryCard());
            preparedStatement.setString(2, callCard.getBook().getBookId());
            preparedStatement.setInt(3, callCard.getBorrowedDay());
            preparedStatement.setString(4, callCard.getBorrowedDate().toString());
            int result = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CallCard findByCallCardId(String callCardId) {
        CallCard callCard = new CallCard();
        UserDAO userDAO = new UserDAO();
        BooksDAO booksDAO = new BooksDAO();
        String SQL = "SELECT * FROM `CallCard` WHERE `callCardId` = ?";

        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, callCardId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                callCard.setCallCardId(resultSet.getString("callCardId"));
                Book book = booksDAO.findByBookId(resultSet.getString("bookId"));
                User user = userDAO.findByLibraryCard(resultSet.getString("libraryCard"));
                callCard.setBook(book);
                callCard.setUser(user);
                callCard.setBorrowedDay(resultSet.getInt("borrowedDay"));
                callCard.setBorrowedDate(LocalDate.parse(resultSet.getString("borrowedDate")));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callCard;
    }

    public List<CallCard> findAll(FilterForm filterForm) {
        List<CallCard> listCallCard = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        BooksDAO booksDAO = new BooksDAO();

        String WHERE = "";
        String SUBWHERE = "";

        if (!filterForm.getSearch().equals("")) {
            WHERE += " WHERE `libraryCard` IN "
                    + "(SELECT `libraryCard` FROM `User` WHERE `fullname` like '%" + filterForm.getSearch() + "%') ";
        }

        if (!filterForm.getFilter().equals("Tất cả")) {
            SUBWHERE += "WHERE `status` = '" + filterForm.getFilter() + "'";
        }

        if (filterForm.getTypeFilter().equals("Chưa trả")) {
            if (WHERE.equals("")) {
                WHERE += " WHERE `callCardId` NOT IN "
                        + "(SELECT `callCardId` FROM `ReturnBook`)";
            } else {
                WHERE += " AND `callCardId` NOT IN "
                        + "(SELECT `callCardId` FROM `ReturnBook`)";
            }
        }

        if (filterForm.getTypeFilter().equals("Đã trả")) {
            if (WHERE.equals("")) {
                WHERE += " WHERE `callCardId` IN "
                        + "(SELECT `callCardId` FROM `ReturnBook`" + SUBWHERE + " )";
            } else {
                WHERE += " AND `callCardId` IN "
                        + "(SELECT `callCardId` FROM `ReturnBook` " + SUBWHERE + " )";
            }
        }

        String SQL = "SELECT * FROM `CallCard` " + WHERE;
        System.out.println(SQL);
        try {
            Connection connection = JDBC.Connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                CallCard callCard = new CallCard();
                callCard.setCallCardId(resultSet.getString("callCardId"));
                Book book = booksDAO.findByBookId(resultSet.getString("bookId"));
                User user = userDAO.findByLibraryCard(resultSet.getString("libraryCard"));
                callCard.setBook(book);
                callCard.setUser(user);
                callCard.setBorrowedDay(resultSet.getInt("borrowedDay"));
                callCard.setBorrowedDate(LocalDate.parse(resultSet.getString("borrowedDate")));
                listCallCard.add(callCard);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCallCard;
    }

    public int numberOfBorrowingBooksByUser(String libraryCard) {
        String SQL = "SELECT * from `CallCard` WHERE `libraryCard` = ? AND `callCardId` NOT IN "
                + "(SELECT `callCardId` FROM `ReturnBook`)";
        int result = 0;
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, libraryCard);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ++result;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int numberOfBorrowingBooksByBookId(String bookId) {
        String SQL = "SELECT * from `CallCard` WHERE `bookId` = ? AND `callCardId` NOT IN "
                + "(SELECT `callCardId` FROM `ReturnBook`)";
        int result = 0;
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ++result;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<CallCard> findByLibraryCard(String libraryCard) {
        String SQL = "SELECT * from `CallCard` WHERE `libraryCard` = ? ";
        List<CallCard> callCards = new ArrayList<>();
        BooksDAO booksDAO = new BooksDAO();
        UserDAO userDAO = new UserDAO();
        try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, libraryCard);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CallCard callCard = new CallCard();
                callCard.setCallCardId(resultSet.getString("callCardId"));
                Book book = booksDAO.findByBookId(resultSet.getString("bookId"));
                User user = userDAO.findByLibraryCard(resultSet.getString("libraryCard"));
                callCard.setBook(book);
                callCard.setUser(user);
                callCard.setBorrowedDay(resultSet.getInt("borrowedDay"));
                callCard.setBorrowedDate(LocalDate.parse(resultSet.getString("borrowedDate")));
                callCards.add(callCard);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return callCards;
    }
}
