package es.unican.empresariales.julio.polaflix;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import es.unican.empresariales.julio.polaflix.repositories.BillRepository;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "bill")
@IdClass(CompoundIdBill.class)
public class Bill {

    private static final double MONTHLYFEE = 20;

    private double totalCost;
    @Id
    private Date releaseDate;
    private Date paymentDay;
    @Id
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<BillLine> lines;

    @Autowired
    private BillRepository billRepository;

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
            && Objects.equals(this.user, that.user)
            && Objects.equals(this.lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCost, releaseDate, user, lines);
    }

    public void findByUserId(CompoundIdUser userId) throws IllegalArgumentException {
        List<Bill> bills = billRepository.findByUserId(userId);
    }

    
}