package es.unican.empresariales.julio.polaflix;

import java.util.Date;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
@Table(name = "bill")
public class BillLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    private Date visualizationDate;
    private String seriesName;
    private String seasonName;
    private int chapterNumber;
    private double cost;

    @ManyToOne
    private Bill bill;

    public BillLine(Date visualizationDate, String seriesName, String seasonName, int chapterNumber, double cost, Bill bill) {
        this.visualizationDate = visualizationDate;
        this.seriesName = seriesName;
        this.seasonName = seasonName;
        this.chapterNumber = chapterNumber;
        this.cost = cost;
        this.bill = bill;
        bill.addBillLine(this);
    }

    //Getters & Setters
    public Date getVisualizationDate() {
        return visualizationDate;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public double getCost() {
        return cost;
    }

    public Bill getBill() {
        return bill;
    }

    /**************************************************************** */



}