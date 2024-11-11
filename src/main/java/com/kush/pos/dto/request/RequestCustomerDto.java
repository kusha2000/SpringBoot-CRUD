package com.kush.pos.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestCustomerDto {
    private String name;
    private String address;
    private double salary;
}
