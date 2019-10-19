package ca.ulaval.glo4002.booking.domain;

public class StringId implements Id<String> {

    private String value;

    public StringId(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
