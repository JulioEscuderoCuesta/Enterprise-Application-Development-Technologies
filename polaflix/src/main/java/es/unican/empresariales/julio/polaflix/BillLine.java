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
    private Chapter chapterCharged;
    @ManyToOne
    private Bill bill;

    public BillLine(Date visualizationDate, Chapter chapterCharged) {
        this.visualizationDate = visualizationDate;
        this.chapterCharged = chapterCharged;
    }

    //Getters & Setters
    public Date getVisualizationDate() {
        return visualizationDate;
    }
    
    public Chapter getChapterCharged() {
        return chapterCharged;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    /**************************************************************** */



}