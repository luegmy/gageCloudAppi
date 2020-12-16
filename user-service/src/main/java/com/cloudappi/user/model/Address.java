package com.cloudappi.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position = 0)
	private int id;
	@ApiModelProperty(position = 1)
	private String street;
	@ApiModelProperty(position = 2)
	private String state;
	@ApiModelProperty(position = 3)
	private String city;
	@ApiModelProperty(position = 4)
	private String country;
	@ApiModelProperty(position = 5)
	private String zip;

}
