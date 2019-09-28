package ca.ulaval.glo4002.booking.constants;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FestivalConstants {

    public static class Dates {
        private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FestivalConstants.Dates.FORMAT);
        public static final LocalDate START_DATE = LocalDate.parse("2050-07-17", dateTimeFormatter);
        public static final LocalDate END_DATE = LocalDate.parse("2050-07-24", dateTimeFormatter);
        public static final Integer TOTAL_DURATION_IN_DAYS = 8;
        public static final String FORMAT = "yyyy-MM-dd";

        private Dates() {
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_EXCEPTION_MESSAGE);
        }
    }

    private FestivalConstants() {
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
}
