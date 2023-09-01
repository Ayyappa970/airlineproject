package com.jsp.airlines.dto;

import com.jsp.airlines.entity.Flight;
import com.jsp.airlines.entity.Inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class InventoryDTO {
	private int count;
}
