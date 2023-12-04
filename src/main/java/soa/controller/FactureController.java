package soa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Facture;
import soa.metier.FactureServiceInterface;

import java.util.List;

@RestController
@RequestMapping(value = "/factures", method = RequestMethod.OPTIONS)
@CrossOrigin(origins = "http://localhost:4200/")
public class FactureController {

    @Autowired
    private FactureServiceInterface factureService;

    @PostMapping
    public Facture addFacture(@RequestBody Facture f) {
        return factureService.addFacture(f);
    }

    @GetMapping
    public List<Facture> getAllFactures() {
        return factureService.getAllFactures();
    }

    @GetMapping("/{id}")
    public Facture getFactureById(@PathVariable Long id) {
        return factureService.getFactureById(id);
    }

    @PutMapping("/{id}")
    public Facture updateFacture(@PathVariable Long id, @RequestBody Facture f) {
        // Set the id of the request body to match the path variable
        f.setIdF(id);
        return factureService.updateFacture(f);
    }

    @DeleteMapping("/{id}")
    public void deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
    }
}
