package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.dto.BillDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Discount;
import com.example.demo.entity.User;
import com.example.demo.entity.UserType;
import com.example.demo.repository.DiscountRepository;
import com.example.demo.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class RetailServiceImplTest {

	@Mock
	private UserRepository userRepo;

	@Mock
	private DiscountRepository discountRepo;

	@Mock
	private RetailStoreService service;

	@Mock
	private ItemService itemService;

	@BeforeEach
	public void setup() {
		service = new RetailStoreServiceImpl();
		ReflectionTestUtils.setField(service, "userRepo", userRepo);
		ReflectionTestUtils.setField(service, "discountRepo", discountRepo);
		ReflectionTestUtils.setField(service, "service", itemService);
	}

	@Test
	void calculateBillAmountTestForNewCustomer() {

		when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(getUserObject()));
		
		when(discountRepo.getDiscountByUserType(Mockito.anyInt())).thenReturn(getDiscountObject());
		
		when(itemService.getItemWiseAmount(getObject().getItemList(),getDiscountObject().getDiscountPercentage())).thenReturn(new BigDecimal(525));

		BillDTO discAmount = service.calculateBillAmount(getObject());
		
		assertEquals(getBillDTOResultObject(), discAmount);
		
		assertNotNull(discAmount);
	}
	
	
	@Test
	void calculateBillAmountTestForOldCustomer() {

		
		when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(getOldUserObject()));
		
		when(discountRepo.getDiscountByUserType(Mockito.anyInt())).thenReturn(getDiscountObjectForZeroDiscount());
		
		when(itemService.getItemWiseAmount(getObject().getItemList(),getDiscountObject().getDiscountPercentage())).thenReturn(new BigDecimal(525));
		
		when(itemService.getItemWiseAmount(getObject().getItemList(),new BigDecimal(5))).thenReturn(new BigDecimal(725));

		BillDTO discAmount = service.calculateBillAmount(getObject());
		
		assertEquals(getBillDTOResultObjectForOldCustomer(), discAmount);
		
		assertNotNull(discAmount);
	}

	private BillDTO getObject() {
		BillDTO bill = new BillDTO();
		ItemDTO item1 = new ItemDTO();
		item1.setId(1);
		item1.setQuantity(new BigDecimal(2));

		ItemDTO item2 = new ItemDTO();
		item2.setId(2);
		item2.setQuantity(new BigDecimal(50));

		List<ItemDTO> list = new ArrayList<>();
		list.add(item1);
		list.add(item2);
		bill.setItemList(list);
		bill.setUserid(3);

		return bill;

	}
	
	private User getUserObject() {
		User user = new User();
		user.setId(1);
		user.setCreationdate(LocalDate.now());
		user.setName("abc");
		user.setUserRoleId(getUserTypeObject());
		return user;
	}
	
	private UserType getUserTypeObject() {
		UserType type = new UserType();
		type.setId(1);
		type.setTypename("Employee");
		return type;
	}
	
	private Discount getDiscountObject() {
		Discount disc = new Discount();
		disc.setId(1);
		disc.setDiscountPercentage(new BigDecimal(30));	
		disc.setUserTypeId(getUserTypeObject());
		return disc;
	}
	
	private BillDTO getBillDTOResultObject() {
		BillDTO bill = getObject();
		bill.setBillAmount(new BigDecimal(525));
		bill.setDiscountedAmount(new BigDecimal(500));
		
		return bill;

	}
	public User getOldUserObject() {
		User user = new User();
		user.setId(3);
		user.setName("xyz");
		user.setCreationdate(LocalDate.now().minusYears(2));
		user.setUserRoleId(getUserTypeObjectForOtherType());
		return user;
	}
	private UserType getUserTypeObjectForOtherType() {
		UserType type = new UserType();
		type.setId(3);
		type.setTypename("Other");
		return type;
	}
	private Discount getDiscountObjectForZeroDiscount() {
		Discount disc = new Discount();
		disc.setId(1);
		disc.setDiscountPercentage(new BigDecimal(0));	
		disc.setUserTypeId(getUserTypeObjectForOtherType());
		return disc;
	}
	
	private BillDTO getBillDTOResultObjectForOldCustomer() {
		BillDTO bill = getObject();
		bill.setBillAmount(new BigDecimal(725));
		bill.setDiscountedAmount(new BigDecimal(690));
		
		return bill;

	}
}
