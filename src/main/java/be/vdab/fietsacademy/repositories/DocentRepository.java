package be.vdab.fietsacademy.repositories;
import be.vdab.fietsacademy.domain.Docent;

import java.math.BigDecimal;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface DocentRepository {
    Optional<Docent> findById(long id);
    void create(Docent docent);
    void delete(long id);
}
