package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class ZonedDateTimeMappingTest2 {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ISO_DATE_TIME;
	private static final ZonedDateTimeMapping DESC = ZonedDateTimeMapping.of(FORMAT);
	
	private static Serialized of(ZonedDateTime d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.format(FORMAT));
	}
	private static Serialized serialize(ZonedDateTime d) {
		return DESC.serialize(d);
	}
	private static ZonedDateTime deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		ZonedDateTime now = ZonedDateTime.now();
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		ZonedDateTime now = ZonedDateTime.now();
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
