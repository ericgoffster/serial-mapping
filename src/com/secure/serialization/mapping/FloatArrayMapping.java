package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class FloatArrayMapping extends Mapping<float[]> {
	public static FloatArrayMapping DESC = new FloatArrayMapping();
	private FloatArrayMapping() {
		super(FloatArrayMapping::serializeIt, FloatArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(float[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static float[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asFloats(); 
	}
}
