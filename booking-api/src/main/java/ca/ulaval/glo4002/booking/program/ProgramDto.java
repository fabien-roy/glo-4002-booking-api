package ca.ulaval.glo4002.booking.program;

import java.util.List;

public class ProgramDto {

    private List<ProgramEventDto> program;

    public ProgramDto(List<ProgramEventDto> program) {
        this.program = program;
    }

    public List<ProgramEventDto> getProgram() {
        return program;
    }
}
