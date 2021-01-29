package com.secure.serialization.mapping;

import java.math.BigDecimal;

public final class ShortMapping extends NumberBasedMapping<Short> {
	public static final ShortMapping DESC = new ShortMapping();
	private ShortMapping() {
		super(BigDecimal::valueOf, BigDecimal::shortValue);
	}
}
