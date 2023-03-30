package es.unican.empresariales.julio.polaflix.entities;
import java.util.Objects;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double totalCost;
    private int month;
    private int year;
    private BillStatus status;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<BillLine> lines;

    public Bill() {
        
    }
    
    public Bill(int month, int year, User user) {
        totalCost = 0.0;
        this.month = month;
        this.year = year; 
        this.user = user;
        status = BillStatus.INPROGRESS;
        lines = new ArrayList<BillLine>();
    }

    //Getters & Setters
    public double getTotalCost() {
        return totalCost;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setBillStatus(BillStatus status) {
        this.status = status;
    }

    /**************************************************************** */

    public void calculateBill() {
        if(user instanceof MonthlyUser)
            totalCost = ((MonthlyUser)user).getFee();
        else {
            for(BillLine line: lines) {
                totalCost += line.getCharge();
            }
        }
    }

    public void addBillLine(BillLine line) {
        lines.add(line);
        line.setBill(this);
    }

    public void removeBillLine(BillLine line) {
        lines.add(line);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Bill that = (Bill) o;
        return Objects.equals(this.totalCost, that.totalCost)
            && Objects.equals(this.user, that.user)
            && Objects.equals(this.lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCost, user, lines);
    }


    
}