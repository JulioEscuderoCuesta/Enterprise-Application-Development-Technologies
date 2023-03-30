package es.unican.empresariales.julio.polaflix.entities;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Table(name = "categorie")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Categorie {

    @Id
    private static double pricePerChapter;

    public Categorie(double pricePerChapter) {
        Categorie.pricePerChapter = pricePerChapter;
    }

    public double getPricePerChapter() {
        return pricePerChapter;
    }
}