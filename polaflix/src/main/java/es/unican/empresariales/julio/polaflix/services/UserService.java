package es.unican.empresariales.julio.polaflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.empresariales.julio.polaflix.CompoundIdUser;
import es.unican.empresariales.julio.polaflix.User;
import es.unican.empresariales.julio.polaflix.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public User findByUserId(CompoundIdUser userId) throws IllegalArgumentException {

        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        return user;
    }
    
}