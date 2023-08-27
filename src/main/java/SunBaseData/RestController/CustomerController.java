package SunBaseData.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SunBaseData.Exception.CustomerNotFoundException;
import SunBaseData.Service.AuthService;
import SunBaseData.Service.CustomerService;
import SunBaseData.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/customers")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/create")
    public ResponseEntity<CustomerEntity> createCustomer(@RequestHeader("Authorization") String bearerToken, @RequestBody CustomerEntity customer) {
        // Validate the bearer token (You should implement this logic in AuthService)
        if (!authService.isValidBearerToken(bearerToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CustomerEntity createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
	
	@GetMapping("/getAllCustomer")
    public ResponseEntity<List<CustomerEntity>> getAllCustomers(@RequestHeader("Authorization") String bearerToken) {
        // Validate the bearer token
        if (!authService.isValidBearerToken(bearerToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<CustomerEntity> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
	
	@GetMapping("/{customerId}")
    public ResponseEntity<CustomerEntity> getCustomerById(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer customerId) {
        // Validate the bearer token
        if (!authService.isValidBearerToken(bearerToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<CustomerEntity> customer = customerService.getCustomerById(customerId);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
	@PutMapping("/{customerId}")
    public ResponseEntity<CustomerEntity> updateCustomer(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer customerId, @RequestBody CustomerEntity updatedCustomer) {
        // Validate the bearer token
        if (!authService.isValidBearerToken(bearerToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            CustomerEntity customer = customerService.updateCustomer(customerId, updatedCustomer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer customerId) {
        // Validate the bearer token
        if (!authService.isValidBearerToken(bearerToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
