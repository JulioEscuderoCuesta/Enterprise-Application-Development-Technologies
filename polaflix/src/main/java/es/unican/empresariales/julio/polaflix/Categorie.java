package es.unican.empresariales.julio.polaflix;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Table(name = "categorie")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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