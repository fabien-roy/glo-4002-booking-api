package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.Pass;
import ca.ulaval.glo4002.booking.passes.domain.PassBundle;
import ca.ulaval.glo4002.booking.profits.Money;

import java.time.LocalDateTime;
import java.util.List;
import ca.ulaval.glo4002.booking.profits.Profit;

public class Order {

	private OrderNumber orderNumber;
	private LocalDateTime orderDate;
	private PassBundle passBundle;

	public Order(OrderNumber orderNumber, LocalDateTime orderDate, PassBundle passBundle) {
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.passBundle = passBundle;
	}

	public OrderNumber getOrderNumber() {
		return orderNumber;
	}

	public String getVendorCode() {
		return orderNumber.getVendorCode();
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public PassBundle getPassBundle() {
		return passBundle;
	}

	public Money getPrice() {
		return passBundle.getPrice();
	}

	public List<Pass> getPasses() {
		return passBundle.getPasses();
	}

	public void updateProfit(Profit profit) {
		passBundle.updateProfit(profit);
	}
}