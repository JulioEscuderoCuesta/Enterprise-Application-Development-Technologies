package es.unican.empresariales.julio.polaflix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
@Table(name = "season")
public class Season {

    @Id
    private String name;
    private int number;
    @Id
    @ManyToOne
    private Series series;
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters;

    public Season(String name, int number, Series series, ArrayList<Chapter> chapters) {
        this.name = name;
        this.number = number;
        this.series = series;
        this.chapters = chapters;
    }
    
    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public Series getSeries() {
        return series;
    }

    public List<Chapter> getChapters() {
        return this.chapters;
    }

    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Season that = (Season) o;
        return Objects.equals(this.name, that.name)
            && Objects.equals(this.series, that.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, series);
    }

    public boolean isTheLast() {
        List<Season> seasons = getSeries().getSeasons();
        if(this.equals(getSeries().getSeasons().get(seasons.size() - 1)))
            return true;
        return false;
    }

}