package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class FloatArrayMappingTest {

	private static final FloatArrayMapping DESC = FloatArrayMapping.DESC;
	
	private static Serialized of(float[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(float i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(float[] d) {
		return DESC.serialize(d);
	}
	private static float[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new float[0]), of(new float[0]));
		assertEquals(serialize(new float[] {6,7}), of(new float[] {6,7}));
	}

	@Test
	public void testDeserialize() {
		assertTrue(Arrays.equals(deserialize(of(new float[0])), new float[0]));
		assertTrue(Arrays.equals(deserialize(of(new float[] {6, 7})), new float[] {6, 7}));
		assertTrue(Arrays.equals(deserialize(of(null)), null));
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
