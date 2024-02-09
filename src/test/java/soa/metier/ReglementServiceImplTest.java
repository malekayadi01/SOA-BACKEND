// ReglementServiceImplTest.java
package soa.metier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import soa.entities.Facture;
import soa.entities.Reglement;
import soa.repository.ReglementRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReglementServiceImplTest {

    @Mock
    private ReglementRepository reglementRepository;

    @Mock
    private FactureServiceInterface factureService;

    @InjectMocks
    private ReglementServiceImpl reglementService;

    @Test
    public void testAddReglement() {
        // Mock data and behavior
        Reglement reglement = new Reglement();
        Facture associatedFacture = new Facture(); // Set up an associated facture
        reglement.setFacture(associatedFacture);

        // Simulate the save operation failure
        when(reglementRepository.save(reglement)).thenReturn(null);

        // Call the service method
        Reglement savedReglement = reglementService.addReglement(reglement);

        // Verify the behavior
        verify(factureService, never()).updateMontantRestantAPayer(anyLong(), anyDouble());
        verify(factureService, never()).updateMontantPayer(anyLong(), anyDouble());

        // Assert the result
        assertNull(savedReglement);
    }
    @Test
    public void testAddReglementFailure() {
        // Mock data and behavior
        Reglement reglement = new Reglement();
        Facture associatedFacture = new Facture(); // Set up an associated facture
        reglement.setFacture(associatedFacture);

        // Simulate the save operation failure
        when(reglementRepository.save(reglement)).thenReturn(null); // or use thenThrow(new SomeExceptionType("message")) to simulate an exception

        // Call the service method
        Reglement savedReglement = reglementService.addReglement(reglement);

        // Verify the behavior
        verify(factureService, never()).updateMontantRestantAPayer(anyLong(), anyDouble());
        verify(factureService, never()).updateMontantPayer(anyLong(), anyDouble());

        // Assert the result
        assertNull(savedReglement);
    }

    @Test
    public void testGetAllReglements() {
        // Mock data and behavior
        when(reglementRepository.findAll()).thenReturn(null); // Simulate a failure

        // Call the service method
        List<Reglement> result = reglementService.getAllReglements();

        // Assert the result
        assertNull(result);
    }
    @Test
    public void testGetAllReglementsWhenEmptyList() {
        // Mock data and simulate an empty list being returned
        when(reglementRepository.findAll()).thenReturn(Collections.emptyList());

        // Call the service method
        List<Reglement> result = reglementService.getAllReglements();

        // Assert the result
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetReglementById() {
        // Mock data and behavior
        when(reglementRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing reglement

        // Call the service method
        Reglement result = reglementService.getReglementById(1L);

        // Assert the result
        assertNull(result);
    }
    @Test
    public void testGetReglementByIdWhenNotExists() {
        // Mock data and simulate a non-existing Reglement
        when(reglementRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Reglement result = reglementService.getReglementById(1L);

        // Assert the result
        assertNull(result);
    }

    @Test
    public void testUpdateReglement() {
        // Mock data and behavior
        Reglement existingReglement = new Reglement();
        existingReglement.setIdR(1L);
        existingReglement.setMontant((int) 100.0);
        existingReglement.setFacture(new Facture()); // Set up an associated facture

        Reglement updatedReglement = new Reglement();
        updatedReglement.setIdR(1L);
        updatedReglement.setMontant((int) 150.0);

        when(reglementRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing reglement

        // Call the service method
        Reglement result = reglementService.updateReglement(updatedReglement);

        // Verify the behavior
        verify(factureService, never()).updateMontantPayer(anyLong(), anyDouble());
        verify(factureService, never()).updateMontantRestantAPayer(anyLong(), anyDouble());
        verify(reglementRepository, never()).save(any(Reglement.class));

        // Assert the result
        assertNull(result);
    }
    @Test
    public void testUpdateReglementWhenNotExists() {
        // Mock data and behavior
        Reglement updatedReglement = new Reglement();
        updatedReglement.setIdR(1L);
        updatedReglement.setMontant((int) 150.0);

        when(reglementRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing reglement

        // Call the service method
        Reglement result = reglementService.updateReglement(updatedReglement);

        // Verify the behavior
        verify(factureService, never()).updateMontantPayer(anyLong(), anyDouble());
        verify(factureService, never()).updateMontantRestantAPayer(anyLong(), anyDouble());
        verify(reglementRepository, never()).save(any(Reglement.class));

        // Assert the result
        assertNull(result);
    }

    @Test
    public void testDeleteReglement() {
        // Mock data and behavior
        when(reglementRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing reglement

        // Call the service method
        reglementService.deleteReglement(1L);

        // Verify the behavior
        verify(factureService, never()).updateMontantPayer(anyLong(), anyDouble());
        verify(factureService, never()).updateMontantRestantAPayer(anyLong(), anyDouble());
        verify(reglementRepository, never()).deleteById(anyLong());
    }
    @Test
    public void testDeleteNonExistingReglement() {
        // Mock data and behavior
        when(reglementRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing reglement

        // Call the service method
        reglementService.deleteReglement(1L);

        // Verify the behavior
        verify(factureService, never()).updateMontantPayer(anyLong(), anyDouble());
        verify(factureService, never()).updateMontantRestantAPayer(anyLong(), anyDouble());
        verify(reglementRepository, never()).deleteById(anyLong());
    }
    @Test
    public void testUpdateReglementWithNegativeMontant() {
        // Mock data and behavior
        Reglement existingReglement = new Reglement();
        existingReglement.setIdR(1L);
        existingReglement.setMontant((int) 100.0);
        existingReglement.setFacture(new Facture()); // Set up an associated facture

        Reglement updatedReglement = new Reglement();
        updatedReglement.setIdR(1L);
        updatedReglement.setMontant((int) -50.0);

        when(reglementRepository.findById(1L)).thenReturn(Optional.of(existingReglement));

        // Call the service method
        Reglement result = reglementService.updateReglement(updatedReglement);

        // Verify the behavior
        verify(factureService, never()).updateMontantPayer(anyLong(), anyDouble());
        verify(factureService, never()).updateMontantRestantAPayer(anyLong(), anyDouble());
        verify(reglementRepository, times(1)).save(any(Reglement.class));

        // Assert the result
        assertNull(result);
    }

}
