package be.vdab.fietsacademy.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
/**
 * @Author Andre Komdeur
 */
class DocentTest {
    private final static BigDecimal WEDDE = BigDecimal.valueOf(200);
    private Docent docent1;
    private Docent docent2;
    private Campus campus1;
    private Docent nogEensDocent1;

    @BeforeEach
    void beforeEach(){
        campus1 = new Campus("test", new Adres("test", "test", "test", "test"));
        docent1 = new Docent("Test","Test",WEDDE,"t.test@test.be",Geslacht.MAN
//        , campus1
        );
        docent2 = new Docent("test2", "test2", WEDDE, "test2@test.be", Geslacht.MAN);
        nogEensDocent1 = new Docent("test", "test", WEDDE, "t.test@test.be", Geslacht.MAN);
    }
    @Test
    void docentenZijnGelijkAlsHunEmailAdressenGelijkZijn() {
        assertThat(docent1).isEqualTo(nogEensDocent1);
    }
    @Test
    void docentenZijnVerschillendAlsHunEmailAdressenVerschillen() {
        assertThat(docent1).isNotEqualTo(docent2);
    }
    @Test
    void eenDocentVerschiltVanNull() {
        assertThat(docent1).isNotEqualTo(null);
    }
    @Test
    void eenDocentVerschiltVanEenAnderTypeObject() {
        assertThat(docent1).isNotEqualTo("");
    }
    @Test
    void gelijkeDocentenGevenDezelfdeHashCode() {
        assertThat(docent1).hasSameHashCodeAs(nogEensDocent1);
    }
    @Test
    void meerdereDocentenKunnenTotDezelfdeCampusBehoren() {
        assertThat(campus1.add(docent1)).isTrue();
        assertThat(campus1.add(docent2)).isTrue();
    }
    @Test
    void verhoging(){
        docent1.verhoging(BigDecimal.TEN);
        assertThat(docent1.getWedde().equals(BigDecimal.valueOf(220)));
    }
    @Test
    void opslagMetNullMislukt() {
        assertThatNullPointerException().isThrownBy(() -> docent1.verhoging(null));
    }

    @Test
    void opslagMet0Mislukt() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> docent1.verhoging(BigDecimal.ZERO));
    }
    @Test
    void negatieveOpslagMislukt() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> docent1.verhoging(BigDecimal.valueOf(-1)));
    }
    @Test
    void eenNieuweDocentHeeftGeenBijnamen() {
        assertThat(docent1.getBijnamen()).isEmpty();
    }
    @Test
    void bijnaamToevoegen() {
        assertThat(docent1.addBijnaam("test")).isTrue();
        assertThat(docent1.getBijnamen()).containsOnly("test");
    }
    @Test
    void tweeKeerDezelfdeBijnaamMislukt() {
        docent1.addBijnaam("test");
        assertThat(docent1.addBijnaam("test")).isFalse();
        assertThat(docent1.getBijnamen()).containsOnly("test");
    }
    @Test
    void nullAlsBijnaamMislukt() {
        assertThatNullPointerException().isThrownBy(() -> docent1.addBijnaam(null));
    }
    @Test
    void eenLegeBijnaamMislukt() {
        assertThatIllegalArgumentException().isThrownBy(() -> docent1.addBijnaam(""));
    }
    @Test
    void eenBijnaamMetEnkelSpatiesMislukt() {
        assertThatIllegalArgumentException().isThrownBy(() -> docent1.addBijnaam(" "));
    }
    @Test
    void bijnaamVerwijderen() {
        docent1.addBijnaam("test");
        assertThat(docent1.removeBijnaam("test")).isTrue();
        assertThat(docent1.getBijnamen()).isEmpty();
    }
    @Test
    void eenBijnaamVerwijderenDieJeNietToevoegdeMislukt() {
        docent1.addBijnaam("test");
        assertThat(docent1.removeBijnaam("test2")).isFalse();
        assertThat(docent1.getBijnamen()).containsOnly("test");
    }
}
