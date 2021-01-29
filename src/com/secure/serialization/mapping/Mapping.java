package com.secure.serialization.mapping;

import java.util.Objects;
import java.util.function.Function;

import com.secure.serialization.objects.Serialized;

public abstract class Mapping<T> {
	private final Function<T, Serialized> serialize;
	private final Function<Serialized, T> deserialize;
	public Mapping(Function<T, Serialized> serialize, Function<Serialized, T> deserialize) {
		this.serialize = Objects.requireNonNull(serialize);
		this.deserialize = Objects.requireNonNull(deserialize);
	}
	public final Serialized serialize(T o) {
		return serialize.apply(o);
	}
	
	public final T deserialize(Serialized o) {
		return deserialize.apply(o);
	}
}
