

package Entity;

/**
 *
 * @author Phong Lu Minh
 */
public class AwaitingApproval {

private int id;
private User user;
private Book book;
private int borrowedDay;

    public AwaitingApproval(int id, User user, Book book, int borrowedDay) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.borrowedDay = borrowedDay;
    }

    public AwaitingApproval() {
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
