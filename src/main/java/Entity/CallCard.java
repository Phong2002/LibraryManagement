

package Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Phong Lu Minh
 */
public class CallCard {
    private String callCardId;
    private User user;
    private Book book;
    private int borrowedDay;
    private LocalDate borrowedDate;

    public CallCard() {
    }

    public CallCard(String callCardId, User user, Book book, int borrowedDay, LocalDate borrowedDate) {
        this.callCardId = callCardId;
        this.user = user;
        this.book = book;
        this.borrowedDay = borrowedDay;
        this.borrowedDate = borrowedDate;
    }

    public String getCallCardId() {
        return callCardId;
    }

    public void setCallCardId(String callCardId) {
        this.callCardId = callCardId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBorrowedDay() {
        return borrowedDay;
    }

    public void setBorrowedDay(int borrowedDay) {
        this.borrowedDay = borrowedDay;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
    
    

}

