// FactureServiceImpl.java
package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Facture;
import soa.repository.FactureRepository;

import java.util.List;

@Service
public class FactureServiceImpl implements FactureServiceInterface {

    @Autowired
    private FactureRepository factureRepository;

    @Override
    public Facture addFacture(Facture f) {
        f.setMontantRestantAPayer(f.getMontant());
        f.setMontantPayer(0);
        return factureRepository.save(f);
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public Facture updateFacture(Facture f) {
        return factureRepository.save(f);
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

    @Override
    public void updateMontantRestantAPayer(Long factureId, double montant) {
        Facture facture = factureRepository.findById(factureId).orElse(null);
        if (facture != null) {
            double montantRestant = facture.getMontantRestantAPayer() - montant;

            // Check if montant restant is non-negative
            if (montantRestant >= 0) {
                facture.setMontantRestantAPayer(montantRestant);
                factureRepository.save(facture);
            }
        }
    }
    @Override
    public void updateMontantPayer(Long factureId, double montant) {
        Facture facture = factureRepository.findById(factureId).orElse(null);
        if (facture != null) {
            double montantPayer = facture.getMontantPayer() + montant;
            facture.setMontantPayer(montantPayer);
            factureRepository.save(facture);
        }
    }

}
