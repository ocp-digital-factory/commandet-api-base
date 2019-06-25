/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.ocp.tp1.domain.rest.vo.exhange;

import lombok.Data;

/**
 *
 * @author YOUNES
 */
@Data
public class ProduitVo {

    private String libelle;
    private String reference;
    private CategorieProduitVo categorieProduitVo;

}
