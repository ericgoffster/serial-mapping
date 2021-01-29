package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class BigIntegerMappingTest {

	private static final BigIntegerMapping DESC = BigIntegerMapping.DESC;
	
	private static Serialized of(BigInteger d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(new BigDecimal(d));
	}
	private static Serialized serialize(BigInteger d) {
		return DESC.serialize(d);
	}
	private static BigInteger deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(new BigInteger("5")), of(new BigInteger("5")));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(new BigInteger("5"))), new BigInteger("5"));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
