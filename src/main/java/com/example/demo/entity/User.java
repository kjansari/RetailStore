package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity(name="users")
@Data
public class User {
	@Id
	@GeneratedValue
	private int id;

	@NotNull
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "typeid",referencedColumnName = "id")
	private UserType userRoleId;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate creationdate;
}
