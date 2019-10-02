package ca.ulaval.glo4002.booking.exceptions;

import ca.ulaval.glo4002.booking.dto.ErrorDto;

public abstract class FestivalException extends RuntimeException {

    private ErrorDto errorDto;
    protected String error;
    protected String description;

    public FestivalException(){
        super();
    }

    public FestivalException(String message) {
        super(message);
    }

    public FestivalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FestivalException(Throwable cause) {
        super(cause);
    }

    public FestivalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FestivalException(String message, String error, String description) {
        super(message);

        this.error = error;
        this.description = description;
    }

    public ErrorDto toErrorDto() {
        errorDto = new ErrorDto();
        errorDto.error = error;
        errorDto.description = description;

        return errorDto;
    }
}
