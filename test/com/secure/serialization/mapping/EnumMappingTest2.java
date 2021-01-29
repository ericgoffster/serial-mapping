package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class EnumMappingTest2 {
	
	public enum Foo {
		a,b;
	}


	private static final EnumMapping<Foo> DESC = EnumMapping.of(e -> e.name().toUpperCase(), str -> Foo.valueOf(str.toLowerCase()));
	
	private static Serialized of(Foo d) {
		return d == null ? SerializedFactory.NULL : SerializedFactory.of(d.name().toUpperCase());
	}
	private static Serialized serialize(Foo d) {
		return DESC.serialize(d);
	}
	private static Foo deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(Foo.a), of(Foo.a));
		assertEquals(serialize(Foo.b), of(Foo.b));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(Foo.a)), Foo.a);
		assertEquals(deserialize(of(Foo.b)), Foo.b);
		assertEquals(deserialize(SerializedFactory.of("a")), Foo.a);
		assertEquals(deserialize(SerializedFactory.of("A")), Foo.a);
		assertEquals(deserialize(SerializedFactory.of("b")), Foo.b);
		assertEquals(deserialize(SerializedFactory.of("B")), Foo.b);
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
