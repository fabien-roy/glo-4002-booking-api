package ca.ulaval.glo4002.booking.parsers;

// TODO : Got to make sure T implements ParsableEntity and U implements Dto
public interface Parser<T, U> {
    public T parse(U dto);
}
