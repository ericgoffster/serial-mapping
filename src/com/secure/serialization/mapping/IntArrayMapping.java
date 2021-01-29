package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class IntArrayMapping extends Mapping<int[]> {
	public static IntArrayMapping DESC = new IntArrayMapping();
	private IntArrayMapping() {
		super(IntArrayMapping::serializeIt, IntArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(int[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		Serialized arr = SerializedFactory.array();
		for(int e: obj) {
			arr = arr.add(SerializedFactory.of(e));
		}
		return arr;
	}

	private static int[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asInts();
	}
}
