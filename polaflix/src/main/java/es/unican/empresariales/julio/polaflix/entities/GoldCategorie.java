package es.unican.empresariales.julio.polaflix.entities;

import javax.persistence.Entity;

@Entity(name = "GoldCategorie")
public class GoldCategorie extends Categorie {

    private static final double PRICEGOLDCATEGORIE = 1.5;

    public GoldCategorie() {
        super(PRICEGOLDCATEGORIE);
    }
}