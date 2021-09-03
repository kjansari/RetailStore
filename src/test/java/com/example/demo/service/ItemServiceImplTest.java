package com.example.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.RetailItemType;
import com.example.demo.repository.ItemRepository;

@ExtendWith(SpringExtension.class)
class ItemServiceImplTest {

	
	ItemService service ;

	@Mock
	ItemRepository dao;	
	
	@BeforeEach
	public void setup() {
		service= new ItemServiceImpl();
		ReflectionTestUtils.setField(service, "itemRepo", dao);
		ReflectionTestUtils.setField(service, "percentageValue", BigDecimal.valueOf(100));
	}

	@Test
	public void getItemWiseAmountTestWithDiscountApplicable() throws Exception {

		List<ItemDTO> listItem = getlistObject();
		RetailItemType itemType = new RetailItemType(2,"stastionary","y");
		Item item = new Item(1,"pencil",itemType,new BigDecimal(10));
		when(dao.findById(Mockito.anyInt())).thenReturn(Optional.of(item));
		
		BigDecimal discAmount = service.getItemWiseAmount(listItem, new BigDecimal(10));
		assertEquals(new BigDecimal(1386), discAmount);
	}
	
	
	@Test
	public void getItemWiseAmountTestWithDiscountNotApplicable() throws Exception {

		List<ItemDTO> listItem = getlistObject();
		RetailItemType itemType = new RetailItemType(1,"groceries","n");
		Item item = new Item(1,"sugar",itemType,new BigDecimal(10));
		when(dao.findById(Mockito.anyInt())).thenReturn(Optional.of(item));
		
		BigDecimal discAmount = service.getItemWiseAmount(listItem, new BigDecimal(10));
		assertEquals(new BigDecimal(1540), discAmount);
	}

	private List<ItemDTO> getlistObject() {
		List<ItemDTO> listItem = new ArrayList();
		ItemDTO i1 = new ItemDTO(1, new BigDecimal(2));
		ItemDTO i2 = new ItemDTO(2, new BigDecimal(50));
		ItemDTO i3 = new ItemDTO(7, new BigDecimal(2));
		ItemDTO i4 = new ItemDTO(3, new BigDecimal(100));
		listItem.add(i1);
		listItem.add(i2);
		listItem.add(i3);
		listItem.add(i4);
		return listItem;
	}

}
