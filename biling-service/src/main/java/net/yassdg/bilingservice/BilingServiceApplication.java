package net.yassdg.bilingservice;

import net.yassdg.bilingservice.entities.Bill;
import net.yassdg.bilingservice.entities.ProductItem;
import net.yassdg.bilingservice.repository.BillRepository;
import net.yassdg.bilingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BilingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilingServiceApplication.class, args);
    }

    @Bean
    public  CommandLineRunner commandLineRunner(
            BillRepository billRepository,
            ProductItemRepository productItemRepository){
        return args ->{
            List<Long> customersIds =   List.of(1L,2L,3L);
            List<Long> productsIds =   List.of(1L,2L,3L);
            customersIds.forEach(clientId ->{
                Bill bill = new Bill();
                bill.setBillingDate(new Date());
                bill.setCustomerId(clientId);
                billRepository.save(bill);
                productsIds.forEach(productId->{
                    ProductItem productItem = new ProductItem();
                    productItem.setPrice(1000*Math.random()*600);
                    productItem.setQuantity(1+new Random().nextInt(20));
                    productItem.setProductId(productId);
                    productItem.setBill(bill);
                    productItemRepository.save(productItem);
                });
            });
        };
    }

}
