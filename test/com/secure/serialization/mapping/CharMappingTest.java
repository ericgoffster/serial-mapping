package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class CharMappingTest {


	private static final CharMapping DESC = CharMapping.DESC;
	
	private static Serialized of(Character d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.charValue());
	}
	private static Serialized serialize(Character d) {
		return DESC.serialize(d);
	}
	private static Character deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Character.valueOf(' ')), of(Character.valueOf(' ')));
		assertEquals(serialize(Character.valueOf(Character.MAX_VALUE)), of(Character.valueOf(Character.MAX_VALUE)));
		assertEquals(serialize(Character.valueOf(Character.MIN_VALUE)), of(Character.valueOf(Character.MIN_VALUE)));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(SerializedFactory.of("")), Character.valueOf((char)0));
		assertEquals(deserialize(of(Character.valueOf(' '))), Character.valueOf(' '));
		assertEquals(deserialize(of(Character.valueOf(Character.MAX_VALUE))), Character.valueOf(Character.MAX_VALUE));
		assertEquals(deserialize(of(Character.valueOf(Character.MIN_VALUE))), Character.valueOf(Character.MIN_VALUE));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}


}
