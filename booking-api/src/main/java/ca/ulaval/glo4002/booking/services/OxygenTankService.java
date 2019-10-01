package ca.ulaval.glo4002.booking.services;

import ca.ulaval.glo4002.booking.domainobjects.oxygen.OxygenTank;
import ca.ulaval.glo4002.booking.domainobjects.qualities.Quality;

import java.time.LocalDate;

public interface OxygenTankService extends Service<OxygenTank> {

    OxygenTank save(OxygenTank oxygenTank);

    Iterable<OxygenTank> findAll();

    OxygenTank findById(Long id);

    Iterable<OxygenTank> order(Quality quality, LocalDate orderDate);
}
