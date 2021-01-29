package com.secure.serialization.mapping;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class LongArrayMappingTest {

	private static final LongArrayMapping DESC = LongArrayMapping.DESC;
	
	private static Serialized of(long[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(long i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(long[] d) {
		return DESC.serialize(d);
	}
	private static long[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new long[0]), of(new long[0]));
		assertEquals(serialize(new long[] {6,7}), of(new long[] {6,7}));
	}

	@Test
	public void testDeserialize() {
		assertArrayEquals(deserialize(of(new long[0])), new long[0]);
		assertArrayEquals(deserialize(of(new long[] {6, 7})), new long[] {6, 7});
		assertArrayEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
