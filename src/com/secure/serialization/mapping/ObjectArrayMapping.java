package com.secure.serialization.mapping;

import java.util.Arrays;
import java.util.Objects;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class ObjectArrayMapping<E> extends Mapping<E[]> {
	public static <E> ObjectArrayMapping<E> of(E[] sample, Mapping<E> elementDesc) {
		return new ObjectArrayMapping<>(Objects.requireNonNull(sample), Objects.requireNonNull(elementDesc));
	}

	private ObjectArrayMapping(E[] sample, Mapping<E> elementDesc) {
		super(so -> serialize(so, elementDesc), o -> deserialize(sample, elementDesc, o));
	}
	
	private static <E> Serialized serialize(E[] obj, Mapping<E> elementDesc) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(E e: obj) {
			arr = arr.add(elementDesc.serialize(e));
		}
		return arr;
	}

	private static <E> E[] deserialize(E[] sample, Mapping<E> elementDesc,
			Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		E[] arr = Arrays.copyOf(sample, obj.size());
		for(int i = 0; i < arr.length; i++) {
			arr[i] = elementDesc.deserialize(obj.get(i));
		}
		return arr;
	}
}
