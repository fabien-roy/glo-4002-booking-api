package ca.ulaval.glo4002.booking.builders.passes;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PackagePassOption;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainobjects.passes.options.SinglePassOption;
import ca.ulaval.glo4002.booking.exceptions.passes.PassOptionNotFoundException;

public class PassOptionBuilder implements Builder<PassOption> {

    public PassOption buildById(Long id) {
        if (id.equals(PassConstants.Options.PACKAGE_ID)) {
            return buildPackagePassOption();
        } else if (id.equals(PassConstants.Options.SINGLE_ID)) {
            return buildSinglePassOption();
        } else {
            throw new PassOptionNotFoundException(id.toString());
        }
    }

    public PassOption buildByName(String name) {
        switch (name) {
            case PassConstants.Options.PACKAGE_NAME:
                return buildPackagePassOption();
            case PassConstants.Options.SINGLE_NAME:
                return buildSinglePassOption();
            default:
                throw new PassOptionNotFoundException(name);
        }
    }

    private PassOption buildPackagePassOption() {
        return new PackagePassOption();
    }

    private PassOption buildSinglePassOption() {
        return new SinglePassOption();
    }
}
