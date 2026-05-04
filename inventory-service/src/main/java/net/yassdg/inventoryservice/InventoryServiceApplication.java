package net.yassdg.inventoryservice;

import net.yassdg.inventoryservice.Repository.ProductRepository;
import net.yassdg.inventoryservice.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner strart(ProductRepository productRepository){
        return args ->{
            productRepository.save(Product.builder().name("Computer").price(12000).quantity(45).build());
            productRepository.save(Product.builder().name("Printer").price(34000).quantity(10).build());
            productRepository.save(Product.builder().name("Smart phone").price(110000).quantity(6).build());
            //productRepository.save(Product.builder().name("").price().quantity().build());
            //productRepository.save(Product.builder().name("").price().quantity().build());


        };
    }
}
