/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.ocp.tp1.domain.model.dao;

import ma.ocp.tp1.domain.bean.CommandeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author YOUNES
 */
@Repository
public interface CommandeItemDao extends JpaRepository<CommandeItem, Long>{
    public List<CommandeItem> findByCommandeReference(String reference);
}
