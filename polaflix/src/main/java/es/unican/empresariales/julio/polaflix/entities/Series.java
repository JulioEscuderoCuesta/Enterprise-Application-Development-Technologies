package es.unican.empresariales.julio.polaflix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
@Table(name = "Series")
public abstract class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String synopsis;
    private List<String> creators;
    private List<String> actors;
    @OneToOne
    private Categorie categorie;
    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Season> seasons;

    public Series(String name, String synopsis, ArrayList<String> creators, ArrayList<String> actors, Categorie categorie, ArrayList<Season> seasons) {
        this.name = name;
        this.synopsis = synopsis;
        this.creators = creators;
        this.actors = actors;
        this.categorie = categorie;
        this.seasons = seasons;
    }

    //Getters & ArrayListters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return Objects.equals(this.name, that.name)
            && Objects.equals(this.synopsis, that.synopsis)
            && Objects.equals(this.creators, that.creators)
            && Objects.equals(this.actors, that.actors)
            && Objects.equals(this.seasons, that.seasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, synopsis, creators, actors, seasons);
    }


}