package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BillDTO;
import com.example.demo.service.RetailStoreService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("bills")
@Slf4j
@Validated
public class RetailStoreAPIController {

	@Autowired
	private RetailStoreService service;


	@PostMapping("/calculate")
	public BillDTO calculateBill( @Valid @RequestBody BillDTO dto) {
		log.info("called with userid "+dto.getUserid());
		
		return service.calculateBillAmount(dto);

	}

}
