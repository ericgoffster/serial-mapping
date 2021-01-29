package com.secure.serialization.mapping;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class HashMapMapping<E> extends MapBasedMapping<E, Map<String, E>> {
	public static <U> HashMapMapping<U> of(Mapping<U> elementDesc) {
		return new HashMapMapping<>(Objects.requireNonNull(elementDesc));
	}
	private HashMapMapping(Mapping<E> elementDesc) {
		super(n -> new LinkedHashMap<>(n), elementDesc);
	}
}
