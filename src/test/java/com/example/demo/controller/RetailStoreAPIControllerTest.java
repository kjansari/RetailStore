package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.dto.BillDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.repository.DiscountRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ItemService;
import com.example.demo.service.RetailStoreService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RetailStoreAPIController.class)
class RetailStoreAPIControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private RetailStoreService service;
	
	@Mock
	private UserRepository userRepo;

	@Mock
	private DiscountRepository discountRepo;


	@Mock
	private ItemService itemService;

	@Test
	void calculateBillTest() throws Exception {
		String json = "{\r\n" + "	\"itemList\":[{\"id\":1,\"quantity\":2},{\"id\":2,\"quantity\":50}],\r\n"
				+ "    \"userid\":3\r\n" + "    }";

		String expected = "{\"billAmount\":525,\"discountedAmount\":500,\"itemList\":[{\"id\":1,\"quantity\":2},{\"id\":2,\"quantity\":50}],\"userid\":3}";
		
		when(service.calculateBillAmount(getObject())).thenReturn(getBillAmount());
		
		RequestBuilder request = MockMvcRequestBuilders.post("/bills/calculate").contentType(MediaType.APPLICATION_JSON)
				.content(json);
		MvcResult result = mockmvc.perform(request).andExpect(status().isOk()).andReturn();

		assertEquals(expected, result.getResponse().getContentAsString());
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
		
		return  bill;

	}
	
	private BillDTO getBillAmount() {
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
		bill.setBillAmount(new BigDecimal(525));
		bill.setDiscountedAmount(new BigDecimal(500));
		
		return  bill;

	}

}
