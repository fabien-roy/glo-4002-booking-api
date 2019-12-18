package ca.ulaval.glo4002.booking.orders.rest;

import ca.ulaval.glo4002.booking.passes.rest.PassRequest;

public class OrderRequest {

	private String orderDate;
	private String vendorCode;
	private PassRequest passes;

	public OrderRequest() {
		// Empty constructor for parsing
	}

	public OrderRequest(String orderDate, String vendorCode, PassRequest passes) {
		this.orderDate = orderDate;
		this.vendorCode = vendorCode;
		this.passes = passes;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public PassRequest getPasses() {
		return passes;
	}
}
