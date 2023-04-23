package es.unican.empresariales.julio.polaflix.entities;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

@Entity
public class Categorie {

    @Id
    private String name;
    private double pricePerChapter;
    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)
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

    /**************************************************************** */

    public void addSeries(Series series) {
        seriesOfThisCategorie.add(series);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Categorie that = (Categorie) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}