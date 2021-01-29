package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class StringArrayMapping extends Mapping<String[]> {
	public static StringArrayMapping DESC = new StringArrayMapping();
	private StringArrayMapping() {
		super(StringArrayMapping::serializeIt, StringArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(String[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static String[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asStrings();
	}
}
