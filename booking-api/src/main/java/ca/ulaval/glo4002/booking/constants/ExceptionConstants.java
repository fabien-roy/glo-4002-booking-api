package ca.ulaval.glo4002.booking.constants;

public class ExceptionConstants {
    public static final String PASS_NOT_FOUND_MESSAGE = "Pass not found";
    public static final String PASS_CATEGORY_NOT_FOUND_MESSAGE = "Pass category not found";
    public static final String PASS_OPTION_NOT_FOUND_MESSAGE = "Pass option not found";
    public static final String PASS_ALREADY_CREATED_MESSAGE = "Pass created exists";
    public static final String PASS_DTO_INVALID_MESSAGE = "Invalid pass DTO";

    public static final String SHUTTLE_NOT_FOUND_MESSAGE = "Shuttle not found";
    public static final String SHUTTLE_CATEGORY_NOT_FOUND_MESSAGE = "Shuttle category not found";
    public static final String SHUTTLE_TYPE_NOT_FOUND_MESSAGE = "Shuttle type not found";

    public static final String OXYGEN_TANK_NOT_FOUND_MESSAGE = "Oxygen tank not found";
    public static final String OXYGEN_CATEGORY_NOT_FOUND_MESSAGE = "Oxygen category not found";
    public static final String OXYGEN_PRODUCTION_NOT_FOUND_MESSAGE = "Oxygen production not found";
    public static final String OXYGEN_UNIT_TYPE_NOT_FOUND_MESSAGE = "Oxygen unit type not found";

    public static final String ORDER_NOT_FOUND_MESSAGE = "Order not found";
    public static final String ORDER_ALREADY_CREATED_MESSAGE = "Order created exists";
    public static final String ORDER_DTO_INVALID_MESSAGE = "Invalid order DTO";

    public static final String VENDOR_NOT_FOUND_MESSAGE = "Vendor not found";
    public static final String INVALID_EVENT_DATE_MESSAGE = "Invalid event date";
    public static final String SHUTTLE_FULL_MESSAGE = "Shuttle capacity is full";

    public static final String UTILITY_CLASS_MESSAGE = "Utility class";
    public static final String UNUSED_METHOD_MESSAGE = "Unused method";

    private ExceptionConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }
}