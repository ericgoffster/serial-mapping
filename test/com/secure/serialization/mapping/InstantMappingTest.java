package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class InstantMappingTest {
	private static final InstantMapping DESC = InstantMapping.DESC;
	
	private static Serialized of(Instant d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.toString());
	}
	private static Serialized serialize(Instant d) {
		return DESC.serialize(d);
	}
	private static Instant deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		Instant now = Instant.now();
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		Instant now = Instant.now();
		assertEquals(deserialize(of(now)), now);
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadEnum() {
		deserialize(SerializedFactory.of("c"));
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
