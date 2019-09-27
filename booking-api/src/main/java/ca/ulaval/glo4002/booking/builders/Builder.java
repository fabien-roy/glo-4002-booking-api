package ca.ulaval.glo4002.booking.builders;

public interface Builder<T> {

    public T buildById(Long id);
    public T buildByName(String name);
}
