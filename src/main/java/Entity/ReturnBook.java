

package Entity;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Phong Lu Minh
 */
public class ReturnBook {
    private String returnBookId;
    private CallCard callCard;
    private LocalDate payDay;
    private String status;
    private int fines;
    private String note;

    public ReturnBook(String returnBookId, CallCard callCard, LocalDate payDay, String status, int fines) {
        this.returnBookId = returnBookId;
        this.callCard = callCard;
        this.payDay = payDay;
        this.status = status;
        this.fines = fines;
    }

    public ReturnBook() {
    }

    public String getReturnBookId() {
        return returnBookId;
    }

    public void setReturnBookId(String returnBookId) {
        this.returnBookId = returnBookId;
    }

    public CallCard getCallCard() {
        return callCard;
    }

    public void setCallCard(CallCard callCard) {
        this.callCard = callCard;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public void setPayDay(LocalDate payDay) {
        this.payDay = payDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFines() {
        if(callCard!=null){
        int totalFines = 0;
        totalFines+=numberOfDaysOverdue()*10000;
        
        if(status.equals("Hư hỏng")){
            totalFines+=callCard.getBook().getPrice()/2;
        }
        else if(status.equals("Làm mất")){
            totalFines=callCard.getBook().getPrice()+20000;
        }
        
        if(totalFines>callCard.getBook().getPrice()+20000){
            totalFines=callCard.getBook().getPrice()+20000;
        }
        return totalFines;
    }
    return 0;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    public int numberOfDaysOverdue(){
        if(callCard!=null){
             LocalDate startDate = callCard.getBorrowedDate();
        LocalDate endDate = payDay;
        int numberOfDay = Period.between(startDate, endDate).getDays();
        return (numberOfDay>callCard.getBorrowedDay())?numberOfDay-callCard.getBorrowedDay():0;
        }
        
       return 0;
    }
}
