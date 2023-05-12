package es.unican.empresariales.julio.polaflix.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.empresariales.julio.polaflix.entities.Chapter;
import es.unican.empresariales.julio.polaflix.entities.Season;
import es.unican.empresariales.julio.polaflix.entities.Series;
import es.unican.empresariales.julio.polaflix.repositories.SeriesRepository;

@Service
public class SeriesService {
    
    @Autowired
    protected SeriesRepository sr;

    public Optional<List<Series>> findAllSeries() {
        return Optional.of(sr.findAll());
    }

    public Optional<Series> findSeries(Long seriesId) {
        return sr.findById(seriesId);
    }

    public Optional<List<Season>> findAllSeasonsBySeriesId(Long seriesId) {
        return sr.findAllSeasons(seriesId);

    }

    public Optional<List<Chapter>> findAllChapterBySeasonNumberAndBySeriesId(String seasonNumber, Long seriesId) {
        return sr.findChapters(seasonNumber, seriesId);
    }
}
