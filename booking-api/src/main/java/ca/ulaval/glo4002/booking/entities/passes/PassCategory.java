package ca.ulaval.glo4002.booking.entities.passes;

import ca.ulaval.glo4002.booking.entities.oxygen.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.shuttles.ShuttleCategory;

import java.util.HashMap;
import java.util.Map;

public class PassCategory {
	private Long id;
	private String name;
	private Map<PassOption, Double> pricePerOption; // TODO : Conditions for rebate are not implemented yet
	private ShuttleCategory shuttleCategory;
	private OxygenCategory oxygenCategory;

	public PassCategory(Long id, String name, HashMap<PassOption, Double> pricePerOption, ShuttleCategory shuttleCategory, OxygenCategory oxygenCategory) {
	    this.id = id;
	    this.name = name;
	    this.pricePerOption = pricePerOption;
	    this.shuttleCategory = shuttleCategory;
	    this.oxygenCategory = oxygenCategory;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<PassOption, Double> getPricePerOption() {
        return pricePerOption;
    }

    public ShuttleCategory getShuttleCategory() {
        return shuttleCategory;
    }

    public OxygenCategory getOxygenCategory() {
        return oxygenCategory;
    }
}
