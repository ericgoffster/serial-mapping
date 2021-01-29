package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class DoubleArrayMappingTest {

	private static final DoubleArrayMapping DESC = DoubleArrayMapping.DESC;
	
	private static Serialized of(double[] d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(double i: d) {
			arr = arr.add(i);
		}
		return arr;
	}
	private static Serialized serialize(double[] d) {
		return DESC.serialize(d);
	}
	private static double[] deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(null), of(null));
		assertEquals(serialize(new double[0]), of(new double[0]));
		assertEquals(serialize(new double[] {6,7}), of(new double[] {6,7}));
	}

	@Test
	public void testDeserialize() {
		assertTrue(Arrays.equals(deserialize(of(new double[0])), new double[0]));
		assertTrue(Arrays.equals(deserialize(of(new double[] {6, 7})), new double[] {6, 7}));
		assertTrue(Arrays.equals(deserialize(of(null)), null));
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}

}
