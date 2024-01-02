package com.ohdogcat.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
	
	public static String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd. HH:mm"); // yyyy. MM. dd. HH:mm
        return dateTime.format(formatter);
    }
	
}
