package es.unican.empresariales.julio.polaflix.repositories;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.unican.empresariales.julio.polaflix.entities.Bill;
import es.unican.empresariales.julio.polaflix.entities.Series;
import es.unican.empresariales.julio.polaflix.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT b FROM Bill b WHERE b.who = ?1")
    public Set<Bill> findBillsByUserId(User user);

    @Query("SELECT b FROM Bill b WHERE b.who = ?1 AND b.id = ?2")
    public Optional<Bill> findBillByUserId(User user, Long billId);

    //@Query("SELECT s FROM Series s, Users u WHERE u.id = :userId AND s MEMBER OF u.pendingSeries")
    @Query("SELECT b FROM Bill b WHERE b.who = ?1 AND b.id = ?2")
    public List<Series> findPendingSeriesByUserId(Long userId);


}
