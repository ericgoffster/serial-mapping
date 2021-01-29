package com.secure.serialization.mapping;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.secure.serialization.objects.Serialized;

public final class FieldDescription<T, E> {
	private final String fieldName;
	private final Function<T, E> get;
	private final BiFunction<T, E, T> set;
	private final Mapping<E> typeDescription;
	public FieldDescription(String name, Function<T, E> get, BiFunction<T, E, T> set, Mapping<E> desc) {
		this.fieldName = Objects.requireNonNull(name);
		this.get = Objects.requireNonNull(get);
		this.set = Objects.requireNonNull(set);
		this.typeDescription = Objects.requireNonNull(desc);
	}
	public FieldDescription(String name, Function<T, E> get, Mapping<E> desc) {
		this.fieldName = Objects.requireNonNull(name);
		this.get = Objects.requireNonNull(get);
		this.set = null;
		this.typeDescription = Objects.requireNonNull(desc);
	}
	public FieldDescription(String name, Function<T, E> get, BiConsumer<T, E> set, Mapping<E> desc) {
		Objects.requireNonNull(set);
		this.fieldName = name;
		this.get = get;
		this.set = (t, u) -> { set.accept(t, u); return t;};
		this.typeDescription = desc;
	}
	
	public Serialized get(T obj) {
		return typeDescription.serialize(get.apply(obj));
	}
	
	public T set(T obj, Serialized v) {
		Objects.requireNonNull(set);
		return set.apply(obj, typeDescription.deserialize(v));
	}
	public String getFieldName() {
		return fieldName;
	}
	public boolean haveSet() {
		return set != null;
	}
}