package es.unican.empresariales.julio.polaflix.entities;

import jakarta.persistence.Entity;

@Entity
public class NormalUser extends User{

    private NormalUser() {
        super();
    }
    
    public NormalUser(String name, String password, String iban) {
        super(name, password, iban);
    }
    
}
