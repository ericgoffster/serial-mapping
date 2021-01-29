package com.secure.serialization.mapping;

import java.math.BigDecimal;
import java.util.function.Function;

import com.secure.serialization.objects.SerializedFactory;

public abstract class NumberBasedMapping<T> extends Mapping<T> {
	protected NumberBasedMapping(Function<T, BigDecimal> mapFrom, Function<BigDecimal, T> mapTo) {
		super(
				o -> o == null ? SerializedFactory.NULL : SerializedFactory.of(mapFrom.apply(o)),
				o -> o.isNull() ? null : mapTo.apply(o.asBigDecimal()));
	}
}
