package ca.ulaval.glo4002.booking.constants;

public class QualityConstants {

    public static final Long SUPERNOVA_ID = 0L;
    public static final Long SUPERGIANT_ID = 1L;
    public static final Long NEBULA_ID = 2L;

    public static final String SUPERNOVA_NAME = "Supernova";
    public static final String SUPERGIANT_NAME = "Supergiant";
    public static final String NEBULA_NAME = "Nebula";
    
    public static final Long SUPERNOVA_OXYGENTANK_NEEDED_PER_DAY = 5L;
    public static final Long SUPERGIANT_OXYGENTANK_NEEDED_PER_DAY = 3L;
    public static final Long NEBULA_OXYGENTANK_NEEDED_PER_DAY = 3L;

    private QualityConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_MESSAGE);
    }
}
