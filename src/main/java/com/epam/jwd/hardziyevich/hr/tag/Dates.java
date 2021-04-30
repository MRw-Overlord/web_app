package com.epam.jwd.hardziyevich.hr.tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Dates {

    public static final String DATE_TIME_PATTERN = "dd.MM.yyyy hh:mm";

    private Dates() {
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}