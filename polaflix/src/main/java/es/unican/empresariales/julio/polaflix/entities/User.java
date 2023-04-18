package es.unican.empresariales.julio.polaflix.entities;

import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Objects;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String password;
    private String iban;
    @OneToMany
    private LinkedHashSet<Series> startedSeries;
    @OneToMany
    private LinkedHashSet<Series> pendingSeries;
    @OneToMany
    private LinkedHashSet<Series> finishedSeries;
    @ManyToMany(mappedBy = "usersWhoWatched")
    private LinkedHashSet<Chapter> chaptersWatched;
    @OneToMany(mappedBy = "user")
    private LinkedHashSet<Bill> bills;

    /**
     * 
     */
    public User(String name, String password, String iban) {
        this.name = name;
        this.password = password;
        this.iban = iban;
        startedSeries = new LinkedHashSet<Series>();
        pendingSeries = new LinkedHashSet<Series>();
        finishedSeries = new LinkedHashSet<Series>();
        chaptersWatched = new LinkedHashSet<>();
        bills = new LinkedHashSet<Bill>();
    }

    //Getters & LinkedHashSetters
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

    public LinkedHashSet<Bill> getBills() {
        return bills;
    }

    public LinkedHashSet<Chapter> getChaptersWatched() {
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
        LinkedHashSet<Bill> pendingBills = new LinkedHashSet<Bill>();
        for(Bill bill : bills) {
            if(bill.getStatus().equals(BillStatus.PENDING))
                pendingBills.add(bill);
        }
        //TODO: 
        if(pendingBills.isEmpty())
            throw new IllegalStateException("");

        Iterator<Bill> iterator = pendingBills.iterator();
        Bill lastBill = null;
        while(iterator.hasNext()) {
            lastBill = iterator.next();
        }
        return lastBill;

    }

    public Bill getBillPerMonth(int month, int year) {
        Bill billOfTheMonth = null;
        LinkedHashSet<Bill> billsOfTheYear = new LinkedHashSet<Bill>();
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
        if(!startedSeries.contains(series) && !finishedSeries.contains(series))
            pendingSeries.add(series);
    }

    public void addSeriesToStartedSeries(Series series) {
        startedSeries.add(series);
    }

    private void addSeriesToFinishedSeries(Series series) {
        finishedSeries.add(series);
    }



}