package be.vdab.fietsacademy.services;
import be.vdab.fietsacademy.domain.Docent;
import be.vdab.fietsacademy.exceptions.DocentNietGevondenException;
import be.vdab.fietsacademy.repositories.DocentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultDocentService implements DocentService {
    private final DocentRepository repository;

    public DefaultDocentService(DocentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void verhoging(long id, BigDecimal percentage){
        Optional<Docent> optionalDocent = repository.findById(id);
        if (optionalDocent.isPresent()) {
            optionalDocent.get().verhoging(percentage);
        } else {
            throw new DocentNietGevondenException();
        }
    }
}
