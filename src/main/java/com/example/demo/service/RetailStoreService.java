package com.example.demo.service;

import com.example.demo.dto.BillDTO;

public interface RetailStoreService {
	public BillDTO calculateBillAmount(BillDTO bill);
}
