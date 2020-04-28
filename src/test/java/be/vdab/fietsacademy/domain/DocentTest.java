package be.vdab.fietsacademy.domain;
import org.assertj.core.api.ThrowableTypeAssert;
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
}
