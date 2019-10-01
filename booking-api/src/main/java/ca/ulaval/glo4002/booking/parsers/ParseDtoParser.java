package ca.ulaval.glo4002.booking.parsers;

public interface ParseDtoParser<T, U> {

    T parseDto(U dto);
}
