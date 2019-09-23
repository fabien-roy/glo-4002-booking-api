package ca.ulaval.glo4002.booking.constants;

public class PassConstants {
    public static class Categories {
        public static final Long SUPERNOVA_ID = 0L;
        public static final Long SUPERGIANT_ID = 1L;
        public static final Long NEBULA_ID = 2L;

        public static final String SUPERNOVA_NAME = "Supernova";
        public static final String SUPERGIANT_NAME = "Supergiant";
        public static final String NEBULA_NAME = "Nebula";

        public static final Double SUPERNOVA_PACKAGE_PRICE = 700000.0;
        public static final Double SUPERGIANT_PACKAGE_PRICE = 500000.0;
        public static final Double NEBULA_PACKAGE_PRICE = 250000.0;

        public static final Double SUPERNOVA_SINGLE_PASS_PRICE = 150000.0;
        public static final Double SUPERGIANT_SINGLE_PASS_PRICE = 100000.0;
        public static final Double NEBULA_SINGLE_PASS_PRICE = 50000.0;

        public static final Long SUPERNOVA_SHUTTLE_CATEGORY_ID = ShuttleConstants.Categories.ET_SPACESHIP_ID;
        public static final Long SUPERGIANT_SHUTTLE_CATEGORY_ID = ShuttleConstants.Categories.MILLENNIUM_FALCON_ID;
        public static final Long NEBULA_SHUTTLE_CATEGORY_ID = ShuttleConstants.Categories.SPACE_X_ID;

        public static final Long SUPERNOVA_OXYGEN_CATEGORY_ID = OxygenConstants.Categories.E_ID;
        public static final Long SUPERGIANT_OXYGEN_CATEGORY_ID = OxygenConstants.Categories.B_ID;
        public static final Long NEBULA_OXYGEN_CATEGORY_ID = OxygenConstants.Categories.A_ID;
    }

    public static class Options {
        public static final Long PACKAGE_ID = 0L;
        public static final Long SINGLE_PASS_ID = 1L;

        public static final String PACKAGE_NAME = "Package";
        public static final String SINGLE_PASS_NAME = "Single pass";
    }
}
