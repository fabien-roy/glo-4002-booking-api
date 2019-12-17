package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;

public class OrderRefactored {

	private OrderNumber orderNumber;
	private OrderDate orderDate;
	private PassRefactored pass;

	public OrderRefactored(OrderDate orderDate, PassRefactored pass) {
		this.orderDate = orderDate;
		this.pass = pass;
	}

	public void setOrderNumber(OrderNumber orderNumber) {
		this.orderNumber = orderNumber;
	}

	public OrderNumber getOrderNumber() {
		return orderNumber;
	}

	public OrderDate getOrderDate() {
		return orderDate;
	}

	public PassRefactored getPass() {
		return pass;
	}

	public void setPassNumber(long passNumber) {
		this.pass.setNumber(passNumber);
	}

	// TODO : Use updateProfit
	public void updateProfit(ProfitReport profitReport) {
		pass.updateProfit(profitReport);
	}
}