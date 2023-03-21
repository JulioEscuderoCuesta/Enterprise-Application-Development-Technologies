package es.unican.empresariales.julio.polaflix.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import es.unican.empresariales.julio.polaflix.Bill;
import es.unican.empresariales.julio.polaflix.CompoundIdBill;
import es.unican.empresariales.julio.polaflix.CompoundIdUser;

public interface BillRepository extends Repository<Bill, CompoundIdBill> {
    
    List<Bill> findByUserId(CompoundIdUser userId);
}
