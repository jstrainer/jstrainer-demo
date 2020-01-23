package com.github.jstrainer.jstrainerdemo.model;

import org.hibernate.validator.constraints.Length;

import com.github.jstrainer.filter.Numeric;
import com.github.jstrainer.filter.ToUpperCase;

import lombok.Data;

@Data
public class Address {

	@Numeric
	private String postcode;

	@Length
	private String streetName;

	private String buildingNumber;

	private String city;

	private String stateOrRegion;

	@ToUpperCase
	private String country;

}
