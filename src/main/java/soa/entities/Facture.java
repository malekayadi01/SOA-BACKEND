package soa.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idF;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
    private String client;
    private double montant;

    @Column(name = "montant_restant_a_payer")
    private double montantRestantAPayer;

    @Column(name = "montant_payer")
    private double montantPayer;

    public Facture() {
        this.montantPayer = 0;
    }

    public Long getIdF() {
        return idF;
    }

    public void setIdF(Long idF) {
        this.idF = idF;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getMontantRestantAPayer() {
        return montantRestantAPayer;
    }

    public void setMontantRestantAPayer(double montantRestantAPayer) {
        this.montantRestantAPayer = montantRestantAPayer;
    }

    public double getMontantPayer() {
        return montantPayer;
    }

    public void setMontantPayer(double montantPayer) {
        this.montantPayer = montantPayer;
    }

    // Ajoutez cette méthode pour mettre à jour le montant payé
    public void updateMontantPayer(double montant) {
        this.montantPayer += montant;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idF=" + idF +
                ", date=" + date +
                ", client='" + client + '\'' +
                ", montant=" + montant +
                ", montantRestantAPayer=" + montantRestantAPayer +
                ", montantPayer=" + montantPayer +
                '}';
    }
}
