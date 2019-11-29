package ca.ulaval.glo4002.booking.orders.rest;

import ca.ulaval.glo4002.booking.passes.domain.PassBundleDto;

public class OrderRequest {

	private String orderDate;
	private String vendorCode;
	private PassBundleDto passes;

	public OrderRequest() {
		// Empty constructor for parsing
	}

	public OrderRequest(String orderDate, String vendorCode, PassBundleDto passes) {
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

	public PassBundleDto getPasses() {
		return passes;
	}

	public void setPasses(PassBundleDto passes) {
		this.passes = passes;
	}
}
