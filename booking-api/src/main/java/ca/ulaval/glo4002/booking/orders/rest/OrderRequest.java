package ca.ulaval.glo4002.booking.orders.rest;

import ca.ulaval.glo4002.booking.passes.rest.PassBundleRequest;

public class OrderRequest {

	private String orderDate;
	private String vendorCode;
	private PassBundleRequest passes;

	public OrderRequest() {
		// Empty constructor for parsing
	}

	public OrderRequest(String orderDate, String vendorCode, PassBundleRequest passes) {
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

	public PassBundleRequest getPasses() {
		return passes;
	}

	public void setPasses(PassBundleRequest passes) {
		this.passes = passes;
	}
}
