package ca.ulaval.glo4002.booking.constants;

public class OxygenConstants {
    public static class Categories {
        public static final Long E_ID = 0L;
        public static final Long B_ID = 1L;
        public static final Long A_ID = 2L;

        public static final String E_NAME = "E";
        public static final String B_NAME = "B";
        public static final String A_NAME = "A";

        public static final Long E_PRODUCTION_ID = Productions.IMMEDIATE_ID;
        public static final Long B_PRODUCTION_ID = Productions.ELECTROLYTES_ID;
        public static final Long A_PRODUCTION_ID = Productions.SPARK_PLUGS_ID;
    }

    public static class Productions {
        public static final Long IMMEDIATE_ID = 0L;
        public static final Long ELECTROLYTES_ID = 1L;
        public static final Long SPARK_PLUGS_ID = 2L;

        public static final String IMMEDIATE_NAME = "Immediate";
        public static final String ELECTROLYTES_NAME = "Electrolytes";
        public static final String SPARK_PLUGS_NAME = "Spark plug";

        public static final Double IMMEDIATE_PRICE_PER_UNIT = 5000.0;
        public static final Double ELECTROLYTES_PRICE_PER_UNIT = 600.0;
        public static final Double SPARK_PLUGS_PRICE_PER_UNIT = 650.0;

        public static final Integer IMMEDIATE_PRODUCED_UNITS = 1;
        public static final Integer ELECTROLYTES_PRODUCED_UNITS = 8;
        public static final Integer SPARK_PLUGS_PRODUCED_UNITS = 15;

        public static final Integer IMMEDIATE_PRODUCED_TANKS = 1;
        public static final Integer ELECTROLYTES_PRODUCED_TANKS = 3;
        public static final Integer SPARK_PLUGS_PRODUCED_TANKS = 5;

        public static final Integer IMMEDIATE_PRODUCTION_TIME = 0;
        public static final Integer ELECTROLYTES_PRODUCTION_TIME = 10;
        public static final Integer SPARK_PLUGS_PRODUCTION_TIME = 20;

        public static final Long IMMEDIATE_UNIT_TYPE_ID = UnitTypes.OXYGEN_TANKS_ID;
        public static final Long ELECTROLYTES_UNIT_TYPE_ID = UnitTypes.WATER_LITERS_ID;
        public static final Long SPARK_PLUGS_UNIT_TYPE_ID = UnitTypes.SPARK_PLUGS_ID;
    }

    public static class UnitTypes {
        public static final Long OXYGEN_TANKS_ID = 0L;
        public static final Long WATER_LITERS_ID = 1L;
        public static final Long SPARK_PLUGS_ID = 2L;

        public static final String OXYGEN_TANKS_NAME = "Oxygen tank";
        public static final String WATER_LITERS_NAME = "Water liter";
        public static final String SPARK_PLUGS_NAME = "Spark plug";
    }
}
