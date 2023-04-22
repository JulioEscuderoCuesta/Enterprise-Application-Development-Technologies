package es.unican.empresariales.julio.polaflix.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class BillLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    private LocalDate visualizationDate;
    private int chapterNumber;
    private int seasonNumber;
    private String seriesName;
    private double charge;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bill bill;

    private BillLine() {
        
    }

    public BillLine(LocalDate localDate, Chapter chapterCharged) {
        this.visualizationDate = localDate;
        this.chapterNumber = chapterCharged.getNumber();
        this.seasonNumber = chapterCharged.getSeason().getNumber();
        this.seriesName = chapterCharged.getSeason().getSeries().getName();
        charge = chapterCharged.getSeason().getSeries().getCategorie().getPricePerChapter();
    }

    //Getters & Setters
    public LocalDate getVisualizationDate() {
        return visualizationDate;
    }
    
    public int getChapterNumber() {
        return chapterNumber;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public double getCharge() {
        return charge;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BillLine that = (BillLine) o;
        return Objects.equals(this.visualizationDate, that.visualizationDate)
            && Objects.equals(this.charge, that.charge)
            && Objects.equals(this.bill, that.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visualizationDate, charge, bill);
    }

}