package com.secure.serialization.mapping;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class BigIntegerMapping extends NumberBasedMapping<BigInteger> {	
	public static final BigIntegerMapping DESC = new BigIntegerMapping();
	private BigIntegerMapping() {
		super(i -> new BigDecimal(i), i -> i.toBigInteger());
	}
}
