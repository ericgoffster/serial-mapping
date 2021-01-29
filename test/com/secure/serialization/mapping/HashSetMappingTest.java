package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class HashSetMappingTest {
	private static final HashSetMapping<Integer> DESC = HashSetMapping.of(IntMapping.DESC);
	
	private static Serialized of(List<Integer> d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(Integer i: d) {
			arr = arr.add(i == null ? SerializedFactory.NULL : SerializedFactory.of(i.intValue()));
		}
		return arr;
	}
	private static Serialized serialize(List<Integer> d) {
		if (d == null) {
			return DESC.serialize(null);
		}
		return DESC.serialize(new HashSet<Integer>(d));
	}
	private static Set<Integer> deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Collections.emptyList()), of(Collections.emptyList()));
		assertEquals(serialize(Arrays.asList(Integer.valueOf(6), Integer.valueOf(7))), of(Arrays.asList(Integer.valueOf(6), Integer.valueOf(7))));
		assertEquals(serialize(Arrays.asList(null, Integer.valueOf(7))), of(Arrays.asList(null, Integer.valueOf(7))));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(Arrays.asList(Integer.valueOf(6), Integer.valueOf(7)))), new HashSet<Integer>(Arrays.asList(Integer.valueOf(7), Integer.valueOf(6))));
		assertEquals(deserialize(of(Arrays.asList(null, Integer.valueOf(7)))), new HashSet<Integer>(Arrays.asList(null, Integer.valueOf(7))));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
