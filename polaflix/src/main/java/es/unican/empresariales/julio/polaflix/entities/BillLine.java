package es.unican.empresariales.julio.polaflix.entities;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
@Table(name = "BillLines")
public class BillLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    private Date visualizationDate;
    private Chapter chapterCharged;
    private double charge;
    @ManyToOne
    private Bill bill;

    public BillLine(Date visualizationDate, Chapter chapterCharged) {
        this.visualizationDate = visualizationDate;
        this.chapterCharged = chapterCharged;
        charge = this.chapterCharged.getSeason().getSeries().getCategorie().getPricePerChapter();
    }

    //Getters & Setters
    public Date getVisualizationDate() {
        return visualizationDate;
    }
    
    public Chapter getChapterCharged() {
        return chapterCharged;
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
            && Objects.equals(this.chapterCharged, that.chapterCharged)
            && Objects.equals(this.charge, that.charge)
            && Objects.equals(this.bill, that.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visualizationDate, chapterCharged, charge, bill);
    }

}