package com.secure.serialization.mapping;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public abstract class MapBasedMapping<E, T extends Map<String, E>> extends Mapping<T> {
	protected MapBasedMapping(Function<Integer, T>constructor, Mapping<E> elementDesc) {
		super(o -> serialize(o, elementDesc), o -> deserialize(constructor, elementDesc, o));
	}
	
	private static <E, T extends Map<String, E>> Serialized serialize(T obj, Mapping<E> elementDesc) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		Serialized so = SerializedFactory.object();
		for(Entry<String, E> e: obj.entrySet()) {
			so = so.set(e.getKey(), elementDesc.serialize(e.getValue()));
		}
		return so;
	}

	private static <E, T extends Map<String, E>> T deserialize(Function<Integer, T> constructor,
			Mapping<E> elementDesc, Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		T c = constructor.apply(Integer.valueOf(obj.size()));
		for(int i = 0; i < obj.size(); i++) {
			String name = obj.getFieldName(i);
			if (obj.get(name).isPresent()) {
			    c.put(name, elementDesc.deserialize(obj.get(name).get()));
			}
		}
		return c;
	}
}
