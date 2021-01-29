package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class LongArrayMapping extends Mapping<long[]> {
	public static LongArrayMapping DESC = new LongArrayMapping();
	private LongArrayMapping() {
		super(LongArrayMapping::serializeIt, LongArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(long[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static long[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asLongs();
	}
}
