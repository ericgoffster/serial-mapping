package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.time.Period;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class PeriodMappingTest {
	private static final PeriodMapping DESC = PeriodMapping.DESC;
	
	private static Serialized of(Period d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.toString());
	}
	private static Serialized serialize(Period d) {
		return DESC.serialize(d);
	}
	private static Period deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		Period now = Period.of(3, 2, 1);
		assertEquals(serialize(now), of(now));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		Period now = Period.of(3, 2, 1);
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
