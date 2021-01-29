package com.secure.serialization.mapping;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public final class HashSetMapping<E> extends CollectionBasedMapping<E, Set<E>> {
	public static <U> HashSetMapping<U> of(Mapping<U> elementDesc) {
		return new HashSetMapping<>(Objects.requireNonNull(elementDesc));
	}
	private HashSetMapping(Mapping<E> elementDesc) {
		super(n -> new LinkedHashSet<>(n), elementDesc);
	}
}
