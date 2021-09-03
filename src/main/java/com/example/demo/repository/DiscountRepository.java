package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer>{

	@Query("select d from Discount d where usertypeid= ?1")
	public Discount getDiscountByUserType(int i);
	
	

}
