package es.unican.empresariales.julio.polaflix;

import java.sql.Date;
import java.util.ArrayList;

public class Season {

    private String name;
    private int number;
    private Date releaseDate;
    private Series series;
    private ArrayList<Chapter> chapters;

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

    public ArrayList<Chapter> getChapters() {
        return this.chapters;
    }

    /**************************************************************** */
}