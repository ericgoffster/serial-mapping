package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class BooleanMappingTest {

	private static final BooleanMapping DESC = BooleanMapping.DESC;
	
	private static Serialized of(Boolean d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.booleanValue());
	}
	private static Serialized serialize(Boolean d) {
		return DESC.serialize(d);
	}
	private static Boolean deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Boolean.TRUE), of(Boolean.TRUE));
		assertEquals(serialize(Boolean.FALSE), of(Boolean.FALSE));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(Boolean.TRUE)), Boolean.TRUE);
		assertEquals(deserialize(of(Boolean.FALSE)), Boolean.FALSE);
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
