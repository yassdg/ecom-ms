package net.yassdg.bilingservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.yassdg.bilingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("inventory-service")
public interface InventoryServiceRestClient  {
    @GetMapping("/products/{id}")
    @CircuitBreaker(name="inventory-service", fallbackMethod = "getDefaultProduct")
    Product getProduct(@PathVariable Long id);

    default Product getDefaultProduct(Long id, Exception e){
        return Product.builder().Id(id).build();
    }
}
