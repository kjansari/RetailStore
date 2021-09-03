package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.RetailItemType;

public interface ItemTypeRepository extends JpaRepository<RetailItemType, Integer> {

	
}
