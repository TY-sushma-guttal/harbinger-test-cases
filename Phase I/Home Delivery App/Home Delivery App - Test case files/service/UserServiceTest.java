package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.GetAllUserDTO;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserAddress;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	
	/**
	 * This is the test case for getUserDetails method
	 */
	@Test
	void getUserDetails() {
		List<User> userList = new ArrayList<User>();
		
		User user = new User(1l, "userName", 9876543212l, "user@gmail.com", new ArrayList<>(), new ArrayList<>(),
				new ArrayList<>());
		
		List<Product> products = new ArrayList<Product>();
		Product product =new Product();
		products.add(product);
		user.setUserProductList(products);
		
		List<UserAddress> addresses = new ArrayList<UserAddress>();
		UserAddress address = new UserAddress();
		addresses.add(address);
		user.setUserAddressList(addresses);
		
		userList.add(user);
		
		when(userRepository.findAll()).thenReturn(userList);
		
		List<GetAllUserDTO> result = userService.getUserDetails();
		assertThat(result.get(0).getEmailId()).isEqualTo("user@gmail.com");

	}

}
