package es.unican.empresariales.julio.polaflix;

import java.sql.Date;
import java.util.ArrayList;

public abstract class Series {

    private String name;
    private Date releaseDate;
    private String synopsis;
    private ArrayList<String> creators;
    private ArrayList<String> actors;
    private Categorie categorie;
    private ArrayList<Season> seasons;
    private ArrayList<Chapter> chapters;

    public Series(String name, Date releaseDate, String synopsis, ArrayList<String> creators, ArrayList<String> actors, Categorie categorie, ArrayList<Season> seasons, ArrayList<Chapter> chapters) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.creators = creators;
        this.actors = actors;
        this.categorie = categorie;
        this.categorie = categorie;
        this.seasons = seasons;
        this.chapters = chapters;
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

    public ArrayList<String> getCreator() {
        return creators;
    }

    public ArrayList<String> getActors() {
        return this.actors;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void ArrayListCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    /**************************************************************** */

    public void watch() {
        // TODO:
    }
}