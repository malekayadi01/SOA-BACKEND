package soa.metier;

import org.junit.jupiter.api.Test;
import soa.entities.Facture;
import soa.entities.PaymentType;
import soa.entities.Reglement;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReglementTest {

    @Test
    public void testSetMontantWithNegativeValue() {
        // Arrange
        Reglement reglement = new Reglement();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> reglement.setMontant(-50));
        assertThat(exception.getMessage()).isEqualTo("Montant must be a non-negative value");
    }

    @Test
    public void testToString() {
        // Arrange
        Reglement reglement = new Reglement();
        reglement.setIdR(1L);
        reglement.setDateR(new Date());
        reglement.setMontant(100);
        reglement.setType(PaymentType.ESPECE);
        reglement.setChequeTraiteNumber("12345");
        Facture facture = new Facture();
        facture.setIdF(2L);
        reglement.setFacture(facture);

        // Act
        String result = reglement.toString();

        // Assert
        assertThat(result).contains("idR=1", "montant=100", "type=ESPECE", "chequeTraiteNumber='12345'", "facture=Facture{idF=2");
    }

    @Test
    public void testDefaultValues() {
        // Arrange
        Reglement reglement = new Reglement();

        // Assert
        assertThat(reglement.getIdR()).isNull();
        assertThat(reglement.getDateR()).isNotNull();
        assertThat(reglement.getMontant()).isEqualTo(0);
        assertThat(reglement.getType()).isNull();
        assertThat(reglement.getChequeTraiteNumber()).isNull();
        assertThat(reglement.getFacture()).isNull();
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Reglement reglement = new Reglement();
        Date newDate = new Date();
        int newMontant = 150;
        PaymentType newType = PaymentType.CHEQUE;
        String newChequeTraiteNumber = "67890";
        Facture newFacture = new Facture();
        newFacture.setIdF(3L);

        // Act
        reglement.setIdR(2L);
        reglement.setDateR(newDate);
        reglement.setMontant(newMontant);
        reglement.setType(newType);
        reglement.setChequeTraiteNumber(newChequeTraiteNumber);
        reglement.setFacture(newFacture);

        // Assert
        assertThat(reglement.getIdR()).isEqualTo(2L);
        assertThat(reglement.getDateR()).isEqualTo(newDate);
        assertThat(reglement.getMontant()).isEqualTo(newMontant);
        assertThat(reglement.getType()).isEqualTo(newType);
        assertThat(reglement.getChequeTraiteNumber()).isEqualTo(newChequeTraiteNumber);
        assertThat(reglement.getFacture()).isEqualTo(newFacture);
    }

    @Test
    public void testSetType() {
        // Arrange
        Reglement reglement = new Reglement();

        // Act
        reglement.setType(PaymentType.ESPECE);

        // Assert
        assertThat(reglement.getType()).isEqualTo(PaymentType.ESPECE);
    }

    // Add more test methods as needed
    @Test
    public void testSetMontantNegative_Exception() {
        // Arrange
        Reglement reglement = new Reglement();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> reglement.setMontant(-50));
        assertThat(exception.getMessage()).isEqualTo("Montant must be a non-negative value");
        assertThat(reglement.getMontant()).isEqualTo(0); // S'assurer que montant reste inchangé
    }

    @Test
    public void testSetChequeTraiteNumber() {
        // Arrange
        Reglement reglement = new Reglement();
        String newChequeTraiteNumber = "12345";

        // Act
        reglement.setChequeTraiteNumber(newChequeTraiteNumber);

        // Assert
        assertThat(reglement.getChequeTraiteNumber()).isEqualTo(newChequeTraiteNumber);
    }

    @Test
    public void testSetMontantWithNegativeAmount_Exception() {
        // Arrange
        Reglement reglement = new Reglement();

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> reglement.setMontant(-50));
        assertThat(exception.getMessage()).isEqualTo("Montant must be a non-negative value");
        assertThat(reglement.getMontant()).isEqualTo(0); // S'assurer que montant reste inchangé
    }

    @Test
    public void testUpdateMontantWithPositiveAmount() {
        // Arrange
        Reglement reglement = new Reglement();
        reglement.setMontant(50);

        // Act
        reglement.setMontant(100);

        // Assert
        assertThat(reglement.getMontant()).isEqualTo(100);
    }
    @Test
    public void testUpdateMontantAfterPartialRefund() {
        // Arrange
        Reglement reglement = new Reglement();
        reglement.setMontant(100);

        // Act
        reglement.setMontant(80);

        // Assert
        assertThat(reglement.getMontant()).isEqualTo(80);
    }
    @Test
    public void testUpdateReglementDate() {
        // Arrange
        Reglement reglement = new Reglement();
        Date newDate = new Date();

        // Act
        reglement.setDateR(newDate);

        // Assert
        assertThat(reglement.getDateR()).isEqualTo(newDate);
    }


}
