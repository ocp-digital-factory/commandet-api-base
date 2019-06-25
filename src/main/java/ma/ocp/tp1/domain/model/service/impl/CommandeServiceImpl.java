/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.ocp.tp1.domain.model.service.impl;

import ma.ocp.tp1.domain.bean.Commande;
import ma.ocp.tp1.domain.bean.CommandeItem;
import ma.ocp.tp1.domain.model.dao.CommandeDao;
import ma.ocp.tp1.domain.model.service.CommandeItemService;
import ma.ocp.tp1.domain.model.service.CommandeService;
import ma.ocp.tp1.domain.rest.vo.exhange.ProduitVo;
import ma.ocp.tp1.ext.ProduitProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author YOUNES
 */
@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private CommandeDao commandeDao;
    @Autowired
    private CommandeItemService commandeItemService;


   @Autowired
    private ProduitProxy produitProxy;

    @Override
    public List<Commande> findAll() {
        return commandeDao.findAll();
    }

    @Override
    public Commande saveCommandeWithCommandeItems(Commande commande) {
       if (validateCommande(commande.getCommandeItems())) {
            calculerTotal(commande, commande.getCommandeItems());
            commandeDao.save(commande);
            commandeItemService.saveCommandeItems(commande, commande.getCommandeItems());
            System.out.println("save commandeItems success");
            return commande;
       } else {
           return null;
       }
    }

    @Override
    public Commande findByReference(String reference) {
        return commandeDao.findByReference(reference);
    }

    @Override
    public ProduitVo findProductByReference(String reference) {
        return produitProxy.findByReference(reference);
    }

    private void calculerTotal(Commande commande, List<CommandeItem> commandeItems) {
        BigDecimal total = BigDecimal.ZERO;
        if (commandeItems != null && !commandeItems.isEmpty()) {
            for (CommandeItem commandeItem : commandeItems) {
                total = total.add(commandeItem.getPrix().multiply(commandeItem.getQte()));
            }
        }
        commande.setTotal(total);
    }

    private boolean validateCommande(List<CommandeItem> commandeItems) {
        return validateReferenceProduit(commandeItems);
    }

   private boolean validateReferenceProduit(List<CommandeItem> commandeItems) {
        if (commandeItems == null || commandeItems.isEmpty()) {
            return false;
        }else {
            int cmp = 0;
            for (CommandeItem commandeItem : commandeItems) {
                if (produitProxy.findByReference(commandeItem.getReferenceProduit()) != null) {
                    cmp++;
                }
            }
            System.out.println("ha cmp ==> "+cmp+" o ha size ==> "+commandeItems.size());
            return (cmp == commandeItems.size());
       //     return true;
        }
    }

}
