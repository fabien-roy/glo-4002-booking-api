package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassList;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;

public class OrderRefactored {

	private OrderNumber orderNumber;
	private OrderDate orderDate;
	private PassList pass;

	public OrderRefactored(OrderDate orderDate, PassList pass) {
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

	public PassList getPass() {
		return pass;
	}

	public void setPassNumber(long passNumber) {
		this.pass.setNumber(passNumber);
	}

	public Money getPrice() {
		return pass.getPrice();
	}

	// TODO : Use updateProfit
	public void updateProfit(ProfitReport profitReport) {
		pass.updateProfit(profitReport);
	}
}