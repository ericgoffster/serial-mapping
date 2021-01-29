package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class LocalTimeMappingTest2 {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ISO_TIME;
	private static final LocalTimeMapping DESC = LocalTimeMapping.of(FORMAT);
	
	private static Serialized of(LocalTime d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.format(FORMAT));
	}
	private static Serialized serialize(LocalTime d) {
		return DESC.serialize(d);
	}
	private static LocalTime deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		LocalTime now = LocalTime.now();
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		LocalTime now = LocalTime.now();
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
