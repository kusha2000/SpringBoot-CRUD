package com.kush.pos.service.impl;

import com.kush.pos.dto.core.CustomerDto;
import com.kush.pos.dto.request.RequestCustomerDto;
import com.kush.pos.dto.response.ResponseCustomerDto;
import com.kush.pos.dto.response.paginated.model.CustomerPaginatedDto;
import com.kush.pos.entity.Customer;
import com.kush.pos.repo.CustomerRepo;
import com.kush.pos.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public ResponseCustomerDto createCustomer(RequestCustomerDto dto) {
        CustomerDto customerDto=new CustomerDto(
                0,
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

        Customer customer=new Customer(
                0,
                customerDto.getPublicId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary(),
                customerDto.isActiveState(),
                null
        );



        customerRepo.save(customer);

        return new ResponseCustomerDto(
                customer.getPublicId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary(),
                customerDto.isActiveState()
        );
    }

    @Override
    public ResponseCustomerDto findCustomer(long id) throws ClassNotFoundException {
        Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);
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

    @Override
    public ResponseCustomerDto updateCustomer(RequestCustomerDto dto, long id) throws ClassNotFoundException {
        Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);

       if(selectedCustomer.isEmpty()) throw new ClassNotFoundException();

        selectedCustomer.get().setName(dto.getName());
        selectedCustomer.get().setAddress(dto.getAddress());
        selectedCustomer.get().setSalary(dto.getSalary());

        customerRepo.save(selectedCustomer.get());

        return new ResponseCustomerDto(
                selectedCustomer.get().getPublicId(),
                selectedCustomer.get().getName(),
                selectedCustomer.get().getAddress(),
                selectedCustomer.get().getSalary(),
                selectedCustomer.get().isActiveState()
        );
    }

    @Override
    public void deleteCustomer(long id){

        //========
        //Method 1
        //========

        /*Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);

        if(selectedCustomer.isPresent()){

            customerRepo.delete(selectedCustomer.get());

            return null;
        }

        throw new ClassNotFoundException();*/


        //========
        //Method 2
        //========


        customerRepo.deleteByPublicId(id);

    }

    @Override
    public CustomerPaginatedDto searchAllCustomers() {

        //===========================
        //Method 1-Without Pagination
        //===========================

        List<Customer> customers = customerRepo.findAll();

        List<ResponseCustomerDto> responseCustomers = customers.stream()
                .map(customer -> new ResponseCustomerDto(
                        customer.getPublicId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getSalary(),
                        customer.isActiveState()
                ))
                .collect(Collectors.toList());

        return new CustomerPaginatedDto(responseCustomers.size(), responseCustomers);


        //===========================
        //Method 2-using Pagination
        //===========================

        //Page<Customer> customers = customerRepo.findAll(PageRequest.of(page,size));

        //================================================
        //Method 3-using Pagination(with using search also)
        //=================================================

//        Page<Customer> customers = customerRepo.searchAllByAddressOrName(searchText,PageRequest.of(page,size));
//
//
//        List<ResponseCustomerDto> list=new ArrayList<>();
//
//        //long recordCount=customerRepo.count();
//        long recordCount=customerRepo.countDataWithSearchText(searchText);
//
//        for(Customer d:customers){
//            list.add(new ResponseCustomerDto(
//                    d.getPublicId(),
//                    d.getName(),
//                    d.getAddress(),
//                    d.getSalary(),
//                    d.isActiveState()
//            ));
//        }
//        return new CustomerPaginatedDto(recordCount, list);
   }
}
