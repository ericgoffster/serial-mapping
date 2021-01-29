package com.secure.serialization.mapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class ClassMapping<T> extends Mapping<T> {
	@SafeVarargs
	public static <T> ClassMapping<T> of(Function<Serialized, T> constructor, FieldDescription<T, ?> ... fieldDescriptions) {
		return new ClassMapping<T>(Objects.requireNonNull(constructor), toMap(fieldDescriptions));
	}
	
	@SafeVarargs
	public static <T> ClassMapping<T> of(Supplier<T> constructor, FieldDescription<T, ?> ... fieldDescriptions) {
		Objects.requireNonNull(constructor);
		return new ClassMapping<T>(m -> constructor.get(), toMap(fieldDescriptions));
	}

	private ClassMapping(Function<Serialized, T> constructor, Map<String, FieldDescription<T, ?>> fieldDescriptions) {
		super(o -> serialize(o, fieldDescriptions), o -> deserialize(fieldDescriptions, constructor, o));
	}

	@SafeVarargs
	private static <T> Map<String, FieldDescription<T, ?>> toMap(FieldDescription<T, ?> ... fieldDescriptions) {
		Map<String, FieldDescription<T, ?>> m = new HashMap<>();
		for(FieldDescription<T, ?> fieldDescription: fieldDescriptions) {
			m.put(fieldDescription.getFieldName(), fieldDescription);
		}
		return m;
	}
	
	private static <T> Serialized serialize(T obj, Map<String, FieldDescription<T, ?>> fieldDescriptions) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		Serialized so = SerializedFactory.object();
		for(FieldDescription<T, ?> fieldDescription: fieldDescriptions.values()) {
			so = so.set(fieldDescription.getFieldName(), fieldDescription.get(obj));
		}
		return so;
	}

	private static <T> T deserialize(Map<String, FieldDescription<T, ?>> fieldDescriptions,
			Function<Serialized, T> constructor, Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		T o = constructor.apply(obj);
		for(int i = 0; i < obj.size(); i++) {
			String name = obj.getFieldName(i);
			FieldDescription<T, ?> fieldDescription = fieldDescriptions.get(name);
			if (fieldDescription != null && fieldDescription.haveSet()) {
				if (obj.get(name).isPresent()) {
			        o = fieldDescription.set(o, obj.get(name).get());
				}
			}
		}
		return o;
	}
}
