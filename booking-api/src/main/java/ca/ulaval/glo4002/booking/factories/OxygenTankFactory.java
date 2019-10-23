package ca.ulaval.glo4002.booking.factories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.enterprise.module.bootstrap.BootException;

import ca.ulaval.glo4002.booking.domain.OxygenTank;
import ca.ulaval.glo4002.booking.domain.OxygenTankInventory;
import ca.ulaval.glo4002.booking.domain.orders.OrderDate;
import ca.ulaval.glo4002.booking.enums.OxygenTankCategory;

public class OxygenTankFactory {
	
	private OxygenTankInventory inventory;
	
	private static final LocalDate START_OF_FESTIVAL_DATE = LocalDate.of(2050, 07, 17);
	
	public OxygenTankFactory(OxygenTankInventory inventory) {
		this.inventory = inventory;
	}
	
	// TODO : Demandé dans le cas ou on book un Nebula le 17 juillet et qu'on a en réserve 1 A + 1 B est-ce qu'on doit seulement créer 1 E et réserver A et B ou on réserve A et on créer 2 E
    public List<OxygenTank> buildOxygenTankByCategory(OxygenTankCategory category, LocalDate requestDate, Long numberOfDays) throws BootException {
    	if(requestDate.isAfter(START_OF_FESTIVAL_DATE)) throw new BootException("Error trying to book after start of festival");
    	
    	List<OxygenTank> createdTanks = new ArrayList<>();
    	
    	Long quantityToCover = 0L;
        
    	if(category == OxygenTankCategory.CATEGORY_E) {
    		quantityToCover = numberOfDays * 5;
        } else {
        	quantityToCover = numberOfDays * 3;
        }
    	
    	quantityToCover = checkIfNotInUseCanCoverTheNeed(category, quantityToCover);
    
    	if(quantityToCover > 0) {
    		category = getCategoryForRequestDate(category, requestDate);
    	}
    	
    	return createdTanks;
    }

    // TODO : really feel like a hack to iterate through enum, also will work if there is no more time to produce anything except E but
    // if there is time we want to create A or B before using stored tank.
    // see TODO of buildOxygenTankByCategory this logic is maybe not needed
	private Long checkIfNotInUseCanCoverTheNeed(OxygenTankCategory category, Long quantityToCover) {
		OxygenTankCategory[] categories = OxygenTankCategory.values();
		Integer idx = category.ordinal();
		
		quantityToCover = inventory.requestTankByCategory(category, quantityToCover);
		
		while(quantityToCover != 0  && idx < categories.length) {
			quantityToCover = inventory.requestTankByCategory(categories[idx], quantityToCover);
			idx++;
		}
		
		return quantityToCover;
	}

	private OxygenTankCategory getCategoryForRequestDate(OxygenTankCategory category, LocalDate requestDate) {
		return null;
	}
}
