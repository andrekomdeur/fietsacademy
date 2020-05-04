package be.vdab.fietsacademy.domain;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * @Author Andre Komdeur
 */
@Entity
@Table(name = "docenten")
public class Docent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;
    private String emailAdres;
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;
    @ElementCollection
    @CollectionTable(name = "docentenbijnamen",
            joinColumns = @JoinColumn(name = "docentid"))
    @Column(name = "bijnaam")
    private Set<String> bijnamen;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "campusid")
//    private Campus campus;

    protected Docent() {
    }

    public Docent(String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht
//            , Campus campus
    ) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.emailAdres = emailAdres;
        this.geslacht = geslacht;
//        this.campus = campus;
        this.bijnamen = new LinkedHashSet<>();
    }

    public void verhoging(BigDecimal percentage) {
        if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
        wedde = wedde.multiply(factor, new MathContext(2, RoundingMode.HALF_UP));
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Geslacht getGeslacht() { return geslacht; }

    public BigDecimal getWedde() {
        return wedde;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public Set<String> getBijnamen() {
        return Collections.unmodifiableSet(bijnamen);
    }

//    public Campus getCampus() { return campus; }

    public boolean addBijnaam(String bijnaam) {
        if (bijnaam.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return bijnamen.add(bijnaam);
    }

    public boolean removeBijnaam(String bijnaam) {
        return bijnamen.remove(bijnaam);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Docent) {
            return emailAdres.equalsIgnoreCase(((Docent) obj).emailAdres);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return emailAdres == null ? 0 : emailAdres.toLowerCase().hashCode();
    }
}
