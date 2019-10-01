package ca.ulaval.glo4002.booking.builders;

public interface Builder<T> {

    T buildById(Long id);
    T buildByName(String name);
}
