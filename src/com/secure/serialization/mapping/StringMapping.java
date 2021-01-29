package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class StringMapping extends Mapping<String> {
	public static final StringMapping DESC = new StringMapping();

	private StringMapping() {	
		super(SerializedFactory::of, Serialized::asString);
	}
}
