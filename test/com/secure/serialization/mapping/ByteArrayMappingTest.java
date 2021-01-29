package com.secure.serialization.mapping;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class ByteArrayMappingTest {

	private static final ByteArrayMapping DESC = ByteArrayMapping.DESC;
	
	private static Serialized of(byte[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(int i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(byte[] d) {
		return DESC.serialize(d);
	}
	private static byte[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new byte[0]), of(new byte[0]));
		assertEquals(serialize(new byte[] {6,7}), of(new byte[] {6,7}));
	}

	@Test
	public void testDeserialize() {
		assertArrayEquals(deserialize(of(new byte[0])), new byte[0]);
		assertArrayEquals(deserialize(of(new byte[] {6, 7})), new byte[] {6, 7});
		assertArrayEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
