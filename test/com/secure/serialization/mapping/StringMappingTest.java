package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class StringMappingTest {

	private static final StringMapping DESC = StringMapping.DESC;
	
	private static Serialized of(String d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d);
	}
	private static Serialized serialize(String d) {
		return DESC.serialize(d);
	}
	private static String deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(""), of(""));
		assertEquals(serialize("abc"), of("abc"));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of("")), "");
		assertEquals(deserialize(of("abc")), "abc");
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
