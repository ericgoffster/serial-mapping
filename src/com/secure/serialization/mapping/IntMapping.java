package com.secure.serialization.mapping;

import java.math.BigDecimal;

public final class IntMapping extends NumberBasedMapping<Integer> {
	public static final IntMapping DESC = new IntMapping();
	private IntMapping() {
		super(BigDecimal::valueOf, BigDecimal::intValue);
	}
}
