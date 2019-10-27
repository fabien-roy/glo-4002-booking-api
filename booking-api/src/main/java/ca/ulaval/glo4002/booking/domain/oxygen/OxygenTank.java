package ca.ulaval.glo4002.booking.domain.oxygen;

import java.math.BigDecimal;

import ca.ulaval.glo4002.booking.domain.money.Money;
import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

public class OxygenTank {
	private Long id;
	private OxygenTankCategory category;
	private OxygenDate requestDate;
	private OxygenDate readyDate;
	private Money totalPrice;

	public OxygenTank(Long id, OxygenTankCategory category, OxygenDate requestDate) {
		this.id = id;
		this.category = category;
		this.requestDate = requestDate;
		this.setMoney();
		this.setReadyDate();
	}

	// TODO : generate an new ID ?
	public OxygenTank(OxygenTankCategory category, OxygenDate requestDate) {
		this.category = category;
		this.requestDate = requestDate;
		this.setMoney();
		this.setReadyDate();
	}

	private void setMoney() {
		this.totalPrice = this.calculateMoney();
	}

	private Money calculateMoney() {
		int nbTankCreated = 1;
		int nbRessources = 0;
		int ressourcesPrice = 0;

		if (this.category == OxygenTankCategory.CATEGORY_A) {
			nbTankCreated = 5;
			nbRessources = 15;
			ressourcesPrice = 650;
		} else if (this.category == OxygenTankCategory.CATEGORY_B) {
			nbTankCreated = 3;
			nbRessources = 8;
			ressourcesPrice = 600;
		} else if (this.category == OxygenTankCategory.CATEGORY_E) {
			nbTankCreated = 1;
			nbRessources = 1;
			ressourcesPrice = 5000;
		}

		BigDecimal tankPrice = new BigDecimal((nbRessources * ressourcesPrice) / nbTankCreated);
		return new Money(tankPrice);
	}

	private void setReadyDate() {
		this.readyDate = this.calculateReadyDate();
	}

	private OxygenDate calculateReadyDate() {
		OxygenDate readyDate = this.requestDate;
		if (this.category == OxygenTankCategory.CATEGORY_A) {
			readyDate.addDays(20L);
		} else if (this.category == OxygenTankCategory.CATEGORY_B) {
			readyDate.addDays(10L);
		}
		return readyDate;
	}

	public Long getId() {
		return this.id;
	}

	public OxygenTankCategory getCategory() {
		return this.category;
	}

	public OxygenDate getRequestDate() {
		return this.requestDate;
	}

	public OxygenDate getReadyDate() {
		return this.readyDate;
	}

	public Money getMoney() {
		return this.totalPrice;
	}
}
