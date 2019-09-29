package ca.ulaval.glo4002.booking.domainObjects.passes.categories;

import ca.ulaval.glo4002.booking.domainObjects.Qualifiable;
import ca.ulaval.glo4002.booking.domainObjects.passes.options.PassOption;
import ca.ulaval.glo4002.booking.domainObjects.qualities.Quality;

import java.util.Map;

public abstract class PassCategory implements Qualifiable {
	protected Long id;
	protected String name;
	protected Quality quality;
    protected Map<PassOption, Double> pricePerOption; // TODO : Conditions for rebate are not implemented yet

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Quality getQuality() {
        return quality;
    }

    public Map<PassOption, Double> getPricePerOption() {
        return pricePerOption;
    }
}
