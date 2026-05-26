package net.yassdg.bilingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import net.yassdg.bilingservice.model.Product;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItem implements Serializable {
    @Id
    @GeneratedValue
    private  Long id;
    private  long productId;
    private  int quantity;
    private  double price;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  Bill bill;
    @Transient
    private Product product;
}
