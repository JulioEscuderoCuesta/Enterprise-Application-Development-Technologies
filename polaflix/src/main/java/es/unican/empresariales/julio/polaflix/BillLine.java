package es.unican.empresariales.julio.polaflix;

import java.util.Date;

public class BillLine {

    private Date visualizationDate;
    private String seriesName;
    private String seasonName;
    private int chapterNumber;
    private double cost;

    public BillLine(Date visualizationDate, String seriesName, String seasonName, int chapterNumber, double cost, Bill bill) {
        this.visualizationDate = visualizationDate;
        this.seriesName = seriesName;
        this.seasonName = seasonName;
        this.chapterNumber = chapterNumber;
        this.cost = cost;
        bill.addBillLine(this);
    }

    //Getters & Setters
    public Date getVisualizationDate() {
        return visualizationDate;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public double getCost() {
        return cost;
    }

    /**************************************************************** */



}