package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class MonthDayMappingTest2 {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd");
	private static final MonthDayMapping DESC = MonthDayMapping.of(FORMAT);
	
	private static Serialized of(MonthDay d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.format(FORMAT));
	}
	private static Serialized serialize(MonthDay d) {
		return DESC.serialize(d);
	}
	private static MonthDay deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		MonthDay now = MonthDay.now();
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		MonthDay now = MonthDay.now();
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
