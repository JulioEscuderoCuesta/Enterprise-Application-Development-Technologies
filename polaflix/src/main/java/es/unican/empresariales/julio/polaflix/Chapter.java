package es.unican.empresariales.julio.polaflix;

public class Chapter {

    private String title;
    private String description;
    private int number;
    private double duration;
    private String link;
    //private boolean alreadyWatched;
    private Season season;

    public Chapter(String title, String description, int number, double duration, String link, Season season) {
        this.title = title;
        this.description = description;
        this.number = number;
        this.duration = duration;
        this.link = link;
        //alreadyWatched = false;
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

    public Season getSeason() {
        return season;
    }

    /**************************************************************** */

    public void watch() {
        // TODO:
    }

}