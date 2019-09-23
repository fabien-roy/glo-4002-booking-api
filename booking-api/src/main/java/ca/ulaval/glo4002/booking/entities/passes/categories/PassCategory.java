package ca.ulaval.glo4002.booking.entities.passes.categories;

import ca.ulaval.glo4002.booking.entities.oxygen.categories.OxygenCategory;
import ca.ulaval.glo4002.booking.entities.passes.options.PassOption;
import ca.ulaval.glo4002.booking.entities.shuttles.categories.ShuttleCategory;

import java.util.Map;

public abstract class PassCategory {
	protected Long id;
	protected String name;
	protected ShuttleCategory shuttleCategory;
	protected OxygenCategory oxygenCategory;
    protected Map<PassOption, Double> pricePerOption; // TODO : Conditions for rebate are not implemented yet

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
