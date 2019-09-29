package ca.ulaval.glo4002.booking.parsers;

public interface Parser<T, U, V> {
    T parseDto(U dto);
    T parseEntity(V entity);
    U toDto(T object);
    V toEntity(T object);
}
