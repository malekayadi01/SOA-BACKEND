// Reglement.java
package soa.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Reglement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idR;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateR = new Date();

    private int montant;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    private String chequeTraiteNumber;

    @ManyToOne
    private Facture facture;

    public Reglement() {
    }

    public Reglement(Long idR, Date dateR, int montant, PaymentType type, String chequeTraiteNumber, Facture facture) {
        this.idR = idR;
        this.dateR = dateR;
        this.montant = montant;
        this.type = type;
        this.chequeTraiteNumber = chequeTraiteNumber;
        this.facture = facture;
    }

    // Getter and setter for the new attribute
    public String getChequeTraiteNumber() {
        return chequeTraiteNumber;
    }

    public void setChequeTraiteNumber(String chequeTraiteNumber) {
        this.chequeTraiteNumber = chequeTraiteNumber;
    }

    public Long getIdR() {
        return idR;
    }

    public void setIdR(Long idR) {
        this.idR = idR;
    }

    public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    @Override
    public String toString() {
        return "Reglement{" +
                "idR=" + idR +
                ", dateR=" + dateR +
                ", montant=" + montant +
                ", type=" + type +
                ", chequeTraiteNumber='" + chequeTraiteNumber + '\'' +
                ", facture=" + facture +
                '}';
    }
}
