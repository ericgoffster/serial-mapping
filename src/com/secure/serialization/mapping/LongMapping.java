package com.secure.serialization.mapping;

import java.math.BigDecimal;

public final class LongMapping extends NumberBasedMapping<Long> {
	public static final LongMapping DESC = new LongMapping();
	private LongMapping() {
		super(BigDecimal::valueOf, BigDecimal::longValue);
	}
}
