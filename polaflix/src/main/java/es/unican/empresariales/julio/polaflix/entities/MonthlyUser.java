package es.unican.empresariales.julio.polaflix.entities;

import jakarta.persistence.Entity;

@Entity
public class MonthlyUser extends User {
    
    private static double fee = 20.0;

    private MonthlyUser() {
        super();
    }

    public MonthlyUser(String name, String password, String iban) {
        super(name, password, iban);
    }

    public double getFee() {
        return fee;
    }
}
