package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class IntMappingTest {

	private static final IntMapping DESC = IntMapping.DESC;
	
	private static Serialized of(String d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(new BigDecimal(d));
	}
	private static Serialized serialize(Integer d) {
		return DESC.serialize(d);
	}
	private static Integer deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Integer.valueOf(5)), of("5"));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of("5")), Integer.valueOf(5));
		long l = 5000000000000000L;
		assertEquals(deserialize(of(String.valueOf(l))), Integer.valueOf((int)l));
		l = Integer.MAX_VALUE + 1L;
		assertEquals(deserialize(of(String.valueOf(l))), Integer.valueOf((int)l));
		l = Integer.MIN_VALUE - 1L;
		assertEquals(deserialize(of(String.valueOf(l))), Integer.valueOf((int)l));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
