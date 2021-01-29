package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class ShortArrayMapping extends Mapping<short[]> {
	public static ShortArrayMapping DESC = new ShortArrayMapping();
	private ShortArrayMapping() {
		super(ShortArrayMapping::serializeIt, ShortArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(short[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static short[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asShorts();
	}
}
