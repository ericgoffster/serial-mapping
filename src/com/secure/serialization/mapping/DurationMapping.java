package com.secure.serialization.mapping;

import java.time.Duration;

public final class DurationMapping extends StringBasedMapping<Duration> {
	public static DurationMapping DESC = new DurationMapping();
	private DurationMapping() {
		super(Duration::toString, Duration::parse);
	}
}
