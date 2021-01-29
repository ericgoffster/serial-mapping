package com.secure.serialization.mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class LocalDateTimeMapping extends StringBasedMapping<LocalDateTime> {
	public static LocalDateTimeMapping DESC = new LocalDateTimeMapping();
	private LocalDateTimeMapping() {
		super(LocalDateTime::toString, LocalDateTime::parse);
	}
	private LocalDateTimeMapping(DateTimeFormatter formatter) {
		super(d -> d.format(formatter), str -> LocalDateTime.parse(str, formatter));
	}
	public static LocalDateTimeMapping of(DateTimeFormatter formatter) {
		return new LocalDateTimeMapping(Objects.requireNonNull(formatter));
	}
}
