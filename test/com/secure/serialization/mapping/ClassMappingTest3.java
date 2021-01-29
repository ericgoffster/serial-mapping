package com.secure.serialization.mapping;

import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public class ClassMappingTest3 {
	public static class MySubObject {
		private final int x;
		public int getX() {
			return x;
		}
		public MySubObject setX(int x) {
			return new MySubObject(x, y);
		}
		public MySubObject setY(int y) {
			return new MySubObject(x, y);
		}

		public int getY() {
			return y;
		}
		private final int y;
	    public MySubObject(int x, int y) {
	    	this.x = x;
	    	this.y = y;
	    }
	    
	    @Override
		public int hashCode() {
			return Objects.hash(Integer.valueOf(x), Integer.valueOf(y));
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MySubObject other = (MySubObject) obj;
			return x == other.x && y == other.y;
		}
		@Override
		public String toString() {
			return "MySubObject [x=" + x + ", y=" + y + "]";
		}
	}
	public static final ClassMapping<MySubObject> DESC = ClassMapping.of(
    		m -> new MySubObject(0,0),
    		new FieldDescription<>("x", MySubObject::getX, MySubObject::setX, IntMapping.DESC),
    		new FieldDescription<>("y", MySubObject::getY, MySubObject::setY, IntMapping.DESC));

	
	private static Serialized of(MySubObject d) {
		if (d == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.object()
				.set("x", d.x)
				.set("y", d.y);
	}
	private static Serialized serialize(MySubObject d) {
		return DESC.serialize(d);
	}
	private static MySubObject deserialize(Serialized d) {
		return DESC.deserialize(d);
	}

	@Test
	public void testSerialize() {
		assertEquals(serialize(new MySubObject(5,6)), of(new MySubObject(5,6)));
		assertEquals(serialize(null), of(null));
	}

	@Test
	public void testDeserialize() {
		assertEquals(deserialize(of(new MySubObject(5,6))), new MySubObject(5,6));
		assertEquals(deserialize(of(null)), null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSerializeNull() {
		deserialize(null);
	}
}
