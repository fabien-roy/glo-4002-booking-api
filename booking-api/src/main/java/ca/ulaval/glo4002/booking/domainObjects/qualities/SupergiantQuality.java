package ca.ulaval.glo4002.booking.domainObjects.qualities;

import ca.ulaval.glo4002.booking.constants.QualityConstants;

public class SupergiantQuality extends Quality {

    public SupergiantQuality() {
        this.id = QualityConstants.SUPERGIANT_ID;
        this.name = QualityConstants.SUPERGIANT_NAME;
        this.oxygenTanksNeededPerDay = QualityConstants.SUPERGIANT_OXYGENTANK_NEEDED_PER_DAY;
    }
}
