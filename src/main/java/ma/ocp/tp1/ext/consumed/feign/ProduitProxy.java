package ma.ocp.tp1.ext.consumed.feign;

import ma.ocp.tp1.domain.rest.vo.exhange.ProduitVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micorservice-produit-v4-api", url = "localhost:8090")
public interface ProduitProxy {
    @GetMapping("/produit_api/produit/reference/{reference}")
    public ProduitVo findByReference(@PathVariable("reference") String reference);
}
