package es.unican.empresariales.julio.polaflix.entities;

import java.util.Objects;

public class CompoundIdSeason {
    
    private int number;
    private Series series;

    private CompoundIdSeason() {

    }

    public CompoundIdSeason(int number, Series series) {
        this.number = number;
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
            && Objects.equals(this.number, that.getNumber())
            && Objects.equals(this.series, that.getSeries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, series);
    }
}
