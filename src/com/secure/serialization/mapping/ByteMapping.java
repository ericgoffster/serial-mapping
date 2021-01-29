package com.secure.serialization.mapping;

import java.math.BigDecimal;

public final class ByteMapping extends NumberBasedMapping<Byte> {
	public static final ByteMapping DESC = new ByteMapping();
	private ByteMapping() {
		super(BigDecimal::valueOf, BigDecimal::byteValue);
	}
}
