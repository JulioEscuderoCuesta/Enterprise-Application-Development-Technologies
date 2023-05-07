package es.unican.empresariales.julio.polaflix.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.empresariales.julio.polaflix.Views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @JsonIgnore
    private Long id;
    
    @JsonView({Views.UserView.class})
    private String name;
    private String password;
    private String iban;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonView({Views.UserView.class})
    private List<Series> startedSeries;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonView({Views.UserView.class})
    private List<Series> pendingSeries;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonView({Views.UserView.class})
    private List<Series> finishedSeries;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonView({Views.UserChaptersWatched.class})
    private Set<Chapter> chaptersWatched;
    @OneToMany(mappedBy = "who", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonView({Views.UserBills.class})
    private Set<Bill> bills;

    protected User() {

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

    public List<Series> getPendingSeries() {
        return pendingSeries;
    }

    public List<Series> getStartedSeries() {
        return startedSeries;
    }

    public List<Series> getFinishedSeries() {
        return finishedSeries;
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
        BillLine newBillLine;
        Bill currentBill = getBillCurrentMonth();
        if(currentBill == null) {
            LocalDate currentDate = LocalDate.now();
            newBillLine = new BillLine(LocalDate.now(), chapter);
            currentBill = new Bill(currentDate.getMonthValue(), currentDate.getYear(), this, newBillLine);
            newBillLine.setBill(currentBill);
            bills.add(currentBill); 
        } else {
            newBillLine = new BillLine(LocalDate.now(), chapter, currentBill);
            currentBill.addBillLine(newBillLine);
        }

        //Adds chapter to list of chapters watched
        chaptersWatched.add(chapter);

        //Add this user to list of users who whatched the chapter
        chapter.getWatchedBy().add(this);

        //Mark series as started
        if(!startedSeries.contains(chapter.getSeason().getSeries()))
            addSeriesToStartedSeries(chapter.getSeason().getSeries());

        //Check if it is the last of the series
        if(chapter.isTheLast())
            addSeriesToFinishedSeries(chapter.getSeason().getSeries());
        
    }

    /**
     * Gets user's bill of the current month
     * @return
     */
    public Bill getBillCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        for(Bill bill : bills) {
            if(bill.getWhichYear() == currentDate.getYear() && bill.getWhichMonth() == currentDate.getMonthValue())
                return bill;
        }
        return null;

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


    /**
    * Add a series to the pending series list if it does not exist in the started or finished series lists.
    * @param series the series to be added to the pending list.
    */
    public void addSeriesToPendingSeries(Series series) {
        if(!startedSeries.contains(series) && !finishedSeries.contains(series))
            pendingSeries.add(series);
    }

    /**
    * Add a series to the started series list, and remove it from the pending series list if it exists.
    * If the user has already finished the series and starts it again, it will be marked as started.
    * @param series the series to be added to the started list.
    */
    public void addSeriesToStartedSeries(Series series) {
        startedSeries.add(series);
        if(pendingSeries.contains(series))
            pendingSeries.remove(series);
        //Si el usuario terminó la serie y la vuelve a empezar, ¿cómo marco la serie?
        
    }
    /**
    * Add a series to the finished series list.
    * @param series the series to be added to the finished list.
    */
    public void addSeriesToFinishedSeries(Series series) {
            startedSeries.remove(series);
            finishedSeries.add(series);
        
    }



}