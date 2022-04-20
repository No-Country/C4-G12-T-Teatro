package com.teatro.util.formateador;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Calendar;

public class FormateadorFecha {

	private FormateadorFecha() {
		 throw new IllegalStateException();
	}
	
	public static DateTimeFormatter getFormateador() {
		return  new DateTimeFormatterBuilder().appendPattern("dd-MM[-yyyy][ [HH][:mm][:ss][.SSS]]")
	            .parseDefaulting(ChronoField.YEAR_OF_ERA, Calendar.getInstance().get(Calendar.YEAR))
	            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
	            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
	            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
	            .toFormatter();
	}
}

