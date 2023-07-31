package com.drn.springcloud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String buildingNumber;
    private String streetName;
    private String city;
    private String country;
    private String postalCode;

}
