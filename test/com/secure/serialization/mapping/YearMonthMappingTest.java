package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.YearMonth;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class YearMonthMappingTest {
	private static final YearMonthMapping DESC = YearMonthMapping.DESC;
	
	private static Serialized of(YearMonth d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.toString());
	}
	private static Serialized serialize(YearMonth d) {
		return DESC.serialize(d);
	}
	private static YearMonth deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		YearMonth now = YearMonth.now();
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		YearMonth now = YearMonth.now();
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
