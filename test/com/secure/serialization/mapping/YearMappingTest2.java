package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.Year;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class YearMappingTest2 {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy");
	private static final YearMapping DESC = YearMapping.of(FORMAT);
	
	private static Serialized of(Year d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.format(FORMAT));
	}
	private static Serialized serialize(Year d) {
		return DESC.serialize(d);
	}
	private static Year deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		Year now = Year.now();
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		Year now = Year.now();
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
