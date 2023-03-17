package es.unican.empresariales.julio.polaflix;

public abstract class Categorie {

    private double pricePerChapter;

    public Categorie(double pricePerChapter) {
        this.pricePerChapter = pricePerChapter;
    }

    public double getPricePerChapter() {
        return pricePerChapter;
    }
}