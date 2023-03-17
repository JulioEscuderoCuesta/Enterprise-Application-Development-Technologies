package es.unican.empresariales.julio.polaflix;
import javax.persistence.Entity;
import javax.persistence.Table;

import jakarta.persistence.Id;

@Entity
@Table(name = "categorie")
public abstract class Categorie {

    @Id
    private double pricePerChapter;

    public Categorie(double pricePerChapter) {
        this.pricePerChapter = pricePerChapter;
    }

    public double getPricePerChapter() {
        return pricePerChapter;
    }
}