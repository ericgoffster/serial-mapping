package com.secure.serialization.mapping;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class LocalTimeMapping extends StringBasedMapping<LocalTime> {
	public static LocalTimeMapping DESC = new LocalTimeMapping();
	private LocalTimeMapping() {
		super(LocalTime::toString, LocalTime::parse);
	}
	private LocalTimeMapping(DateTimeFormatter formatter) {
		super(d -> d.format(formatter), str -> LocalTime.parse(str, formatter));
	}
	public static LocalTimeMapping of(DateTimeFormatter formatter) {
		return new LocalTimeMapping(Objects.requireNonNull(formatter));
	}
}
