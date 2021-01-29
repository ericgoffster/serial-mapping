package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class HashMapMappingTest {
	private static final HashMapMapping<Integer> DESC = HashMapMapping.of(IntMapping.DESC);
	
	private static Serialized of(Map<String, Integer> d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized so = SerializedFactory.object();
		for(Entry<String, Integer> e: d.entrySet()) {
			Integer i = e.getValue();
			so = so.set(e.getKey(), i == null ? SerializedFactory.NULL : SerializedFactory.of(i.intValue()));
		}
		return so;
	}
	private static Serialized serialize(Map<String, Integer> d) {
		if (d == null) {
			return DESC.serialize(null);
		}
		return DESC.serialize(new HashMap<String, Integer>(d));
	}
	private static Map<String, Integer> deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Collections.emptyMap()), of(Collections.emptyMap()));
		{
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("x", Integer.valueOf(5));
			m.put("y", Integer.valueOf(6));
			assertEquals(serialize(m), of(m));
		}
		{
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("x", null);
			m.put("y", Integer.valueOf(6));
			assertEquals(serialize(m), of(m));
		}
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(Collections.emptyMap())), Collections.emptyMap());
		assertEquals(deserialize(of(null)), null);
		{
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("x", Integer.valueOf(5));
			m.put("y", Integer.valueOf(6));
			assertEquals(deserialize(of(m)), m);
		}
		{
			Map<String, Integer> m = new HashMap<String, Integer>();
			m.put("x", null);
			m.put("y", Integer.valueOf(6));
			assertEquals(deserialize(of(m)), m);
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
