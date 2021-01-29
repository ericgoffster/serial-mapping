package com.secure.serialization.mapping;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class ZonedDateTimeMapping extends StringBasedMapping<ZonedDateTime> {
	public static ZonedDateTimeMapping DESC = new ZonedDateTimeMapping();
	private ZonedDateTimeMapping() {
		super(ZonedDateTime::toString, ZonedDateTime::parse);
	}
	private ZonedDateTimeMapping(DateTimeFormatter formatter) {
		super(d -> d.format(formatter), str -> ZonedDateTime.parse(str, formatter));
	}
	public static ZonedDateTimeMapping of(DateTimeFormatter formatter) {
		return new ZonedDateTimeMapping(Objects.requireNonNull(formatter));
	}
}
