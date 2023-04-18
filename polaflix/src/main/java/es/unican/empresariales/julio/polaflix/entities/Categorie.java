package es.unican.empresariales.julio.polaflix.entities;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "categorie")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Categorie {

    @Id
    private static double pricePerChapter;
    @OneToMany
    private Set<Series> seriesOfThisCategorie;

    public Categorie(double pricePerChapter) {
        Categorie.pricePerChapter = pricePerChapter;
        this.seriesOfThisCategorie = new HashSet<>();
    }

    public double getPricePerChapter() {
        return pricePerChapter;
    }

    public Set<Series> getSeriesOfThisCategorie() {
        return seriesOfThisCategorie;
    }
}