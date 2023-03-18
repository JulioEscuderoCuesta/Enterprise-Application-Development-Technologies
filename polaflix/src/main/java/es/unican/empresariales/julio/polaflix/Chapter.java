package es.unican.empresariales.julio.polaflix;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "chapter")
@IdClass(CompoundIdChapter.class)
public class Chapter {

    @Id
    private String title;
    private String description;
    private int number;
    private double duration;
    private String link;
    //private boolean alreadyWatched;
    @Id
    @ManyToOne
    private Season season;

    public Chapter(String title, String description, int number, double duration, String link, Season season) {
        this.title = title;
        this.description = description;
        this.number = number;
        this.duration = duration;
        this.link = link;
        //alreadyWatched = false;
        this.season = season;
    }

    //Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getDuration() {
        return duration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Season getSeason() {
        return season;
    }

    /**************************************************************** */

    public void watch() {
        // TODO:
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Chapter that = (Chapter) o;
        return super.equals(that)
            && Objects.equals(this.title, that.title)
            && Objects.equals(this.season, that.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, season);
    }

}