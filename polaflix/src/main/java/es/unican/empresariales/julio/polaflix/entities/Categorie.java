package es.unican.empresariales.julio.polaflix.entities;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
public abstract class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private double pricePerChapter;
    @OneToMany(mappedBy = "categorie")
    private Set<Series> seriesOfThisCategorie;

    private Categorie() {

    }

    public Categorie(String name, double pricePerChapter) {
        this.name = name;
        this.pricePerChapter = pricePerChapter;
        this.seriesOfThisCategorie = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerChapter() {
        return pricePerChapter;
    }

    public void setPricePerChapter(double pricePerChapter) {
        this.pricePerChapter = pricePerChapter;
    }

    public Set<Series> getSeriesOfThisCategorie() {
        return seriesOfThisCategorie;
    }
}