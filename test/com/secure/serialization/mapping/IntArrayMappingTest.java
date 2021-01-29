package com.secure.serialization.mapping;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class IntArrayMappingTest {

	private static final IntArrayMapping DESC = IntArrayMapping.DESC;
	
	private static Serialized of(int[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(int i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(int[] d) {
		return DESC.serialize(d);
	}
	private static int[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new int[0]), of(new int[0]));
		assertEquals(serialize(new int[] {6,7}), of(new int[] {6,7}));
	}

	@Test
	public void testDeserialize() {
		assertArrayEquals(deserialize(of(new int[0])), new int[0]);
		assertArrayEquals(deserialize(of(new int[] {6, 7})), new int[] {6, 7});
		assertArrayEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
