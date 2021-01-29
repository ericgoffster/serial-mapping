package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class DurationMappingTest {
	private static final DurationMapping DESC = DurationMapping.DESC;
	
	private static Serialized of(Duration d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.toString());
	}
	private static Serialized serialize(Duration d) {
		return DESC.serialize(d);
	}
	private static Duration deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		Duration now = Duration.of(3, ChronoUnit.DAYS);
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		Duration now = Duration.of(3, ChronoUnit.DAYS);
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
