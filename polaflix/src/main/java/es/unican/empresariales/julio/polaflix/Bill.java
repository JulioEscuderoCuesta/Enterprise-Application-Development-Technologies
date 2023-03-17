package es.unican.empresariales.julio.polaflix;
import java.util.Date;
import java.util.Set;
import java.util.ArrayList;

public class Bill {

    private static final double MONTHLYFEE = 20;
    
    private double totalCost;
    private Date releaseDate;
    private Date paymentDay;
    private User user;
    private ArrayList<BillLine> lines;

    public Bill(Date releaseDate,  User user) {
        this.releaseDate = releaseDate;
        this.user = user;
        lines = new ArrayList<BillLine>();
        totalCost = 0.0;
    }

    //Getters & Setters
    public double getTotalCost() {
        return totalCost;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Date getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Date paymentDay) {
        this.paymentDay = paymentDay;
    }

    public User getUser() {
        return user;
    }

    /**************************************************************** */

    public void calculateBill() {
        if(user.getUserType().equals(UserType.MONTHLYUSER))
            totalCost = MONTHLYFEE;
        else {
            for(BillLine line: lines) {
                totalCost += line.getCost();
            }
        }
    }

    public void addBillLine(BillLine line) {
        lines.add(line);
    }

    public void removeBillLine(BillLine line) {
        lines.add(line);
    }

    
}