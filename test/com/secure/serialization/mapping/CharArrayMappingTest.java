package com.secure.serialization.mapping;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class CharArrayMappingTest {

	private static final CharArrayMapping DESC = CharArrayMapping.DESC;
	
	private static Serialized of(char[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(d);
	}
	private static Serialized serialize(char[] d) {
		return DESC.serialize(d);
	}
	private static char[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new char[0]), of(new char[0]));
		assertEquals(serialize(new char[] {6, 7}), of(new char[] {6, 7}));
	}

	@Test
	public void testDeserialize() {
		assertArrayEquals(deserialize(of(new char[0])), new char[0]);
		assertArrayEquals(deserialize(of(new char[] {6, 7})), new char[] {6, 7});
		assertArrayEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
