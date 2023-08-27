package SunBaseData.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import SunBaseData.entity.CustomerEntity;

public interface CustomerRepository  extends JpaRepository<CustomerEntity, Integer>{
	
	Optional<CustomerEntity> findByEmail(String email); 
}
