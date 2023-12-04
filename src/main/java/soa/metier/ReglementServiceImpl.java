package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Facture;
import soa.entities.Reglement;
import soa.repository.ReglementRepository;

import java.util.List;

@Service
public class ReglementServiceImpl implements ReglementServiceInterface {

    @Autowired
    private ReglementRepository reglementRepository;

    @Autowired
    private FactureServiceInterface factureService;

    @Override
    public Reglement addReglement(Reglement r) {
        // Save the règlement
        Reglement savedReglement = reglementRepository.save(r);

        // Update the montant restant à payer in the associated facture
        factureService.updateMontantRestantAPayer(r.getFacture().getIdF(), r.getMontant());

        // Update the montant payé in the associated facture
        factureService.updateMontantPayer(r.getFacture().getIdF(), r.getMontant());

        // Return the saved règlement
        return savedReglement;
    }


    @Override
    public List<Reglement> getAllReglements() {
        return reglementRepository.findAll();
    }

    @Override
    public Reglement getReglementById(Long id) {
        return reglementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reglement> getReglementsForFacture(Long factureId) {
        return reglementRepository.findByFacture_IdF(factureId);
    }

    @Override
    public Reglement updateReglement(Reglement updatedReglement) {
        // Find the existing règlement by ID
        Reglement existingReglement = reglementRepository.findById(updatedReglement.getIdR()).orElse(null);

        if (existingReglement != null) {
            // Calculate the difference in montant
            double montantDifference = updatedReglement.getMontant() - existingReglement.getMontant();

            // Get the associated facture ID
            Long factureId = existingReglement.getFacture().getIdF();

            // Update the montant in the associated facture
            factureService.updateMontantPayer(factureId,  montantDifference);
            factureService.updateMontantRestantAPayer(factureId, existingReglement.getMontant());

            // Update the règlement with the new values
            existingReglement.setMontant(updatedReglement.getMontant());
            // You may want to update other properties as needed

            // Save the updated règlement
            Reglement savedReglement = reglementRepository.save(existingReglement);

            return savedReglement;
        }

        return null; // or throw an exception if the règlement is not found
    }

    @Override
    public void deleteReglement(Long id) {
        // Trouver le règlement par ID
        Reglement reglementToDelete = reglementRepository.findById(id).orElse(null);

        if (reglementToDelete != null) {
            // Obtenir la facture associée
            Facture facture = reglementToDelete.getFacture();

            if (facture != null) {
                // Obtenir l'ID de la facture associée
                Long factureId = facture.getIdF();

                // Mettre à jour le montant payé et le montant restant à payer dans la facture associée
                factureService.updateMontantPayer(factureId, - reglementToDelete.getMontant());
                factureService.updateMontantRestantAPayer(factureId, reglementToDelete.getMontant());
            }

            // Supprimer le règlement
            reglementRepository.deleteById(id);
        }
    }


}
