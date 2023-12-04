package soa.dao;

import soa.entities.Reglement;

import java.util.List;

public interface IReglementDao {

    Reglement save(Reglement reglement);

    List<Reglement> findAll();

    Reglement findOne(Long id);

    Reglement update(Reglement reglement);

    void delete(Long id);

    // Add any additional methods specific to Reglement if needed

}
