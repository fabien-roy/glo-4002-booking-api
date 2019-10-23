package ca.ulaval.glo4002.booking.parsers;

import ca.ulaval.glo4002.booking.domain.passes.PassOption;
import ca.ulaval.glo4002.booking.dto.PassesDto;
import ca.ulaval.glo4002.booking.enums.PassOptions;
import ca.ulaval.glo4002.booking.factories.PassFactory;

public class PassesParser {

    private PassFactory passFactory;

    public PassesParser(PassFactory passFactory) {
        this.passFactory = passFactory;
    }

    public PassOption parsePassOption(PassesDto passesDto) {
        PassOptions passOptionElem = PassOptions.get(passesDto.getPassOption());

        return passFactory.buildPassOption(passOptionElem);
    }

    // TODO : ACP : PassesParser.parserPassCategory
    /*
    public PassCategory parsePassCategory(PassesDto passesDto) {
        PassCategories passCategoryElem;

        try {
            passCategoryElem = PassCategories.get(passesDto.getPassCategory());
        } catch(Exception exception) {
            throw new InvalidPassCategoryException();
        }

        return passFactory.buildPassCategory(passCategoryElem);
    }
    */
}
