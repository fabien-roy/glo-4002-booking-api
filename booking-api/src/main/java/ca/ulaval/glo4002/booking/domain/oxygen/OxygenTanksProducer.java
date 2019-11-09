package ca.ulaval.glo4002.booking.domain.oxygen;

import ca.ulaval.glo4002.booking.domain.events.EventDate;
import ca.ulaval.glo4002.booking.enums.OxygenCategories;
import ca.ulaval.glo4002.booking.exceptions.oxygen.InvalidOxygenCategoryException;
import ca.ulaval.glo4002.booking.factories.OxygenTankFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OxygenTanksProducer {
	
	private OxygenTankInventory inventory;
	private OxygenTankFactory factory;
	
	public OxygenTanksProducer() {
		this.inventory = new OxygenTankInventory();
		this.factory = new OxygenTankFactory();
	}
	
	public OxygenTanksProducer(OxygenTankInventory inventory, OxygenTankFactory factory) {
		this.inventory = inventory;
		this.factory = factory;
	}
	
	public List<OxygenTank> produceOxygenForOrder(OxygenCategories category, LocalDate requestDate, Integer numberOfDays) {
		List<OxygenTank> newTanks = new ArrayList<OxygenTank>();
		Integer quantityToCover = getQuantityToCoverForOrderCategory(category, numberOfDays);
		OxygenCategories possibleCategory = getCategoryForRequestDate(category, requestDate);
		
		quantityToCover = checkInventory(category, possibleCategory, quantityToCover);
		System.out.println(possibleCategory);
		System.out.println(requestDate);
		System.out.println(quantityToCover);

		if(quantityToCover > 0) {

			newTanks.addAll(factory.buildOxygenTank(possibleCategory, requestDate, quantityToCover));
			
			inventory.addTanksToInventory(category, newTanks);
			inventory.requestTankByCategory(category, quantityToCover);
		}
		
		return newTanks;
	}
	
	private Integer getQuantityToCoverForOrderCategory(OxygenCategories category, Integer numberOfDays) {
		if (category == OxygenCategories.E) {
			return (numberOfDays * 5);
		} else {
			return (numberOfDays * 3);
		}
	}
	
	private Integer checkInventory(OxygenCategories category, OxygenCategories possibleCategory, Integer quantityToCover) {
		if (category == OxygenCategories.A) {
			if(possibleCategory == OxygenCategories.A) {
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.A, quantityToCover);
			}
			else if (possibleCategory == OxygenCategories.B) {
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.A, quantityToCover);
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.B, quantityToCover);
				
			} else if(possibleCategory == OxygenCategories.E) {
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.A, quantityToCover);
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.B, quantityToCover);
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.E, quantityToCover);
			}
		} else if(category == OxygenCategories.B) {
			if(possibleCategory == OxygenCategories.B) {
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.B, quantityToCover);
			}
			else if(possibleCategory == OxygenCategories.E) {
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.B, quantityToCover);
				quantityToCover = inventory.requestTankByCategory(OxygenCategories.E, quantityToCover);
			}
		} else if (category == OxygenCategories.E) {
			quantityToCover = inventory.requestTankByCategory(OxygenCategories.E, quantityToCover);
		}

		return quantityToCover;
	}
	
	private OxygenCategories getCategoryForRequestDate(OxygenCategories category, LocalDate requestDate) {
		LocalDate readyBeforeDate = EventDate.START_DATE.plusDays(1);

		switch (category) {
            case A:
                if(requestDate.plusDays(20).isBefore(readyBeforeDate)) {
                    return OxygenCategories.A;
                } else if (requestDate.plusDays(10).isBefore(readyBeforeDate)) {
                    return OxygenCategories.B;
                } else {
                    return OxygenCategories.E;
                }
            case B:
                if(requestDate.plusDays(10).isBefore(readyBeforeDate)) {
                    return OxygenCategories.B;
                } else {
                    return OxygenCategories.E;
                }
            case E:
                return OxygenCategories.E;
            default:
                throw new InvalidOxygenCategoryException(category);
        }
	}

}