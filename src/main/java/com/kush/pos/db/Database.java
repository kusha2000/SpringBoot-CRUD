package com.kush.pos.db;

import com.kush.pos.dto.core.CustomerDto;
import com.kush.pos.dto.request.RequestCustomerDto;
import com.kush.pos.dto.response.ResponseCustomerDto;
import com.kush.pos.dto.response.paginated.model.CustomerPaginatedDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Database {
    public static ArrayList<CustomerDto> customerTable=new ArrayList<>();

    public static ResponseCustomerDto createCustomer(RequestCustomerDto dto){
        CustomerDto customerDto=new CustomerDto(
                new Random().nextLong(100001),
                new Random().nextLong(100001),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary(),
                true,
                null,
                null,
                null,
                null
        );

        customerTable.add(customerDto);
        return new ResponseCustomerDto(
                customerDto.getPublicId(),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary(),
                customerDto.isActiveState()
        );
    }

    public static ResponseCustomerDto findCustomer(int id) throws ClassNotFoundException {

        Optional<CustomerDto> selectedCustomer= customerTable.stream().filter(e -> e.getPublicId()==id).findFirst();

        if(selectedCustomer.isPresent()){
            return new ResponseCustomerDto(
                    selectedCustomer.get().getPublicId(),
                    selectedCustomer.get().getName(),
                    selectedCustomer.get().getAddress(),
                    selectedCustomer.get().getSalary(),
                    selectedCustomer.get().isActiveState()
            );
        }

        throw new ClassNotFoundException();

    }

    public static ResponseCustomerDto updateCustomer(RequestCustomerDto dto,int id) throws ClassNotFoundException {
        Optional<CustomerDto> selectedCustomer= customerTable.stream().filter(e -> e.getPublicId()==id).findFirst();

        if(selectedCustomer.isPresent()){
                    selectedCustomer.get().setName(dto.getName());
                    selectedCustomer.get().setAddress(dto.getAddress());
                    selectedCustomer.get().setSalary(dto.getSalary());

            return new ResponseCustomerDto(
                    selectedCustomer.get().getPublicId(),
                    selectedCustomer.get().getName(),
                    selectedCustomer.get().getAddress(),
                    selectedCustomer.get().getSalary(),
                    selectedCustomer.get().isActiveState()
            );
        }
        throw new ClassNotFoundException();
    }

    public static ResponseCustomerDto deleteCustomer(int id) throws ClassNotFoundException {
        Optional<CustomerDto> selectedCustomer= customerTable.stream().filter(e -> e.getPublicId()==id).findFirst();

        if(selectedCustomer.isPresent()){
            customerTable.remove(selectedCustomer.get());
            return null;
        }

        throw new ClassNotFoundException();
    }

    public static CustomerPaginatedDto searchAllCustomers(int page,int size,String searchText){
        List<ResponseCustomerDto> list=new ArrayList<>();
        for(CustomerDto d:customerTable){
            list.add(new ResponseCustomerDto(
                    d.getPublicId(),
                    d.getName(),
                    d.getAddress(),
                    d.getSalary(),
                    d.isActiveState()
            ));
        }
        return new CustomerPaginatedDto(customerTable.size(), list);
    }
}
