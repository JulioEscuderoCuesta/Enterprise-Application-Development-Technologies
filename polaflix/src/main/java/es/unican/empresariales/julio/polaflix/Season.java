package es.unican.empresariales.julio.polaflix;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Season {

    private String name;
    private int number;
    private Date releaseDate;
    private Series series;
    private List<Chapter> chapters;

    public Season(String name, int number, Date releaseDate, Series series, ArrayList<Chapter> chapters) {
        this.name = name;
        this.number = number;
        this.releaseDate = releaseDate;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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
        return super.equals(that)
            && Objects.equals(this.name, that.name)
            && Objects.equals(this.number, that.number)
            && Objects.equals(this.series, that.series)
            && Objects.equals(this.chapters, that.chapters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, series, chapters);
    }

}