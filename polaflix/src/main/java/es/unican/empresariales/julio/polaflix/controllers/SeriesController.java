package es.unican.empresariales.julio.polaflix.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.empresariales.julio.polaflix.Views;
import es.unican.empresariales.julio.polaflix.entities.Chapter;
import es.unican.empresariales.julio.polaflix.entities.Season;
import es.unican.empresariales.julio.polaflix.entities.Series;
import es.unican.empresariales.julio.polaflix.services.SeriesService;

@RestController
@RequestMapping("/series")
public class SeriesController {
    
    @Autowired
    private SeriesService ss;

    @GetMapping
    @JsonView(Views.AddSeriesView.class)
    public ResponseEntity<List<Series>> getSeries() {
        Optional<List<Series>> optionalSeries = ss.findAllSeries();
        ResponseEntity<List<Series>> result;
        if(optionalSeries.isPresent()) {
            result = ResponseEntity.ok(optionalSeries.get());
        }
        else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @GetMapping("/{seriesId}")
    @JsonView(Views.SelectSeriesToWatch.class)
    public ResponseEntity<Series> getSeries(@PathVariable Long seriesId) {
        Optional<Series> series = ss.findSeries(seriesId);
        ResponseEntity<Series> result;

        if (series.isPresent()) {
            result = ResponseEntity.ok(series.get());
        }
        else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @GetMapping("/{seriesId}/seasons")
    @JsonView(Views.SelectSeriesToWatch.class)
    public ResponseEntity<List<Season>> getSeasons(@PathVariable Long seriesId) {
        Optional<List<Season>> optionalSeasons = ss.findAllSeasonsBySeriesId(seriesId);
        ResponseEntity<List<Season>> result;
        if(optionalSeasons.isPresent()) {
            result = ResponseEntity.ok(optionalSeasons.get());
        }
        else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @GetMapping("/{seriesId}/seasons/{seasonNumber}/chapters")
    @JsonView(Views.SelectSeriesToWatch.class)
    public ResponseEntity<List<Chapter>> getSeasons(@PathVariable String seasonNumber, @PathVariable Long seriesId) {
        Optional<List<Chapter>> optionalChapters = ss.findAllChapterBySeasonNumberAndBySeriesId(seasonNumber, seriesId);
        ResponseEntity<List<Chapter>> result;
        if(optionalChapters.isPresent()) {
            result = ResponseEntity.ok(optionalChapters.get());
        }
        else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }
    
}
