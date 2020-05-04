package be.vdab.fietsacademy.repositories;
import be.vdab.fietsacademy.domain.Cursus;

import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface CursusRepository {
Optional<Cursus> findById(String id);
void create(Cursus cursus);
}
