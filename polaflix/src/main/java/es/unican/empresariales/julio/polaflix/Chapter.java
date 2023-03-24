package es.unican.empresariales.julio.polaflix;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Chapter")
@IdClass(CompoundIdChapter.class)
public class Chapter {

    @Id
    private String title;
    private String description;
    private int number;
    private double duration;
    private String link;
    private Date releaseDate;
    @Id
    @ManyToOne
    private Season season;
    @ManyToMany(mappedBy = "chapter")
    private ArrayList<User> usersWhoWatched;

    public Chapter(String title, String description, int number, double duration, String link, Date releaseDate, Season season) {
        this.title = title;
        this.description = description;
        this.number = number;
        this.duration = duration;
        this.link = link;
        this.releaseDate = releaseDate;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Season getSeason() {
        return season;
    }

    /**************************************************************** */

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

    public boolean isTheLast() {
        if(!getSeason().isTheLast())
            return false;
        List<Chapter> chapters = getSeason().getChapters();
        if(this.equals(getSeason().getChapters().get(chapters.size() - 1)))
            return true;
        return false;
        
        
    }

}