

package DAO;

import Entity.CallCard;
import Entity.ReturnBook;
import Ultis.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Phong Lu Minh
 */
public class ReturnBookDAO {

public ReturnBook findByCallCardId(String callCardId){
    ReturnBook returnBook = new ReturnBook();
    String SQL = "SELECT*FROM `ReturnBook` WHERE `callCardId` = ?";
    CallCardDAO callCardDAO = new CallCardDAO();
    
    try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, callCardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CallCard callCard = callCardDAO.findByCallCardId(resultSet.getString("callCardId"));
                returnBook.setReturnBookId(resultSet.getString("returnBookId"));
                returnBook.setCallCard(callCard);
                returnBook.setPayDay(LocalDate.parse(resultSet.getString("payDay")));
                returnBook.setStatus(resultSet.getString("status"));
                returnBook.setNote(resultSet.getString("note"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return returnBook;
}

public void save(ReturnBook returnBook){
    String SQL = "INSERT INTO `ReturnBook`(`callCardId`,`payDay`,`status`,`fines`,`note`) VALUES(?,?,?,?,?)";
    
    try {
            Connection connection = JDBC.Connection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,returnBook.getCallCard().getCallCardId());
            preparedStatement.setString(2,returnBook.getPayDay().toString());
            preparedStatement.setString(3, returnBook.getStatus());
            preparedStatement.setInt(4,returnBook.getFines());
            preparedStatement.setString(5,returnBook.getNote());
            int result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
