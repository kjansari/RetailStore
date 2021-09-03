package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{

}
