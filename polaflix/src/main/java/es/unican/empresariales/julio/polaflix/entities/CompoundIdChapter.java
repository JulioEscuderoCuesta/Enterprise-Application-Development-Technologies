package es.unican.empresariales.julio.polaflix.entities;

import java.io.Serializable;
import java.util.Objects;

public class CompoundIdChapter implements Serializable{
    
    private String title;
    private Season season;

    private CompoundIdChapter() {

    }

    public CompoundIdChapter(String title, Season season) {
        this.title = title;
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    /**************************************************************** */
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Chapter that = (Chapter) o;
        return super.equals(that)
            && Objects.equals(this.title, that.getTitle())
            && Objects.equals(this.season, that.getSeason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, season);
    }
}
