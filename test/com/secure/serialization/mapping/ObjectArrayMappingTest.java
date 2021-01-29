package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class ObjectArrayMappingTest {

	private static final ObjectArrayMapping<String> DESC = ObjectArrayMapping.of(new String[0], StringMapping.DESC);
	
	private static Serialized of(String[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(String i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(String[] d) {
		return DESC.serialize(d);
	}
	private static String[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new String[0]), of(new String[0]));
		assertEquals(serialize(new String[] {"6","7"}), of(new String[] {"6","7"}));
	}

	@Test
	public void testDeserialize() {
		assertTrue(Arrays.equals(deserialize(of(new String[0])), new String[0]));
		assertTrue(Arrays.equals(deserialize(of(new String[] {"6","7"})), new String[] {"6","7"}));
		assertTrue(Arrays.equals(deserialize(of(null)), null));
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
