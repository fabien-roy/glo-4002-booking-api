package ca.ulaval.glo4002.booking.program.rest;

import java.util.List;

public class ProgramRequest {

    private List<ProgramEventRequest> program;

    public ProgramRequest() {
        // Empty constructor for parsing
    }

    public ProgramRequest(List<ProgramEventRequest> program) {
        this.program = program;
    }

    public List<ProgramEventRequest> getProgram() {
        return program;
    }
}
