package net.yassdg.bilingservice.data;

import lombok.experimental.UtilityClass;
import net.yassdg.bilingservice.model.Customer;


@UtilityClass
public class CustomerTestData {

    public static Customer customerOne() {
        return Customer.builder()
                .id(1L)
                .name("Customer 1")
                .email("customer1@domain.ext")
                .build();
    }

    public static Customer customerTwo() {
        return Customer.builder()
                .id(1L)
                .name("Customer 2")
                .email("customer2@domain.ext")
                .build();
    }

}
