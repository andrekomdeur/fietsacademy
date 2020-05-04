package be.vdab.fietsacademy.domain;
/**
 * @Author Andre Komdeur
 */
import javax.persistence.*;
import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cursus {
    @Id
    private String id;
    private String naam;

    protected Cursus() {
    }

    public Cursus(String naam) {
        id = UUID.randomUUID().toString();
        this.naam = naam;
    }

    public String getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
