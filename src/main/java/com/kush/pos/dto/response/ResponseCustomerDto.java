package com.kush.pos.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseCustomerDto {

    private long publicId;
    private String name;
    private String address;
    private double salary;
    private boolean activeState;
}
