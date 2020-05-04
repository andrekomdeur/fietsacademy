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
    @BeforeEach
    void beforeEach(){
        docent1 = new Docent("Test","Test",WEDDE,"t.test@test.com",Geslacht.MAN);
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
