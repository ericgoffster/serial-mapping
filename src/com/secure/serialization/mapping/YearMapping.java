package com.secure.serialization.mapping;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class YearMapping extends StringBasedMapping<Year> {
	public static YearMapping DESC = new YearMapping();
	private YearMapping() {
		super(Year::toString, Year::parse);
	}
	private YearMapping(DateTimeFormatter formatter) {
		super(d -> d.format(formatter), str -> Year.parse(str, formatter));
	}
	public static YearMapping of(DateTimeFormatter formatter) {
		return new YearMapping(Objects.requireNonNull(formatter));
	}
}
