package com.kush.pos.api;

import com.kush.pos.db.Database;
import com.kush.pos.dto.request.RequestCustomerDto;

import com.kush.pos.service.CustomerService;
import com.kush.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody RequestCustomerDto customerDto) {

        // =================
        // Save in the RAM
        //==================

        /*return Database.createCustomer(customerDto).toString();
        var savedData= Database.createCustomer(customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Saved:",savedData), HttpStatus.CREATED
       );*/


        // ====================
        // Save in the Database
        //=====================

        var savedData= customerService.createCustomer(customerDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Saved:",savedData), HttpStatus.CREATED
       );

    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findCustomer(@PathVariable long id) throws ClassNotFoundException {

        // =================
        // Save in the RAM
        //==================


        /*return new ResponseEntity<>(
                new StandardResponse(200,"Customer Data",Database.findCustomer(id)), HttpStatus.OK
        );*/

        // ====================
        // Save in the Database
        //=====================

        return new ResponseEntity<>(
                new StandardResponse(200,"Customer Data",customerService.findCustomer(id)), HttpStatus.OK
        );
    }

    @PutMapping(params="id")
    public ResponseEntity<StandardResponse>  updateCustomer(@RequestParam int id,@RequestBody RequestCustomerDto customerDto) throws ClassNotFoundException {

        // =================
        // Save in the RAM
        //==================


        /*var savedData= Database.updateCustomer(customerDto,id);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Updated:",savedData), HttpStatus.CREATED
        );*/

        // ====================
        // Save in the Database
        //=====================

        var savedData= customerService.updateCustomer(customerDto,id);
        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Updated:",savedData), HttpStatus.CREATED
        );

    }

    @DeleteMapping(params="id")
    public ResponseEntity<StandardResponse>  deleteCustomer(@RequestParam int id) throws ClassNotFoundException {

        // =================
        // Save in the RAM
        //==================


        /*return new ResponseEntity<>(
                new StandardResponse(201,"Customer Deleted",Database.deleteCustomer(id)), HttpStatus.NO_CONTENT
        );*/

        // ====================
        // Save in the Database
        //=====================

        customerService.deleteCustomer(id);

        return new ResponseEntity<>(
                new StandardResponse(201,"Customer Deleted",null), HttpStatus.NO_CONTENT
        );

    }



    @GetMapping("/list")
    public ResponseEntity<StandardResponse>   getAllCustomer() {

        // =================
        // Save in the RAM
        //==================


        /*return new ResponseEntity<>(
                new StandardResponse(200,"Customer List",Database.searchAllCustomers(page,size,searchText)), HttpStatus.OK
        );*/

        // ====================
        // Save in the Database
        //=====================

        return new ResponseEntity<>(
                new StandardResponse(200,"Customer List",customerService.searchAllCustomers()), HttpStatus.OK
        );
    }
}
