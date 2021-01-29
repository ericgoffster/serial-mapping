package com.secure.serialization.mapping;

import java.math.BigDecimal;

public final class DoubleMapping extends NumberBasedMapping<Double> {
	public static final DoubleMapping DESC = new DoubleMapping();
	private DoubleMapping() {
		super(BigDecimal::valueOf, BigDecimal::doubleValue);
	}
}
