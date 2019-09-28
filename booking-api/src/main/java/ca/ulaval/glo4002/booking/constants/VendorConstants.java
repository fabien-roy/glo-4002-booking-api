package ca.ulaval.glo4002.booking.constants;

public class VendorConstants {
    public static class VendorCode{
        public static final Long TEAM_VENDOR_ID = 1L;
        public static final String TEAM_VENDOR_CODE = "TEAM";

        private VendorCode(){
            throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_EXCEPTION_MESSAGE);
        }
    }

    private VendorConstants(){
        throw new IllegalStateException(ExceptionConstants.UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
}
