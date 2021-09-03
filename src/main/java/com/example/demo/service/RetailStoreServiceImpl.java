package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BillDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Discount;
import com.example.demo.entity.User;
import com.example.demo.repository.DiscountRepository;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RetailStoreServiceImpl implements RetailStoreService {


	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DiscountRepository discountRepo;
	
	@Autowired
	private ItemService service;
	
	@Value("${percentage.hundred}")
	private int hundredValue;
	
	@Value("${discountlimitForAmount}")
	private BigDecimal discountlimitForAmount;
	
	@Value("${noOfYear}")
	private int noOfYear;
	
	@Value("${discountOnBillAmount}")
	private int discountOnBillAmount;
	
	@Value("${discountIfOldCustomer}")
	private BigDecimal discountIfOldCustomer;


	@Override
	public BillDTO calculateBillAmount(BillDTO bill) {
		List<ItemDTO> listItem = bill.getItemList();
		BigDecimal amount = new BigDecimal(0);
		BigDecimal discountedPrice = new BigDecimal(0);

		Optional<User> user = userRepo.findById(bill.getUserid());
		int usertype = user.get().getUserRoleId().getId();
		Discount dicount = discountRepo.getDiscountByUserType(usertype);
		log.info("{}",dicount.getDiscountPercentage() );

		LocalDate lt = LocalDate.now();
		Period p = Period.between(user.get().getCreationdate(), lt);

		// check how old customer
		if (p.getYears() >= noOfYear && dicount.getDiscountPercentage().intValue() == BigDecimal.ZERO.intValue()) {
			dicount.setDiscountPercentage(discountIfOldCustomer);
		}

		
		amount = service.getItemWiseAmount(listItem, dicount.getDiscountPercentage());

		bill.setBillAmount(amount);// actual amount

		if (amount.compareTo(discountlimitForAmount) == 1 || amount.compareTo(discountlimitForAmount) == 0) {
			int a = amount.intValue() / hundredValue;
			int discount = a * discountOnBillAmount;
			discountedPrice = amount.subtract(new BigDecimal(discount));
		}

		bill.setDiscountedAmount(discountedPrice);// discount amount

		return bill;
	}


}
