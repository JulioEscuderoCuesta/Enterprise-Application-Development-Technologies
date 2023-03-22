package es.unican.empresariales.julio.polaflix;

import java.util.Objects;

public class CompoundIdSeason {
    
    private String name;
    private Series series;

    private CompoundIdSeason() {

    }

    public CompoundIdSeason(String name, Series series) {
        this.name = name;
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Series getChapters() {
        return series;
    }

    public void setChapters(Series series) {
        this.series = series;
    }

    /*****************************************************************/

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Season that = (Season) o;
        return super.equals(that)
            && Objects.equals(this.name, that.getName())
            && Objects.equals(this.series, that.getSeries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, series);
    }
}
