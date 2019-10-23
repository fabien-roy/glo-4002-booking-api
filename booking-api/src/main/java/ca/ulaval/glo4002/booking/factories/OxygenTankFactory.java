package ca.ulaval.glo4002.booking.factories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.enterprise.module.bootstrap.BootException;

import ca.ulaval.glo4002.booking.domain.OxygenDate;
import ca.ulaval.glo4002.booking.domain.OxygenTank;
import ca.ulaval.glo4002.booking.domain.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

public class OxygenTankFactory {
	
	private OxygenTankInventory inventory;
	
	private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
	private static final OxygenTankCategory[] categories = OxygenTankCategory.values();
	
	public OxygenTankFactory(OxygenTankInventory inventory) {
		this.inventory = inventory;
	}
	
    public List<OxygenTank> buildOxygenTankByCategory(OxygenTankCategory category, LocalDate requestDate, Long numberOfDays) throws BootException {
    	if(requestDate.isAfter(START_OF_FESTIVAL_DATE)) throw new BootException("Error trying to book after start of festival");
    	
    	List<OxygenTank> createdTanks = new ArrayList<>();
    	
    	Long quantityToCover = 0L;
        
    	if(category == OxygenTankCategory.CATEGORY_E) {
    		quantityToCover = numberOfDays * 5;
        } else {
        	quantityToCover = numberOfDays * 3;
        }
    	
    	quantityToCover = inventory.requestTankByCategory(category, quantityToCover);
    
    	if(quantityToCover > 0) {
    		OxygenTankCategory categoryForDate = getCategoryForRequestDate(category, requestDate);
    		if(category == categoryForDate) {
    			for(int i = 0; i < quantityToCover; i++) {
    				createdTanks.add(new OxygenTank(categoryForDate, new OxygenDate(requestDate)));
    			}
    		} else {
    			quantityToCover = inventory.requestTankByCategory(category, quantityToCover);
    			
    			if(quantityToCover > 0) {
    				for(int i = 0; i < quantityToCover; i++) {
    					createdTanks.add(new OxygenTank(categoryForDate, new OxygenDate(requestDate)));
    				}
    			}
    		}
    		
    	}
    	
    	return createdTanks;
    }

    // TODO : Refactor needed
	private OxygenTankCategory getCategoryForRequestDate(OxygenTankCategory category, LocalDate requestDate) {
		if(category == OxygenTankCategory.CATEGORY_A) {
			if(requestDate.plusDays(20).isBefore(START_OF_FESTIVAL_DATE.plusDays(1))) {
				return OxygenTankCategory.CATEGORY_A;
			} else if (requestDate.plusDays(10).isBefore(START_OF_FESTIVAL_DATE.plusDays(1))) {
				return OxygenTankCategory.CATEGORY_B;
			} else {
				return OxygenTankCategory.CATEGORY_E;
			}
		} else if(category == OxygenTankCategory.CATEGORY_B) {
			if(requestDate.plusDays(10).isBefore(START_OF_FESTIVAL_DATE.plusDays(1))) {
				return OxygenTankCategory.CATEGORY_B;
			} else {
				return OxygenTankCategory.CATEGORY_E;
			}
		} else {
			return OxygenTankCategory.CATEGORY_E;
		}
	}
}
