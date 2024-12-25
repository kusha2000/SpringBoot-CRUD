package com.kush.pos.service;

import com.kush.pos.dto.core.CustomerDto;
import com.kush.pos.dto.request.RequestCustomerDto;
import com.kush.pos.dto.response.ResponseCustomerDto;
import com.kush.pos.dto.response.paginated.model.CustomerPaginatedDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface CustomerService {
    public  ResponseCustomerDto createCustomer(RequestCustomerDto dto);

    public  ResponseCustomerDto findCustomer(long id) throws ClassNotFoundException;

    public  ResponseCustomerDto updateCustomer(RequestCustomerDto dto,long id) throws ClassNotFoundException;

    public  void deleteCustomer(long id);

    public  CustomerPaginatedDto searchAllCustomers(int page, int size, String searchText);
}
