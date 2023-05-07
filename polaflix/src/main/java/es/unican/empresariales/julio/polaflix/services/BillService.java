package es.unican.empresariales.julio.polaflix.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unican.empresariales.julio.polaflix.entities.Bill;
import es.unican.empresariales.julio.polaflix.repositories.BillRepository;

@Service
public class BillService {

    @Autowired
    protected BillRepository br;

    public Optional<Bill> getBillById(Long id) {
        return br.findById(id);
   }
    public Optional<Set<Bill>> findByUserId(Long userId) {
        return br.findByUserId(userId);
    }

    public void updateBill(Bill bill) {
        br.save(bill);
    }

    public void deleteBill(Bill bill) {
        br.delete(bill);
    }
    
}
