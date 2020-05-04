package be.vdab.fietsacademy.domain;
/**
 * @Author Andre Komdeur
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class TelefoonNrTest {
    private TelefoonNr nummer1, nogEensNummer1, nummer2;
    @BeforeEach
    void beforeEach() {
        nummer1 = new TelefoonNr("1", false, "");
        nogEensNummer1 = new TelefoonNr("1", false, "");
        nummer2 = new TelefoonNr("2", false, "");
    }
    @Test
    void telefoonNrsZijnGelijkAlsHunNummersGelijkZijn() {
        assertThat(nummer1).isEqualTo(nogEensNummer1);
    }
    @Test
    void telefooNrsZijnVerschillendAlsHunNummersVerschillen() {
        assertThat(nummer1).isNotEqualTo(nummer2);
    }
    @Test
    void eenTelefoonNrVerschiltVanNull() {
        assertThat(nummer1).isNotEqualTo(null);
    }
    @Test
    void eenTelefoonNrVerschiltVanEenAnderTypeObject() {
        assertThat(nummer1).isNotEqualTo("");
    }
    @Test
    void gelijkeTelefoonNrsGevenDezelfdeHashCode() {
        assertThat(nummer1).hasSameHashCodeAs(nogEensNummer1);
    }
}