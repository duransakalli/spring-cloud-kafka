package com.drn.springcloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private String ssn;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;

}
