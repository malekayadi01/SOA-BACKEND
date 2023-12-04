package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Reglement;

import java.util.List;

public interface ReglementRepository extends JpaRepository<Reglement, Long> {
    List<Reglement> findByFacture_IdF(Long factureId);
    // Add any custom query methods if needed
}
