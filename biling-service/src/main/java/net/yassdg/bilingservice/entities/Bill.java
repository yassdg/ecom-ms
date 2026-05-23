package net.yassdg.bilingservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.yassdg.bilingservice.model.Customer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime billingDate;
    private Long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;

}
