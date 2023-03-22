package es.unican.empresariales.julio.polaflix;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import es.unican.empresariales.julio.polaflix.repositories.BillRepository;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double totalCost;
    @Id
    private int month;
    @Id
    private int year;
    private BillStatus status;
    @ManyToOne
    @Id
    private User user;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<BillLine> lines;

    @Autowired
    private BillRepository billRepository;

    public Bill(int month, int year) {
        totalCost = 0.0;
        this.month = month;
        this.year = year; 
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

    public int getYear() {
        return year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                totalCost += line.getChapterCharged().getSeason().getSeries().getCategorie().getPricePerChapter();
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
        return super.equals(that)
            && Objects.equals(this.totalCost, that.totalCost)
            && Objects.equals(this.user, that.user)
            && Objects.equals(this.lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCost, user, lines);
    }

    public void findByUserId(CompoundIdUser userId) throws IllegalArgumentException {
        List<Bill> bills = billRepository.findByUserId(userId);
    }

    
}