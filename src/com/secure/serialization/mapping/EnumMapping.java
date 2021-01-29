package com.secure.serialization.mapping;

import java.util.Objects;
import java.util.function.Function;

public final class EnumMapping<E extends Enum<E>> extends StringBasedMapping<E> {
	private EnumMapping(Function<E, String> toString, Function<String, E> fromString) {
		super(toString, fromString);
	}
	public static <E extends Enum<E>> EnumMapping<E> of(Class<E> cl) {
		Objects.requireNonNull(cl);
		return new EnumMapping<>(Enum::name, str -> Enum.valueOf(cl, str));
	}
	public static <E extends Enum<E>> EnumMapping<E> of(Function<E, String> toString, Function<String, E> fromString) {
		return new EnumMapping<>(Objects.requireNonNull(toString), Objects.requireNonNull(fromString));
	}
}
