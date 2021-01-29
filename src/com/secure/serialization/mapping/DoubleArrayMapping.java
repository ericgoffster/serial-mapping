package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class DoubleArrayMapping extends Mapping<double[]> {
	public static DoubleArrayMapping DESC = new DoubleArrayMapping();
	private DoubleArrayMapping() {
		super(DoubleArrayMapping::serializeIt, DoubleArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(double[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static double[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asDoubles();
	}
}
