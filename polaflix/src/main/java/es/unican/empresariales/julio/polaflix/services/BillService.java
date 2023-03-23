package es.unican.empresariales.julio.polaflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.unican.empresariales.julio.polaflix.Bill;
import es.unican.empresariales.julio.polaflix.repositories.BillRepository;

public class BillService {

    @Autowired
    private BillRepository billRepository;
    
    public List<Bill> findByStatus(String status) {
        return billRepository.findByStatus(status);
        
    }

    public List<Bill> findByPriceMoreThan(double price) {
        return billRepository.findByPriceMoreThan(price);
    }

    
}
