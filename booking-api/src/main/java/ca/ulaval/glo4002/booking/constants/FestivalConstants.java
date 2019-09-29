package ca.ulaval.glo4002.booking.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FestivalConstants {

    public static class Dates {
        public static final LocalDate START_DATE = LocalDate.of(2050,7,17);
        public static final LocalDate END_DATE = LocalDate.of(2050,7,24);
        public static final LocalDateTime ORDER_START_DATE_TIME = LocalDateTime.of(2050, 1, 1, 0, 0, 0);
        public static final LocalDateTime ORDER_END_DATE_TIME = LocalDateTime.of(2050, 7, 16, 23, 59, 59);
        public static final String DATE_FORMAT = "yyyy-MM-dd";
        public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm";

        private Dates() {
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
        }
    }

    private FestivalConstants() {
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
    }
}
