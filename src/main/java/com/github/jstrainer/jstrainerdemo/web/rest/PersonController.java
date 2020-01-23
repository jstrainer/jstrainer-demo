package com.github.jstrainer.jstrainerdemo.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jstrainer.Filtered;
import com.github.jstrainer.jstrainerdemo.model.Person;

@RestController
@RequestMapping("/people")
public class PersonController {

	Logger logger = LoggerFactory.getLogger(PersonController.class);

	@PostMapping
	public Person create(@RequestBody @Validated @Filtered Person person) {
		return person;
	}

}
