package soa.metier;

import org.junit.jupiter.api.Test;
import soa.entities.Facture;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactureTest {

    @Test
    public void testUpdateMontantPayer() {
        // Arrange
        Facture facture = new Facture();
        facture.setMontantPayer(50.0);

        // Act
        facture.updateMontantPayer(30.0);

        // Assert
        assertThat(facture.getMontantPayer()).isEqualTo(80.0);
    }

    @Test
    public void testToString() {
        // Arrange
        Facture facture = new Facture();
        facture.setIdF(1L);
        facture.setDate(new Date());
        facture.setClient("John Doe");
        facture.setMontant(100.0);
        facture.setMontantRestantAPayer(80.0);
        facture.setMontantPayer(20.0);

        // Act
        String result = facture.toString();

        // Assert
        assertThat(result).contains("idF=1", "client='John Doe'", "montant=100.0", "montantRestantAPayer=80.0", "montantPayer=20.0");
    }

    @Test
    public void testDefaultValues() {
        // Arrange
        Facture facture = new Facture();

        // Assert
        assertThat(facture.getIdF()).isNull();
        assertThat(facture.getDate()).isNotNull();
        assertThat(facture.getClient()).isNull();
        assertThat(facture.getMontant()).isEqualTo(0.0);
        assertThat(facture.getMontantRestantAPayer()).isEqualTo(0.0);
        assertThat(facture.getMontantPayer()).isEqualTo(0.0);
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Facture facture = new Facture();
        Date newDate = new Date();
        String newClient = "Jane Doe";
        double newMontant = 150.0;

        // Act
        facture.setIdF(2L);
        facture.setDate(newDate);
        facture.setClient(newClient);
        facture.setMontant(newMontant);
        facture.setMontantRestantAPayer(120.0);
        facture.setMontantPayer(30.0);

        // Assert
        assertThat(facture.getIdF()).isEqualTo(2L);
        assertThat(facture.getDate()).isEqualTo(newDate);
        assertThat(facture.getClient()).isEqualTo(newClient);
        assertThat(facture.getMontant()).isEqualTo(newMontant);
        assertThat(facture.getMontantRestantAPayer()).isEqualTo(120.0);
        assertThat(facture.getMontantPayer()).isEqualTo(30.0);
    }

    @Test
    public void testUpdateMontantPayerWithNegativeAmount_Exception() {
        // Arrange
        Facture facture = new Facture();
        facture.setMontantPayer(50.0);

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> facture.updateMontantPayer(-30.0));
        assertThat(exception.getMessage()).isEqualTo("Montant must be a non-negative value");
        assertThat(facture.getMontantPayer()).isEqualTo(50.0); // S'assurer que montantPayer reste inchangé
    }

    @Test
    public void testUpdateMontantRestantAPayerWithNegativeAmount_Exception() {
        // Arrange
        Facture facture = new Facture();
        facture.setMontantRestantAPayer(80.0);

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> facture.setMontantRestantAPayer(-30.0));
        assertThat(exception.getMessage()).isEqualTo("MontantRestantAPayer must be a non-negative value");
        assertThat(facture.getMontantRestantAPayer()).isEqualTo(80.0); // S'assurer que montantRestantAPayer reste inchangé
    }

    @Test
    public void testSetMontantRestantAPayerNegative_Exception() {
        // Arrange
        Facture facture = new Facture();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> facture.setMontantRestantAPayer(-30.0));
        assertThat(exception.getMessage()).isEqualTo("MontantRestantAPayer must be a non-negative value");
        assertThat(facture.getMontantRestantAPayer()).isEqualTo(0.0); // Ensure that montantRestantAPayer remains unchanged
    }



    @Test
    public void testUpdateMontantWithPositiveAmount() {
        // Arrange
        Facture facture = new Facture();
        facture.setMontant(100.0);

        // Act
        facture.setMontant(150.0);

        // Assert
        assertThat(facture.getMontant()).isEqualTo(150.0);
    }


    @Test
    public void testUpdateMontantRestantAPayerEqualToMontant() {
        // Arrange
        Facture facture = new Facture();
        facture.setMontant(100.0);

        // Act
        facture.setMontantRestantAPayer(100.0);

        // Assert
        assertThat(facture.getMontantRestantAPayer()).isEqualTo(100.0);
    }

    @Test
    public void testUpdateMontantRestantAPayerNegativeAfterPayment_Exception() {
        // Arrange
        Facture facture = new Facture();
        facture.setMontant(100.0);
        facture.setMontantRestantAPayer(80.0);

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> facture.setMontantRestantAPayer(-30.0));
        assertThat(exception.getMessage()).isEqualTo("MontantRestantAPayer must be a non-negative value");
        assertThat(facture.getMontantRestantAPayer()).isEqualTo(80.0); // Ensure that montantRestantAPayer remains unchanged
    }

    @Test
    public void testUpdateMontantPayerEqualToMontant() {
        // Arrange
        Facture facture = new Facture();
        facture.setMontant(100.0);

        // Act
        facture.setMontantPayer(100.0);

        // Assert
        assertThat(facture.getMontantPayer()).isEqualTo(100.0);
    }



}
