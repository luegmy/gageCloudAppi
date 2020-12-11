package com.cloudappi.user.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String birthDate;//format LocalDateTime
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_address")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Address address;
}
