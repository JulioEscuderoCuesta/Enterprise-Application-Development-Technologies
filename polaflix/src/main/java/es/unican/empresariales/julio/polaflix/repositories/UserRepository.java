package es.unican.empresariales.julio.polaflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.unican.empresariales.julio.polaflix.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    
}
