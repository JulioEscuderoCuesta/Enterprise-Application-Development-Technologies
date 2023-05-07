package es.unican.empresariales.julio.polaflix.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.unican.empresariales.julio.polaflix.entities.Chapter;
import es.unican.empresariales.julio.polaflix.entities.Season;
import es.unican.empresariales.julio.polaflix.entities.Series;

public interface SeriesRepository extends JpaRepository<Series, Long> {

    @Query("SELECT c FROM Chapter c JOIN c.season se JOIN se.series sr WHERE c.title = ?1 AND se.number = ?2 and sr.name = ?3")
    Optional<Chapter> findChapter(String title, int number, String name);

    @Query("SELECT se FROM Season se JOIN se.series sr WHERE sr.id = ?1")
    Optional<List<Season>> findAllSeasons(Long seriesId);

    @Query("SELECT c FROM Chapter c JOIN c.season se JOIN se.series sr WHERE se.number = ?1 And sr.id = ?2")
    Optional<List<Chapter>> findChapters(String seasonNumber, Long seriesId);



    
}
