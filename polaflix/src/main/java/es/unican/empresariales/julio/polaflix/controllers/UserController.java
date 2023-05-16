package es.unican.empresariales.julio.polaflix.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.empresariales.julio.polaflix.Views;
import es.unican.empresariales.julio.polaflix.entities.Bill;
import es.unican.empresariales.julio.polaflix.entities.Chapter;
import es.unican.empresariales.julio.polaflix.entities.Series;
import es.unican.empresariales.julio.polaflix.entities.User;
import es.unican.empresariales.julio.polaflix.services.UserService;
import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService us;
    

    @GetMapping("/{userId}")
    @JsonView(Views.UserView.class)
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        Optional<User> user = us.findUser(userId);
        ResponseEntity<User> result;
        if (user.isPresent()) 
            result = ResponseEntity.ok(user.get());        
        else 
            result = ResponseEntity.notFound().build();       
        return result;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        Optional<User> user = us.createNewUser(newUser);
        ResponseEntity<User> result;
        if (user.isPresent()) 
            result = ResponseEntity.ok(user.get());       
        else 
            result = ResponseEntity.notFound().build();
        return result;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        Optional<User> optionalUser = us.deleteUser(userId);
        ResponseEntity<User> result;
        if(optionalUser.isPresent()) 
            result = ResponseEntity.ok(optionalUser.get());        
        else 
            result = ResponseEntity.notFound().build();
        return result;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestParam Long userId, @RequestBody User userDetail) {
        Optional<User> user = us.updateUser(userId, userDetail);
        ResponseEntity<User> result;
        if(user.isPresent()) 
            result = ResponseEntity.ok(user.get());
        else 
            result = ResponseEntity.notFound().build();
        return result;
    }

    
    @GetMapping("/{userId}/bills")
    @JsonView(Views.SeeChargesView.class)
    public ResponseEntity<Set<Bill>> getBillsByUserId(@PathVariable Long userId) {
        Set<Bill> bills = us.findBillsByUserId(userId);
        ResponseEntity<Set<Bill>> result;
        if(!bills.isEmpty()) 
            result = ResponseEntity.ok(bills);
        else 
            result = ResponseEntity.notFound().build();
        return result;
    }

    @GetMapping("/{userId}/bills/{billId}")
    @JsonView(Views.SeeChargesView.class)
    public ResponseEntity<Bill> getBillsByUserId(@PathVariable Long userId, @PathVariable Long billId) {
        Optional<Bill> list = us.findBillByUserId(userId, billId);
        ResponseEntity<Bill> result;
        if(list.isPresent()) 
            result = ResponseEntity.ok(list.get());
        else 
            result = ResponseEntity.notFound().build();
        return result;
    }

    @GetMapping("/{userId}/pendingSeries")
    public ResponseEntity<List<Series>> getPendingSeries(@PathVariable Long userId) {
        List<Series> pendingSeries = us.getPendingSeriesByUserId(userId);
        ResponseEntity<List<Series>> result;
        if(!pendingSeries.isEmpty()) 
            result = ResponseEntity.ok(pendingSeries);
        else
            result = ResponseEntity.notFound().build();
        return result;
        
    }

    @PutMapping("/{userId}/pendingSeries/{seriesId}")
    public boolean addSeriesToPendingSeries(@PathVariable Long userId, @PathVariable Long seriesId) {
        return us.addSeriesToPendingSeries(userId, seriesId);
        
        
    }


    @PutMapping("/{userId}/chaptersWatched")
    public boolean watchChapter(@RequestParam Long userId, @RequestBody Chapter chapter) {
        return us.watchChapter(userId, chapter);
    }

    
}
