package es.unican.empresariales.julio.polaflix.entities;

import java.io.Serializable;
import java.util.Objects;

public class CompoundIdUser implements Serializable {

    private String name;
    private String password;

    private CompoundIdUser() {

    }

    public CompoundIdUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return super.equals(that)
            && Objects.equals(this.name, that.getName())
            && Objects.equals(this.password, that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }
    
}
