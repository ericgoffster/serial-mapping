package com.secure.serialization.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ArrayListMapping<E> extends CollectionBasedMapping<E, List<E>> {
	public static <E> ArrayListMapping<E> of(Mapping<E> elementDesc) {
		return new ArrayListMapping<>(Objects.requireNonNull(elementDesc));
	}

	private ArrayListMapping(Mapping<E> elementDesc) {
		super(n -> new ArrayList<>(n), elementDesc);
	}
}
