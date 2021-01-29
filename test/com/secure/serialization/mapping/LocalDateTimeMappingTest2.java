package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class LocalDateTimeMappingTest2 {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ISO_DATE_TIME;
	private static final LocalDateTimeMapping DESC = LocalDateTimeMapping.of(FORMAT);
	
	private static Serialized of(LocalDateTime d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.format(FORMAT));
	}
	private static Serialized serialize(LocalDateTime d) {
		return DESC.serialize(d);
	}
	private static LocalDateTime deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		LocalDateTime now = LocalDateTime.now();
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		LocalDateTime now = LocalDateTime.now();
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
