package net.yassdg.bilingservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long Id;
    private  String name;
    private double price;
    private int quantity;
}