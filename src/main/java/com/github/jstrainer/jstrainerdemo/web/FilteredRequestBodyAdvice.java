package com.github.jstrainer.jstrainerdemo.web;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.github.jstrainer.Filtered;
import com.github.jstrainer.Strainer;

@ControllerAdvice
public class FilteredRequestBodyAdvice implements RequestBodyAdvice {

	@Autowired
	private Strainer strainer;

	@Override
	public boolean supports(MethodParameter methodParameter, Type valueType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return methodParameter.hasParameterAnnotation(Filtered.class);
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type valueType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type valueType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		strainer.filter(body);
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type valueType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

}
