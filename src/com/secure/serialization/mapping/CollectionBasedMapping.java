package com.secure.serialization.mapping;

import java.util.Collection;
import java.util.function.Function;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public abstract class CollectionBasedMapping<E, T extends Collection<E>> extends Mapping<T> {
	protected CollectionBasedMapping(Function<Integer,T> constructor, Mapping<E> elementDesc) {
		super(o -> serialize(o, elementDesc), o -> deserialize(constructor, elementDesc, o));
	}
	
	private static <E, T extends Collection<E>> Serialized serialize(T obj, Mapping<E> elementDesc) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(E o: obj) {
			arr = arr.add(elementDesc.serialize(o));
		}
		return arr;
	}

	private static <E, T extends Collection<E>> T deserialize(Function<Integer,T> constructor,
			Mapping<E> elementDesc, Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		int numElements = obj.size();
		T c = constructor.apply(Integer.valueOf(numElements));
		for(int i = 0; i < numElements; i++) {
			c.add(elementDesc.deserialize(obj.get(i)));
		}
		return c;
	}
}
