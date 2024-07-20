package com.food.phat.dto;


import lombok.Data;

@Data
public class CustomerAddressDTO {

    private Integer customerAddressId;
    private String address;
    private String country;
    private String city;
    private String phone;
}
