package es.unican.empresariales.julio.polaflix.entities;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
@IdClass(CompoundIdSeason.class)
public class Season {

    @Id
    private int number;
    @Id
    @ManyToOne
    private Series series;
    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters;

    private Season() {
        
    }
    public Season(int number, Series series) {
        this.number = number;
        this.series = series;
    }
    
    //Getters & Setters
    public int getNumber() {
        return this.number;
    }

    public Series getSeries() {
        return series;
    }

    public List<Chapter> getChapters() {
        return this.chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Season that = (Season) o;
        return Objects.equals(this.number, that.number)
            && Objects.equals(this.series, that.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, series);
    }

    /**
     * Determines if this is the last season of its series.
     * @return true if this is the last season of its series, false otherwise.
     */
    public boolean isTheLast() {
        List<Season> seasons = getSeries().getSeasons();
        if(this.equals(getSeries().getSeasons().get(seasons.size() - 1)))
            return true;
        return false;
    }

}