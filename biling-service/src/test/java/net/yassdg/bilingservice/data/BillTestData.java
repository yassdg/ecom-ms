package net.yassdg.bilingservice.data;

import lombok.experimental.UtilityClass;
import net.yassdg.bilingservice.entities.Bill;
import net.yassdg.bilingservice.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class BillTestData {

    public static Long ID = 1L;
    public static Pageable PAGE = PageRequest.of(0, 10);
    public static final String ERROR_MESSAGE = "Exception message";


    public static List<Bill> listOfBills() {
        return List.of(billOne(),billTwo());
    }

    public static Page<Bill> pageOfBills() {
        return new PageImpl<>(BillTestData.listOfBills());
    }

    public static Bill billOne() {
       final Customer customer = CustomerTestData.customerOne();
        return Bill.builder()
                .id(1L)
                .customerId(customer.getId())
                .billingDate(LocalDateTime.now().minusMonths(1))
                .customer(customer)
                .build();
    }

    public static Bill billTwo() {
        final Customer customer = CustomerTestData.customerTwo();
        return Bill.builder()
                .id(2L)
                .customerId(customer.getId())
                .billingDate(LocalDateTime.now().minusDays(7))
                .customer(customer)
                .build();
    }

}
