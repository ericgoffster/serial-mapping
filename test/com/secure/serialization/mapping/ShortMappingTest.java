package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class ShortMappingTest {

	private static final ShortMapping DESC = ShortMapping.DESC;
	
	private static Serialized of(String d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(new BigDecimal(d));
	}
	private static Serialized serialize(Short d) {
		return DESC.serialize(d);
	}
	private static Short deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Short.valueOf((short)5)), of("5"));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of("5")), Short.valueOf((short)5));
		long l = 5000000000000000L;
		assertEquals(deserialize(of(String.valueOf(l))), Short.valueOf((short)l));
		l = Short.MAX_VALUE + 1L;
		assertEquals(deserialize(of(String.valueOf(l))), Short.valueOf((short)l));
		l = Short.MIN_VALUE - 1L;
		assertEquals(deserialize(of(String.valueOf(l))), Short.valueOf((short)l));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
