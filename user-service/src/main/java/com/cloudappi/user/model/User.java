package com.cloudappi.user.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id user",position = 0)
	private int id;
	
	@NotEmpty
	@ApiModelProperty(notes = "name user" ,position = 1)
	private String name;
	
	@Email
	@ApiModelProperty(notes = "email user" ,position = 2)
	private String email;
	
	@NotNull
	@ApiModelProperty(notes = "LocalDateTime type",position = 3)
	private String birthDate;//format LocalDateTime
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_address")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@NotNull
	@ApiModelProperty(position = 4)
	private Address address;
}
