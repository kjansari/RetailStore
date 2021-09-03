package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.RetailItemType;
import com.example.demo.repository.ItemRepository;
import com.example.demo.util.DiscountApplicableType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepo;
	
	@Value("${percentage.hundred}")
	private BigDecimal percentageValue;
	
	@Override
	public BigDecimal getItemWiseAmount(List<ItemDTO> listItem,BigDecimal discountRate) {
		log.info( "getItemWiseAmount called");
		BigDecimal amount = new BigDecimal(0);
		for (ItemDTO item : listItem) {
			Optional<Item> itemObj = itemRepo.findById(item.getId());
			Item i = itemObj.get();
			BigDecimal price = getItemPrice(i.getPrice(), i.getTypeId(), discountRate);
			BigDecimal itemPrice = price.multiply(item.getQuantity());
			amount = amount.add(itemPrice);
		}
		return amount;
	}
	
	private BigDecimal getItemPrice(BigDecimal price, RetailItemType itemtype, BigDecimal discountPercentage) {
		log.info("{} ",itemtype.getDiscountapplicable() );
		if(StringUtils.equalsIgnoreCase(itemtype.getDiscountapplicable() , DiscountApplicableType.NOT_APPLICABALE.getTypeCode())) {
			return price;
		} else {
			BigDecimal result = price.multiply(discountPercentage).divide(percentageValue);
			price = price.subtract(result);
			return price;
		}
	}

}
