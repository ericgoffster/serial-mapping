package com.secure.serialization.mapping;

import java.time.Period;

public final class PeriodMapping extends StringBasedMapping<Period> {
	public static PeriodMapping DESC = new PeriodMapping();
	private PeriodMapping() {
		super(Period::toString, Period::parse);
	}
}
