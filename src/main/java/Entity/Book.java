

package Entity;

/**
 *
 * @author Phong Lu Minh
 */
public class Book {

private String bookId;
private String name;
private String publisher;
private int yearOfPublisher;
private String author;
private String subject;
private int price;
private int totalQuantity;
private String summary;

    public Book() {
    }

    public Book(String bookId, String name, String publisher, int yearOfPublisher, String author, String subject, int price, int totalQuantity, String summary) {
        this.bookId = bookId;
        this.name = name;
        this.publisher = publisher;
        this.yearOfPublisher = yearOfPublisher;
        this.author = author;
        this.subject = subject;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.summary = summary;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearOfPublisher() {
        return yearOfPublisher;
    }

    public void setYearOfPublisher(int yearOfPublisher) {
        this.yearOfPublisher = yearOfPublisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

   

}
