package com.secure.serialization.mapping;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class ShortArrayMappingTest {

	private static final ShortArrayMapping DESC = ShortArrayMapping.DESC;
	
	private static Serialized of(short[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(int i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(short[] d) {
		return DESC.serialize(d);
	}
	private static short[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new short[0]), of(new short[0]));
		assertEquals(serialize(new short[] {6,7}), of(new short[] {6,7}));
	}

	@Test
	public void testDeserialize() {
		assertArrayEquals(deserialize(of(new short[0])), new short[0]);
		assertArrayEquals(deserialize(of(new short[] {6, 7})), new short[] {6, 7});
		assertArrayEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
