package net.yassdg.customerservice;

import net.yassdg.customerservice.entities.Customer;
import net.yassdg.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args ->{
            //customerRepository.save(new Customer(null,"Yacine","med@gmail.com"));
            customerRepository.save(Customer.builder().name("Yacine").email("med@gmail.com").build());
            customerRepository.save(Customer.builder().name("Fallou").email("fall@gmail.com").build());
            customerRepository.save(Customer.builder().name("Khady").email("kdy@gmail.com").build());

        };
    }
}
