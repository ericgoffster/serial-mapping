package com.secure.serialization.mapping;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class MonthDayMapping extends StringBasedMapping<MonthDay> {
	public static MonthDayMapping DESC = new MonthDayMapping();
	private MonthDayMapping() {
		super(MonthDay::toString, MonthDay::parse);
	}
	private MonthDayMapping(DateTimeFormatter formatter) {
		super(d -> d.format(formatter), str -> MonthDay.parse(str, formatter));
	}
	public static MonthDayMapping of(DateTimeFormatter formatter) {
		return new MonthDayMapping(Objects.requireNonNull(formatter));
	}
}
