package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class ArrayListMappingTest {

	private static final ArrayListMapping<Integer> DESC = ArrayListMapping.of(IntMapping.DESC);
	
	private static Serialized of(List<Integer> d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized so = SerializedFactory.array();
		for(Integer i: d) {
			so = so.add(i == null ? SerializedFactory.NULL : SerializedFactory.of(i.intValue()));
		}
		return so;
	}
	private static Serialized serialize(List<Integer> d) {
		return DESC.serialize(d);
	}
	private static List<Integer> deserialize(Serialized d) {
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
		assertEquals(deserialize(of(Collections.emptyList())), Collections.emptyList());
		assertEquals(deserialize(of(Arrays.asList(Integer.valueOf(6), Integer.valueOf(7)))), Arrays.asList(Integer.valueOf(6), Integer.valueOf(7)));
		assertEquals(deserialize(of(Arrays.asList(null, Integer.valueOf(7)))), Arrays.asList(null, Integer.valueOf(7)));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
