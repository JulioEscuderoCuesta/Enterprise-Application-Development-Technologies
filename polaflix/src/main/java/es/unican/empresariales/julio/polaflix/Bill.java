package es.unican.empresariales.julio.polaflix;
import java.util.Date;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Bill that = (Bill) o;
        return super.equals(that)
            && Objects.equals(this.totalCost, that.totalCost)
            && Objects.equals(this.releaseDate, that.releaseDate)
            && Objects.equals(this.paymentDay, that.paymentDay)
            && Objects.equals(this.user, that.user)
            && Objects.equals(this.lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCost, releaseDate, paymentDay, user, lines);
    }

    
}