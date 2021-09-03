package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Discount;
import com.example.demo.entity.UserType;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = { "spring.jpa.hibernate.ddl-auto=create-drop" })
class DiscountRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	UserTypeRepository userTypeRepo;

	@Test
	void getDiscountByUserTypeTest() {

		Discount discount = getDiscountObject();
		discountRepository.save(discount);
		entityManager.flush();

		Discount result = discountRepository.getDiscountByUserType(discount.getUserTypeId().getId());

		assertEquals(getDiscountObject(), result);

	}

	private Discount getDiscountObject() {
		UserType type = new UserType(1, "Employee");
		return new Discount(2, new BigDecimal(5), type);
	}

}
