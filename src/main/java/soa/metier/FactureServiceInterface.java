// FactureServiceInterface.java
package soa.metier;

import soa.entities.Facture;

import java.util.List;

public interface FactureServiceInterface {
    Facture addFacture(Facture f);
    List<Facture> getAllFactures();
    Facture getFactureById(Long id);
    Facture updateFacture(Facture f);
    void deleteFacture(Long id);
    void updateMontantRestantAPayer(Long factureId, double montant);
    void updateMontantPayer(Long factureId, double montant);
}
