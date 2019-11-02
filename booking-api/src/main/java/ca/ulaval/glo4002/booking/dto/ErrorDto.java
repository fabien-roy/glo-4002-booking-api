package ca.ulaval.glo4002.booking.dto;

public class ErrorDto {

    private String error;
    private String description;

    public ErrorDto(String error, String description) {
        this.error = error;
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }
}
