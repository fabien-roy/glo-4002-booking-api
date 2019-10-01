package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.oxygen.categories.OxygenCategory;

import java.time.LocalDate;

public interface OxygenTankService extends Service<OxygenTank> {

    OxygenCategory getOxygenCategoryForTimeTable(OxygenCategory category, LocalDate timeRequested);

    OxygenTank save(OxygenTank oxygenTank);

    Iterable<OxygenTank> findAll();

    OxygenTank findById(Long id);
}
