package ca.ulaval.glo4002.booking.constants;

public class ExceptionConstants {
	public static class Pass {
		public static final String NOT_FOUND_MESSAGE = "Pass not found";
		public static final String CATEGORY_NOT_FOUND_MESSAGE = "Pass category not found";
		public static final String OPTION_NOT_FOUND_MESSAGE = "Pass option not found";
		public static final String ALREADY_CREATED_MESSAGE = "Pass created exists";
		public static final String DTO_INVALID_MESSAGE = "Invalid pass DTO";
	}

	public static class Shuttle {
		public static final String NOT_FOUND_MESSAGE = "Shuttle not found";
		public static final String CATEGORY_NOT_FOUND_MESSAGE = "Shuttle category not found";
		public static final String TYPE_NOT_FOUND_MESSAGE = "Shuttle type not found";
		public static final String ALREADY_CREATED_MESSAGE = "Shuttle created exists";
		public static final String FULL_MESSAGE = "Shuttle capacity is full";
	}

	public static class Oxygen {
		public static final String TANK_NOT_FOUND_MESSAGE = "Oxygen tank not found";
		public static final String CATEGORY_NOT_FOUND_MESSAGE = "Oxygen category not found";
		public static final String PRODUCTION_NOT_FOUND_MESSAGE = "Oxygen production not found";
		public static final String UNIT_TYPE_NOT_FOUND_MESSAGE = "Oxygen unit type not found";
		public static final String DTO_INVALID_MESSAGE = "Invalid oxygen DTO";
	}

	public static class Order {
		public static final String NOT_FOUND_MESSAGE = "Order not found";
		public static final String ALREADY_CREATED_MESSAGE = "Order created exists";
		public static final String DTO_INVALID_MESSAGE = "Invalid order DTO";
	}

	public static class Vendor {
		public static final String NOT_FOUND_MESSAGE = "Vendor not found";
	}

    public static final String UTILITY_CLASS_MESSAGE = "Utility class";
    public static final String UNUSED_METHOD_MESSAGE = "Unused method";
    public static final String INVALID_DATE_MESSAGE = "Invalid date";
    public static final String INVALID_DATE_TIME_MESSAGE = "Invalid date time";

	private ExceptionConstants() {
		throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
	}
}