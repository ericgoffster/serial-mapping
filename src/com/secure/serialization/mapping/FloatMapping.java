package com.secure.serialization.mapping;

import java.math.BigDecimal;

public final class FloatMapping extends NumberBasedMapping<Float> {
	public static final FloatMapping DESC = new FloatMapping();
	private FloatMapping() {
		super(BigDecimal::valueOf, BigDecimal::floatValue);
	}
}
