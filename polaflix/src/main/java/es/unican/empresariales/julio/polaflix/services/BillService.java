package es.unican.empresariales.julio.polaflix.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.unican.empresariales.julio.polaflix.entities.Bill;
import es.unican.empresariales.julio.polaflix.repositories.BillRepository;
import es.unican.empresariales.julio.polaflix.repositories.UserRepository;

@Service
public class BillService {

    @Autowired
    protected BillRepository br;

    @Autowired
    protected UserRepository ur;

    @Transactional
    public Optional<Bill> getBillById(Long id) {
        return br.findById(id);
    }
    @Transactional
    public Optional<Set<Bill>> findByUserId(Long userId) {
        return br.findByUserId(userId);
    }
    
}
