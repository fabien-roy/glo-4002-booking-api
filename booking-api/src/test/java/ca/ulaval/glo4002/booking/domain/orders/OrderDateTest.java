package ca.ulaval.glo4002.booking.domain.orders;

import ca.ulaval.glo4002.booking.exceptions.orders.InvalidOrderDateFormatException;
import ca.ulaval.glo4002.booking.exceptions.orders.OutOfBoundsOrderDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderDateTest {

}