package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class LongMappingTest {
	private static final LongMapping DESC = LongMapping.DESC;
	
	private static Serialized of(Long d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.longValue());
	}
	private static Serialized serialize(Long d) {
		return DESC.serialize(d);
	}
	private static Long deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Long.valueOf(5)), of(Long.valueOf(5)));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(Long.valueOf(5))), Long.valueOf(5));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
