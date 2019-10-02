package ca.ulaval.glo4002.booking.constants;

public class ExceptionConstants {

	public static class Order {
		public static final String NOT_FOUND_DESCRIPTION = "Order with number {orderNumber} not found";
		public static final String NOT_FOUND_ERROR = "ORDER NOT FOUND";
		public static final String ALREADY_CREATED_DESCRIPTION = "Order with number {orderNumber} already exists";
		public static final String ALREADY_CREATED_ERROR = "ORDER_ALREADY_CREATED";
		public static final String INVALID_FORMAT_DESCRIPTION = "Invalid order format";
		public static final String INVALID_FORMAT_ERROR = "INVALID_ORDER_DTO";
		public static final String INVALID_DATE_DESCRIPTION = "Order date should be between {startDate} and {endDate}";
		public static final String INVALID_DATE_ERROR = "INVALID_ORDER_DATE";
	}

    public static class Pass {
		public static final String NOT_FOUND_DESCRIPTION = "Pass with number {passNumber} not found";
		public static final String NOT_FOUND_ERROR = "PASS_NOT_FOUND";
		public static final String ALREADY_CREATED_DESCRIPTION = "Pass with {passNumber} already exists";
		public static final String ALREADY_CREATED_ERROR = "PASS_ALREADY_CREATED";
		public static final String CATEGORY_NOT_FOUND_DESCRIPTION = "Pass category with name {name} not found";
		public static final String CATEGORY_NOT_FOUND_ERROR = "PASS_CATEGORY_NOT_FOUND";
		public static final String OPTION_NOT_FOUND_DESCRIPTION = "Pass option with name {name} not found";
		public static final String OPTION_NOT_FOUND_ERROR = "PASS_OPTION_NOT_FOUND";
		public static final String OPTION_PACKAGE_WITH_EVENT_DATE_DESCRIPTION = "Package passes cannot have event dates";
		public static final String OPTION_PACKAGE_WITH_EVENT_DATE_ERROR = "OPTION_PACKAGE_WITH_EVENT_DATE";
		public static final String INVALID_FORMAT_DESCRIPTION = "Invalid format";
		public static final String INVALID_FORMAT_ERROR = "INVALID_PASS_FORMAT";
		public static final String INVALID_DATE_DESCRIPTION = "Pass date should be between {startDate} and {endDate}";
		public static final String INVALID_DATE_ERROR = "INVALID_PASS_DATE";
	}

	public static class Oxygen {
		public static final String TANK_NOT_FOUND_DESCRIPTION = "Oxygen tank not found";
		public static final String TANK_NOT_FOUND_ERROR = "OXYGEN_TANK_NOT_FOUND";
		public static final String TANK_ALREADY_CREATED_DESCRIPTION = "OXYGEN_TANK_ALREADY_CREATED";
		public static final String TANK_ALREADY_CREATED_ERROR = "Oxygen tank already created";
		public static final String CATEGORY_NOT_FOUND_DESCRIPTION = "Oxygen category not found";
		public static final String CATEGORY_NOT_FOUND_ERROR = "OXYGEN_CATEGORY_NOT_FOUND";
		public static final String PRODUCTION_NOT_FOUND_DESCRIPTION = "Oxygen production not found";
		public static final String PRODUCTION_NOT_FOUND_ERROR = "OXYGEN_PRODUCTION_NOT_FOUND";
		public static final String UNIT_TYPE_NOT_FOUND_DESCRIPTION = "Oxygen unit type not found";
		public static final String UNIT_TYPE_NOT_FOUND_ERROR = "OXYGEN_UNIT_TYPE_NOT_FOUND";
	}

	public static class Shuttle {
		public static final String NOT_FOUND_DESCRIPTION = "Shuttle not found";
		public static final String NOT_FOUND_ERROR = "SHUTTLE_NOT_FOUND";
		public static final String CATEGORY_NOT_FOUND_DESCRIPTION = "Shuttle category not found";
		public static final String CATEGORY_NOT_FOUND_ERROR = "SHUTTLE_CATEGORY_NOT_FOUND";
		public static final String TYPE_NOT_FOUND_DESCRIPTION = "Shuttle type not found";
		public static final String TYPE_NOT_FOUND_ERROR = "SHUTTLE_TYPE_NOT_FOUND";
		public static final String ALREADY_CREATED_DESCRIPTION = "Shuttle created exists";
		public static final String ALREADY_CREATED_ERROR = "SHUTTLE_CREATED_EXISTS";
		public static final String FULL_DESCRIPTION = "Shuttle capacity is full";
		public static final String FULL_ERROR = "SHUTTLE_CAPACITY_IS_FULL";
	}

	public static class Vendor {
		public static final String NOT_FOUND_DESCRIPTION = "Vendor with code {vendorCode} not found";
		public static final String NOT_FOUND_ERROR = "VENDOR_NOT_FOUND";
	}
	
	public static class Passenger {
		public static final String NOT_FOUND_DESCRIPTION = "Passenger not found";
		public static final String NOT_FOUND_ERROR = "PASSENGER_NOT_FOUND";
		public static final String ALREADY_CREATED_DESCRIPTION = "Passenger created exists";
		public static final String ALREADY_CREATED_ERROR = "PASSENGER_CREATED_EXISTS";
	}
	
	public static class Trip {
		public static final String NOT_FOUND_DESCRIPTION = "Trip not found";
		public static final String NOT_FOUND_ERROR = "TRIP_NOT_FOUND";
		public static final String ALREADY_CREATED_DESCRIPTION = "Trip created exists";
		public static final String ALREADY_CREATED_ERROR = "TRIP_CREATED_EXISTS";
	}

    public static final String UTILITY_CLASS_ERROR = "UTILITY_CLASS";
    public static final String UNUSED_METHOD_DESCRIPTION = "Unused method";
	public static final String UNUSED_METHOD_ERROR = "UNUSED_METHOD";
    public static final String INVALID_DATE_DESCRIPTION = "Invalid date";
	public static final String INVALID_DATE_ERROR = "INVALID_DATE";
    public static final String INVALID_DATE_TIME_DESCRIPTION = "Invalid date time";
	public static final String INVALID_DATE_TIME_ERROR = "INVALID_DATE_TIME";

	private ExceptionConstants() {
		throw new IllegalStateException(UTILITY_CLASS_ERROR);
	}
}