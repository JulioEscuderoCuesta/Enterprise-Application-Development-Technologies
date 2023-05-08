package es.unican.empresariales.julio.polaflix.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.unican.empresariales.julio.polaflix.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT b.who FROM Bill b WHERE b.id = ?1")
    public User findByBillId(Long billId);

    
    
}
