package es.unican.empresariales.julio.polaflix;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Table;

import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
@Table(name = "user")
public class User {

    @Id
    private String name;
    @Id
    private String password;
    private String iban;
    @Enumerated(EnumType.STRING)
    private UserType type;
    @OneToMany
    private List<Series> startedSeries;
    @OneToMany
    private List<Series> pendingSeries;
    @OneToMany
    private List<Series> finishedSeries;
    @OneToMany(mappedBy = "user")
    private List<Bill> billsPaid;
    @OneToMany(mappedBy = "user")
    private List<Bill> pendingBills;

    /**
     * 
     */
    public User(String name, String password, String iban, UserType type) {
        this.name = name;
        this.password = password;
        this.iban =iban;
        this.type = type;
        startedSeries = new ArrayList<Series>();
        pendingSeries = new ArrayList<Series>();
        finishedSeries = new ArrayList<Series>();
        billsPaid = new ArrayList<Bill>();
        pendingBills = new ArrayList<Bill>();
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

    public UserType getUserType() {
        return type;
    }

    public void setUserType(UserType type) {
        this.type = type;
    }

    public List<Bill> getBillsPaid() {
        return billsPaid;
    }

    public List<Bill> getPendingBills() {
        return pendingBills;
    }
    
    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return super.equals(that)
            && Objects.equals(this.name, that.name)
            && Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public Bill getBillCurrentMonth() {
        return pendingBills.get(pendingBills.size() - 1);

    }

    public Bill getBillPerMonth(String month) {
        Bill billOfThatMonth = null;
        ArrayList<Bill> auxList = new ArrayList<Bill>();
        auxList.addAll(pendingBills);
        auxList.addAll(billsPaid);

        Calendar cal = Calendar.getInstance();
        String monthInString;

        for(Bill bill: auxList) {
            cal.setTime(bill.getReleaseDate());
            monthInString = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            if(monthInString == month)
                billOfThatMonth = bill;
        }
        return billOfThatMonth;
    }

    public void addBillToPendingBills(Bill bill) {
        pendingBills.add(bill);
    }

    public void addBillToBillsPaid(Bill bill) {
        billsPaid.add(bill);
    }

    public void addSeriesToPendingSeries(Series series) {
        pendingSeries.add(series);
    }

    public void addSeriesToStartedSeries(Series series) {
        startedSeries.add(series);
    }

    public void addSeriesToFinishedSeries(Series series) {
        finishedSeries.add(series);
    }


}