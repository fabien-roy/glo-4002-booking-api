package ca.ulaval.glo4002.booking.parsers;

public interface EntityParser<T, U> {

    T parseEntity(U entity);

    U toEntity(T object);
}
