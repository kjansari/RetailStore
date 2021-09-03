package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.dto.ItemDTO;

public interface ItemService {

	public BigDecimal getItemWiseAmount(List<ItemDTO> listItem,BigDecimal discountRate);
}
