package es.unican.empresariales.julio.polaflix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.unican.empresariales.julio.polaflix.CompoundIdUser;
import es.unican.empresariales.julio.polaflix.User;

@Repository
public interface UserRepository extends JpaRepository<User, CompoundIdUser> {
    

    public Optional<User> findById(CompoundIdUser userId);
   
}
