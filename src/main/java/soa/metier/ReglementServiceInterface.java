package soa.metier;

import soa.entities.Reglement;

import java.util.List;

public interface ReglementServiceInterface {
    Reglement addReglement(Reglement r);
    List<Reglement> getAllReglements();
    Reglement getReglementById(Long id);
    Reglement updateReglement(Reglement r);  // Add this method
    void deleteReglement(Long id);             // Add this method

    List<Reglement> getReglementsForFacture(Long factureId);
}
