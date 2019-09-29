package ca.ulaval.glo4002.booking.builders.passes;

import ca.ulaval.glo4002.booking.builders.Builder;
import ca.ulaval.glo4002.booking.constants.PassConstants;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.PackagePassOption;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.SinglePassOption;
import ca.ulaval.glo4002.booking.exceptions.passes.PassOptionNotFoundException;

public class PassOptionBuilder implements Builder<PassOption> {

    public PassOption buildById(Long id) {
        if (id.equals(PassConstants.Options.PACKAGE_ID)) {
            return buildPackagePassOption();
        } else if (id.equals(PassConstants.Options.SINGLE_ID)) {
            return buildSinglePassOption();
        } else {
            throw new PassOptionNotFoundException();
        }
    }

    public PassOption buildByName(String name) {
        if (name.equals(PassConstants.Options.PACKAGE_NAME)) {
            return buildPackagePassOption();
        } else if (name.equals(PassConstants.Options.SINGLE_NAME)) {
            return buildSinglePassOption();
        } else {
            throw new PassOptionNotFoundException();
        }
    }

    private PassOption buildPackagePassOption() {
        return new PackagePassOption();
    }

    private PassOption buildSinglePassOption() {
        return new SinglePassOption();
    }
}
