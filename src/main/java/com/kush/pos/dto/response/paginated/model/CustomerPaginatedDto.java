package com.kush.pos.dto.response.paginated.model;


import com.kush.pos.dto.core.CustomerDto;
import com.kush.pos.dto.response.ResponseCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerPaginatedDto {
    private long dataCount;
    private List<ResponseCustomerDto> list;
}
