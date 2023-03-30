package es.unican.empresariales.julio.polaflix.entities;

public class MonthlyUser extends User {
    
    private static double fee = 20.0;

    public MonthlyUser(String name, String password, String iban) {
        super(name, password, iban);
    }

    public double getFee() {
        return fee;
    }
}
