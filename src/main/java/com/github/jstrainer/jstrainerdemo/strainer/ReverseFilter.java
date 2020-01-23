package com.github.jstrainer.jstrainerdemo.strainer;

import com.github.jstrainer.filter.Filter;

public class ReverseFilter implements Filter<Reverse, String> {

	@Override
	public String filter(String value, Reverse annotation) {
		if (value == null) {
			return value;
		}

		return new StringBuffer(value).reverse().toString();
	}

}
