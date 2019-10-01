package ca.ulaval.glo4002.booking.parsers;

public interface DtoParser<T, U> {

    T parseDto(U dto);

    U toDto(T object);
}
