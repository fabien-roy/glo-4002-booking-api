package ca.ulaval.glo4002.booking.orders.domain;

import ca.ulaval.glo4002.booking.passes.domain.PassRefactored;
import ca.ulaval.glo4002.booking.profits.domain.Money;
import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;

import java.math.BigDecimal;
import java.util.List;

public class OrderRefactored {

	private OrderNumber orderNumber;
	private OrderDate orderDate;
	private List<PassRefactored> passes;

	public OrderRefactored(OrderDate orderDate, List<PassRefactored> passes) {
		this.orderDate = orderDate;
		this.passes = passes;
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

	public List<PassRefactored> getPasses() {
		return passes;
	}

	public Money getPrice() {
		Money price = new Money(BigDecimal.ZERO);

		for (PassRefactored pass : passes) {
			price = price.add(pass.getPrice());
		}

		return price;
	}

	// TODO : Use updateProfit
	public void updateProfit(ProfitReport profitReport) {
	    profitReport.addRevenue(getPrice());
	}
}