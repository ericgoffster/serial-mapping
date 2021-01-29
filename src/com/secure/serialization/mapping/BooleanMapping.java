package com.secure.serialization.mapping;

import com.secure.serialization.objects.SerializedFactory;

public final class BooleanMapping extends Mapping<Boolean> {
	public static final BooleanMapping DESC = new BooleanMapping();
	
	private BooleanMapping() {
		super(
				b -> b == null ? SerializedFactory.NULL : SerializedFactory.of(b.booleanValue()),
				b -> b.isNull() ? null : Boolean.valueOf(b.asBoolean()));
	}
}
