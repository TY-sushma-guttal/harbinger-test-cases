package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.AssignDeliveryRequestDTO;
import com.example.demo.dto.AssignDeliveryResponseDTO;
import com.example.demo.exception.AssignDeliveryException;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.pojo.DeliveryPartner;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserAddress;
import com.example.demo.repository.DeliveryPartnerRepository;
import com.example.demo.repository.UserAddressRepository;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class DeliveryPartnerServiceTest {

	@InjectMocks
	private DeliveryPartnerService deliveryPartnerService;

	@Mock
	private DeliveryPartnerRepository deliveryPartnerRepository;

	@Mock
	private UserAddressRepository addressRepository;

	@Mock
	private UserRepository userRepository;

	private AssignDeliveryRequestDTO assignDeliveryRequestDTO;

	private DeliveryPartner deliveryPartner;

	private User user;

	@BeforeEach
	void testValueSetup() {
		assignDeliveryRequestDTO = new AssignDeliveryRequestDTO(1l, 1l, "userAddress", LocalDateTime.now(),
				LocalDateTime.now());
		deliveryPartner = new DeliveryPartner(1l, "deliveryPartnerName", "location", LocalDateTime.now(),
				LocalDateTime.now(), 4.3, new ArrayList<>());
		user = new User(1l, "userName", 9876543212l, "user@gmail.com", new ArrayList<>(), new ArrayList<>(),
				new ArrayList<>());
	}

	
	/**
	 * This is the test case which includes AssignDeliveryException() in assignDelivery method
	 * @throws Exception
	 */
	@Test
	void assignDeliveryWithAssignDeliveryException() throws Exception {

		Optional<DeliveryPartner> optionalDeliveryPartner = Optional.of(deliveryPartner);
		Optional<User> userOptional = Optional.of(user);

		List<UserAddress> userAddressList = user.getUserAddressList();
		UserAddress userpresentAddress = new UserAddress();
		userAddressList.add(userpresentAddress);

		when(deliveryPartnerRepository.findById(Mockito.any())).thenReturn(optionalDeliveryPartner);

		when(userRepository.findById(Mockito.any())).thenReturn(userOptional);

		when(addressRepository.findByAddressIdInAndAddressType(Mockito.anyList(), Mockito.anyString()))
				.thenReturn(userpresentAddress);

		assertThatThrownBy(() -> deliveryPartnerService.assignDelivery(assignDeliveryRequestDTO))
				.isInstanceOf(AssignDeliveryException.class);
	}

	
	/**
	 * This is the test case which includes RecordNotFoundException() in assignDelivery method
	 * @throws Exception
	 */
	@Test
	void assignDeliveryWithRecordNotFoundException() {
		Optional<DeliveryPartner> optionalDeliveryPartner = Optional.empty();
		Optional<User> userOptional = Optional.empty();

		when(deliveryPartnerRepository.findById(Mockito.any())).thenReturn(optionalDeliveryPartner);

		when(userRepository.findById(Mockito.any())).thenReturn(userOptional);

		assertThatThrownBy(() -> deliveryPartnerService.assignDelivery(assignDeliveryRequestDTO))
				.isInstanceOf(RecordNotFoundException.class);
	}

	
	/**
	 * This is the test case which includes the scenario if the user address is not present in assignDelivery method
	 * @throws Exception
	 */
	@Test
	void assignDeliveryWithoutUserAddress() {
		Optional<DeliveryPartner> optionalDeliveryPartner = Optional.of(deliveryPartner);
		Optional<User> userOptional = Optional.of(user);

		when(deliveryPartnerRepository.findById(Mockito.any())).thenReturn(optionalDeliveryPartner);

		when(userRepository.findById(Mockito.any())).thenReturn(userOptional);

		when(addressRepository.findByAddressIdInAndAddressType(Mockito.anyList(), Mockito.anyString()))
				.thenReturn(null);

		assertThatThrownBy(() -> deliveryPartnerService.assignDelivery(assignDeliveryRequestDTO))
				.isInstanceOf(RecordNotFoundException.class);

	}

	/**
	 * This is the test case used for checking the availability of delivery partner in assignDelivery method
	 * @throws Exception
	 */
	@Test
	void checkAvailability() throws Exception {

		LocalDateTime availableFrom = LocalDateTime.of(2021, 10, 17, 1, 0);
		LocalDateTime availableTo = LocalDateTime.of(2023, 10, 17, 1, 0);

		AssignDeliveryRequestDTO assignDeliveryRequestDTO = new AssignDeliveryRequestDTO(1l, 1l, "userAddress",
				LocalDateTime.now(), LocalDateTime.now());

		DeliveryPartner deliveryPartner = new DeliveryPartner(1l, "deliveryPartnerName", "location", availableFrom,
				availableTo, 4.3, new ArrayList<>());
		Optional<DeliveryPartner> optionalDeliveryPartner = Optional.of(deliveryPartner);

		Optional<User> userOptional = Optional.of(user);

		List<UserAddress> userAddressList = user.getUserAddressList();
		UserAddress userpresentAddress = new UserAddress();
		userpresentAddress.setAddress("location");
		userAddressList.add(userpresentAddress);

		when(deliveryPartnerRepository.findById(Mockito.any())).thenReturn(optionalDeliveryPartner);

		when(userRepository.findById(Mockito.any())).thenReturn(userOptional);

		when(userRepository.save(Mockito.any())).thenReturn(user);

		when(addressRepository.findByAddressIdInAndAddressType(Mockito.anyList(), Mockito.anyString()))
				.thenReturn(userpresentAddress);

		AssignDeliveryResponseDTO assignDelivery = deliveryPartnerService.assignDelivery(assignDeliveryRequestDTO);
		assertThat(assignDelivery.getDeliveryPartnerDTO().getDeliveryPartnerId()).isEqualTo(1l);
	}

	
	/**
	 * This is the test case which includes the scenario if the delivery partner is available with all the required criteria
	 * @throws Exception
	 */
	@Test
	void assignDeliveryWithDeliveryPartner() throws Exception {

		LocalDateTime availableFrom = LocalDateTime.of(2021, 10, 17, 1, 0);
		LocalDateTime availableTo = LocalDateTime.of(2023, 10, 17, 1, 0);

		AssignDeliveryRequestDTO requestDTO = new AssignDeliveryRequestDTO(1l, 1l, "userAddress", LocalDateTime.now(),
				LocalDateTime.now());

		List<DeliveryPartner> deliveryPartners = new ArrayList<DeliveryPartner>();
		DeliveryPartner deliveryPartnerTwo = new DeliveryPartner(2l, "John", "location", availableFrom,
				availableTo, 4.3, new ArrayList<>());
		DeliveryPartner deliveryPartnerThree = new DeliveryPartner(3l, "Robert", "location", availableFrom,
				availableTo, 4.2, new ArrayList<>());
		deliveryPartners.add(deliveryPartner);
		deliveryPartners.add(deliveryPartnerTwo);
		deliveryPartners.add(deliveryPartnerThree);

		Optional<DeliveryPartner> optionalDeliveryPartner = Optional.of(deliveryPartner);
		Optional<User> userOptional = Optional.of(user);

		List<UserAddress> userAddressList = user.getUserAddressList();
		UserAddress userpresentAddress = new UserAddress();
		userpresentAddress.setAddress("location");
		userAddressList.add(userpresentAddress);

		when(deliveryPartnerRepository.findById(Mockito.any())).thenReturn(optionalDeliveryPartner);

//		when(deliveryPartnerRepository.findByLocation(Mockito.anyString())).thenReturn(deliveryPartners);

		when(userRepository.findById(Mockito.any())).thenReturn(userOptional);
		
		when(userRepository.save(Mockito.any())).thenReturn(user);

		when(addressRepository.findByAddressIdInAndAddressType(Mockito.anyList(), Mockito.anyString()))
				.thenReturn(userpresentAddress);

		AssignDeliveryResponseDTO assignDelivery = deliveryPartnerService.assignDelivery(requestDTO);
		assertThat(assignDelivery.getDeliveryPartnerDTO().getDeliveryPartnerId()).isEqualTo(2l);
	}
	
	
	/**
	 * This is the test case for the positive scenario of findAllDeliveryPartners API
	 */
	@Test
	void findAllDeliveryPartnersWhenSuccess() {
		Optional<User> userOptional = Optional.of(user);
		List<DeliveryPartner> deliveryPartners = new ArrayList<DeliveryPartner>();
		deliveryPartners.add(deliveryPartner);
		
		when(userRepository.findById(Mockito.any())).thenReturn(userOptional);
		when(deliveryPartnerRepository.findAll()).thenReturn(deliveryPartners);
		
		List<DeliveryPartner> result = deliveryPartnerService.findAllDeliveryPartners(1l);
		assertThat(result.get(0).getDeliveryPartnerId()).isEqualTo(1l);
	}
	
	/**
	 *This is the test case for the negative scenario of findAllDeliveryPartners API
	 */
	@Test
	void findAllDeliveryPartnersWhenFailure() {
		Optional<User> userOptional = Optional.empty();
		
		when(userRepository.findById(Mockito.any())).thenReturn(userOptional);
		
		assertThatThrownBy(() -> deliveryPartnerService.findAllDeliveryPartners(1l))
		.isInstanceOf(RecordNotFoundException.class);
	}

}
