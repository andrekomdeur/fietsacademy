package be.vdab.fietsacademy.repositories;
import be.vdab.fietsacademy.domain.Campus;

import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface CampusRepository {
    void create(Campus campus);
    Optional<Campus> findById(long id);
}
