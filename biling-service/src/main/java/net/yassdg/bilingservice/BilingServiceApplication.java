package net.yassdg.bilingservice;

import lombok.RequiredArgsConstructor;
import net.yassdg.bilingservice.entities.Bill;
import net.yassdg.bilingservice.entities.ProductItem;
import net.yassdg.bilingservice.repository.BillRepository;
import net.yassdg.bilingservice.repository.ProductItemRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@SpringBootApplication
@EnableFeignClients
public class BilingServiceApplication implements CommandLineRunner {

    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;


    public static void main(String[] args) {
        SpringApplication.run(BilingServiceApplication.class, args);
    }

    @Override
    public void run(String @NonNull ... args) {
        List<Long> customersIds = List.of(1L, 2L, 3L);
        List<Long> productsIds = List.of(1L, 2L, 3L);
        customersIds.forEach(clientId -> {
            Bill bill = new Bill();
            bill.setBillingDate(LocalDateTime.now());
            bill.setCustomerId(clientId);
            billRepository.save(bill);
            productsIds.forEach(productId -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(1000 * Math.random() * 600);
                productItem.setQuantity(1 + new Random().nextInt(20));
                productItem.setProductId(productId);
                productItem.setBill(bill);
                productItemRepository.save(productItem);
            });
        });
    }
}
