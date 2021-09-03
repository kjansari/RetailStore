package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetailItemType {

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	private String typename;
	
	@NotNull
	private String discountapplicable;	
	
}
