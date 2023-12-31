package es.unican.empresariales.julio.polaflix.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import es.unican.empresariales.julio.polaflix.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
 import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.SelectSeriesToWatch.class, Views.AddSeriesView.class, Views.UserView.class})
    private Long seriesId;

    @JsonView({Views.SelectSeriesToWatch.class, Views.AddSeriesView.class, Views.UserView.class})
    private String name;
    @JsonView({Views.AddSeriesView.class})
    private String synopsis;
    @JsonView({Views.AddSeriesView.class})
    private Set<String> creators;
    @JsonView({Views.AddSeriesView.class})
    private Set<String> actors;
    @ManyToOne
    @JsonView({Views.AddSeriesView.class})
    @JsonManagedReference
    private Categorie categorie;
    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonView({Views.SelectSeriesToWatch.class})
    private List<Season> seasons;

    private Series() {

    }

    public Series(String name, String synopsis, Set<String> creators, Set<String> actors, Categorie categorie) {
        this.name = name;
        this.synopsis = synopsis;
        this.creators = creators;
        this.actors = actors;
        this.categorie = categorie;
        categorie.addSeries(this);
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

    public Set<String> getCreator() {
        return creators;
    }

    public Set<String> getActors() {
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

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
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