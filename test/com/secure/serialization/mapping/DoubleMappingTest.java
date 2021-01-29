package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class DoubleMappingTest {
	private static final DoubleMapping DESC = DoubleMapping.DESC;
	
	private static Serialized of(Double d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(BigDecimal.valueOf(d.doubleValue()));
	}
	private static Serialized serialize(Double d) {
		return DESC.serialize(d);
	}
	private static Double deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Double.valueOf(5)), of(Double.valueOf(5)));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(Double.valueOf(5))), Double.valueOf(5.0));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
