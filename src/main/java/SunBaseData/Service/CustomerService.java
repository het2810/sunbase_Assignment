package SunBaseData.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SunBaseData.Exception.CustomerNotFoundException;
import SunBaseData.Exception.DuplicateCustomerException;
import SunBaseData.entity.CustomerEntity;
import SunBaseData.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	// Create Customer
	public CustomerEntity createCustomer(CustomerEntity customer) {
		 if (customerRepo.findByEmail(customer.getEmail()).isPresent()) {
	            throw new DuplicateCustomerException("A customer with this email already exists.");
	        }
		return customerRepo.save(customer);
	}

	// Read Data
	public List<CustomerEntity> getAllCustomers() {
		return (List<CustomerEntity>) customerRepo.findAll();
	}

	public Optional<CustomerEntity> getCustomerById(Integer customerId) {
		return customerRepo.findById(customerId);
	}
	
	//Update Customer
	public CustomerEntity updateCustomer(Integer customerId,CustomerEntity updatedCustomer) {
		Optional<CustomerEntity> existingCustomer = customerRepo.findById(customerId);
		
		if(existingCustomer.isPresent()) {
			updatedCustomer.setCustomerId(customerId);
			return customerRepo.save(updatedCustomer);
		}else {
			throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
		}

	}
	
	//Delete Customer
	public void deleteCustomer(Integer CustomerId) {
		if(!customerRepo.existsById(CustomerId)) {
			throw new CustomerNotFoundException("Customer Not Found with Id :"+ CustomerId);
		}
		customerRepo.deleteById(CustomerId);
		
	}

}
