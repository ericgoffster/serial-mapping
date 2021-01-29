package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class CharArrayMapping extends Mapping<char[]> {
	public static CharArrayMapping DESC = new CharArrayMapping();
	private CharArrayMapping() {
		super(CharArrayMapping::serializeIt, CharArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(char[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static char[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asChars();
	}
}
