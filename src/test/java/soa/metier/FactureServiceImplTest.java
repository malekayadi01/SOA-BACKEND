// FactureServiceImplTest.java
package soa.metier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import soa.entities.Facture;
import soa.repository.FactureRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FactureServiceImplTest {

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private FactureServiceImpl factureService;

    @Test
    public void testAddFacture() {
        // Mock data and behavior
        Facture facture = new Facture();

        // Simulate the save operation
        when(factureRepository.save(facture)).thenReturn(facture);

        // Call the service method
        Facture savedFacture = factureService.addFacture(facture);

        // Assert the result
        assertNotNull(savedFacture);
        assertEquals(facture, savedFacture);
        assertEquals(facture.getMontant(), savedFacture.getMontant());
        assertEquals(0, savedFacture.getMontantPayer());
        assertEquals(facture.getMontant(), savedFacture.getMontantRestantAPayer());
    }

    @Test
    public void testAddFactureFailure() {
        // Mock data and behavior
        Facture facture = new Facture();

        // Simulate the save operation failure
        when(factureRepository.save(facture)).thenReturn(null);

        // Call the service method
        Facture savedFacture = factureService.addFacture(facture);

        // Assert the result
        assertNull(savedFacture);
    }

    @Test
    public void testGetAllFactures() {
        // Mock data and behavior
        when(factureRepository.findAll()).thenReturn(null); // Simulate a failure

        // Call the service method
        List<Facture> result = factureService.getAllFactures();

        // Assert the result
        assertNull(result);
    }

    @Test
    public void testGetAllFacturesWhenEmptyList() {
        // Mock data and simulate an empty list being returned
        when(factureRepository.findAll()).thenReturn(Collections.emptyList());

        // Call the service method
        List<Facture> result = factureService.getAllFactures();

        // Assert the result
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetFactureById() {
        // Mock data and behavior
        when(factureRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing facture

        // Call the service method
        Facture result = factureService.getFactureById(1L);

        // Assert the result
        assertNull(result);
    }

    @Test
    public void testGetFactureByIdWhenNotExists() {
        // Mock data and simulate a non-existing Facture
        when(factureRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method
        Facture result = factureService.getFactureById(1L);

        // Assert the result
        assertNull(result);
    }

    @Test
    public void testUpdateFactureWhenNotExists() {
        // Mock data and behavior
        Facture updatedFacture = new Facture();
        updatedFacture.setIdF(1L);
        updatedFacture.setMontant(150.0);

        when(factureRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing facture

        // Call the service method
        Facture result = factureService.updateFacture(updatedFacture);

        // Assert the result
        assertNull(result);
    }

    @Test
    public void testUpdateFacture() {
        // Mock data and behavior
        Facture existingFacture = new Facture();
        existingFacture.setIdF(1L);
        existingFacture.setMontant(100.0);

        Facture updatedFacture = new Facture();
        updatedFacture.setIdF(1L);
        updatedFacture.setMontant(150.0);

        when(factureRepository.findById(1L)).thenReturn(Optional.of(existingFacture));

        // Call the service method
        Facture result = factureService.updateFacture(updatedFacture);

        // Assert the result
        assertNotNull(result);
        assertEquals(updatedFacture.getMontant(), result.getMontant());
    }

    @Test
    public void testDeleteFacture() {
        // Mock data and behavior
        when(factureRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing facture

        // Call the service method
        factureService.deleteFacture(1L);

        // Verify the behavior
        verify(factureRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteNonExistingFacture() {
        // Mock data and behavior
        when(factureRepository.findById(1L)).thenReturn(Optional.empty()); // Simulate a non-existing facture

        // Call the service method
        factureService.deleteFacture(1L);

        // Verify the behavior
        verify(factureRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testUpdateFactureWithNegativeMontant() {
        // Mock data and behavior
        Facture existingFacture = new Facture();
        existingFacture.setIdF(1L);
        existingFacture.setMontant(100.0);
        existingFacture.setMontantPayer(30.0);

        Facture updatedFacture = new Facture();
        updatedFacture.setIdF(1L);
        updatedFacture.setMontant(-50.0);

        when(factureRepository.findById(1L)).thenReturn(Optional.of(existingFacture));

        // Call the service method
        Facture result = factureService.updateFacture(updatedFacture);

        // Verify the behavior
        verify(factureRepository, never()).save(any(Facture.class));

        // Assert the facture remains unchanged
        assertEquals(100.0, existingFacture.getMontant());
        assertEquals(30.0, existingFacture.getMontantPayer());
    }
}
