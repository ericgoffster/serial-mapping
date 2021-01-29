package com.secure.serialization.mapping;

import com.secure.serialization.objects.SerializedFactory;

public final class CharMapping extends Mapping<Character> {
	public static final CharMapping DESC = new CharMapping();

	private CharMapping() {	
		super(c -> c == null ? SerializedFactory.NULL : SerializedFactory.of(c.charValue()),
				c -> c.isNull() ? null : Character.valueOf(c.asChar()));
	}
}
