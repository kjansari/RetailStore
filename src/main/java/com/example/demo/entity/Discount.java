package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@Column(name="discount_percentage")
	private BigDecimal discountPercentage;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usertypeid",referencedColumnName = "id")
	private UserType userTypeId;
	
	
}
