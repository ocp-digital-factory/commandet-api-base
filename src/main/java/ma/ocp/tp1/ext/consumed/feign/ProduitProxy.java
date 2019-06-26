package ma.ocp.tp1.ext.consumed.feign;

import ma.ocp.tp1.domain.rest.vo.exhange.ProduitVo;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MICROSERVICE-PRODUITS")
@RibbonClient("MICROSERVICE-PRODUITS")
public interface ProduitProxy {
    @GetMapping("/produit_api/produit/reference/{reference}")
    public ProduitVo findByReference(@PathVariable("reference") String reference);

    @PostMapping("/produit_api/produit/referenece/exists")
    public Integer checkPorduits(@RequestBody ProduitVo produits);
}
