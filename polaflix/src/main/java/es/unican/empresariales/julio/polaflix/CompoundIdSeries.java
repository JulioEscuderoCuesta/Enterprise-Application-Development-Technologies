package es.unican.empresariales.julio.polaflix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CompoundIdSeries implements Serializable {

    private String name;
    private List<Season> seasons; 

    private CompoundIdSeries() {

    }

    public CompoundIdSeries(String name, ArrayList<Season> seasons) {
        this.name = name;
        this.seasons = seasons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Series that = (Series) o;
        return super.equals(that)
            && Objects.equals(this.name, that.getName())
            && Objects.equals(this.seasons, that.getSeasons());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, seasons);
    }
}