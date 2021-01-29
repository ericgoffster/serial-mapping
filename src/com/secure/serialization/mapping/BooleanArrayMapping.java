package com.secure.serialization.mapping;

import org.granitesoft.serialization.objects.Serialized;
import org.granitesoft.serialization.objects.SerializedFactory;

public final class BooleanArrayMapping extends Mapping<boolean[]> {
	public static BooleanArrayMapping DESC = new BooleanArrayMapping();
	private BooleanArrayMapping() {
		super(BooleanArrayMapping::serializeIt, BooleanArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(boolean[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static boolean[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asBooleans();
	}
}
