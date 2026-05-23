package net.yassdg.bilingservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {
    private Long id;
    private String name;
    private  String email;
}
