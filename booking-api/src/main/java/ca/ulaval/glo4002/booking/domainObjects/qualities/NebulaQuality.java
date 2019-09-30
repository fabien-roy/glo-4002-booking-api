package ca.ulaval.glo4002.booking.domainObjects.qualities;

import ca.ulaval.glo4002.booking.constants.QualityConstants;

public class NebulaQuality extends Quality {

    public NebulaQuality() {
        this.id = QualityConstants.NEBULA_ID;
        this.name = QualityConstants.NEBULA_NAME;
        this.oxygenTanksNeededPerDay = QualityConstants.NEBULA_OXYGENTANK_NEEDED_PER_DAY;
    }
}
