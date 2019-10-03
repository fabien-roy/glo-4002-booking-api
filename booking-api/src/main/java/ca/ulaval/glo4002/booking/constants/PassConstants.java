package ca.ulaval.glo4002.booking.constants;

public class PassConstants {

    public static class Categories {

        public static final Long SUPERNOVA_ID = 0L;
        public static final Long SUPERGIANT_ID = 1L;
        public static final Long NEBULA_ID = 2L;

        public static final String SUPERNOVA_NAME = "supernova";
        public static final String SUPERGIANT_NAME = "supergiant";
        public static final String NEBULA_NAME = "nebula";

        public static final double SUPERNOVA_PACKAGE_PRICE = 700000.00d;
        public static final double SUPERGIANT_PACKAGE_PRICE = 500000.00d;
        public static final double NEBULA_PACKAGE_PRICE = 250000.00d;

        public static final double SUPERNOVA_SINGLE_PASS_PRICE = 150000.00d;
        public static final double SUPERGIANT_SINGLE_PASS_PRICE = 100000.00d;
        public static final double NEBULA_SINGLE_PASS_PRICE = 50000.00d;

        public static final Long SUPERNOVA_SHUTTLE_CATEGORY_ID = ShuttleConstants.Categories.ET_SPACESHIP_ID;
        public static final Long SUPERGIANT_SHUTTLE_CATEGORY_ID = ShuttleConstants.Categories.MILLENNIUM_FALCON_ID;
        public static final Long NEBULA_SHUTTLE_CATEGORY_ID = ShuttleConstants.Categories.SPACE_X_ID;

        public static final Long SUPERNOVA_OXYGEN_CATEGORY_ID = OxygenConstants.Categories.E_ID;
        public static final Long SUPERGIANT_OXYGEN_CATEGORY_ID = OxygenConstants.Categories.B_ID;
        public static final Long NEBULA_OXYGEN_CATEGORY_ID = OxygenConstants.Categories.A_ID;

        public static final int SUPERGIANT_SINGLE_PASS_REBATE_THRESHOLD = 5;
        public static final int NEBULA_SINGLE_PASS_REBATE_THRESHOLD = 4;

        public static final double SUPERGIANT_SINGLE_PASS_REBATE = 0.9d;
        public static final double NEBULA_SINGLE_PASS_REBATE = 0.9d;

        private Categories(){
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_ERROR);
        }
    }

    public static class Options {

        public static final Long PACKAGE_ID = 0L;
        public static final Long SINGLE_ID = 1L;

        public static final String PACKAGE_NAME = "package";
        public static final String SINGLE_NAME = "singlePass";

        private Options(){
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_ERROR);
        }
    }

    private PassConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_ERROR);
    }
}
