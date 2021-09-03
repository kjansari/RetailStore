package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Bill {
	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid",referencedColumnName = "id")
	private User userid;
	
	
	@NotNull
	private long amount;
}
