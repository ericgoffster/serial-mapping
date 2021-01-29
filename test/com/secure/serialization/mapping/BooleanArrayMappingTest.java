package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class BooleanArrayMappingTest {

	private static final BooleanArrayMapping DESC = BooleanArrayMapping.DESC;
	
	private static Serialized of(boolean[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(boolean i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(boolean[] d) {
		return DESC.serialize(d);
	}
	private static boolean[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new boolean[0]), of(new boolean[0]));
		assertEquals(serialize(new boolean[] {true,false}), of(new boolean[] {true,false}));
	}

	@Test
	public void testDeserialize() {
		assertTrue(Arrays.equals(deserialize(of(new boolean[0])), new boolean[0]));
		Serialized of = of(new boolean[] {true,false});
		assertTrue(Arrays.equals(deserialize(of), new boolean[] {true,false}));
		assertTrue(Arrays.equals(deserialize(of(null)), null));
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
