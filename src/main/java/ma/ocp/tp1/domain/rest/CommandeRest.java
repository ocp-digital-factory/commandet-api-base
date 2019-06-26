/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.ocp.tp1.domain.rest;

import ma.ocp.tp1.domain.bean.Commande;
import ma.ocp.tp1.domain.bean.CommandeItem;
import ma.ocp.tp1.domain.model.service.CommandeItemService;
import ma.ocp.tp1.domain.model.service.CommandeService;
import ma.ocp.tp1.domain.rest.converter.CommandeConverter;
import ma.ocp.tp1.domain.rest.converter.CommandeItemConverter;
import ma.ocp.tp1.domain.rest.vo.CommandeItemVo;
import ma.ocp.tp1.domain.rest.vo.CommandeVo;
import ma.ocp.tp1.domain.rest.vo.exhange.ProduitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author YOUNES
 */
@RestController
@RequestMapping("/commande-api/commandes")
public class CommandeRest {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private CommandeItemService commandeItemService;

    @Autowired
    private CommandeItemConverter commandeItemConverter;

    @Autowired
    private CommandeConverter commandeConverter;

    @GetMapping("/reference/{reference}/commande-items")
    public List<CommandeItemVo> findByCommande(@PathVariable("reference") String reference) {
        final List<CommandeItem> commandeItems = commandeItemService.findByCommandeReference(reference);
        return commandeItemConverter.toVo(commandeItems);
    }

    @GetMapping("/")
    public List<CommandeVo> findAll() {
        return commandeConverter.toVo(commandeService.findAll());
    }

    @PostMapping("/")
    public CommandeVo saveCommandeWithCommandeItems(@RequestBody CommandeVo commandeVo) {
        Commande myCommande = commandeConverter.toItem(commandeVo);
        Commande commande = commandeService.saveCommandeWithCommandeItems(myCommande);
        return commandeConverter.toVo(commande);
    }

    @GetMapping("/reference/{reference}")
    public CommandeVo findByReference(@PathVariable("reference") String reference) {
        final Commande commande = commandeService.findByReference(reference);
        return commandeConverter.toVo(commande);
    }

    @GetMapping("/produit/{reference}")
    public ResponseEntity findProduitByReference(@PathVariable("reference") String reference) {
        ProduitVo produitByReference = commandeService.findProduitByReference(reference);

        if(produitByReference.getReference() != null) {
            return ResponseEntity.ok(produitByReference);
        }

        return ResponseEntity.notFound().build();
    }


}
