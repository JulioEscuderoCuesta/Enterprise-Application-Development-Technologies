package es.unican.empresariales.julio.polaflix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompoundIdSeason {
    
    private String name;
    private List<Chapter> chapters;

    private CompoundIdSeason() {

    }

    public CompoundIdSeason(String name, ArrayList<Chapter> chapters) {
        this.name = name;
        this.chapters = chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    /*****************************************************************/

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Season that = (Season) o;
        return super.equals(that)
            && Objects.equals(this.name, that.getName())
            && Objects.equals(this.chapters, that.getChapters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, chapters);
    }
}
