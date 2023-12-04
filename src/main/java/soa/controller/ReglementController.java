package soa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Reglement;
import soa.metier.ReglementServiceInterface;

import java.util.List;

@RestController
@RequestMapping(value = "/reglements", method = RequestMethod.OPTIONS)
@CrossOrigin(origins = "http://localhost:4200/")
public class ReglementController {

    @Autowired
    private ReglementServiceInterface reglementService;

    @PostMapping
    public Reglement addReglement(@RequestBody Reglement r) {
        return reglementService.addReglement(r);
    }

    @GetMapping
    public List<Reglement> getAllReglements() {
        return reglementService.getAllReglements();
    }

    @GetMapping("/{id}")
    public Reglement getReglementById(@PathVariable Long id) {
        return reglementService.getReglementById(id);
    }

    @PutMapping("/{id}")
    public Reglement updateReglement(@PathVariable Long id, @RequestBody Reglement r) {
        // Set the id of the request body to match the path variable
        r.setIdR(id);
        return reglementService.updateReglement(r);
    }

    @DeleteMapping("/{id}")
    public void deleteReglement(@PathVariable Long id) {
        reglementService.deleteReglement(id);
    }

    @GetMapping("/factures/{factureId}")
    public List<Reglement> getReglementsForFacture(@PathVariable Long factureId) {
        return reglementService.getReglementsForFacture(factureId);
    }
}
