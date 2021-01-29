package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class BigDecimalMappingTest {

	private static final BigDecimalMapping DESC = BigDecimalMapping.DESC;
	
	private static Serialized of(BigDecimal d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d);
	}
	private static Serialized serialize(BigDecimal d) {
		return DESC.serialize(d);
	}
	private static BigDecimal deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(new BigDecimal("5.0")), of(new BigDecimal("5.0")));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(new BigDecimal("5.0"))), new BigDecimal("5"));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
