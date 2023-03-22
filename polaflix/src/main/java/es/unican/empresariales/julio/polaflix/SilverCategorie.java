package es.unican.empresariales.julio.polaflix;

import javax.persistence.Entity;

@Entity(name = "SilverCategorie")
public class SilverCategorie extends Categorie {

    private static final double PRICESILVERCATEGORIE = 0.75;

    public SilverCategorie() {
        super(PRICESILVERCATEGORIE);
    }
}