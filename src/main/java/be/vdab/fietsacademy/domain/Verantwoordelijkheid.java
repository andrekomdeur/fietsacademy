package be.vdab.fietsacademy.domain;
import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
/**
 * @Author Andre Komdeur
 */
@Entity
@Table(name = "verantwoordelijkheden")
public class Verantwoordelijkheid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @ManyToMany
    @JoinTable(
            name = "docentenverantwoordelijkheden",
            joinColumns = @JoinColumn(name = "verantwoordelijkheidid"),
            inverseJoinColumns = @JoinColumn(name = "docentid"))
    private Set<Docent> docenten = new LinkedHashSet<>();

    public Verantwoordelijkheid() {
    }

    public Verantwoordelijkheid(String naam) {
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }

    public boolean add(Docent docent) {
        boolean toegevoegd = docenten.add(docent);
        if ( ! docent.getVerantwoordelijkheden().contains(this)) {
            docent.add(this);
        }
        return toegevoegd;
    }
    public boolean remove(Docent docent) {
        boolean verwijderd = docenten.remove(docent);
        if (docent.getVerantwoordelijkheden().contains(this)) {
            docent.remove(this);
        }
        return verwijderd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Verantwoordelijkheid)) return false;
        Verantwoordelijkheid that = (Verantwoordelijkheid) o;
        return Objects.equals(naam, that.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

}