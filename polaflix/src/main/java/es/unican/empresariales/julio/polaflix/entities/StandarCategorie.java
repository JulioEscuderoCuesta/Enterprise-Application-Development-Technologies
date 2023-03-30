package es.unican.empresariales.julio.polaflix.entities;

import javax.persistence.Entity;

@Entity(name = "StandarCategorie")
public class StandarCategorie extends Categorie {

    private static final double PRICESTANDARCATEGORIE = 0.5;

    public StandarCategorie() {
        super(PRICESTANDARCATEGORIE);
    }
}