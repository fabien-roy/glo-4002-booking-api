package ca.ulaval.glo4002.booking.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FestivalConstants {

    public static class Dates {
        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FestivalConstants.Dates.FORMAT);
        public static final LocalDate START_DATE = LocalDate.parse("2050-07-17", dateTimeFormatter);
        public static final LocalDate END_DATE = LocalDate.parse("2050-07-24", dateTimeFormatter);
        public static final String FORMAT = "yyyy-MM-dd";
        public static final LocalDateTime FESTIVAL_START_ORDER = LocalDateTime.of(2050, 1, 1, 0, 0, 0);
        public static final LocalDateTime FESTIVAL_END_ORDER = LocalDateTime.of(2050, 7, 16, 23, 59, 59);


        private Dates() {
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_EXCEPTION_MESSAGE);
        }
    }

    private FestivalConstants() {
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
}
