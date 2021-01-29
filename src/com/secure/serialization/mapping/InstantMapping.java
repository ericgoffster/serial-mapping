package com.secure.serialization.mapping;

import java.time.Instant;

public final class InstantMapping extends StringBasedMapping<Instant> {
	public static InstantMapping DESC = new InstantMapping();
	private InstantMapping() {
		super(Instant::toString, Instant::parse);
	}
}
