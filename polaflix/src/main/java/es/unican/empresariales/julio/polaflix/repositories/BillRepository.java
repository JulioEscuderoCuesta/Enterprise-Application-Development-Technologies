package es.unican.empresariales.julio.polaflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.unican.empresariales.julio.polaflix.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
    
    @Query("SELECT b FROM Bills WHERE b.status = ?1")
    public List<Bill> findByStatus(String status);

    @Query("SELECT b FROM Bills WHERE b.totalCost > ?1")
    public List<Bill> findByPriceMoreThan(double price);
}
