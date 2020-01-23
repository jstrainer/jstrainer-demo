package com.github.jstrainer.jstrainerdemo.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.github.jstrainer.Filtered;
import com.github.jstrainer.filter.StripTags;
import com.github.jstrainer.filter.Trim;
import com.github.jstrainer.jstrainerdemo.strainer.Reverse;

import lombok.Data;

@Data
public class Person {

	@Trim
	@StripTags
	@NotEmpty
	private String name;

	@Trim
	@Email
	private String email;
	
	@Filtered
	private Address address;

	@Reverse
	private String reversed;

}
