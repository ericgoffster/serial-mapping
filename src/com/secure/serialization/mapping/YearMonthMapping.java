package com.secure.serialization.mapping;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class YearMonthMapping extends StringBasedMapping<YearMonth> {
	public static YearMonthMapping DESC = new YearMonthMapping();
	private YearMonthMapping() {
		super(YearMonth::toString, YearMonth::parse);
	}
	private YearMonthMapping(DateTimeFormatter formatter) {
		super(d -> d.format(formatter), str -> YearMonth.parse(str, formatter));
	}
	public static YearMonthMapping of(DateTimeFormatter formatter) {
		return new YearMonthMapping(Objects.requireNonNull(formatter));
	}
}
