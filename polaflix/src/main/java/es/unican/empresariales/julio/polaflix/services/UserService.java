package es.unican.empresariales.julio.polaflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.unican.empresariales.julio.polaflix.entities.Chapter;
import es.unican.empresariales.julio.polaflix.entities.Series;
import es.unican.empresariales.julio.polaflix.entities.User;
import es.unican.empresariales.julio.polaflix.repositories.SeriesRepository;
import es.unican.empresariales.julio.polaflix.repositories.UserRepository;

@Service
public class UserService {

     @Autowired
     protected UserRepository ur;
     @Autowired
     protected SeriesRepository sr;
    
     public Optional<User> getUserById(Long id) {
          return ur.findById(id);
     }

     public Optional<User> createNewUser(User user) {
          return Optional.of(ur.save(user));
     }

     public void deleteUser(User user) {
          ur.delete(user);
     }

     public void updateUser(User user) {
          ur.save(user);
     }

     @Transactional
     public boolean watchChapter(Long userId, Long seriesId) {
          Optional<User> u = ur.findById(userId);
          Optional<Series> s = sr.findById(seriesId);
          User user;
          Series series;
          if(u.isPresent() && s.isPresent()) {
               user = u.get();
               series = s.get();
               user.addSeriesToPendingSeries(series);
               return true;
          }
          return false;  
     }

     @Transactional
     public boolean addSeriesToPendingSeries(Long userId, Long seriesId) {
          Optional<User> u = ur.findById(userId);
          Optional<Series> s = sr.findById(seriesId);
          User user;
          Series series;
          if(u.isPresent() && s.isPresent()) {
               user = u.get();
               series = s.get();
               user.addSeriesToPendingSeries(series);
               return true;
          }
          return false;  
     }

     @Transactional
     public boolean addSeriesToStartedSeries(Long userId, Long seriesId) {
          Optional<User> u = ur.findById(userId);
          Optional<Series> s = sr.findById(seriesId);
          User user;
          Series series;
          if(u.isPresent() && s.isPresent()) {
               user = u.get();
               series = s.get();
               user.addSeriesToPendingSeries(series);
               return true;
          }
          return false;  
         
     }

     @Transactional
     public void watchChapter(Long userId, Chapter chapter) {
          Optional<User> u = ur.findById(userId);
          Optional<Chapter> c= sr.findChapter(chapter.getTitle(), chapter.getSeason().getNumber(), chapter.getSeason().getSeries().getName());
          User user;
          Chapter chapterWatched;
          if(u.isPresent() && c.isPresent()) {
               user = u.get();
               chapterWatched = c.get();
               user.addChapterWatched(chapterWatched);          
          }
     }
    
}
