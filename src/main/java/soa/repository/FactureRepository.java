package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    // Add any custom query methods if needed
}
