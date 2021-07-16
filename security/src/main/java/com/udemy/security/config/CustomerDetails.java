package com.udemy.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udemy.security.model.Customer;
import com.udemy.security.model.CustomerSecurity;
import com.udemy.security.repository.CustomerRepository;

@Service
public class CustomerDetails implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<Customer> customer = customerRepository.findByEmail(username);
		if(customer.size()==0) {
			throw new UsernameNotFoundException("Username not found in application");
		}
		return new CustomerSecurity(customer.get(0));
	}

}
