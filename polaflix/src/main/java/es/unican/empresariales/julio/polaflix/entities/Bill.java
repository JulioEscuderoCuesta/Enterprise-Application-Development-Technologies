package es.unican.empresariales.julio.polaflix.entities;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int whichMonth;
    private int whichYear;
    private BillStatus status;
    @ManyToOne
    private User who;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillLine> lines;

    private Bill() {
        
    }
    
    public Bill(int whichMonth, int whichYear, User who, BillLine line) {
        this.whichMonth = whichMonth;
        this.whichYear = whichYear; 
        this.who = who;
        status = BillStatus.INPROGRESS;
        lines = new ArrayList<BillLine>();
        lines.add(line);
    }

    //Getters & Setters
    public double getTotalCost() {
        return calculateBill();
    }

    public int getWhichMonth() {
        return whichMonth;
    }

    public void setWhichMonth(int whichMonth) {
        this.whichMonth = whichMonth;
    }

    public int getWhichYear() {
        return whichYear;
    }

    public void setWhichYear(int whichYear) {
        this.whichYear = whichYear;
    }

    public User getUser() {
        return who;
    }

    public void setUser(User who) {
        this.who = who;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    /**************************************************************** */

    public double calculateBill() {
        double totalCost = 0.0;
        if(who instanceof MonthlyUser)
            totalCost = ((MonthlyUser)who).getFee();
        else {
            for(BillLine line: lines) {
                totalCost += line.getCharge();
            }
        }
        return totalCost;
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
        return Objects.equals(this.whichMonth, that.whichMonth)
            && Objects.equals(this.whichYear, that.whichYear)
            && Objects.equals(this.who, that.who)
            && Objects.equals(this.lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whichMonth, whichYear, who, lines);
    }


    
}