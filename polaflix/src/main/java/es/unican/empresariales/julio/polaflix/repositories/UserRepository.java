package es.unican.empresariales.julio.polaflix.repositories;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.unican.empresariales.julio.polaflix.entities.Bill;
import es.unican.empresariales.julio.polaflix.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT b.who FROM Bill b WHERE b.id = ?1")
    public User findByBillId(Long billId);

    @Query("SELECT b FROM Bill b WHERE b.who = ?1")
    public Optional<Set<Bill>> findBillsByUserId(User user);

    @Query("SELECT b FROM Bill b WHERE b.who = ?1 AND b.id = ?2")
    public Optional<Bill> findBillByUserId(User user, Long billId);

    
    
}
