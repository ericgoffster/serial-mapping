package com.secure.serialization.mapping;

import java.util.function.Function;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public abstract class StringBasedMapping<T> extends Mapping<T> {
	protected StringBasedMapping(Function<T, String> toString, Function<String, T> fromString) {
		super(o -> serialize(o, toString), o -> deserialize(o, fromString));
	}
	
	private static <T> Serialized serialize(T e, Function<T, String> toString) {
		return e == null ? SerializedFactory.NULL : SerializedFactory.of(toString.apply(e));
	}

	private static <T> T deserialize(Serialized e, Function<String, T> fromString) {
		String stringValue = e.asString();
		if (stringValue == null) {
			return null;
		}
		try {
			return fromString.apply(stringValue);
		} catch (RuntimeException e1) {
			throw new IllegalArgumentException(e1);
		}
	}
}
