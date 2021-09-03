package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillDTO {

	@NotEmpty(message = "List should not empty")
	@JsonProperty("itemList")
	private List<ItemDTO> itemList;

	private BigDecimal billAmount;
	private BigDecimal discountedAmount;
	
	@JsonProperty("userid")
	@NotNull(message="userid is mandatory")
	private Integer userid;

}
