package com.secure.serialization.mapping;

import com.secure.serialization.objects.Serialized;
import com.secure.serialization.objects.SerializedFactory;

public final class ByteArrayMapping extends Mapping<byte[]> {
	public static ByteArrayMapping DESC = new ByteArrayMapping();
	private ByteArrayMapping() {
		super(ByteArrayMapping::serializeIt, ByteArrayMapping::deserializeIt);
	}
	
	private static Serialized serializeIt(byte[] obj) {
		if (obj == null) {
			return SerializedFactory.NULL;
		}
		return SerializedFactory.array(obj);
	}

	private static byte[] deserializeIt(Serialized obj) {
		if (obj.isNull()) {
			return null;
		}
		return obj.asBytes();
	}
}
