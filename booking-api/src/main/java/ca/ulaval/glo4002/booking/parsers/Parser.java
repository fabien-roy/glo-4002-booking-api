package ca.ulaval.glo4002.booking.parsers;

public interface Parser<T, U> {
    T parse(U dto);
}
