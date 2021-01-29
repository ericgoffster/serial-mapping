package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class FloatMappingTest {
	private static final FloatMapping DESC = FloatMapping.DESC;
	
	private static Serialized of(Float d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(BigDecimal.valueOf(d.floatValue()));
	}
	private static Serialized serialize(Float d) {
		return DESC.serialize(d);
	}
	private static Float deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Float.valueOf(5)), of(Float.valueOf(5)));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(Float.valueOf(5))), Float.valueOf(5.0F));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
