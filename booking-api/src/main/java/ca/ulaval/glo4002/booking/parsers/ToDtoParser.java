package ca.ulaval.glo4002.booking.parsers;

public interface ToDtoParser<T, U> {

    U toDto(T object);
}
