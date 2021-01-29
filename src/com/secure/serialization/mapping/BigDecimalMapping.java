package com.secure.serialization.mapping;

import java.math.BigDecimal;

public final class BigDecimalMapping extends NumberBasedMapping<BigDecimal> {
	public static final BigDecimalMapping DESC = new BigDecimalMapping();
	private BigDecimalMapping() {
		super(i -> i, i -> i);
	}
}
