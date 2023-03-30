package es.unican.empresariales.julio.polaflix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {

    @Id
    private String name;
    @Id
    private String password;
    private String iban;
    @OneToMany
    private List<Series> startedSeries;
    @OneToMany
    private List<Series> pendingSeries;
    @OneToMany
    private List<Series> finishedSeries;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ArrayList<Chapter> chaptersWatched;
    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    /**
     * 
     */
    public User(String name, String password, String iban) {
        this.name = name;
        this.password = password;
        this.iban = iban;
        startedSeries = new ArrayList<Series>();
        pendingSeries = new ArrayList<Series>();
        finishedSeries = new ArrayList<Series>();
        chaptersWatched = new ArrayList<>();
        bills = new ArrayList<Bill>();
    }

    //Getters & ArrayListters
    public String getName() {
        return name;
    } 
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public List<Chapter> getChaptersWatched() {
        return chaptersWatched;
    }
    
    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(this.name, that.name)
            && Objects.equals(this.password, that.password);
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public void addChapterWatched(Chapter chapter) {
        chaptersWatched.add(chapter);
        if(chapter.isTheLast())
            addSeriesToFinishedSeries(chapter.getSeason().getSeries());
        
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public Bill getBillCurrentMonth() {
        List<Bill> pendingBills = new ArrayList<Bill>();
        for(Bill bill : bills) {
            if(bill.getStatus().equals(BillStatus.PENDING))
                pendingBills.add(bill);
        }

        //If pendingBills not empty
        return pendingBills.get(0);

    }

    public Bill getBillPerMonth(int month, int year) {
        Bill billOfTheMonth = null;
        ArrayList<Bill> billsOfTheYear = new ArrayList<Bill>();
        for(Bill bill : bills) {
            if(bill.getYear() == year)
                billsOfTheYear.add(bill);
        }

        for(Bill bill : billsOfTheYear) {
            if(bill.getMonth() == month)
                billOfTheMonth = bill;
        }

        return billOfTheMonth;
    }


    public void addSeriesToPendingSeries(Series series) {
        pendingSeries.add(series);
    }

    public void addSeriesToStartedSeries(Series series) {
        startedSeries.add(series);
    }

    private void addSeriesToFinishedSeries(Series series) {
        finishedSeries.add(series);
    }



}