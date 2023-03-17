package es.unican.empresariales.julio.polaflix;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class User {

    private String name;
    private String password;
    private String iban;
    private UserType type;
    private ArrayList<Series> startedSeries;
    private ArrayList<Series> pendingSeries;
    private ArrayList<Series> finishedSeries;
    private ArrayList<Bill> billsPaid;
    private ArrayList<Bill> pendingBills;

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
    
    public void ArrayListName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void ArrayListPassword(String password) {
        this.password = password;
    }

    public String getIban() {
        return iban;
    }

    public void ArrayListIban(String iban) {
        this.iban = iban;
    }

    public UserType getUserType() {
        return type;
    }

    public void ArrayListUserType(UserType type) {
        this.type = type;
    }

    public ArrayList<Bill> getBillsPaid() {
        return billsPaid;
    }

    public ArrayList<Bill> getPendingBills() {
        return pendingBills;
    }
    
    /**************************************************************** */

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