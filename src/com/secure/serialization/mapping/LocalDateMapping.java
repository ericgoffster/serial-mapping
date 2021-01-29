package com.secure.serialization.mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class LocalDateMapping extends StringBasedMapping<LocalDate> {
	public static LocalDateMapping DESC = new LocalDateMapping();
	private LocalDateMapping() {
		super(LocalDate::toString, LocalDate::parse);
	}
	private LocalDateMapping(DateTimeFormatter formatter) {
		super(d -> d.format(formatter), str -> LocalDate.parse(str, formatter));
	}
	public static LocalDateMapping of(DateTimeFormatter formatter) {
		return new LocalDateMapping(Objects.requireNonNull(formatter));
	}
}
