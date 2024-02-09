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

        when(factureRepository.save(facture)).thenReturn(facture);

        // Call the service method
        Facture savedFacture = factureService.addFacture(facture);

        // Assert the result
        assertNotNull(savedFacture);
        assertEquals(0, savedFacture.getMontantPayer());
        assertEquals(savedFacture.getMontant(), savedFacture.getMontantRestantAPayer());
    }

    @Test
    public void testGetAllFactures() {
        // Mock data and behavior
        List<Facture> mockFactures = Collections.singletonList(new Facture());
        when(factureRepository.findAll()).thenReturn(mockFactures);

        // Call the service method
        List<Facture> result = factureService.getAllFactures();

        // Assert the result
        assertEquals(mockFactures, result);
    }

    @Test
    public void testGetFactureById() {
        // Mock data and behavior
        Facture mockFacture = new Facture();
        when(factureRepository.findById(1L)).thenReturn(Optional.of(mockFacture));

        // Call the service method
        Facture result = factureService.getFactureById(1L);

        // Assert the result
        assertEquals(mockFacture, result);
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
        when(factureRepository.save(existingFacture)).thenReturn(updatedFacture);

        // Call the service method
        Facture result = factureService.updateFacture(updatedFacture);

        // Assert the result
        assertNotNull(result);
        assertEquals(updatedFacture.getIdF(), result.getIdF());
        assertEquals(updatedFacture.getMontant(), result.getMontant());
    }

    @Test
    public void testDeleteFacture() {
        // Mock data and behavior
        Facture existingFacture = new Facture();
        existingFacture.setIdF(1L);
        existingFacture.setMontant(100.0);

        when(factureRepository.findById(1L)).thenReturn(Optional.of(existingFacture));

        // Call the service method
        factureService.deleteFacture(1L);

        // Verify the behavior
        verify(factureRepository, times(1)).deleteById(1L);
    }
    // Add more test methods for other functionalities in FactureServiceImpl
}
