package es.unican.empresariales.julio.polaflix;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Series {

    private String name;
    private Date releaseDate;
    private String synopsis;
    private List<String> creators;
    private List<String> actors;
    private Categorie categorie;
    private List<Season> seasons;

    public Series(String name, Date releaseDate, String synopsis, ArrayList<String> creators, ArrayList<String> actors, Categorie categorie, ArrayList<Season> seasons) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.creators = creators;
        this.actors = actors;
        this.categorie = categorie;
        this.categorie = categorie;
        this.seasons = seasons;
    }

    //Getters & ArrayListters
    public String getName() {
        return name;
    }

    public void ArrayListName(String name) {
        this.name = name;
    }

    public Date getReleaseLocalDate() {
        return releaseDate;
    }

    public void setReleaseLocalDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void ArrayListSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<String> getCreator() {
        return creators;
    }

    public List<String> getActors() {
        return this.actors;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void ArrayListCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Series that = (Series) o;
        return super.equals(that)
            && Objects.equals(this.name, that.name)
            && Objects.equals(this.synopsis, that.synopsis)
            && Objects.equals(this.creators, that.creators)
            && Objects.equals(this.actors, that.actors)
            && Objects.equals(this.seasons, that.seasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, synopsis, creators, actors, seasons);
    }
    
    public void watch() {
        // TODO:
    }
}