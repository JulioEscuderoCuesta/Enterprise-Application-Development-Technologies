package es.unican.empresariales.julio.polaflix.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.unican.empresariales.julio.polaflix.entities.Bill;
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
    
     public Optional<User> findUser(Long id) {
          return ur.findById(id);
     }

     public Optional<User> createNewUser(User user) {
          return Optional.of(ur.save(user));
     }

     public Optional<User> deleteUser(Long userId) {
          Optional<User> optionalUser = ur.findById(userId);
          User user;
          if(optionalUser.isPresent()) {
               user = optionalUser.get();
               ur.delete(user);
               return optionalUser;
          }
          return null;
     }

     @Transactional
     public Optional<User> updateUser(Long userId, User userDetail) {
          Optional<User> optionalUser = ur.findById(userId);
          User user;
          if(optionalUser.isPresent()) {
               user = optionalUser.get();
               user.setName(userDetail.getName());
               user.setPassword(userDetail.getPassword());
               user.setIban(userDetail.getIban());
          }
          return optionalUser;
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
     public boolean watchChapter(Long userId, Chapter chapter) {
          Optional<User> u = ur.findById(userId);
          Optional<Chapter> c = sr.findChapter(chapter.getTitle(), chapter.getSeason().getNumber(), chapter.getSeason().getSeries().getName());
          User user;
          Chapter chapterWatched;
          if(u.isPresent() && c.isPresent()) {
               user = u.get();
               chapterWatched = c.get();
               user.addChapterWatched(chapterWatched);   
               return true;       
          }
          return false;
     }

     @Transactional
    public Set<Bill> findBillsByUserId(Long userId) {
          Optional<User> optionalUser = ur.findById(userId);
          if(optionalUser.isPresent()) {
               Set<Bill> bills = ur.findBillsByUserId(optionalUser.get());
               if(!bills.isEmpty()) 
                    return bills;
          }
          return null;
    }

    @Transactional
    public Optional<Bill> findBillByUserId(Long userId, Long billId) {
          Optional<User> optionalUser = ur.findById(userId);
          Optional<Bill> optionalBill;
          if(optionalUser.isPresent()) {
               optionalBill = ur.findBillByUserId(optionalUser.get(), billId);
               if(optionalBill.isPresent()) 
                    return optionalBill;
          }
          return null;
    }

     public List<Series> getPendingSeriesByUserId(Long userId) {
          return ur.findPendingSeriesByUserId(userId);
     }
    
}
