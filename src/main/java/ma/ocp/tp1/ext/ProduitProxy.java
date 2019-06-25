package ma.ocp.tp1.ext;

import ma.ocp.tp1.domain.rest.vo.exhange.ProduitVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-produits",url="localhost:8090")
public interface ProduitProxy {

        @GetMapping("produit_api/produit/reference/{reference}")
        ProduitVo findByReference(@PathVariable("reference") String reference);
}
