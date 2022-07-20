package com.jdc.query.dto;

import java.time.LocalDate;

public record CustomerDates(
		int id,
		String name,
		LocalDate startUse,
		LocalDate lastUse
		) {

}
