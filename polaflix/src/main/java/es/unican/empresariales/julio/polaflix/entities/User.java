package es.unican.empresariales.julio.polaflix.entities;

import java.util.Set;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Series> startedSeries;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Series> pendingSeries;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Series> finishedSeries;
    @ManyToMany(mappedBy = "watchedBy")
    private Set<Chapter> chaptersWatched;
    @OneToMany(mappedBy = "who")
    private Set<Bill> bills;

    private User() {

    }

    /**
     * 
     */
    public User(String name, String password, String iban) {
        this.name = name;
        this.password = password;
        this.iban = iban;
        startedSeries = new ArrayList<>();
        pendingSeries = new ArrayList<>();
        finishedSeries = new ArrayList<>();
        chaptersWatched = new HashSet<>();
        bills = new HashSet<Bill>();
    }

    //Getters & Setters
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

    public Set<Bill> getBills() {
        return bills;
    }

    public Set<Chapter> getChaptersWatched() {
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

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    /**
     * Adds a chapter that has been watched to the list of watched chapters for this user.
     * Also creates a new bill line with the date of today and the given chapter and adds it to the current month's bill.
     * If there is no current month's bill, it creates a new bill for the current month and adds the new bill line to it.
     * Additionally, marks the series that the chapter belongs to as started and adds the chapter to the list of watched chapters.
     * If the chapter is the last one in the series, it also adds the series to the list of finished series for the user.
     * @param chapter The chapter that has been watched.
     */
    public void addChapterWatched(Chapter chapter) {
        //charge chapter
        BillLine newBillLine = new BillLine(LocalDate.now(), chapter);
        Bill currentBill = getBillCurrentMonth();
        if(currentBill== null) {
            LocalDate currentDate = LocalDate.now();
            currentBill = new Bill(currentDate.getMonthValue(), currentDate.getYear(), this, newBillLine); 
        } else {
            currentBill.addBillLine(newBillLine);
        }

        //Mark series as started
        addSeriesToStartedSeries(chapter.getSeason().getSeries());

        //Add chapter to chapters watched and check if it is the last of the series
        chaptersWatched.add(chapter);
        if(chapter.isTheLast())
            addSeriesToFinishedSeries(chapter.getSeason().getSeries());
        
    }

    /**
     * Gets user's bill of the currentMonth
     * @return
     */
    public Bill getBillCurrentMonth() {
        Set<Bill> pendingBills = new HashSet<Bill>();
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

    /**
     * 
     * Returns the bill for the specified month and year. If no bill is found for the specified month and year,
     * null is returned.
     * @param month The month for which the bill is requested (1-12).
     * @param year The year for which the bill is requested.
     * @return The bill for the specified month and year, or null if no bill is found.
     */
    public Bill getBillPerMonth(int month, int year) {
        Bill billOfTheMonth = null;
        Set<Bill> billsOfTheYear = new HashSet<Bill>();
        for(Bill bill : bills) {
            if(bill.getWhichYear() == year)
                billsOfTheYear.add(bill);
        }

        for(Bill bill : billsOfTheYear) {
            if(bill.getWhichMonth() == month)
                billOfTheMonth = bill;
        }

        return billOfTheMonth;
    }


    public void addSeriesToPendingSeries(Series series) {
        if(!startedSeries.contains(series) && !finishedSeries.contains(series))
            pendingSeries.add(series);
    }

    public void addSeriesToStartedSeries(Series series) {
        if(pendingSeries.contains(series)){
            pendingSeries.remove(series);
            startedSeries.add(series);
        }
    }

    public void addSeriesToFinishedSeries(Series series) {
        if(startedSeries.contains(series)) {
            startedSeries.remove(series);
            finishedSeries.add(series);
        }
    }



}