package es.unican.empresariales.julio.polaflix;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class CompoundIdBill implements Serializable {

    private Date releaseDate;
    private User user;

    private CompoundIdBill() {

    }

    public CompoundIdBill(Date releaseDate, User user) {
        this.releaseDate = releaseDate;
        this.user = user;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**************************************************************** */

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Bill that = (Bill) o;
        return super.equals(that)
            && Objects.equals(this.releaseDate, that.getReleaseDate())
            && Objects.equals(this.user, that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(releaseDate, user);
    }
    
}
