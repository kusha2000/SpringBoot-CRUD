package com.kush.pos.api;

import com.kush.pos.db.Database;
import com.kush.pos.dto.request.RequestCustomerDto;

import com.kush.pos.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @PostMapping
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody RequestCustomerDto customerDto) {
        //return Database.createCustomer(customerDto).toString();
        var savedData= Database.createCustomer(customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Saved:",savedData), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findCustomer(@PathVariable int id) throws ClassNotFoundException {
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer Data",Database.findCustomer(id)), HttpStatus.OK
        );
    }

    @PutMapping(params="id")
    public ResponseEntity<StandardResponse>  updateCustomer(@RequestParam int id,@RequestBody RequestCustomerDto customerDto) throws ClassNotFoundException {
        var savedData= Database.updateCustomer(customerDto,id);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Updated:",savedData), HttpStatus.CREATED
        );
    }

    @DeleteMapping(params="id")
    public ResponseEntity<StandardResponse>  deleteCustomer(@RequestParam int id) throws ClassNotFoundException {
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Deleted",Database.deleteCustomer(id)), HttpStatus.NO_CONTENT
        );
    }



    @GetMapping("/list")
    public ResponseEntity<StandardResponse>   getAllCustomer(@RequestParam int page,@RequestParam int size,@RequestParam String searchText) {
        return new ResponseEntity<>(
                new StandardResponse(200,"Customer List",Database.searchAllCustomers(page,size,searchText)), HttpStatus.OK
        );
    }
}
