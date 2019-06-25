/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.ocp.tp1.domain.model.service.impl;

import ma.ocp.tp1.domain.bean.Commande;
import ma.ocp.tp1.domain.bean.CommandeItem;
import ma.ocp.tp1.domain.model.dao.CommandeItemDao;
import ma.ocp.tp1.domain.model.service.CommandeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author YOUNES
 */
@Service
public class CommandeItemServiceImpl implements CommandeItemService {

    @Autowired
    private CommandeItemDao commandeItemDao;

    @Override
    public int saveCommandeItems(Commande commande, List<CommandeItem> commandeItems) {
        if(commandeItems==null || commandeItems.isEmpty()){
            return -1;
        }else{
            for (CommandeItem commandeItem : commandeItems) {
                commandeItem.setCommande(commande);
                commandeItemDao.save(commandeItem);
            }
            return 1;
        }
    }

    @Override
    public List<CommandeItem> findByCommandeReference(String reference) {
        return commandeItemDao.findByCommandeReference(reference);
    }


}
