package es.unican.empresariales.julio.polaflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.empresariales.julio.polaflix.entities.Series;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    
}
