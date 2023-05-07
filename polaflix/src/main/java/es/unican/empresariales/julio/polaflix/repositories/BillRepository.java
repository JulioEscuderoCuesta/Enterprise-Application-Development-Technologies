package es.unican.empresariales.julio.polaflix.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.unican.empresariales.julio.polaflix.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>  {

    @Query("SELECT b FROM Bill b WHERE b.who = ?1")
    public Optional<Set<Bill>> findByUserId(Long userId);
    
}
