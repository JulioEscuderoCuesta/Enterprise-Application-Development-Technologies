package es.unican.empresariales.julio.polaflix.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

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